package me.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {
    private DatagramSocket socket;
    private DatagramPacket packet;
    public UDPServer(){
        try {
            startup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   private void startup() throws IOException {
       socket=new DatagramSocket(8889);
       byte [] bytes=new byte[1024]; // 接受的数据包大小
       while (true){
           packet=new DatagramPacket(bytes,bytes.length);
           socket.receive(packet);
           new Thread(new UDPServerThread(socket,packet)).start();
       }
   }

    public static void main(String[] args) {
        try {
            new UDPServer().startup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void startUDPServer(){
        try {
            new UDPServer().startup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
