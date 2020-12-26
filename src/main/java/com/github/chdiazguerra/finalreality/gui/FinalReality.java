package com.github.chdiazguerra.finalreality.gui;

import com.github.chdiazguerra.finalreality.controller.GameController;
import com.github.chdiazguerra.finalreality.gui.scenes.InitScene;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

/**
 * Main entry point for the application.
 * <p>
 * <Complete here with the details of the implemented application>
 *
 * @author Ignacio Slater Muñoz.
 * @author Christian Diaz Guerra
 */
public class FinalReality extends Application {
  private GameController controller = new GameController();
  private static final String RESOURCE_PATH = "src/main/resources/";

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws FileNotFoundException {
    primaryStage.setTitle("Final reality");
    primaryStage.setResizable(false);

    int width = 500;
    int height = 600;

    primaryStage.setScene(new InitScene(width, height, RESOURCE_PATH, primaryStage, controller).build());

    primaryStage.show();
  }
}