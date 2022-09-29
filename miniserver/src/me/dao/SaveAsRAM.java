package me.dao;

import me.orange.serobj.User;
import me.util.Config;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SaveAsRAM implements Serializable {

    private static HashMap<String, User> userHashMap=new HashMap<>();
    private static SaveAsRAM saveAsRAM;
    private SaveAsRAM(){
        /*
        * 序列化 或者反序列化
        * 单例模式，在第一次请求来时反序列化
        * */
        loadFromFile();
    }
    public static SaveAsRAM getInstance(){
        return saveAsRAM;
    }
    static {
        saveAsRAM=new SaveAsRAM();
    }
    public boolean add(User user){
        return userHashMap.put(user.getEmail(),user)==null;
    }
    public User get(String key){
        return userHashMap.get(key);
    }
    public boolean update(User user){
        return userHashMap.put(user.getEmail(),user)!=null;
    }
    public boolean checkLogin(User user){
       User u=userHashMap.get(user.getEmail());
       if (u==null) return false;
       else{
           if (u.getPassword().equals(user.getPassword())) return true;
           else return false;
       }
    }
    public ArrayList<User> getAllUser(String me){
        ArrayList<User> users=new ArrayList<>();
        for(String key:userHashMap.keySet()) {
            if (!key.equals(me))
            users.add(userHashMap.get(key));
        }
        return users;
    }
    // 序列化至文件 , 应该在关闭服务器时序列化
    public void saveAsFile(){
        File file=new File(Config.SER_PATH);
        FileOutputStream outputStream=null;
        ObjectOutputStream objectOutputStream=null;
        try {
            outputStream=new FileOutputStream(file);
            objectOutputStream=new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(userHashMap);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                assert objectOutputStream != null;
                objectOutputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // 反序列化
    private void loadFromFile(){
        File file=new File(Config.SER_PATH);
        if (!file.exists()) { // 如果文件不存在，自然就无法反序列化
            return;
        }
        FileInputStream inputStream=null;
        ObjectInputStream objectInputStream=null;
        try {
            inputStream=new FileInputStream(file);
            objectInputStream=new ObjectInputStream(inputStream);
            userHashMap= (HashMap<String, User>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                assert objectInputStream != null;
                objectInputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public User getUser(User u){
        return userHashMap.get(u.getEmail());
    }
    public int getMapSize(){
        return userHashMap.size();
    }
}












