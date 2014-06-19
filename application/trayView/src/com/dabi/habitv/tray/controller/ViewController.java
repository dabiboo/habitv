package com.dabi.habitv.tray.controller;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Map;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import com.dabi.habitv.api.plugin.dto.CategoryDTO;
import com.dabi.habitv.api.plugin.dto.EpisodeDTO;
import com.dabi.habitv.api.plugin.exception.TechnicalException;
import com.dabi.habitv.api.plugin.pub.UpdatablePluginEvent;
import com.dabi.habitv.core.config.UserConfig;
import com.dabi.habitv.core.dao.DownloadedDAO;
import com.dabi.habitv.core.event.EpisodeStateEnum;
import com.dabi.habitv.core.event.RetreiveEvent;
import com.dabi.habitv.core.event.SearchCategoryEvent;
import com.dabi.habitv.core.event.SearchEvent;
import com.dabi.habitv.core.event.UpdatePluginEvent;
import com.dabi.habitv.framework.FrameworkConf;
import com.dabi.habitv.framework.plugin.utils.ProcessingThreads;
import com.dabi.habitv.tray.Popin;
import com.dabi.habitv.tray.model.HabitTvViewManager;
import com.dabi.habitv.tray.subscriber.CoreSubscriber;
import com.dabi.habitv.utils.DirUtils;

public class ViewController implements CoreSubscriber {

	private final HabitTvViewManager habitvViewManager;
	private Stage primaryStage;

	public ViewController(final HabitTvViewManager habitvViewManager,
			Stage primaryStage) {
		this.habitvViewManager = habitvViewManager;
		this.primaryStage = primaryStage;
	}

	public final HabitTvViewManager getManager() {
		return habitvViewManager;
	}

	public void start() {
		getManager().startDownloadCheck();
	}

	public void startDownloadCheckDemon() {
		getManager().startDownloadCheckDemon();
	}

	@Override
	public void update(final SearchEvent event) {
		switch (event.getState()) {
		case ALL_RETREIVE_DONE:

			break;
		case ALL_SEARCH_DONE:

			break;
		case BUILD_INDEX:

			break;
		case CHECKING_EPISODES:

			break;
		case DONE:

			break;
		case ERROR:

			break;
		case IDLE:

			break;
		default:
			break;
		}
	}

	@Override
	public void update(final RetreiveEvent event) {
		switch (event.getState()) {
		case BUILD_INDEX:

			break;
		case DOWNLOAD_FAILED:
			getManager().getProgressionModel().updateActionProgress(
					event.getEpisode(), EpisodeStateEnum.DOWNLOAD_FAILED,
					event.getException().getMessage(), null);
			break;
		case DOWNLOADED:
			getManager().getProgressionModel().updateActionProgress(
					event.getEpisode(), EpisodeStateEnum.DOWNLOADED, "", null);
			break;
		case DOWNLOAD_STARTING:
			getManager().getProgressionModel().updateActionProgress(
					event.getEpisode(), EpisodeStateEnum.DOWNLOAD_STARTING, "",
					event.getProcessHolder());
			break;
		case EXPORT_FAILED:
			getManager().getProgressionModel().updateActionProgress(
					event.getEpisode(), EpisodeStateEnum.EXPORT_FAILED,
					event.getOperation(), null);
			break;
		case EXPORT_STARTING:
			getManager().getProgressionModel().updateActionProgress(
					event.getEpisode(), EpisodeStateEnum.EXPORT_STARTING,
					event.getOperation(), event.getProcessHolder());
			break;
		case FAILED:

			break;
		case READY:
			getManager().getProgressionModel().updateActionProgress(
					event.getEpisode(), EpisodeStateEnum.READY, "", null);
			break;
		case TO_DOWNLOAD:
			getManager().getProgressionModel().updateActionProgress(
					event.getEpisode(), EpisodeStateEnum.TO_DOWNLOAD, "", null);
			break;
		case TO_EXPORT:

			break;
		case STOPPED:
			getManager().getProgressionModel().updateActionProgress(
					event.getEpisode(), EpisodeStateEnum.STOPPED, "", null);
			break;
		case TO_MANY_FAILED:
			getManager().getProgressionModel().updateActionProgress(
					event.getEpisode(), EpisodeStateEnum.TO_MANY_FAILED, "",
					null);
			break;
		default:
			break;
		}
	}

	@Override
	public void update(final SearchCategoryEvent event) {
		switch (event.getState()) {
		case BUILDING_CATEGORIES:
			break;
		case DONE:

			break;
		case ERROR:

			break;
		case IDLE:

			break;
		default:
			break;
		}
	}

	public void stop() {
		getManager().forceEnd();
		ProcessingThreads.killAllProcessing();
		System.exit(0);
	}

	public void clear() {
		getManager().clear();
	}

	public Map<String, CategoryDTO> loadCategories() {
		return getManager().loadCategories();
	}

	public void reDoExport() {
		getManager().reDoExport();
	}

	public boolean hasExportToResume() {
		return getManager().hasExportToResume();
	}

	public void clearExport() {
		getManager().clearExport();
	}

	public void update() {
		getManager().update();
	}

	@Override
	public void update(final UpdatePluginEvent event) {
	}

	@Override
	public void update(UpdatablePluginEvent event) {

	}

	public void openErrorFile() {
		open(FrameworkConf.ERROR_FILE);
	}

	public void openIndexDir() {
		final UserConfig config = getManager().getUserConfig();
		open(config.getIndexDir());
	}

	private static void open(final String toOpen) {
		try {
			final String canonicalPath = new File(toOpen).getCanonicalPath();
			if (!Desktop.isDesktopSupported()) {
				return;
			}
			final Desktop desktop = Desktop.getDesktop();

			File file = new File(canonicalPath);
			if (file.exists()) {
				if (file.isFile()) {
					try {
						desktop.open(file);
					} catch (Exception e) {
						ProcessBuilder pb = new ProcessBuilder("Notepad.exe",
								canonicalPath);
						pb.start();
					}
				} else {
					desktop.open(file);
				}
			} else {
				(new Popin()).show("Fichier inexistant", toOpen
						+ " n'existe pas ");
			}

		} catch (final IOException e) {
			throw new TechnicalException(e);
		}
	}

	public void openDownloadDir() {
		final UserConfig config = getManager().getUserConfig();
		open(config.getDownloadOuput().substring(0,
				config.getDownloadOuput().indexOf("#"))); //$NON-NLS-1$
	}

	public void updateGrabconfig(Map<String, CategoryDTO> channels) {
		getManager().updateGrabconfig(channels);
	}

	public UserConfig loadUserConfig() {
		return getManager().getUserConfig();
	}

	public void saveConfig(UserConfig userConfig) {
		getManager().saveConfig(userConfig);
	}

	public void openMainView(final EventHandler<WindowEvent> eventHandler) {
		Platform.runLater(new Runnable() {
			public void run() {
				primaryStage.show();
				primaryStage.setOnHiding(eventHandler);
				primaryStage.setOnCloseRequest(eventHandler);
				primaryStage.toFront();
			}
		});
	}

	public void setDownloaded(EpisodeDTO episode) {
		getManager().setDownloaded(episode);
	}

	public void openIndex(CategoryDTO category) {
		final UserConfig config = getManager().getUserConfig();
		open(DownloadedDAO.getFileIndex(config.getIndexDir(), category));
	}

	public void restart(EpisodeDTO episode, boolean exportOnly) {
		getManager().restart(episode, exportOnly);
	}

	public Collection<EpisodeDTO> findEpisodeByCategory(CategoryDTO category) {
		return getManager().findEpisodeByCategory(category);
	}

	public void downloadEpisode(EpisodeDTO episode) {
		getManager().restart(episode, false);
	}

	public void openLogFile() {
		open(DirUtils.getLogFile());
	}

	public void copyUrl(EpisodeDTO episode) {
		Toolkit.getDefaultToolkit().getSystemClipboard()
				.setContents(new StringSelection(episode.getId()), null);
	}

	public void openInBrowser(EpisodeDTO episodeDTO) {
		try {
			Desktop.getDesktop().browse(new URI(episodeDTO.getId()));
		} catch (IOException | URISyntaxException e) {
			throw new TechnicalException(e);
		}
	}
}
