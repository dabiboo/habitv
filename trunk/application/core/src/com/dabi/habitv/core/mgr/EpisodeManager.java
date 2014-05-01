package com.dabi.habitv.core.mgr;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.dabi.habitv.api.plugin.api.PluginProviderInterface;
import com.dabi.habitv.api.plugin.dto.CategoryDTO;
import com.dabi.habitv.api.plugin.dto.EpisodeDTO;
import com.dabi.habitv.api.plugin.holder.DownloaderPluginHolder;
import com.dabi.habitv.api.plugin.holder.ExporterPluginHolder;
import com.dabi.habitv.api.plugin.holder.ProviderPluginHolder;
import com.dabi.habitv.api.plugin.pub.Publisher;
import com.dabi.habitv.core.dao.DownloadedDAO;
import com.dabi.habitv.core.dao.EpisodeExportState;
import com.dabi.habitv.core.dao.ExportDAO;
import com.dabi.habitv.core.event.RetreiveEvent;
import com.dabi.habitv.core.event.SearchEvent;
import com.dabi.habitv.core.event.SearchStateEnum;
import com.dabi.habitv.core.task.DownloadTask;
import com.dabi.habitv.core.task.ExportTask;
import com.dabi.habitv.core.task.RetrieveTask;
import com.dabi.habitv.core.task.SearchTask;
import com.dabi.habitv.core.task.TaskAdResult;
import com.dabi.habitv.core.task.TaskAdder;
import com.dabi.habitv.core.task.TaskListener;
import com.dabi.habitv.core.task.TaskMgr;
import com.dabi.habitv.core.task.TaskMgrListener;
import com.dabi.habitv.core.task.TaskState;
import com.dabi.habitv.core.task.TaskTypeEnum;

public final class EpisodeManager extends AbstractManager implements TaskAdder {

	private final TaskMgr<RetrieveTask, Object> retreiveMgr;

	private final TaskMgr<DownloadTask, Object> downloadMgr;

	private final TaskMgr<ExportTask, Object> exportMgr;

	private final TaskMgr<SearchTask, Object> searchMgr;

	private final DownloaderPluginHolder downloader;

	private final ExporterPluginHolder exporter;

	private final Publisher<RetreiveEvent> retreivePublisher;

	private final Publisher<SearchEvent> searchPublisher;

	private final Set<Integer> runningRetreiveTasks = new HashSet<>();

	private final Map<EpisodeDTO, Integer> downloadAttempts = new HashMap<>();

	private final ExportDAO exportDAO = new ExportDAO();

	private final Integer maxAttempts;

	EpisodeManager(final DownloaderPluginHolder downloader, final ExporterPluginHolder exporter, final ProviderPluginHolder providerPluginHolder,
			final Map<String, Integer> taskName2PoolSize, final Integer maxAttempts) {
		super(providerPluginHolder);

		// task mgrs
		retreiveMgr = new TaskMgr<RetrieveTask, Object>(TaskTypeEnum.retreive.getPoolSize(taskName2PoolSize), buildRetreiveTaskMgrListener(), taskName2PoolSize);
		downloadMgr = new TaskMgr<DownloadTask, Object>(TaskTypeEnum.download.getPoolSize(taskName2PoolSize), buildDownloadTaskMgrListener(), taskName2PoolSize);
		exportMgr = new TaskMgr<ExportTask, Object>(TaskTypeEnum.export.getPoolSize(taskName2PoolSize), buildExportTaskMgrListener(), taskName2PoolSize);
		searchMgr = new TaskMgr<SearchTask, Object>(TaskTypeEnum.search.getPoolSize(taskName2PoolSize), buildSearchTaskMgrListener(), taskName2PoolSize);
		// publisher
		retreivePublisher = new Publisher<>();
		searchPublisher = new Publisher<>();
		this.downloader = downloader;
		this.exporter = exporter;
		this.maxAttempts = maxAttempts;
	}

	void retreiveEpisode(final Map<String, Set<CategoryDTO>> channel2Categories) {
		for (final PluginProviderInterface provider : getProviderPluginHolder().getPlugins()) {
			final Set<CategoryDTO> categories = channel2Categories.get(provider.getName());
			if (categories != null && !categories.isEmpty()) {
				searchMgr.addTask(new SearchTask(provider, categories, this, searchPublisher, retreivePublisher, downloader, exporter));
			}
		}
	}

	private TaskMgrListener buildExportTaskMgrListener() {
		return new TaskMgrListener() {

			@Override
			public void onFailed(final Throwable throwable) {
				searchPublisher.addNews(new SearchEvent(SearchStateEnum.ERROR, throwable));
			}

			@Override
			public void onAllTreatmentDone() {

			}
		};
	}

	private TaskMgrListener buildDownloadTaskMgrListener() {
		return new TaskMgrListener() {

			@Override
			public void onFailed(final Throwable throwable) {
				searchPublisher.addNews(new SearchEvent(SearchStateEnum.ERROR, throwable));
			}

			@Override
			public void onAllTreatmentDone() {

			}
		};
	}

	private TaskMgrListener buildRetreiveTaskMgrListener() {
		return new TaskMgrListener() {

			@Override
			public void onFailed(final Throwable throwable) {
				searchPublisher.addNews(new SearchEvent(SearchStateEnum.ERROR, throwable));
			}

			@Override
			public void onAllTreatmentDone() {
				searchPublisher.addNews(new SearchEvent(SearchStateEnum.ALL_RETREIVE_DONE));
			}
		};
	}

	private TaskMgrListener buildSearchTaskMgrListener() {
		return new TaskMgrListener() {

			@Override
			public void onFailed(final Throwable throwable) {

			}

			@Override
			public void onAllTreatmentDone() {
				searchPublisher.addNews(new SearchEvent(SearchStateEnum.ALL_SEARCH_DONE));
			}
		};
	}

	@Override
	public TaskAdResult addDownloadTask(final DownloadTask downloadTask, final String channel) {
		downloadMgr.addTask(downloadTask, channel);
		return new TaskAdResult(TaskState.ADDED);
	}

	private synchronized boolean isRetreiveTaskAdded(final RetrieveTask retreiveTask) {
		return runningRetreiveTasks.contains(retreiveTask.getEpisode().hashCode());
	}

	@Override
	public TaskAdResult addRetreiveTask(final RetrieveTask retreiveTask) {
		final TaskState state;
		if (!isRetreiveTaskAdded(retreiveTask)) {
			final Integer attemptsM = downloadAttempts.get(retreiveTask.getEpisode());
			final Integer attempts = (attemptsM == null) ? 0 : attemptsM;
			if (tooManyAttempts(attempts)) {
				state = TaskState.TO_MANY_FAILED;
				// reinit counter
				downloadAttempts.remove(retreiveTask.getEpisode());
			} else {

				retreiveTask.setListener(new TaskListener() {

					@Override
					public void onTaskEnded() {
						runningRetreiveTasks.remove(retreiveTask.getEpisode().hashCode());
						downloadAttempts.remove(retreiveTask.getEpisode());
					}

					@Override
					public void onTaskFailed() {
						runningRetreiveTasks.remove(retreiveTask.getEpisode().hashCode());
						downloadAttempts.put(retreiveTask.getEpisode(), attempts + 1);
					}
				});
				runningRetreiveTasks.add(retreiveTask.getEpisode().hashCode());
				retreiveMgr.addTask(retreiveTask);
				state = TaskState.ADDED;
			}
		} else {
			state = TaskState.ALREADY_ADD;
		}
		return new TaskAdResult(state);
	}

	private boolean tooManyAttempts(final Integer attempts) {
		return maxAttempts != null && attempts >= maxAttempts;
	}

	@Override
	public TaskAdResult addExportTask(final ExportTask exportTask, final String category) {
		exportMgr.addTask(exportTask, category);
		final EpisodeExportState episodeExportState = new EpisodeExportState(exportTask.getEpisode(), exportTask.getRank());
		exportDAO.addExportStep(episodeExportState);
		exportTask.setListener(new TaskListener() {

			@Override
			public void onTaskEnded() {
				exportDAO.removeExportStep(episodeExportState);
			}

			@Override
			public void onTaskFailed() {

			}
		});
		return new TaskAdResult(TaskState.ADDED);
	}

	public Publisher<RetreiveEvent> getRetreivePublisher() {
		return retreivePublisher;
	}

	public Publisher<SearchEvent> getSearchPublisher() {
		return searchPublisher;
	}

	void forceEnd() {
		exportMgr.shutdownNow();
		retreiveMgr.shutdownNow();
		searchMgr.shutdownNow();
	}

	void reTryExport() {
		if (!exportDAO.loadExportStep().isEmpty()) {
			getSearchPublisher().addNews(new SearchEvent(SearchStateEnum.RESUME_EXPORT));
		}
		for (final EpisodeExportState episodeExportState : exportDAO.loadExportStep()) {
			final String channel = episodeExportState.getEpisode().getCategory().getChannel();
			final DownloadedDAO dlDAO = new DownloadedDAO(channel, episodeExportState.getEpisode().getCategory().getName(), downloader.getIndexDir());
			final RetrieveTask retreiveTask = new RetrieveTask(episodeExportState.getEpisode(), retreivePublisher, this, exporter, getProviderPluginHolder()
					.getPlugin(channel), downloader, dlDAO);
			retreiveTask.setEpisodeExportState(episodeExportState);
			addRetreiveTask(retreiveTask);
		}
	}

	public boolean hasExportToResume() {
		return !exportDAO.loadExportStep().isEmpty();
	}

	public void clearExport() {
		exportDAO.init();
	}

}
