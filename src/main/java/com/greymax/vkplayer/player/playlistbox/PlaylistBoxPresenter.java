package com.greymax.vkplayer.player.playlistbox;

import com.greymax.vkplayer.auth.AuthService;
import com.greymax.vkplayer.player.playlist.PlaylistType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.springframework.social.connect.Connection;
import org.springframework.social.vkontakte.api.Audio;
import org.springframework.social.vkontakte.api.VKontakte;

import javax.inject.Inject;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import static com.greymax.vkplayer.player.playlist.PlaylistType.MY_AUDIO;

public class PlaylistBoxPresenter implements Initializable {

    private static final Logger logger = Logger.getLogger(PlaylistBoxPresenter.class.getName());
    private PlaylistType playlistType;
    private VKontakte vkontakte;
    private List<Audio> audioList = new ArrayList<>();

    @Inject
    private AuthService authService;

    @FXML
    public Text playlistName;
    @FXML
    public Button refreshButton;
//  @FXML public Button removeButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection<VKontakte> connection = authService.getConnection();
        vkontakte = connection.getApi();
    }

    public void setPlaylistName(String name) {
        this.playlistName.setText(name);
    }

    public void setPlaylistType(PlaylistType playlistType) {
        this.playlistType = playlistType;
    }

    public List<Audio> getAudioList() {
        return audioList;
    }

    @FXML
    public void onMouseEntered(MouseEvent event) {
//    refreshButton.setVisible(true);
//    removeButton.setVisible(true); // TODO: Not supported yet
    }

    @FXML
    public void onMouseExited(MouseEvent event) {
//    refreshButton.setVisible(false);
//    removeButton.setVisible(false); // TODO: Not supported yet
    }

    @FXML
    public void refreshPlaylist(ActionEvent event) {
        if (playlistType == null) throw new RuntimeException("Playlist should has defined type");
        if (playlistType.equals(MY_AUDIO)) {
            this.audioList = vkontakte.audioOperations().get();
        }
    }

    @FXML
    public void removePlaylist(ActionEvent event) {

    }
}
