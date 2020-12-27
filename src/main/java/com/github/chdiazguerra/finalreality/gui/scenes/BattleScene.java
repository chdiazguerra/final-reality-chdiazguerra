package com.github.chdiazguerra.finalreality.gui.scenes;

import com.github.chdiazguerra.finalreality.controller.GameController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class BattleScene {

    private final String PATH;
    private final GameController controller;
    private final Stage primaryStage;
    int width, height;

    private VBox root, firstEnemyColumn, secondEnemyColumn, playerColumn, infoColumn;
    private HBox topHBox, bottomHBox;
    private List<Button> enemies;
    private List<ImageView> playerImages;
    private List<Label> infoLabels;

    public BattleScene(int width, int height, String pathFiles, Stage primaryStage, GameController controller, List<ImageView> playerImages){
        this.width = width;
        this.height = height;
        this.PATH = pathFiles;
        this.primaryStage = primaryStage;
        this.controller = controller;
        enemies = new ArrayList<>();
        this.playerImages = playerImages;
        infoLabels = new ArrayList<>();
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
            newEnemyButton.setOnAction(event -> {controller.tryAttackEnemy(indexEnemy);});
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

        refreshInfoColumn();

        playerTurnBox();

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

    private void refreshInfoColumn(){
        for(int i=0; i<4; i++){
            String info = controller.getPlayerCharacterInfo(i);
            infoLabels.get(i).setText(info);
        }
    }

    public void waitingText() {
        Label text = new Label("Waiting for players...");
        text.setTextFill(Color.WHITE);

        bottomHBox.getChildren().addAll(text);
    }

    /**
     * Cambiar
     */
    public void playerTurnBox(){
        VBox box = new VBox(5);
        Label text = new Label("Turn of " + controller.getCharacterName((controller.getEnemy(0))));
        text.setTextFill(Color.WHITE);

        Button attack = new Button("Attack");
        attack.setOnAction(event -> {bottomHBox.getChildren().clear(); selectTargetText();});

        Button inventory = new Button("Inventory");
        inventory.setOnAction(event -> {bottomHBox.getChildren().clear();
            try {
                inventoryBox();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });


        box.getChildren().addAll(text, attack, inventory);
        bottomHBox.getChildren().add(box);
    }

    public void selectTargetText(){
        Label text = new Label("Select the Target");
        text.setTextFill(Color.WHITE);

        Button back = new Button("Back");
        back.setOnAction(event -> playerTurnBox());

        bottomHBox.getChildren().addAll(text, back);
    }

    public void attackInfo(){
        Label text = new Label("");
        text.setTextFill(Color.WHITE);

        Button next = new Button("Next");

    }

    public void inventoryBox() throws FileNotFoundException {
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
            Button button = new Button(controller.weaponInfo(controller.getInventoryWeapon(i)));
            firstColumn.getChildren().add(button);
        }
        for (int i = 5; i < 9; i++) {
            Button button = new Button(controller.weaponInfo(controller.getInventoryWeapon(i)));
            secondColumn.getChildren().add(button);
        }

        Button back = new Button("back");
        back.setOnAction(event -> {bottomHBox.getChildren().clear(); playerTurnBox();});
        secondColumn.getChildren().add(back);

        bottomHBox.getChildren().addAll(currentWeapon, firstColumn, secondColumn);


    }



}
