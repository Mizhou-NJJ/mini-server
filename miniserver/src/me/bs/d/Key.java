package me.bs.d;

import java.util.ArrayList;

public class Key {
    private String keyName;
    private ArrayList<City> cities;
    private boolean isComplete;
    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

    public ArrayList<City> getCities() {
        return cities;
    }
    public City getACity(int pos){
        return cities.get(pos);
    }
    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getKeyName() {
        return keyName;
    }
    public int getCitysSize(){
        return cities==null?0:cities.size();
    }
    public boolean isComplete(){
        return isComplete;
    }
    public void isComplete(boolean isComplete){
        this.isComplete=isComplete;
    }
    public void removeCityBy(String cityCode){
        cities.removeIf(c -> c.cityCode.equals(cityCode));
    }
}
