package com.greymax.vkplayer.ui.components.spinner;


public enum SpinnerStyle {
    WHITE(""),
    DARK("_dark");

    private String style;

    SpinnerStyle(String style) {
        this.style = style;
    }

    public String getValue() {
        return style;
    }
}
