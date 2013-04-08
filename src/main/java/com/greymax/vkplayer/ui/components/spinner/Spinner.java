package com.greymax.vkplayer.ui.components.spinner;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Spinner extends Canvas {

    public static final int DEFAULT_SPINNER_SIZE = SpinnerSize.SPINNER24.getValue();
    public static final String DEFAULT_SPINNER_STYLE = SpinnerStyle.DARK.getValue();
    public static final String SPINNER_URL_PATTERN = "image/process/step_%d@%dpx%s.png";
    private Timeline timeLine;
    private List<Image> images;
    private int index = 0;

    public Spinner() {
        init(DEFAULT_SPINNER_SIZE, DEFAULT_SPINNER_STYLE);
    }

    public Spinner(int size) {
        init(size, DEFAULT_SPINNER_STYLE);
    }

    public Spinner(int size, String style) {
        init(size, style);
    }

    private void init(int spinnerSize, String spinnerStyle) {
        images = new ArrayList<>();
        for (int i = 0; i < 12; i++)
            images.add(new Image(Spinner.class.getClassLoader().getResourceAsStream(
                String.format(SPINNER_URL_PATTERN, i+1, spinnerSize, spinnerStyle))));

        setWidth(spinnerSize);
        setHeight(spinnerSize);
        final GraphicsContext context = this.getGraphicsContext2D();

        timeLine = new Timeline();
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.getKeyFrames().add(
                new KeyFrame(Duration.millis(50),
                        new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent t) {
                                context.clearRect(0, 0, getWidth(), getHeight());
                                Image image = images.get(index);
                                context.drawImage(image, 0, 0);
                                index = (index < images.size() - 1) ? index + 1 : 0;
                            }
                        },
                        new KeyValue[0]) // don't use binding
        );
    }

    public void start() {
        timeLine.playFromStart();
        setVisible(true);
    }

    public void stop() {
        timeLine.stop();
        setVisible(false);
    }
}