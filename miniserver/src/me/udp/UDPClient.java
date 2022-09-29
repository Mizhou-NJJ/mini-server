package me.udp;

import me.orange.UDPHeader;

import java.io.IOException;
import java.net.*;

public class UDPClient {
    private DatagramPacket packet;
    private DatagramSocket socket;
    private UDPHeader header;
    public UDPClient(UDPHeader header){
        this.header=header;
    }
    // udp包发送
    public void conn() throws IOException {
        // 获取本地ip(服务器io)
        InetAddress inetAddress=InetAddress.getByName("127.0.0.1");
        int port=8889;
        /*
        * 发送什么? 发送一个UDPHeader
        *
        * */
        byte [] data=header.toString().getBytes();
        packet=new DatagramPacket(data,data.length,inetAddress,port);
        socket=new DatagramSocket();
        socket.send(packet);
        // 接受服务器数据
        /*
        *  服务器会返回一个UDPHeader的字符串
        * */
        byte [] rdata=new byte[1024];
        DatagramPacket rpa=new DatagramPacket(rdata,rdata.length);
        socket.receive(rpa);
        String rs=new String(rpa.getData());
        System.out.println("服务器说:"+rs);
        socket.disconnect();
        socket.close();
    }

    public static void main(String[] args) {
        startUDP();
    }
    public static void startUDP(){
        UDPHeader header=new UDPHeader();
        header.setWhat(UDPHeader.INIT);
        header.setMul("TRUE");
        header.setMsg("你好啊,Python");
        header.setFrom("1798334412");
        try {
            new UDPClient(header).conn();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
