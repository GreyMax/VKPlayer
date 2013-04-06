package com.greymax.vkplayer.ui.components.autocomplete;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public abstract interface AutoFillTextBoxFactory<T>
{
  public abstract void setData(ObservableList<T> paramObservableList);

  public abstract ObservableList<T> getData();

  public abstract ListView<T> getListview();

  public abstract TextField getTextbox();

  public abstract void setListLimit(int paramInt);

  public abstract int getListLimit();

  public abstract void setFilterMode(boolean paramBoolean);

  public abstract boolean getFilterMode();
}