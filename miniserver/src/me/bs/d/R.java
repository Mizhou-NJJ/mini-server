package me.bs.d;
import me.bs.cf.Config;

import java.io.*;
import java.util.ArrayList;

public class R {
    private static ArrayList<City> cities;
    private static ArrayList<ModelKey>modelKeys;
    public R(){}
    public static ArrayList<City> readHotCity()  {
        if (cities!=null){
            ArrayList<City> cctts = new ArrayList<>(cities);
            return cctts;
        }
        File file =new File(Config.PATH_HOT_CITY);
        FileInputStream fileInputStream =null;
        InputStreamReader inputStreamReader =null;
        BufferedReader bufferedReader=null;
        try {
            String line = null;
            fileInputStream =new FileInputStream(file);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader  =new BufferedReader(inputStreamReader);
            cities = new ArrayList<>();
            while ((line= bufferedReader.readLine())!=null){
                line.replace("\r\n","");
                String ox02[] = line.split(":");
                City city =new City();
                city.cityName= ox02[0];
                city.cityCode = ox02[1];
                cities.add(city);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
                inputStreamReader.close();
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return cities;
    }
    public static ArrayList<ModelKey>readeKeys(){
        if (modelKeys!=null) return modelKeys;
        File file =new File(Config.PATH_KEY_WORD);
        FileInputStream fileInputStream =null;
        InputStreamReader inputStreamReader =null;
        BufferedReader bufferedReader=null;
        try {
            String line = null;
            fileInputStream = new FileInputStream(file);
            inputStreamReader=new InputStreamReader(fileInputStream);
            bufferedReader =new BufferedReader(inputStreamReader);
            modelKeys=new ArrayList<>();
            while ((line= bufferedReader.readLine())!=null){
                /* *************** key and value*/
                ModelKey modelKey =new ModelKey();
                line.replace("\r\n","");
                String ox02[] = line.split(":");
                modelKey.setModelKeyName(ox02[0]);
                String v = ox02[1];
                /***********************************/
                String vs[] = v.split("&");
                ArrayList<Key> keylist =new ArrayList<>();
                for(short i =0;i<vs.length;i++){
                    if (!vs[i].equals("")){
                        Key k =new Key();
                        k.setCities(readHotCity());
                        k.setKeyName(vs[i]);
                        keylist.add(k);
                    }
                }
                modelKey.setKeys(keylist);
                modelKeys.add(modelKey);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
                inputStreamReader.close();
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return modelKeys;
    }
    public static int[] readStatus(){
        File file =new File(Config.PATH_STATUS);
        FileReader fileReader =null;
        BufferedReader bufferedReader =null;
        int [] ds =new int[3];
        try {
           fileReader =new FileReader(file);
           bufferedReader = new BufferedReader(fileReader);
           String line =null;
           int t =0;
           while ((line = bufferedReader.readLine())!=null){
               ds[t] = Integer.parseInt(line.split("=")[1]);
               ++t;
           }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                assert bufferedReader != null;
                bufferedReader.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ds;
    }
}
