package com.greymax.vkplayer.ui.components.autocomplete;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.LinkedList;

public class AutoFillTextBox<T> extends Control
    implements AutoFillTextBoxFactory<T> {

  private TextField textbox;
  private ListView<T> listview;
  private ObservableList<T> data = FXCollections.observableArrayList();
  private boolean filterMode;
  private int limit;
  private LinkedList<ChangeListener> selectionListeners;

  public AutoFillTextBox(ObservableList<T> data) {
    init();
    this.data = data;
  }

  public AutoFillTextBox() {
    init();
  }

  private void init() {
    getStyleClass().setAll(new String[]{"autofill-text"});
    this.textbox = new TextField();
    this.listview = new ListView();
    this.limit = 10;
    this.filterMode = false;
    this.selectionListeners = new LinkedList<>();

    listen();
  }

  public void requestFocus() {
    super.requestFocus();
    this.textbox.requestFocus();
    System.out.println("Focued");
  }

  public T getItem() {
    return this.listview.getSelectionModel().getSelectedItem();
  }

  public String getText() {
    return this.textbox.getText();
  }

  public void addData(T data) {
    this.data.add(data);
  }

  public void setData(ObservableList<T> data) {
    this.data = data;
  }

  public ObservableList<T> getData() {
    return this.data;
  }

  public ListView<T> getListview() {
    return this.listview;
  }

  public TextField getTextbox() {
    return this.textbox;
  }

  public void setListLimit(int limit) {
    this.limit = limit;
  }

  public int getListLimit() {
    return this.limit;
  }

  public void setFilterMode(boolean filter) {
    this.filterMode = filter;
  }

  public boolean getFilterMode() {
    return this.filterMode;
  }

  public void setMinSize(double d, double d1) {
    super.setMinSize(d, d1);
    this.textbox.setMinSize(d, d1);
  }

  public void setPrefSize(double d, double d1) {
    super.setPrefSize(d, d1);
    this.textbox.setPrefSize(d, d1);
  }

  public void resize(double d, double d1) {
    super.resize(d, d1);
    this.textbox.resize(d, d1);
  }

  public void setMaxSize(double d, double d1) {
    super.setMaxSize(d, d1);
    this.textbox.setMaxSize(d, d1);
  }

  public StringProperty textProperty() {
    return this.textbox.textProperty();
  }

  /**
   * Adds a listener that notifies when a selection has occured
   * @param listener ChangeListener to use
   */
  public void addSelectionListener(ChangeListener listener) {
    if (listener != null)
      selectionListeners.add(listener);
  }

  public LinkedList<ChangeListener> getSelectionListeners() {
    return selectionListeners;
  }

  private void listen() {
    onMouseClickedProperty().addListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue observableValue, Object o, Object o2) {
        AutoFillTextBox.this.textbox.addEventHandler(MouseEvent.MOUSE_CLICKED, getOnMouseClicked());
      }
    });
  }

//  private void listen() {
//    prefHeightProperty().addListener(new ChangeListener() {
//      @Override
//      public void changed(ObservableValue observableValue, Object o, Object o2) {
//        AutoFillTextBox.this.textbox.setPrefHeight(new Double(String.valueOf(o2)).doubleValue());
//      }
//    });
//    prefWidthProperty().addListener(new ChangeListener() {
//      @Override
//      public void changed(ObservableValue observableValue, Object o, Object o2) {
//        AutoFillTextBox.this.textbox.setPrefWidth(new Double(String.valueOf(o2)).doubleValue());
//      }
//    });
//    minHeightProperty().addListener(new ChangeListener() {
//      @Override
//      public void changed(ObservableValue observableValue, Object o, Object o2) {
//        AutoFillTextBox.this.textbox.setMinHeight(new Double(String.valueOf(o2)).doubleValue());
//      }
//    });
//    maxHeightProperty().addListener(new ChangeListener() {
//      @Override
//      public void changed(ObservableValue observableValue, Object o, Object o2) {
//        AutoFillTextBox.this.textbox.setMaxHeight(new Double(String.valueOf(o2)).doubleValue());
//      }
//    });
//    minWidthProperty().addListener(new ChangeListener() {
//      @Override
//      public void changed(ObservableValue observableValue, Object o, Object o2) {
//        AutoFillTextBox.this.textbox.setMinWidth(new Double(String.valueOf(o2)).doubleValue());
//      }
//    });
//    maxWidthProperty().addListener(new ChangeListener() {
//      @Override
//      public void changed(ObservableValue observableValue, Object o, Object o2) {
//        AutoFillTextBox.this.textbox.setMaxWidth(new Double(String.valueOf(o2)).doubleValue());
//      }
//    });
//  }
}