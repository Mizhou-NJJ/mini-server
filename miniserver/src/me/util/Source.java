package me.util;

import java.awt.*;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

public class Source {
    private static java.awt.Font font;
    public static Font jfOpenTTF(){
       if (font==null){
           File f=new File("/res/jf-open.ttf");
           if (!f.exists()) f=new File("res/jf-open.ttf");
           try {
               font= java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,f);
           } catch (FontFormatException | IOException e) {
               e.printStackTrace();
           }
       }
       return font;
    }
}
