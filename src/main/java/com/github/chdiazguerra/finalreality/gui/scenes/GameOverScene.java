package com.github.chdiazguerra.finalreality.gui.scenes;

import com.github.chdiazguerra.finalreality.gui.FinalReality;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GameOverScene {
    private VBox root;
    private HBox playerCharacters;
    private Label info;
    private Button playAgain, close;
    private String PATH;
    private Clip endingSound;


    public GameOverScene(int width, int height, VBox playerColumn, VBox infoColumn, Stage primaryStage, String pathFiles){
        this.PATH = pathFiles;
        root = new VBox(5);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        root.setAlignment(Pos.CENTER);
        playerCharacters = new HBox(5);
        playerCharacters.setAlignment(Pos.CENTER);
        info = new Label("Thanks for playing.");
        info.setTextFill(Color.WHITE);
        playAgain = new Button("Play Again");
        playAgain.setOnAction(event -> {endingSound.stop();
            try {
                new FinalReality().start(primaryStage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        close = new Button("Close");
        close.setOnAction(event -> primaryStage.close());

        playerCharacters.getChildren().addAll(playerColumn, infoColumn);

        root.getChildren().addAll(playerCharacters, info, playAgain, close);

        Scene scene = new Scene(root, width, height);

        primaryStage.setScene(scene);

        playEndingSound();
    }

    private void playEndingSound() {
        String audioFilePath = PATH + "Sounds/Ending.wav";
        try {
            endingSound = AudioSystem.getClip();
            try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File(audioFilePath))) {
                endingSound.open(audioInputStream);
                endingSound.start();
            }
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }
}
