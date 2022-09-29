package me.orange.serobj;

import java.io.Serializable;

/**
 * 在进行TCP 请求时,序列化这个对象
 */
public class RequestBody implements Serializable {
    public final static int REGISTER=1;
    public final static int LOGIN=2;
    public final static int PULL_FRIENDS=3;
    public final static int PULL_IP=4;
    public final static int FOUND_FRIEND=5;
    public final static int ADD_FRIEND=6;
    public final static int PULL_ALL=7;
    private int type; // 请求类型
    private String ip;
    private Object object; // 请求体
    private static final long serialVersionUID = 57204389;
    public RequestBody(Object o,int type){
       this.object=o;
       this.type=type;
    }

    public int getType() {
        return type;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Object getObject() {
        return object;
    }

    @Override
    public String toString() {
        return "RequestBody{" +
                "type=" + type +
                ", ip='" + ip + '\'' +
                ", object=" +(object==null?"null": object.toString()) +
                '}';
    }
}
