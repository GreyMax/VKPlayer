package com.greymax.vkplayer.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.social.vkontakte.api.Audio;

import java.util.List;

public class PlayerModel {
    private ListProperty<Audio> audioListProperty = new SimpleListProperty<>(FXCollections.observableArrayList());

    public void setAudioList(List<Audio> audioList) {
        audioListProperty.setAll(audioList);
    }

    public ObservableList<Audio> getAudioList() {
        return audioListProperty.get();
    }

    public ListProperty<Audio> audioListProperty() {
        return audioListProperty;
    }
}
