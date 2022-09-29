package me.bs.server;

import me.bs.cf.Config;
import me.ui.Terminal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket serverSocket;
    private static Server server;
    private static Terminal terminal=Terminal.getInstance();
    private Socket client;
    private Server(){

    }
    public static Server getInstance(){
        if(server==null) server =new Server();
        return server;
    }
    public  void startServer(int port){
        new Thread(new Runnable() {
            @Override
            public void run() {
                serverStart(port);
            }
        }).start();
    }
    private  void serverStart(int port){
        try {
            serverSocket = new ServerSocket(port);
            if(serverSocket.isBound())
            terminal.defaultAddInTerminalData(terminal.green("服务器启动成功..."),Terminal.TCP_GAME);
            else terminal.defaultAddInTerminalData(terminal.red("服务器启动失败..."),Terminal.TCP_GAME);
            while (true){
                client = serverSocket.accept();
                new Thread(new ServerWorker(client)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
