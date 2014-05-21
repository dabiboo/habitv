package com.dabi.habitv.tray.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import com.dabi.habitv.api.plugin.dto.CategoryDTO;
import com.dabi.habitv.api.plugin.dto.EpisodeDTO;
import com.dabi.habitv.api.plugin.exception.TechnicalException;
import com.dabi.habitv.core.event.EpisodeStateEnum;
import com.dabi.habitv.tray.model.ActionProgress;
import com.dabi.habitv.tray.utils.LabelUtils;

public class DownloadBox extends BorderPane {

	private final ActionProgress actionProgress;
	private Pane statePanel;

	private ContextMenu contextMenu = new ContextMenu();

	public DownloadBox(ActionProgress actionProgress) {
		super();
		this.actionProgress = actionProgress;

		EpisodeDTO episode = actionProgress.getEpisode();
		CategoryDTO category = episode.getCategory();

		setLeft(new HBox(10, new Label(category.getChannel() + "/"
				+ category.getName()), new Label(episode.getName())));
		statePanel = new Pane(getStateWidget(null));
		setRight(statePanel);
		setPadding(new Insets(10, 10, 0, 10));
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				buildMenuItem();
				contextMenu.show(DownloadBox.this, event.getScreenX(),
						event.getScreenY());
			}
		});
	}

	void buildMenuItem() {
		contextMenu.getItems().clear();
		if (actionProgress.getState().isInProgress()) {
			MenuItem menuItem = new MenuItem("Arrêter");
			menuItem.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					actionProgress.getProcessHolder().stop();
				}
			});
			contextMenu.getItems().add(menuItem);
			contextMenu.getItems().add(
					new MenuItem("Arrêter et marquer comme téléchargé"));
		}
		contextMenu.getItems().add(new MenuItem("Ouvrir l'index"));

		if (actionProgress.getState().hasFailed()) {
			contextMenu.getItems().add(new MenuItem("Relancer"));
		}
	}

	public void update() {
		Node oldWidget = statePanel.getChildren().get(0);
		Node newWidget = getStateWidget(oldWidget);
		if (!oldWidget.equals(newWidget)) {
			statePanel.getChildren().clear();
			statePanel.getChildren().add(newWidget);
		}
		runCheckProgressThread();
	}

	private Thread checkThread;

	private void runCheckProgressThread() {

		if (actionInProgress()) {
			if (checkThread == null) {
				checkThread = new Thread(new Runnable() {

					@Override
					public void run() {
						while (actionInProgress()) {
							try {
								Thread.sleep(2000);
								update();
							} catch (final InterruptedException e) {
								throw new TechnicalException(e);
							}
						}
					}
				});
				checkThread.start();
			}
		}
	}

	private boolean actionInProgress() {
		return actionProgress.getState() == EpisodeStateEnum.DOWNLOAD_STARTING
				|| actionProgress.getState() == EpisodeStateEnum.EXPORT_STARTING;
	}

	private Node getStateWidget(Node oldWidget) {
		Node widget;
		switch (actionProgress.getState()) {
		case DOWNLOAD_STARTING:
			widget = getProgressBarWidget(oldWidget);
			break;
		default:
			widget = getLabelWidget(oldWidget);
			break;
		}
		return widget;
	}

	private Node getProgressBarWidget(Node oldWidget) {
		Node widget;
		Double progressDouble;
		String progress = actionProgress.getProgress();
		progressDouble = getProgressDouble(progress);
		
		ProgressIndicatorBar progressBar;
		if (oldWidget == null || !(oldWidget instanceof ProgressIndicatorBar)) {
			progressBar = new ProgressIndicatorBar();
		} else {
			progressBar = (ProgressIndicatorBar) oldWidget;
		}
		progressBar.setProgress(progressDouble);
		// if (progress != null) {
		// progressBar.setTooltip(new Tooltip(progress + "%"));
		// }
		widget = progressBar;
		return widget;
	}

	private Double getProgressDouble(String progress) {
		Double progressDouble;
		try {
			if (progress == null) {
				progressDouble = 0.0;
			} else {
				progressDouble = Double.parseDouble(progress) / 100;
			}
		} catch (NumberFormatException e) {
			progressDouble = 0.0;
		}
		return progressDouble;
	}

	private Node getLabelWidget(Node oldWidget) {
		Node widget;
		Label label;
		if (oldWidget == null || !(oldWidget instanceof Label)) {
			label = new Label();
			label.setMaxWidth(50);
		} else {
			label = (Label) oldWidget;
		}
		widget = label;
		label.setText(LabelUtils.buildStateLabel(actionProgress));
		return widget;
	}

	@Override
	public int hashCode() {
		return actionProgress.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DownloadBox other = (DownloadBox) obj;
		if (actionProgress == null) {
			if (other.actionProgress != null)
				return false;
		} else {
			return actionProgress.getEpisode().equals(
					other.actionProgress.getEpisode());
		}
		return true;
	};

}
