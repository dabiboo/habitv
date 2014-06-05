package com.dabi.habitv.core.mgr;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.dabi.habitv.core.event.RetreiveEvent;
import com.dabi.habitv.core.event.SearchEvent;
import com.dabi.habitv.core.event.SearchStateEnum;
import com.dabi.habitv.core.publisher.Publisher;
import com.dabi.habitv.core.task.DownloadTask;
import com.dabi.habitv.core.task.ExportTask;
import com.dabi.habitv.core.task.RetreiveTask;
import com.dabi.habitv.core.task.SearchTask;
import com.dabi.habitv.core.task.TaskAdder;
import com.dabi.habitv.core.task.TaskListener;
import com.dabi.habitv.core.task.TaskMgr;
import com.dabi.habitv.core.task.TaskMgrListener;
import com.dabi.habitv.core.task.TaskTypeEnum;
import com.dabi.habitv.framework.plugin.api.dto.CategoryDTO;
import com.dabi.habitv.framework.plugin.api.dto.DownloaderDTO;
import com.dabi.habitv.framework.plugin.api.dto.ExporterDTO;
import com.dabi.habitv.framework.plugin.api.provider.PluginProviderInterface;

public final class EpisodeManager extends AbstractManager implements TaskAdder {

	private final TaskMgr<RetreiveTask, Object> retreiveMgr;

	private final TaskMgr<DownloadTask, Object> downloadMgr;

	private final TaskMgr<ExportTask, Object> exportMgr;

	private final TaskMgr<SearchTask, Object> searchMgr;

	private final DownloaderDTO downloader;

	private final ExporterDTO exporter;

	private final Publisher<RetreiveEvent> retreivePublisher;

	private final Publisher<SearchEvent> searchPublisher;

	private final Set<Integer> runningRetreiveTasks = new HashSet<>();

	public EpisodeManager(final DownloaderDTO downloader, final ExporterDTO exporter, final Collection<PluginProviderInterface> collection,
			final Map<String, Integer> taskName2PoolSize) {
		super(collection);

		// task mgrs
		retreiveMgr = new TaskMgr<RetreiveTask, Object>(taskName2PoolSize.get(TaskTypeEnum.retreive.toString()), buildRetreiveTaskMgrListener(),
				taskName2PoolSize);
		downloadMgr = new TaskMgr<DownloadTask, Object>(taskName2PoolSize.get(TaskTypeEnum.download.toString()), buildDownloadTaskMgrListener(),
				taskName2PoolSize);
		exportMgr = new TaskMgr<ExportTask, Object>(taskName2PoolSize.get(TaskTypeEnum.export.toString()), buildExportTaskMgrListener(), taskName2PoolSize);
		searchMgr = new TaskMgr<SearchTask, Object>(taskName2PoolSize.get(TaskTypeEnum.search.toString()), buildSearchTaskMgrListener(), taskName2PoolSize);
		// publisher
		retreivePublisher = new Publisher<>();
		searchPublisher = new Publisher<>();
		this.downloader = downloader;
		this.exporter = exporter;
	}

	public void retreiveEpisode(final Collection<PluginProviderInterface> providerList, final Map<String, Set<CategoryDTO>> channel2Categories) {
		for (final PluginProviderInterface provider : providerList) {
			// method must be asynchronous
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
	public void addDownloadTask(final DownloadTask downloadTask, final String channel) {
		downloadMgr.addTask(downloadTask, channel);
	}

	private synchronized boolean isRetreiveTaskAdded(final RetreiveTask retreiveTask) {
		return runningRetreiveTasks.contains(retreiveTask.getEpisode().hashCode());
	}

	@Override
	public void addRetreiveTask(final RetreiveTask retreiveTask) {
		if (!isRetreiveTaskAdded(retreiveTask)) {
			retreiveTask.setListener(new TaskListener() {

				@Override
				public void onTaskEnded() {
					runningRetreiveTasks.remove(retreiveTask.getEpisode().hashCode());
				}
			});
			runningRetreiveTasks.add(retreiveTask.getEpisode().hashCode());
			retreiveMgr.addTask(retreiveTask);
		}
	}

	@Override
	public void addExportTask(final ExportTask exportTask, final String category) {
		exportMgr.addTask(exportTask, category);
	}

	public Publisher<RetreiveEvent> getRetreivePublisher() {
		return retreivePublisher;
	}

	public Publisher<SearchEvent> getSearchPublisher() {
		return searchPublisher;
	}

	public void forceEnd() {
		exportMgr.shutdownNow();
		retreiveMgr.shutdownNow();
		searchMgr.shutdownNow();
	}

}