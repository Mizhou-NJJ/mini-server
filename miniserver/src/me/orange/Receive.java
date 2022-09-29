package me.orange;

import java.io.Serializable;

public class Receive implements Serializable {
    public static final String OK="OK";
    public static final String ERROR="ERROR";
    private static final long serialVersionUID = 125566L;
    private String msg;
    private Object obj;
    public Receive(){

    }
    public Receive(String msg){
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
