package me.bs.d;

import me.bs.cf.Config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class W {
    public static void writeStatus(int modelkey_pos,int key_pos,int city_pos){
        File file =new File(Config.PATH_STATUS);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write("modelpos="+modelkey_pos+"\n");
            fileWriter.write("keypos="+key_pos+"\n");
            fileWriter.write("citypos="+city_pos);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                assert fileWriter != null;
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
