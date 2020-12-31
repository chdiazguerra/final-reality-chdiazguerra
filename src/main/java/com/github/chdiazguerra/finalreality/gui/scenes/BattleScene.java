package com.github.chdiazguerra.finalreality.gui.scenes;

import com.github.chdiazguerra.finalreality.controller.GameController;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class BattleScene implements IBattleScene{

    private final String PATH;
    private final GameController controller;
    private final Stage primaryStage;
    int width, height;

    private VBox root, firstEnemyColumn, secondEnemyColumn, playerColumn, infoColumn;
    private HBox topHBox, bottomHBox;
    private List<Button> enemies;
    private List<ImageView> playerImages;
    private List<Label> infoLabels;

    private Button waitingNext;

    private Clip backgroundSound, victorySound, lostSound;

    public BattleScene(int width, int height, String pathFiles, Stage primaryStage, GameController controller, List<ImageView> playerImages){
        this.width = width;
        this.height = height;
        this.PATH = pathFiles;
        this.primaryStage = primaryStage;
        this.controller = controller;
        enemies = new ArrayList<>();
        this.playerImages = playerImages;
        infoLabels = new ArrayList<>();
        controller.setScene(this);
    }

    public Scene build() throws FileNotFoundException {
        root = new VBox(5);
        root.setPrefWidth(width);
        root.setPadding(new Insets(10));
        root.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY,
                Insets.EMPTY)));

        topHBox = new HBox(5);
        root.getChildren().add(topHBox);

        firstEnemyColumn = new VBox(5);
        secondEnemyColumn = new VBox(5);
        secondEnemyColumn.setPrefWidth(250);

        playerColumn = new VBox(5);
        infoColumn = new VBox(5);

        topHBox.getChildren().addAll(firstEnemyColumn, secondEnemyColumn, playerColumn, infoColumn);

        bottomHBox = new HBox(5);
        bottomHBox.setPrefWidth(width);
        bottomHBox.setPrefHeight(180);
        bottomHBox.setStyle("-fx-border-color: white;");
        root.getChildren().add(bottomHBox);

        Scene scene = new Scene(root, width, height);

        for(int i=0; i<controller.getAllEnemies().size(); i++){
            int indexEnemy = i;
            ImageView imageEnemy = new ImageView(new Image(new FileInputStream(PATH + "Enemy.gif")));
            Button newEnemyButton = new Button();
            newEnemyButton.setGraphic(imageEnemy);
            newEnemyButton.setOnAction(event -> controller.tryAttackEnemy(indexEnemy));
            enemies.add(newEnemyButton);
        }

        setEnemyColumns();

        for(ImageView image: playerImages){
            image.setFitHeight(70);
        }
        playerColumn.getChildren().addAll(playerImages);

        for(int i=0; i<4; i++){
            Label infoCharacter = new Label();
            infoCharacter.setPrefHeight(70);
            infoCharacter.setPrefWidth(100);
            infoCharacter.setStyle("-fx-border-color: white;");
            infoCharacter.setTextFill(Color.WHITE);
            infoLabels.add(infoCharacter);
            infoColumn.getChildren().add(infoCharacter);
        }

        setInfoColumn();

        Label text = new Label("The Battle Begins...");
        text.setTextFill(Color.WHITE);

        waitingNext = new Button("Next");
        waitingNext.setOnAction(event -> turnBox());
        waitingNext.setDisable(true);

        bottomHBox.getChildren().addAll(text, waitingNext);

        controller.initialize();

        playInitBattleSound();

        return scene;
    }

    private void setEnemyColumns(){
        int numberOfEnemies = controller.getAllEnemies().size();
        int numberOfEnemiesFirstColumn = Math.min(4, numberOfEnemies);
        int numberOfEnemiesSecondColumn = numberOfEnemies - numberOfEnemiesFirstColumn;

        for(int i = 0; i<numberOfEnemiesFirstColumn; i++){
            firstEnemyColumn.getChildren().add(enemies.get(i));
        }
        for(int i=0; i<numberOfEnemiesSecondColumn; i++){
            secondEnemyColumn.getChildren().add(enemies.get(4+i));
        }
    }

    @Override
    public void refreshEnemyColumns(int indexEnemy){
        if(controller.isEnemyDead(indexEnemy)) {
            enemies.get(indexEnemy).setDisable(true);
        }
    }

    private void setInfoColumn(){
        for(int i=0; i<4; i++){
            String info = controller.getPlayerCharacterInfo(i);
            infoLabels.get(i).setText(info);
        }
    }

    @Override
    public void refreshInfoColumn(int indexPlayer){
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.2), infoLabels.get(indexPlayer));
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setCycleCount(2);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
        String info = controller.getPlayerCharacterInfo(indexPlayer);
        infoLabels.get(indexPlayer).setText(info);
        if(controller.isPlayerDead(indexPlayer)) {
            infoLabels.get(indexPlayer).setTextFill(Color.RED);
        }
    }


    @Override
    public void waitingText() {
        bottomHBox.getChildren().clear();
        waitingNext.setDisable(true);
        Label text = new Label("Waiting for players...");
        text.setTextFill(Color.WHITE);

        bottomHBox.getChildren().addAll(text, waitingNext);
    }


    @Override
    public void playerTurnBox(){
        bottomHBox.getChildren().clear();
        VBox box = new VBox(5);
        Label text = new Label("Turn of " + controller.getCharacterName(controller.getCharacterTurn()));
        text.setTextFill(Color.WHITE);

        Button attack = new Button("Attack");
        attack.setOnAction(event -> {selectTargetText(); controller.attack();});

        Button inventory = new Button("Inventory");
        inventory.setOnAction(event -> {inventoryBox(); controller.toInventory();});


        box.getChildren().addAll(text, attack, inventory);
        bottomHBox.getChildren().add(box);
    }

    @Override
    public void selectTargetText(){
        bottomHBox.getChildren().clear();
        Label text = new Label("Select the Target");
        text.setTextFill(Color.WHITE);

        Button back = new Button("Back");
        back.setOnAction(event -> {playerTurnBox(); controller.back();});

        bottomHBox.getChildren().addAll(text, back);
    }

    @Override
    public void attackInfo(String attackingName, int damage, String attackedName){
        bottomHBox.getChildren().clear();
        playDamageSound();
        Label text = new Label(attackingName + " did " + damage + " to " + attackedName);
        text.setTextFill(Color.WHITE);

        Button next = new Button("Next");
        next.setOnAction(event -> controller.next());

        bottomHBox.getChildren().addAll(text, next);

    }

    @Override
    public void inventoryBox() {
        bottomHBox.getChildren().clear();
        VBox currentWeapon = new VBox(5);
        currentWeapon.setPrefWidth(80);

        VBox firstColumn = new VBox();
        firstColumn.setPrefWidth(200);

        VBox secondColumn = new VBox();
        secondColumn.setPrefWidth(200);


        Label currentWeaponInfo = new Label(controller.infoWeaponPlayerTurn());
        currentWeaponInfo.setTextFill(Color.WHITE);
        currentWeapon.getChildren().add(currentWeaponInfo);

        for (int i = 0; i < 5; i++) {
            int indexWeapon = i;
            Button button = new Button(controller.weaponInfo(controller.getInventoryWeapon(i)));
            button.setPrefWidth(200);
            button.setOnAction(event -> controller.tryToEquipWeapon(indexWeapon));
            firstColumn.getChildren().add(button);
        }
        for (int i = 5; i < 9; i++) {
            int indexWeapon = i;
            Button button = new Button(controller.weaponInfo(controller.getInventoryWeapon(i)));
            button.setPrefWidth(200);
            button.setOnAction(event -> controller.tryToEquipWeapon(indexWeapon));
            secondColumn.getChildren().add(button);
        }

        Button back = new Button("back");
        back.setOnAction(event -> {playerTurnBox(); controller.back();});
        secondColumn.getChildren().add(back);

        bottomHBox.getChildren().addAll(currentWeapon, firstColumn, secondColumn);


    }


    @Override
    public void turnBox() {
        bottomHBox.getChildren().clear();
        Label text = new Label("Turn of " + controller.getCharacterName(controller.getCharacterTurn()));
        text.setTextFill(Color.WHITE);

        Button next = new Button("Next");
        next.setOnAction(event -> controller.next());
        bottomHBox.getChildren().addAll(text, next);
    }


    @Override
    public void enemyChoosing(){
        bottomHBox.getChildren().clear();
        Label text = new Label(controller.getCharacterName(controller.getCharacterTurn()) + " is choosing action");
        text.setTextFill(Color.WHITE);
        Button next = new Button("Next");
        next.setOnAction(event -> {next.setDisable(true); controller.tryAttackPlayer(); setInfoColumn();});
        bottomHBox.getChildren().addAll(text, next);
    }

    @Override
    public void winGame(){
        backgroundSound.stop();
        bottomHBox.getChildren().clear();
        playVictorySound();
        Label text = new Label("No enemies left. Congratulations. You Win!!");
        text.setTextFill(Color.WHITE);
        Button next = new Button("Next");
        next.setOnAction(event -> {victorySound.stop(); new GameOverScene(width, height, playerColumn, infoColumn, primaryStage, PATH);});

        bottomHBox.getChildren().addAll(text, next);
    }

    @Override
    public void lostGame(){
        backgroundSound.stop();
        bottomHBox.getChildren().clear();
        playLostSound();
        Label text = new Label("No party characters left. You lost :(");
        text.setTextFill(Color.WHITE);
        Button next = new Button("Next");
        next.setOnAction(event -> {lostSound.stop(); new GameOverScene(width, height, playerColumn, infoColumn, primaryStage, PATH);});

        bottomHBox.getChildren().addAll(text, next);
    }

    @Override
    public void refreshWaitingNext(){
        waitingNext.setDisable(false);
    }

    private void playBattleSound() {
        String audioFilePath = PATH + "Sounds/Battle.wav";
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

    private void playInitBattleSound() {
        String audioFilePath = PATH + "Sounds/InitBattle.wav";
        try {
            Clip initBattleSound = AudioSystem.getClip();
            try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File(audioFilePath))) {
                initBattleSound.open(audioInputStream);
                initBattleSound.start();
                initBattleSound.addLineListener(event -> {if(event.getType()==LineEvent.Type.STOP){
                playBattleSound();}
                });
            }
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    private void playDamageSound() {
        String audioFilePath = PATH + "Sounds/Damage.wav";
        try {
            Clip damageSound = AudioSystem.getClip();
            try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File(audioFilePath))) {
                damageSound.open(audioInputStream);
                damageSound.start();
            }
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    private void playVictorySound() {
        String audioFilePath = PATH + "Sounds/Victory.wav";
        try {
            victorySound = AudioSystem.getClip();
            try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File(audioFilePath))) {
                victorySound.open(audioInputStream);
                victorySound.start();
            }
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    private void playLostSound() {
        String audioFilePath = PATH + "Sounds/Lost.wav";
        try {
            lostSound = AudioSystem.getClip();
            try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File(audioFilePath))) {
                lostSound.open(audioInputStream);
                lostSound.start();
            }
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }


}
