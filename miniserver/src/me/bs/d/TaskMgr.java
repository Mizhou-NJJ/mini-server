package me.bs.d;

import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;
import me.ui.Terminal;

import java.util.ArrayList;

public class TaskMgr {
    private static Terminal terminal=Terminal.getInstance();
    private static int model_pos=0;
    private static int key_pos=0;
    private static int city_pos =0;
    private static ArrayList<Key> now_keys;
    private static ArrayList<City> now_cities;
    private static ArrayList<ModelKey> modelKeys;
    private static boolean allComplete;
    static {
        synchronized (TaskMgr.class) {
            modelKeys = R.readeKeys();
            int statsus[] = R.readStatus();
            model_pos = statsus[0];
            key_pos = statsus[1];
            city_pos = statsus[2];
        }
    }
    public synchronized static String getTask(){
        /*
        * String的格式是什么
        *  status:200&modelkey:java&key:Java工程师&city:010000&pagestart:10&pageend:44
        *  那么要如何取任务呢
        * */
        StringBuilder builder=new StringBuilder();
        ModelKey modelKey=null;
        Key key = null;
        City city = null;
        if(model_pos<modelKeys.size()){
            modelKey = modelKeys.get(model_pos);
            if (key_pos<modelKey.getKeysSize()){
                key = modelKey.getAKey(key_pos);
               if (city_pos<key.getCitysSize()){ //
                   city = key.getACity(city_pos);
                   city_pos++;
               }else {
//                   System.out.println("该key的所有city均已完成");
                   // 下一个key
                   city_pos = 0;
                   if(++key_pos<modelKey.getKeysSize()){ // 取下一个key
                       key = modelKey.getAKey(key_pos);
                       city = key.getACity(city_pos);
                       city_pos++;
                   }else { // 说明 该modelkey的所有key都用完了
                      if (++model_pos<modelKeys.size()){
                          modelKey = modelKeys.get(model_pos);
                          key_pos = 0;
                          city_pos = 0;
                          key = modelKey.getAKey(key_pos);
                          city = key.getACity(city_pos);
                          city_pos++;
                      }else {
//                          System.out.println("所有任务均完成");
                          terminal.defaultAddInTerminalData(terminal.green("所有的任务都已经完成了!"),Terminal.TCP_GAME);
                          allComplete = true;
                      }
                   }
               }
            }else {
//                System.out.println("该ModelKey的所有key均已完成");
                // 下一个ModelKey
                model_pos++;
            }
        }else {
//            System.out.println("所有任务均完成");
            terminal.defaultAddInTerminalData(terminal.green("所有的任务都已经完成了!"),Terminal.TCP_GAME);
            allComplete = true;
        }
        if(allComplete){
            builder.append("status:000&msg:All complete");
        }else {
            builder.append("status:200&modelkey:");
            builder.append(modelKey.getModelKeyName());
            builder.append("&key:");
            builder.append(key.getKeyName());
            builder.append("&citycode:" + city.cityCode);
            builder.append("&cityname:" + city.cityName);
            builder.append("&msg:NotOver");
        }
//        System.out.println("model_pos:"+model_pos+"---key_pos:"+key_pos+"---city_pos:"+city_pos);
       return builder.toString();
    }
    public static void saveStatus(){
        W.writeStatus(model_pos,key_pos,city_pos);
    }
}
