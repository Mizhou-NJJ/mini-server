package me.udp;

import me.dao.SaveAsRAM;
import me.orange.UDPHeader;
import me.orange.serobj.User;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

public class UDPServerThread implements Runnable{
    private DatagramPacket packet;
    private DatagramSocket socket;
    private SaveAsRAM saveAsRAM;
    public UDPServerThread(DatagramSocket socket,DatagramPacket packet){
        this.socket=socket;
        this.packet=packet;
        saveAsRAM=SaveAsRAM.getInstance();
    }
    @Override
    public void run() {
        // 获取客户端信息
        String header=new String(packet.getData(),0,packet.getLength());
        /*
        *  自定义的UDP头部，转成HashMap形式，方便取值
        * */
        UDPHeader udpHeader=new UDPHeader();
        HashMap<String,String> hashMap=udpHeader.headerToHashMap(header);
        /*
        *  what
        *  from
        *  to
        *  mul
        *  time
        *  msg: 消息内容
        *
        * */
        // 判断 what 是什么类型的请求
        switch (hashMap.get("what")){
            case UDPHeader.UPDATE:
            case UDPHeader.INIT: //初始化客户端 端口 ip 信息
                // 获取ip 和 端口
                InetAddress inetAddress=packet.getAddress();
                int port=packet.getPort();
                // 获得账号
                String accoount=hashMap.get("from");
                User u=saveAsRAM.get(accoount);
                UDPHeader h=new UDPHeader();
                h.setMsg("初始化完成");
                h.setMul("TRUE");
                h.setFrom(accoount);
                sendPersonal(h);
            case UDPHeader.MESSAGE: // 发送消息
                udpHeader.setWhat("MESSAGE");
                udpHeader.setFrom(hashMap.get("from"));
                udpHeader.setTo("None");
                udpHeader.setMul(hashMap.get("mul"));
                udpHeader.setTime(hashMap.get("time"));
                udpHeader.setMsg(hashMap.get("msg"));
                if (hashMap.get("mul").equals("TRUE")){ //群聊模式
                    sendAll(udpHeader);
                }else{ // 发送至单人
                }
                break;
        }
    }
    /*
    * 群发
    *
    * */
    private void sendAll(UDPHeader header){
        String hs=header.toString();
        byte []bytes=hs.getBytes();
        for(User u:saveAsRAM.getAllUser("")){
            // 改
            DatagramPacket rp=new DatagramPacket(bytes,bytes.length,null,0);
            try {
                socket.send(rp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void sendPersonal(UDPHeader header){
        byte [] bytes=header.toString().getBytes();
        InetAddress address=packet.getAddress();
        int port=packet.getPort();
        DatagramPacket dp=new DatagramPacket(bytes,bytes.length,address,port);
        try {
            socket.send(dp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
