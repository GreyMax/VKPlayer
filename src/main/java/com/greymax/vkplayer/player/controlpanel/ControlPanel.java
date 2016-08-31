package com.greymax.vkplayer.player.controlpanel;

import javafx.scene.layout.HBox;

public class ControlPanel extends HBox {

    public ControlPanel() {
        new ControlPanelView().getViewAsync(getChildren()::add);
    }
}
