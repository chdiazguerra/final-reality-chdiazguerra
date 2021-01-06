package com.github.chdiazguerra.finalreality.gui.scenes;

import com.github.chdiazguerra.finalreality.controller.GameController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Creates the initial scene, where the user configures the number of enemies, the name of the player characters
 * and their types.
 *
 * @author Christian Diaz Guerra
 */
public class InitScene {
    private Random rng = new Random();
    private GameController controller;
    private Stage primaryStage;
    private Scene scene;
    private VBox root;
    private final String PATH;
    private int totalAccepted = 0;
    private HBox firstBox, secondBox, thirdBox, fourthBox, enemyBox;
    private int width, height;
    private List<ImageView> playerImages;

    private List<String> namesUsed;
    private Label info;

    private Clip backgroundSound;


    /**
     * Creates the initial scene, initializing the arguments passed.
     *
     * @param width
     *      width of the window
     * @param height
     *      height of the window
     * @param pathFiles
     *      path of the resources
     * @param primaryStage
     *      stage
     * @param controller
     *      game controller
     */
    public InitScene(int width, int height, String pathFiles, Stage primaryStage, GameController controller){
        this.width = width;
        this.height = height;
        this.controller = controller;
        this.primaryStage = primaryStage;
        this.PATH = pathFiles;
        playerImages = new ArrayList<>();
        namesUsed = new ArrayList<>();
    }

    /**
     * Builds the initial scene and shows it in the stage.
     */
    public Scene build() throws FileNotFoundException {

        root = new VBox(10);
        root.setPadding(new Insets(10));

        scene = new Scene(root, width, height);

        root.setBackground(new Background(new BackgroundFill(Color.rgb(0,42,136),
                CornerRadii.EMPTY,
                Insets.EMPTY)));

        Label welcome = new Label("Welcome to Final Reality!\nSet your party and number of enemies.");
        welcome.setTextFill(Color.WHITE);
        welcome.setFont(new Font("Arial", 15));
        root.getChildren().add(welcome);

        firstBox = new HBox(5);
        secondBox = new HBox(5);
        thirdBox = new HBox(5);
        fourthBox = new HBox(5);


        enemyBox = new HBox();
        ImageView imageEnemy = new ImageView(new Image(new FileInputStream(PATH + "Enemy.gif")));
        Label enemyLabel = new Label("Enemies");
        enemyLabel.setTextFill(Color.WHITE);
        Spinner<Integer> numberEnemies = new Spinner<>(1,7,4, 1);
        Button enemyAccept = new Button("Accept");
        enemyAccept.setOnAction(event -> {acceptEnemy(numberEnemies.getValue()); numberEnemies.setDisable(true); enemyAccept.setDisable(true); totalAccepted++; playMoveSound();});
        enemyBox.setSpacing(5);
        enemyBox.getChildren().addAll(imageEnemy, enemyLabel, numberEnemies, enemyAccept);


        root.getChildren().addAll(firstBox, secondBox, thirdBox, fourthBox, enemyBox);

        knightBox(firstBox);
        thiefBox(secondBox);
        whiteMageBox(thirdBox);
        blackMageBox(fourthBox);


        Button finalNext = new Button("Next");
        finalNext.setOnAction(event -> {
            try {
                next();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        finalNext.setPadding(new Insets(20));

        info = new Label();
        info.setTextFill(Color.WHITE);
        root.getChildren().addAll(finalNext, info);

        playPreludeSound();


        return scene;
    }


    /**
     * Builds the box for the selection of a knight as player character, with its image, class, text field
     * for the name and accept button.
     */
    private void knightBox(HBox box) throws FileNotFoundException {

        ImageView image = new ImageView(new Image(new FileInputStream(PATH + "Knight.gif")));
        Label classCharacter = new Label("Knight");
        classCharacter.setTextFill(Color.WHITE);

        Button next =  new Button(">");
        next.setOnAction(event -> {
            try {
                box.getChildren().clear();
                thiefBox(box);
                playMoveSound();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        TextField name = new TextField();

        Button accept = new Button("Accept");
        accept.setOnAction(event -> {
            if(!namesUsed.contains(name.getText())){
                accept(box);
                controller.createEngineer(name.getText(),
                rng.nextInt(20)+50,
                rng.nextInt(2)+5);
                totalAccepted++;
                playerImages.add(image);
                namesUsed.add(name.getText());
                info.setText("");
            }else{
                info.setText("Names cannot be repeated");
            }
            playMoveSound();
        });

        box.getChildren().addAll(image, classCharacter, next, name, accept);
    }

    /**
     * Builds the box for the selection of a thief as player character, with its image, class, text field
     * for the name and accept button.
     */
    private void thiefBox(HBox box) throws FileNotFoundException {

        ImageView image = new ImageView(new Image(new FileInputStream(PATH + "Thief.gif")));
        Label classCharacter = new Label("Thief");
        classCharacter.setTextFill(Color.WHITE);

        Button next =  new Button(">");
        next.setOnAction(event -> {
            try {
                box.getChildren().clear();
                whiteMageBox(box);
                playMoveSound();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            });

        TextField name = new TextField();

        Button accept = new Button("Accept");
        accept.setOnAction(event -> {
            if(!namesUsed.contains(name.getText())){
                accept(box);
                controller.createThief(name.getText(),
                        rng.nextInt(20)+50,
                        rng.nextInt(3)+2);
                totalAccepted++;
                playerImages.add(image);
                namesUsed.add(name.getText());
                info.setText("");
            }else{
                info.setText("Names cannot be repeated");
            }
            playMoveSound();
        });

        box.getChildren().addAll(image, classCharacter, next, name, accept);


    }

    /**
     * Builds the box for the selection of a white mage as player character, with its image, class, text field
     * for the name and accept button.
     */
    private void whiteMageBox(HBox box) throws FileNotFoundException {
        ImageView image = new ImageView(new Image(new FileInputStream(PATH + "WhiteMage.gif")));
        Label classCharacter = new Label("White Mage");
        classCharacter.setTextFill(Color.WHITE);

        Button next =  new Button(">");
        next.setOnAction(event -> {
            try {
                box.getChildren().clear();
                blackMageBox(box);
                playMoveSound();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            });

        TextField name = new TextField();

        Button accept = new Button("Accept");
        accept.setOnAction(event -> {
            if(!namesUsed.contains(name.getText())){
                accept(box);
                controller.createWhiteMage(name.getText(),
                        rng.nextInt(20)+50,
                        rng.nextInt(4)+1);
                totalAccepted++;
                playerImages.add(image);
                namesUsed.add(name.getText());
                info.setText("");
            }else{
                info.setText("Names cannot be repeated");
            }
            playMoveSound();
        });

        box.getChildren().addAll(image, classCharacter, next, name, accept);


    }

    /**
     * Builds the box for the selection of a black mage as player character, with its image, class, text field
     * for the name and accept button.
     */
    private void blackMageBox(HBox box) throws FileNotFoundException {
        ImageView image = new ImageView(new Image(new FileInputStream(PATH + "BlackMage.gif")));
        Label classCharacter = new Label("Black Mage");
        classCharacter.setTextFill(Color.WHITE);

        Button next =  new Button(">");
        next.setOnAction(event -> {
            try {
                box.getChildren().clear();
                engineerBox(box);
                playMoveSound();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            });

        TextField name = new TextField();

        Button accept = new Button("Accept");
        accept.setOnAction(event -> {
            if(!namesUsed.contains(name.getText())){
                accept(box);
                controller.createBlackMage(name.getText(),
                        rng.nextInt(20)+50,
                        rng.nextInt(4)+1);
                totalAccepted++;
                playerImages.add(image);
                namesUsed.add(name.getText());
                info.setText("");
            }else{
                info.setText("Names cannot be repeated");
            }
            playMoveSound();
        });

        box.getChildren().addAll(image, classCharacter, next, name, accept);


    }

    /**
     * Builds the box for the selection of a engineer as player character, with its image, class, text field
     * for the name and accept button.
     */
    private void engineerBox(HBox box) throws FileNotFoundException {
        ImageView image = new ImageView(new Image(new FileInputStream(PATH + "Engineer.gif")));
        Label classCharacter = new Label("Engineer");
        classCharacter.setTextFill(Color.WHITE);

        Button next =  new Button(">");
        next.setOnAction(event -> {
            try {
                box.getChildren().clear();
                knightBox(box);
                playMoveSound();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            });

        TextField name = new TextField();

        Button accept = new Button("Accept");
        accept.setOnAction(event -> {
            if(!namesUsed.contains(name.getText())){
                accept(box);
                controller.createEngineer(name.getText(),
                        rng.nextInt(20)+50,
                        rng.nextInt(5)+2);
                totalAccepted++;
                playerImages.add(image);
                namesUsed.add(name.getText());
                info.setText("");
            }else{
                info.setText("Names cannot be repeated");
            }
            playMoveSound();
        });

        box.getChildren().addAll(image, classCharacter, next, name, accept);


    }

    /**
     * Action for the accept button of every player character selection.
     */
    private void accept(HBox box){
        box.getChildren().get(2).setDisable(true);
        box.getChildren().get(3).setDisable(true);
        box.getChildren().get(4).setDisable(true);
    }

    /**
     * Action for the accept button of the enemy selection
     */
    private void acceptEnemy(Integer numberOfEnemies){
        for(int i=0; i<numberOfEnemies; i++){
            controller.createEnemy("Enemy "+i,
                    rng.nextInt(20)+15,
                    rng.nextInt(20)+50,
                    rng.nextInt(6)+1,
                    rng.nextInt(12)+8);
        }
    }

    /**
     * Action for the next button, activated only if the user finished setting up the game.
     */
    private void next() throws FileNotFoundException {
        if(totalAccepted==5){
            backgroundSound.stop();
            primaryStage.setScene(new BattleScene(width, height, PATH, primaryStage, controller, playerImages).build());
        }else{
            info.setText("You have to finish setting up the game");
        }
        playMoveSound();
    }

    private void playPreludeSound() {
        String audioFilePath = PATH + "Sounds/Prelude.wav";
        try {
            backgroundSound = AudioSystem.getClip();
            try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File(audioFilePath))) {
                backgroundSound.open(audioInputStream);
                backgroundSound.loop(Clip.LOOP_CONTINUOUSLY);
                backgroundSound.start();
            }
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays the sound for the buttons clicks.
     */
    private void playMoveSound(){
        String audioFilePath = PATH + "Sounds/Move.wav";
        try {
            Clip moveSound = AudioSystem.getClip();
            try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File(audioFilePath))) {
                moveSound.open(audioInputStream);
                moveSound.start();
            }
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }
}
