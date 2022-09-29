package me.bs.server;

import me.bs.d.TaskMgr;
import me.ui.Terminal;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;

public class ServerWorker implements Runnable{
/* ------------------------Requests Code----------------*/
    private final static String PULL_TASK = "PULL_TASK";
    public final static String PUSH_TASK = "PUSH_TASK";
    /*---------------End Requests Code ----------------------*/
    /*
    *  The requests format is:
    * key::value&&key::value&&key::value....
    * if this is a pull r the body is:
    * code::0x00&&
    * the response is :
    * key::xx&&city::xx&&page::xx&&status::1
    * ***********************************************************************************
    *  if this r is push
    * code::0x01&&key::xx&&city::xx&&page::xxx
    * response is :
    * status: 1
    *
    * */
    private Socket client;
    private static Terminal terminal = Terminal.getInstance();
    public ServerWorker(Socket client){
        this.client=client;
    }
    @Override
    public void run() {
//        try {
//        } catch (SocketException e) {
//            e.printStackTrace();
//        }
        InputStream inputStream = null;
        OutputStream outputStream =null;
        OutputStreamWriter outputStreamWriter = null;
        InputStreamReader inputStreamReader = null;
        char[] dates=null;
        try {
            inputStream = client.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
//            outputStream = client.getOutputStream();
//            bufferedOutputStream =new BufferedOutputStream(outputStream);
            dates= new char[1024*512];
            inputStreamReader.read(dates);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap<String,String> mapRbody = null;
        mapRbody= toHashMap(new String(dates));
        // ****************************************************
        switch (mapRbody.get("code")){
            case PULL_TASK:
                String s = TaskMgr.getTask();
                terminal.defaultAddInTerminalData(terminal.small(mapRbody.get("id")+"拉取:"+terminal.blue(s)),Terminal.TCP_GAME);
                try {
                    outputStream = client.getOutputStream();
                    outputStreamWriter = new OutputStreamWriter(outputStream,"utf-8");
                    outputStreamWriter.write(s);
                    outputStreamWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        inputStreamReader.close();
                        inputStream.close();
                        outputStreamWriter.close();
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

//                System.out.println(s);
//                System.out.println("###########################################################");
                break;
            case PUSH_TASK:
                break;
        }
//        try {
//            client.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    private HashMap<String,String> toHashMap(String rbody){
        String kd[] = rbody.split("&");
//        terminal.defaultAddInTerminalData(String.valueOf(kd.length),Terminal.TCP_GAME);
//        terminal.defaultAddInTerminalData(rbody,Terminal.TCP_GAME);
        HashMap<String,String> map=new HashMap<>();
        for(short i =0;i<kd.length;i++){
            String k_v[] = kd[i].split(":");
//            terminal.defaultAddInTerminalData(k_v[0]+k_v[1],Terminal.TCP_GAME);
            map.put(k_v[0],k_v[1]);
        }
        return map;
    }
}
