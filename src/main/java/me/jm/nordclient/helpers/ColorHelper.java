package me.jm.nordclient.helpers;

import java.awt.*;

public class ColorHelper {
    public static int chroma(int delay){
        double chromaState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
        chromaState %= 360;
        return Color.getHSBColor((float) (chromaState / 360.0f), 0.5f, 1f).getRGB();
    }
}
