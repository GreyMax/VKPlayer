package com.greymax.vkplayer.player.playlist;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

import static com.greymax.vkplayer.player.playlist.PlaylistType.MY_AUDIO;
import static com.greymax.vkplayer.player.playlist.PlaylistType.RECOMMENDED;
import static javafx.scene.input.KeyCode.ENTER;

@Deprecated
public class Playlist extends HBox implements EventHandler {

    public static final String REFRESH_BUTTON = "refresh";
    public static final String REMOVE_BUTTON = "remove";
    private final static String PLAY_LIST_IMAGE_PATH = "com/greymax/vkplayer/player/playlistbox/images/playlist.png";
    private PlaylistType type;
    private Text nameField;
    private Button refreshButton;
    private Button removeButton;
    private VBox refreshButtonWrapper;
    private VBox removeButtonWrapper;
    private String playlistName;

    private VBox newPlaylistFieldWrapper;
    private TextField newPlaylistField;

    private List<EventHandler> eventHandlerList = new ArrayList<>();

    public Playlist() {
        init();
    }

    public Playlist(String playlistName) {
        this();
        this.playlistName = playlistName;
        nameField.setText(playlistName);
    }

    public Playlist(PlaylistType playlistType, String playlistName) {
        this(playlistName);
        this.type = playlistType;
        if (type.equals(MY_AUDIO) || type.equals(RECOMMENDED))
            getChildren().add(refreshButton);
        if (type.equals(PlaylistType.ALBUM)) {
            getChildren().add(refreshButton);
            getChildren().add(removeButton);
        }
    }

    // new playlist
    public Playlist(PlaylistType playlistType, final ListView listView) {
        if (!playlistType.equals(PlaylistType.NEW_ALBUM))
            throw new UnsupportedOperationException();

        getStyleClass().add("new-playlist");
        newPlaylistField = new TextField();
        newPlaylistField.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        newPlaylistField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(ENTER)) {
                String newPlaylistName = newPlaylistField.getText();
                if (newPlaylistName != null && !newPlaylistName.isEmpty()) {
                    getChildren().remove(newPlaylistFieldWrapper);
                    init();
                    setPlaylistName(newPlaylistName);
                    setType(PlaylistType.ALBUM);
                }
            }
            if (keyEvent.getCode().equals(KeyCode.ESCAPE)) {
                listView.getItems().remove(listView.getItems().size() - 1);
            }
        });

        newPlaylistFieldWrapper = VBoxBuilder.create().build();
        newPlaylistFieldWrapper.getChildren().add(newPlaylistField);
        setHgrow(newPlaylistFieldWrapper, Priority.ALWAYS);
        getChildren().add(newPlaylistFieldWrapper);
        newPlaylistField.requestFocus();
    }

    private void init() {
        Image image = new Image(getClass().getClassLoader().getResource(PLAY_LIST_IMAGE_PATH).toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(12);
        imageView.setFitHeight(12);
        Label iconLabel = new Label("", imageView);
        iconLabel.getStyleClass().add("playlist-icon");
        VBox iconWrapper = VBoxBuilder.create().build();
        iconWrapper.setAlignment(Pos.CENTER);
        iconWrapper.getChildren().add(iconLabel);
        setHgrow(iconWrapper, Priority.NEVER);

        nameField = new Text();
        nameField.getStyleClass().add("playlist-name");
        nameField.setFont(Font.font("Arial", 12));
        VBox nameWrapper = VBoxBuilder.create().build();
        nameWrapper.setAlignment(Pos.CENTER_LEFT);
        nameWrapper.getChildren().add(nameField);
        setHgrow(nameWrapper, Priority.ALWAYS);

        ActionButtonListener actionButtonListener = new ActionButtonListener(this);

        refreshButton = new Button();
        refreshButton.setId(REFRESH_BUTTON);
        refreshButton.getStyleClass().add("playlist-refresh-button");
        refreshButton.setFocusTraversable(false);
        refreshButton.setMinWidth(12);
        refreshButton.setPrefWidth(12);
        refreshButton.setMaxWidth(12);
        refreshButton.setMinHeight(12);
        refreshButton.setPrefHeight(12);
        refreshButton.setMaxHeight(12);
        refreshButton.setOnAction(actionButtonListener);
        refreshButton.setVisible(false);
        refreshButtonWrapper = VBoxBuilder.create().build();
        refreshButtonWrapper.setAlignment(Pos.CENTER);
        refreshButtonWrapper.getChildren().add(refreshButton);

        removeButton = new Button();
        removeButton.setId(REMOVE_BUTTON);
        removeButton.getStyleClass().add("playlist-remove-button");
        removeButton.setFocusTraversable(false);
        removeButton.setMinWidth(12);
        removeButton.setPrefWidth(12);
        removeButton.setMaxWidth(12);
        removeButton.setMinHeight(12);
        removeButton.setPrefHeight(12);
        removeButton.setMaxHeight(12);
        removeButton.setOnAction(actionButtonListener);
        removeButton.setVisible(false);
        removeButtonWrapper = VBoxBuilder.create().build();
        removeButtonWrapper.setAlignment(Pos.CENTER);
        removeButtonWrapper.getChildren().add(removeButton);

        getStyleClass().remove("new-playlist");
        setPadding(new Insets(0, 0, 0, 10));
        setSpacing(3.0);
        setAlignment(Pos.CENTER_LEFT);
        getChildren().addAll(iconWrapper, nameWrapper);
        setOnMouseEntered(this);
        setOnMouseExited(this);
    }

    public PlaylistType getType() {
        return type;
    }

    public void setType(PlaylistType type) {
        this.type = type;
        if (type.equals(MY_AUDIO) || type.equals(RECOMMENDED))
            getChildren().add(refreshButtonWrapper);
        if (type.equals(PlaylistType.ALBUM)) {
            getChildren().add(refreshButtonWrapper);
            getChildren().add(removeButtonWrapper);
        }
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
        nameField.setText(playlistName);
    }

    public void addActionListener(EventHandler eventHandler) {
        eventHandlerList.add(eventHandler);
    }

    @Override
    public void handle(Event event) {
        if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
            refreshButton.setVisible(true);
            removeButton.setVisible(true);
        }
        if (event.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
            refreshButton.setVisible(false);
            removeButton.setVisible(false);
        }
    }

    class ActionButtonListener implements EventHandler {

        private Playlist target;

        public ActionButtonListener(Playlist playlist) {
            this.target = playlist;
        }

        @Override
        public void handle(Event event) {
            if (((Button) event.getSource()).getId().equals(REFRESH_BUTTON))
                for (EventHandler eventHandler : eventHandlerList)
                    eventHandler.handle(new PlaylistEvent(PlaylistEventType.REFRESH.getValue(), target));
            if (((Button) event.getSource()).getId().equals(REMOVE_BUTTON))
                for (EventHandler eventHandler : eventHandlerList)
                    eventHandler.handle(new PlaylistEvent(PlaylistEventType.DELETE.getValue(), target));
        }
    }
}
