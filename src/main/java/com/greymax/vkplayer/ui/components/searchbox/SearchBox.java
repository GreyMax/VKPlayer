package com.greymax.vkplayer.ui.components.searchbox;

import com.greymax.vkplayer.ui.components.autocomplete.AutoFillTextBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class SearchBox extends Region {

  private AutoFillTextBox textBox;
  private Button clearButton;

  public SearchBox() {
    setId("SearchBox");
    getStyleClass().add("search-box");
    setMinHeight(24);
    setPrefSize(200, 24);
//    setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
    textBox = new AutoFillTextBox();
    HBox.setHgrow(textBox.getTextbox(), Priority.ALWAYS);
    textBox.getTextbox().getStyleClass().add("search-text-field");
    textBox.getTextbox().setPromptText("Search");
    clearButton = new Button();
    clearButton.setVisible(false);
    getChildren().addAll(textBox, clearButton);
    clearButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent actionEvent) {
        textBox.getTextbox().setText("");
        textBox.getTextbox().requestFocus();
      }
    });
    textBox.textProperty().addListener(new ChangeListener<String>() {
      @Override public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        clearButton.setVisible(textBox.getText().length() != 0);
      }
    });
  }

  @Override
  protected void layoutChildren() {
    textBox.resize(getWidth(), getHeight());
    clearButton.resizeRelocate(getWidth() - 18, 6, 12, 13);
  }
}
