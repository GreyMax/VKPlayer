package com.greymax.vkplayer.ui;

import javafx.collections.ObservableList;
import javafx.scene.control.Control;

import java.util.Arrays;
import java.util.List;

public class UIUtils {

  public static void toggleClass(Control control, String className) {
    ObservableList<String> styleClass = control.getStyleClass();
    if (styleClass.contains(className)) {
      styleClass.remove(className);
    } else {
      styleClass.add(className);
    }
  }

  public static void addClassFromQueue(Control control, String... classNames) {
    ObservableList<String> styleClass = control.getStyleClass();
    List<String> classNamesList = Arrays.asList(classNames);
    String existingClass = classNamesList.stream().filter(styleClass::contains).findFirst().orElse(null);
    if (existingClass == null) {
      styleClass.add(classNamesList.get(0));
    } else {
      styleClass.remove(existingClass);
      int classIndex = classNamesList.indexOf(existingClass);
      if (classIndex < classNamesList.size() - 1) {
        styleClass.add(classNamesList.get(classIndex + 1));
      }
    }
  }
}
