package me.tcpip;

import me.orange.Receive;
import me.orange.serobj.User;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class TCPClient {
    public TCPClient(){

    }
    public void conn() throws IOException, ClassNotFoundException {
        Socket socket=new Socket("127.0.0.1",8888);
        InputStream inputStream=socket.getInputStream();
        OutputStream outputStream=socket.getOutputStream();
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(outputStream);
        ObjectInputStream objectInputStream=new ObjectInputStream(inputStream);
        User user=new User();
        user.setNick("Mizhu");
        // 收取从客户端的数据
        Receive receive= (Receive) objectInputStream.readObject();
        ArrayList<User> users= (ArrayList<User>) receive.getObj();
        System.out.println("收到服务器的数据:"+receive.getMsg());
        if (users!=null) {
            for (User u : users) {
            }
        }
        // close cg
        objectOutputStream.close();
        objectInputStream.close();
        inputStream.close();
        outputStream.close();
        socket.close();
        // testUDP
//        UDPServer.startUDPServer();
//        UDPClient.startUDP();
    }

    public static void main(String[] args) {
        try {
            new TCPClient().conn();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
