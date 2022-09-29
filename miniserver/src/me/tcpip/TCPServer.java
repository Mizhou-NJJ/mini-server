package me.tcpip;

import me.ui.Terminal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private Terminal terminal;
    private boolean isCont=true;
    private ServerSocket serverSocket;
    private Socket client;
    private int port;
    public TCPServer(int port){
        this.port=port;
        terminal=Terminal.getInstance();
    }
    public boolean startServer() {
       new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   serverSocket = new ServerSocket(port);
                   while (isCont) {
                       client = serverSocket.accept();
                       terminal.defaultAddInTerminalData(terminal.green("有新的请求"),Terminal.TCP_CHAT);
                       new Thread(new TCPServerThread(client)).start();
                   }
               }catch (IOException e) {
                   isCont=false;
                   e.printStackTrace();
                   terminal.defaultAddInTerminalData(terminal.red(e.getMessage()),Terminal.TCP_CHAT);
               }
           }
       }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (serverSocket!=null) return true;
        return false;
    }
    public boolean closeServer(){
        isCont=false;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
