package com.greymax.vkplayer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Auth extends Application {

    private Stage stage;
    private double mousePositionX;
    private double mousePositionY;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ui/auth/index.fxml"));
        root.getStylesheets().add("/resources/css/auth/index.css");
        primaryStage.setTitle("Login form");
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getIcons().add(new Image("/resources/image/ico.png"));

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #51759c;");
        ToolBar toolBar = new ToolBar();
        int height = 25;
        toolBar.setPrefHeight(height);
        toolBar.setMinHeight(height);
        toolBar.setMaxHeight(height);
        toolBar.setStyle("-fx-alignment: center-right");
        toolBar.getItems().add(new WindowButtons());
        toolBar.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mousePositionX = mouseEvent.getScreenX();
                mousePositionY = mouseEvent.getScreenY();
            }
        });
        toolBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                double shiftX = mouseEvent.getScreenX() - mousePositionX;
                double shiftY = mouseEvent.getScreenY() - mousePositionY;
                primaryStage.setX(primaryStage.getX() + shiftX);
                primaryStage.setY(primaryStage.getY() + shiftY);

                mousePositionX = mouseEvent.getScreenX();
                mousePositionY = mouseEvent.getScreenY();
            }
        });
        borderPane.setTop(toolBar);
        borderPane.setCenter(root);

        primaryStage.setScene(new Scene(borderPane, 300, 210));
        primaryStage.show();
        stage = primaryStage;
    }

    class WindowButtons extends HBox {

        public WindowButtons() {

            Button minimizeButton = new Button("_");
            minimizeButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    stage.setIconified(true);
                }
            });
            Button closeBtn = new Button("X");
            closeBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Platform.exit();
                }
            });

            this.getChildren().add(minimizeButton);
            this.getChildren().add(closeBtn);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
