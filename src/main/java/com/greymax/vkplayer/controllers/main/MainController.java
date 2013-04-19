package com.greymax.vkplayer.controllers.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public SplitPane splitPane;
    @FXML
    public AnchorPane firstPane;
    @FXML
    public AnchorPane secondPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
