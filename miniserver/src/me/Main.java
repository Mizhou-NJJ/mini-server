package me;

import me.m.mswing.style.Style;
import me.ui.MFrame;

public class Main {
    public static void main(String[] args) {
        Style.MFrame.height=400;
        Style.MFrame.width=400;
        MFrame frame=new MFrame("Blue Orange Server",420,450);
        frame.setSize(420,450);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setVisible(true);
    }
}
