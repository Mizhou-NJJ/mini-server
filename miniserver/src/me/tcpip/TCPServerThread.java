package me.tcpip;

import me.dao.SaveAsRAM;
import me.orange.serobj.RequestBody;
import me.orange.serobj.ResponseBody;
import me.orange.serobj.User;
import me.ui.Terminal;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class TCPServerThread implements Runnable{
    private Terminal terminal=Terminal.getInstance();
    private Socket socket;
    private InputStream inputStream;
    private ObjectInputStream objectInputStream;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;
    private SaveAsRAM saveAsRAM=SaveAsRAM.getInstance();
    public TCPServerThread(Socket socket){
        this.socket=socket;
    }
    @Override
    public void run() {
        try {
            inputStream=socket.getInputStream();
            outputStream=socket.getOutputStream();
           objectInputStream=new ObjectInputStream(inputStream);
           objectOutputStream =new ObjectOutputStream(outputStream);
            RequestBody requestBody = (RequestBody) objectInputStream.readObject();
            terminal.defaultAddInTerminalData(terminal.small(terminal.blue("来自"+socket.getInetAddress().toString()+"的请求体:"+requestBody.toString())),Terminal.TCP_CHAT);
            ResponseBody responseBody=null;
            if (requestBody.getType()==RequestBody.REGISTER){
                // 注册
                User user= (User) requestBody.getObject();
                if (saveAsRAM.add(user)){
                   // 如果存储成功
                    responseBody=new ResponseBody(null,"注册成功!",ResponseBody.OK);
                }else{
                    responseBody=new ResponseBody(null,"注册失败!",ResponseBody.ERROR);
                }
                // 告诉客户端
                objectOutputStream.writeObject(responseBody);

            }else if(requestBody.getType()==RequestBody.LOGIN){ // 登陆
                // 获取账号和密码
                User u= (User) requestBody.getObject();
                if (saveAsRAM.checkLogin(u)){ // 登陆成功
                    User uu=saveAsRAM.get(u.getEmail());
                    String ip=socket.getInetAddress().getHostAddress();
                    System.out.println(ip);
                    uu.setIp(ip);
                    System.out.println("设置后");
                    System.out.println(saveAsRAM.get(u.getEmail()).getIp());
                    //设置ip
                    responseBody=new ResponseBody(uu,"登陆成功!",ResponseBody.OK);
                   objectOutputStream.writeObject(responseBody);
                }else { // 失败
                    responseBody=new ResponseBody(null,"账号或密码错误!",ResponseBody.NO_DATA);
                   objectOutputStream.writeObject(responseBody);
                }
            }else if(requestBody.getType()==RequestBody.PULL_FRIENDS){ //拉去朋友信息
               User ru= (User) requestBody.getObject();
               User user=saveAsRAM.get(ru.getEmail());
               responseBody=new ResponseBody(user,"",ResponseBody.OK);
               objectOutputStream.writeObject(responseBody);
            }else if(requestBody.getType()==RequestBody.PULL_IP){ // 拉去IP信息
                String rm= (String) requestBody.getObject();
                String ip=saveAsRAM.get(rm).getIp();
                responseBody=new ResponseBody(ip,"拉取成功",ResponseBody.OK);
                objectOutputStream.writeObject(responseBody);
            }else  if (requestBody.getType()==RequestBody.FOUND_FRIEND){ // 找盆友
                String rm= (String) requestBody.getObject();
                User u=saveAsRAM.get(rm);
                responseBody=new ResponseBody(u,"成功",ResponseBody.OK);
                objectOutputStream.writeObject(responseBody);
            }else if(requestBody.getType()==RequestBody.ADD_FRIEND){ // 添加盆友
                String s= (String) requestBody.getObject();
                String ms[]=s.split(":");
                User a=saveAsRAM.get(ms[0]);
                User b=saveAsRAM.get(ms[1]);
                User newb=new User();
                newb.setIp(b.getIp());
                newb.setEmail(b.getEmail());
                newb.setNick(b.getNick());
                newb.setIsVIP(b.getIsVIP());
                newb.setImgUrl(b.getImgUrl());
                if (a.getFriends().add(newb)) {
                    responseBody = new ResponseBody(null, "添加成功", ResponseBody.OK);
                }else {
                    responseBody=new ResponseBody(null,"添加失败",ResponseBody.ERROR);
                }
                objectOutputStream.writeObject(responseBody);
            }else if(requestBody.getType()==RequestBody.PULL_ALL){ //           拉去所有人信息
                String me= (String) requestBody.getObject();
                ArrayList<User> users=saveAsRAM.getAllUser(me);
                responseBody=new ResponseBody(users,"拉取成功",ResponseBody.OK);
                objectOutputStream.writeObject(responseBody);
            }
            assert responseBody != null;
            terminal.defaultAddInTerminalData(terminal.small(terminal.green("响应体:"+responseBody.toString())),Terminal.TCP_CHAT);
            // 关闭流
            objectOutputStream.close();
            outputStream.close();
            inputStream.close();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
