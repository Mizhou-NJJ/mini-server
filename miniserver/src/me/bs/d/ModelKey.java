package me.bs.d;

import java.util.ArrayList;

public class ModelKey {
    private String modelKeyName;
    private ArrayList<Key> keys;
    private boolean isComplete;
    public String getModelKeyName() {
        return modelKeyName;
    }
    public void setModelKeyName(String modelKeyName) {
        this.modelKeyName = modelKeyName;
    }
    public boolean isComplete(){
        return isComplete;
    }
    public void isComplete(boolean isComplete){
        this.isComplete=isComplete;
    }

    public ArrayList<Key> getKeys() {
        return keys;
    }
    public Key getAKey(int pos){
        return keys.get(pos);
    }
    public void setKeys(ArrayList<Key> keys) {
        this.keys = keys;
    }
    public int getKeysSize(){
        return keys.size();
    }
}
