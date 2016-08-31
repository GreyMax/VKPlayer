package com.greymax.vkplayer.player.playlistbox;

import com.greymax.vkplayer.player.playlist.PlaylistType;
import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import org.springframework.social.vkontakte.api.Audio;

import java.util.List;

public class PlaylistBox extends HBox {

    private PlaylistBoxPresenter presenter;
    private PlaylistType playlistType;
    private String playlistName;

    public PlaylistBox() {
        PlaylistBoxView view = new PlaylistBoxView();
        this.getChildren().add(view.getView());
        this.presenter = (PlaylistBoxPresenter) view.getPresenter();
    }

    public void init() {
        presenter.refreshPlaylist(new ActionEvent());
    }

    public PlaylistType getPlaylistType() {
        return playlistType;
    }

    public void setPlaylistType(PlaylistType playlistType) {
        this.playlistType = playlistType;
        this.presenter.setPlaylistType(playlistType);
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
        presenter.setPlaylistName(playlistName);
    }

    public List<Audio> getAudio() {
        return presenter.getAudioList();
    }
}
