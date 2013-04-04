package com.javafx.main;

import javax.swing.*;

public class NoJavaFXFallback extends JApplet {

    public NoJavaFXFallback(boolean valjreError, boolean b, String s) {
        System.out.println("================= JavaFX not found ===========");
        System.out.println(s);
    }
}
