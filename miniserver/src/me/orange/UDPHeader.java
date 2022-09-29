package me.orange;

import java.util.HashMap;

public class UDPHeader {
    public final static String INIT="INIT"; // 初始化客户端UDP信息
    public final static String UPDATE="UPDATE"; // 更新客户端UDP消息
    public final static String MESSAGE="MESSAGE"; // 向服务器发送消息
//    private String ip;
    private String from;
    private String to;
    private String mul;
    private String what;
    private String msg;
    private String time;
//    public String getIp() {
//        return ip;
//    }

//    public void setIp(String ip) {
//        this.ip = ip;
//    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMul() {
        return mul;
    }

    public void setMul(String mul) {
        this.mul = mul;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public String getMsg() {
        return msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String toString(){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(what);
        stringBuilder.append("::");
        stringBuilder.append(from);
        stringBuilder.append("::");
        stringBuilder.append(to);
        stringBuilder.append("::");
        stringBuilder.append(mul);
        stringBuilder.append("::");
        stringBuilder.append(time);
        stringBuilder.append("::");
        stringBuilder.append(msg);

        return stringBuilder.toString();
    }
    public HashMap<String,String> headerToHashMap(String header){
        String [] dh=header.split("::");
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("what",dh[0]);
        hashMap.put("from",dh[1]);
        hashMap.put("to",dh[2]);
        hashMap.put("mul",dh[3]);
        hashMap.put("time",dh[4]);
        hashMap.put("msg",dh[5]);
        return hashMap;
    }
}
