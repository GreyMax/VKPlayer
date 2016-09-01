package com.jfoenix;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.skins.JFXRoundButtonSkin;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;

public class JFXRoundButton extends JFXButton {

    public JFXRoundButton() {
        super();
        setSkin(new JFXRoundButtonSkin(this));
        setGraphic(new MaterialIconView());
    }
}
