package com.github.chdiazguerra.finalreality.gui.scenes;

import com.github.chdiazguerra.finalreality.controller.GameController;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class BattleScene {

    private final String PATH;
    private final GameController controller;
    private final Stage primaryStage;
    int width, height;

    private VBox root;

    public BattleScene(int width, int height, String pathFiles, Stage primaryStage, GameController controller){
        this.width = width;
        this.height = height;
        this.PATH = pathFiles;
        this.primaryStage = primaryStage;
        this.controller = controller;
    }

    public Scene build(){
        root = new VBox();
        Scene scene = new Scene(root, width, height);

        return scene;
    }
}
