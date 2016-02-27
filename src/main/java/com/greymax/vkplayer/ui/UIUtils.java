package com.greymax.vkplayer.ui;

import com.jfoenix.skins.JFXSliderSkin;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Slider;

public class UIUtils {

  public static void toggleClass(Control control, String className) {
    ObservableList<String> styleClass = control.getStyleClass();
    if (styleClass.contains(className)) {
      styleClass.remove(className);
    } else {
      styleClass.add(className);
    }
  }

  public static void disableSliderAnimatedThumb(Slider slider) {
    slider.skinProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue instanceof JFXSliderSkin) {
        ObservableList<Node> nodes = ((JFXSliderSkin) newValue).getChildren();
        nodes.get(nodes.size() - 2).setVisible(false);
      }
    });
  }
}
