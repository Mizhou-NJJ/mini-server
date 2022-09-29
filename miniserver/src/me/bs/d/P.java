package me.bs.d;

import java.util.HashMap;

public class P {
    private static String H=null;
    private static HashMap<String,String> hotcitys;
    private static P p;
    private P(){
            p = new P();
    }
    static {
    }
    private static P getInstance(){
        if (p==null){
            synchronized (P.class){
                if (p == null)
                p = new P();
            }
        }
        return p;
    }

}
