package com.greymax.vkplayer.ui.components.spinner;

public enum SpinnerSize {
    SPINNER16(16),
    SPINNER24(24),
    SPINNER32(32);

    private int size;

    SpinnerSize(int size) {
        this.size = size;
    }

    public int getValue() {
        return size;
    }
}
