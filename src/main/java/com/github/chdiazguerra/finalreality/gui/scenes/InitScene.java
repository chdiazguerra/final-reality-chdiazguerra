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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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


    public InitScene(int width, int height, String pathFiles, Stage primaryStage, GameController controller){
        this.width = width;
        this.height = height;
        this.controller = controller;
        this.primaryStage = primaryStage;
        this.PATH = pathFiles;
        playerImages = new ArrayList<>();
    }

    public Scene build() throws FileNotFoundException {

        root = new VBox(10);
        root.setPadding(new Insets(10));

        scene = new Scene(root, width, height);

        root.setBackground(new Background(new BackgroundFill(Color.rgb(0,42,136),
                CornerRadii.EMPTY,
                Insets.EMPTY)));

        Label welcome = new Label("Welcome to Final Reality!\nSet your party and number of enemies.");
        welcome.setTextFill(Color.WHITE);
        welcome.setFont(new Font("Arial", 20));
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
        enemyAccept.setOnAction(event -> {acceptEnemy(numberEnemies.getValue()); numberEnemies.setDisable(true); enemyAccept.setDisable(true); totalAccepted++;});
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

        root.getChildren().addAll(finalNext);


        return scene;
    }


    private void knightBox(HBox box) throws FileNotFoundException {

        ImageView image = new ImageView(new Image(new FileInputStream(PATH + "Knight.gif")));
        Label classCharacter = new Label("Knight");
        classCharacter.setTextFill(Color.WHITE);

        Button next =  new Button(">");
        next.setOnAction(event -> {
            try {
                box.getChildren().clear();
                thiefBox(box);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        TextField name = new TextField();

        Button accept = new Button("Accept");
        accept.setOnAction(event -> {accept(box); controller.createEngineer(name.getText(),
                rng.nextInt(20)+50,
                rng.nextInt(2)+5); totalAccepted++; playerImages.add(image);});

        box.getChildren().addAll(image, classCharacter, next, name, accept);
    }

    private void thiefBox(HBox box) throws FileNotFoundException {

        ImageView image = new ImageView(new Image(new FileInputStream(PATH + "Thief.gif")));
        Label classCharacter = new Label("Thief");
        classCharacter.setTextFill(Color.WHITE);

        Button next =  new Button(">");
        next.setOnAction(event -> {
            try {
                box.getChildren().clear();
                whiteMageBox(box);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            });

        TextField name = new TextField();

        Button accept = new Button("Accept");
        accept.setOnAction(event -> {accept(box);controller.createThief(name.getText(),
                rng.nextInt(20)+50,
                rng.nextInt(3)+2); totalAccepted++; playerImages.add(image);});

        box.getChildren().addAll(image, classCharacter, next, name, accept);

    }

    private void whiteMageBox(HBox box) throws FileNotFoundException {
        ImageView image = new ImageView(new Image(new FileInputStream(PATH + "WhiteMage.gif")));
        Label classCharacter = new Label("White Mage");
        classCharacter.setTextFill(Color.WHITE);

        Button next =  new Button(">");
        next.setOnAction(event -> {
            try {
                box.getChildren().clear();
                blackMageBox(box);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            });

        TextField name = new TextField();

        Button accept = new Button("Accept");
        accept.setOnAction(event -> {accept(box);controller.createWhiteMage(name.getText(),
                rng.nextInt(20)+50,
                rng.nextInt(4)+1); totalAccepted++; playerImages.add(image);});

        box.getChildren().addAll(image, classCharacter, next, name, accept);

    }

    private void blackMageBox(HBox box) throws FileNotFoundException {
        ImageView image = new ImageView(new Image(new FileInputStream(PATH + "BlackMage.gif")));
        Label classCharacter = new Label("Black Mage");
        classCharacter.setTextFill(Color.WHITE);

        Button next =  new Button(">");
        next.setOnAction(event -> {
            try {
                box.getChildren().clear();
                engineerBox(box);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            });

        TextField name = new TextField();

        Button accept = new Button("Accept");
        accept.setOnAction(event -> {accept(box);controller.createBlackMage(name.getText(),
                rng.nextInt(20)+50,
                rng.nextInt(4)+1); totalAccepted++; playerImages.add(image);});

        box.getChildren().addAll(image, classCharacter, next, name, accept);

    }

    private void engineerBox(HBox box) throws FileNotFoundException {
        ImageView image = new ImageView(new Image(new FileInputStream(PATH + "Engineer.gif")));
        Label classCharacter = new Label("Engineer");
        classCharacter.setTextFill(Color.WHITE);

        Button next =  new Button(">");
        next.setOnAction(event -> {
            try {
                box.getChildren().clear();
                knightBox(box);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            });

        TextField name = new TextField();

        Button accept = new Button("Accept");
        accept.setOnAction(event -> {accept(box); controller.createEngineer(name.getText(),
                rng.nextInt(20)+50,
                rng.nextInt(5)+2); totalAccepted++; playerImages.add(image);});

        box.getChildren().addAll(image, classCharacter, next, name, accept);

    }

    private void accept(HBox box){
        box.getChildren().get(2).setDisable(true);
        box.getChildren().get(3).setDisable(true);
        box.getChildren().get(4).setDisable(true);
    }

    private void acceptEnemy(Integer numberOfEnemies){
        for(int i=0; i<numberOfEnemies; i++){
            controller.createEnemy("Enemy "+i,
                    rng.nextInt(20)+15,
                    rng.nextInt(20)+50,
                    rng.nextInt(6)+1,
                    rng.nextInt(12)+8);
        }
    }

    private void next() throws FileNotFoundException {
        if(totalAccepted==5){
            primaryStage.setScene(new BattleScene(width, height, PATH, primaryStage, controller, playerImages).build());
        }
    }
}
