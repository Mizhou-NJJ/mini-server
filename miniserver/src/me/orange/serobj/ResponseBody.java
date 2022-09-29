package me.orange.serobj;

import java.io.Serializable;

public class ResponseBody implements Serializable {
    private static final long serialVersionUID = 57204387;
    public final static int OK=200;
    public final static int ERROR=100;
    public final static int BAD_REQUEST=500;
    public final static int NO_DATA=404;
    private int code; // 响应码
    private String msg; // 响应信息
    private Object o; // 响应体
    public ResponseBody(Object o,String msg,int code){
       this.o=o;
       this.msg=msg;
       this.code=code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObject() {
        return o;
    }

    public void setObject(Object o) {
        this.o = o;
    }

    @Override
    public String toString() {
        return "ResponseBody{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", o=" + o +
                '}';
    }
}
