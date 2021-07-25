package me.jm.nordclient.helpers;

import java.awt.*;

public class ColorHelper {
    public final static int BGR01 = 0x2E3440;
    public final static int BGR02 = 0x3B4252;
    public final static int BGR03 = 0x434C5E;
    public final static int BGR04 = 0x4C566A;

    public final static int WHITE01 = 0xD8DEE9;
    public final static int WHITE02 = 0xE5E9F0;
    public final static int WHITE03 = 0xECEFF4;

    public final static int FORST01 = 0x8FBCBB;
    public final static int FORST02 = 0x88C0D0;
    public final static int FORST03 = 0x81A1C1;
    public final static int FORST04 = 0x5E81AC;


    public static int ToOpacityMode(int color){
        return 0xFF000000 ^ color;
    }

    public static int chroma(int delay){
        double chromaState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
        chromaState %= 360;
        return Color.getHSBColor((float) (chromaState / 360.0f), 0.5f, 1f).getRGB();
    }
}
