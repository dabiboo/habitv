package com.dabi.habitv.tray;

import java.io.IOException;

import com.dabi.habitv.tray.controller.UpdateController;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class HabiTvSplashScreen extends Application {
	private Pane splashLayout;
	private ProgressBar loadProgress;
	private Label progressText;
	private static final int SPLASH_WIDTH = 676;
	private static final int SPLASH_HEIGHT = 227;
	private UpdateController updateController;

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	public HabiTvSplashScreen() {
	}

	@Override
	public void init() throws IOException {
		ImageView splash = new ImageView(new Image(ClassLoader
				.getSystemResource("logo.png").openStream()));
		loadProgress = new ProgressBar();
		loadProgress.setPrefWidth(SPLASH_WIDTH - 20);
		progressText = new Label("Chargement ...");
		splashLayout = new VBox();
		splashLayout.getChildren().addAll(splash, loadProgress, progressText);
		progressText.setAlignment(Pos.CENTER);
		splashLayout
				.setStyle("-fx-padding: 5; -fx-background-color: white; -fx-border-width:5; -fx-border-color: black;");
		splashLayout.setEffect(new DropShadow());
	}

	@Override
	public void start(final Stage initStage) throws Exception {
		initStage.getIcons().add(
				new Image(ClassLoader.getSystemResource("fixe.gif")
						.openStream()));		
		updateController = new UpdateController(this);
		updateController.run(initStage);
	}

	public interface InitHandler {
		void onInitDone();
	}

	public void showSplash(final Stage initStage, Task<?> task,
			final InitHandler initHandler) {
		progressText.textProperty().bind(task.messageProperty());
		loadProgress.progressProperty().bind(task.progressProperty());
		task.stateProperty().addListener(new ChangeListener<Worker.State>() {
			@Override
			public void changed(
					ObservableValue<? extends Worker.State> observableValue,
					Worker.State oldState, Worker.State newState) {
				if (newState == Worker.State.SUCCEEDED) {
					loadProgress.progressProperty().unbind();
					loadProgress.setProgress(1);
					initHandler.onInitDone();
					// mainStage.setIconified(false);
					initStage.toFront();
					FadeTransition fadeSplash = new FadeTransition(Duration
							.seconds(1.2), splashLayout);
					fadeSplash.setFromValue(1.0);
					fadeSplash.setToValue(0.0);
					fadeSplash.setOnFinished(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent actionEvent) {
							initStage.hide();
						}
					});
					fadeSplash.play();
				} // todo add code to gracefully handle other task states.
			}
		});
		Scene splashScene = new Scene(splashLayout);
		initStage.initStyle(StageStyle.UNDECORATED);
		final Rectangle2D bounds = Screen.getPrimary().getBounds();
		initStage.setScene(splashScene);
		initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH
				/ 2);
		initStage.setY(bounds.getMinY() + bounds.getHeight() / 2
				- SPLASH_HEIGHT / 2);
		initStage.show();
	}
}