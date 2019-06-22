package com.kodilla.game.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;

public class Main extends Application {

    public static final String BACKGROUNDS_BATTLE_SHIP_BACKGROUND_PNG_PNG = "/backgrounds/battleShipBackgroundPNG.png";
    public static final String DRUNKEN_SAILOR_AUDIO_MP_3 = "src/main/resources/sounds/drunkenSailorAudio.mp3";
    public static final String FXML_MAIN_SCREEN_FXML = "/fxml/MainScreen.fxml";
    public static MediaPlayer mp;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXML_MAIN_SCREEN_FXML));
        StackPane stackPane = loader.load();
        Image imageBackground = new Image(this.getClass().getResourceAsStream(BACKGROUNDS_BATTLE_SHIP_BACKGROUND_PNG_PNG));
        BackgroundSize backgroundSize = new BackgroundSize(1500, 600, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageBackground, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        String path = new File(DRUNKEN_SAILOR_AUDIO_MP_3).getAbsolutePath();
        mp = new MediaPlayer(new Media(new File(path).toURI().toString()));
        mp.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mp.seek(Duration.ZERO);
            }
        });
        musicPlayer(true);
        stackPane.setBackground(background);
        Scene scene = new Scene(stackPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Battle Ships");
        primaryStage.show();
    }

    public static void musicPlayer(Boolean whetherToPlay) {
        if (whetherToPlay) {
            mp.play();
        } else {
            mp.stop();
        }
    }
}
