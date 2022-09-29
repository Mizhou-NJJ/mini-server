package me.ui;

import me.m.mswing.style.Style;
import org.omg.CORBA.PUBLIC_MEMBER;

import javax.swing.*;
import java.awt.*;

public class Terminal extends JEditorPane {
    public final static int TCP_CHAT=0;
    public final static int TCP_GAME=1;
    public final static int UDP_CHAT=2;
    private static boolean isDebug = true;
    public static JScrollPane scrollPane;
    private StringBuilder tcpChatData=new StringBuilder();
    private StringBuilder udpChatData=new StringBuilder();
    private StringBuilder tcpGameData=new StringBuilder();
    public final static String ROOT="<b>root$:</b>";
    private static Terminal terminal;
    private Terminal(){
        setEditable(false);
        init();
    }
    static {
        terminal=new Terminal();
    }
    public static Terminal getInstance(){
       return terminal;
    }
    private void init(){
        setContentType("text/html");
        setBackground(Style.MColor.WHITE);
    }
    public static void  isDebug(boolean isDebug){
        Terminal.isDebug = isDebug;
    }
    public static boolean getIsDebg(){
        return isDebug;
    }
    public String getTcpChatData(){
        return tcpChatData.toString();
    }
    public String getTcpGameData(){
        return tcpGameData.toString();
    }
    public String getUdpChatData(){
        return udpChatData.toString();
    }
    public synchronized void defaultAddInTerminalData(String s,int type){
        addInTermianl(s,"root$",type);
    }
    public void addInTermianl(String s,String root,int type){
        if (isDebug) {
            switch (type) {
                case TCP_CHAT:
                    tcpChatData.append(B(root + ":")).append(br(s));
                    super.setText("");
                    super.setText(tcpChatData.toString());
                    break;
                case TCP_GAME:
                    tcpGameData.append(B(root + ":")).append(br(s));
                    super.setText("");
                    super.setText(tcpGameData.toString());
                    break;
                case UDP_CHAT:
                    udpChatData.append(B(root + ":")).append(br(s));
                    super.setText("");
                    super.setText(udpChatData.toString());
                    break;
            }
        }
        JScrollBar bar=scrollPane.getHorizontalScrollBar();
        bar.setValue(bar.getMaximum());
    }
    public String green(String origin){
       StringBuilder b=new StringBuilder("<span style='color:green'>");
       b.append(origin);
       b.append("</span>");
       return b.toString();
    }
    public String B(String origin){
        StringBuilder b=new StringBuilder("<b>");
        b.append(origin);
        b.append("</b>");
        return b.toString();
    }
    public String I(String origin){
        StringBuilder b=new StringBuilder("<i>");
        b.append(origin);
        b.append("</i>");
        return b.toString();
    }
    public String red(String origin){
        StringBuilder b=new StringBuilder("<span style='color:red'>");
        b.append(origin);
        b.append("</span>");
        return  b.toString();
    }
    public String orange(String origin){
       StringBuilder b=new StringBuilder("<span style='color:orange'>");
       b.append(origin);
       b.append("</span>");
       return b.toString();
    }
    public String br(String s){
        return s+"<br>";
    }

    @Override
    public void setText(String t) {
    }
    public void clear(){
        super.setText(ROOT);
        tcpChatData=new StringBuilder();
        tcpGameData=new StringBuilder();
    }
    public String small(String t){
        return "<small>"+t+"</small>";
    }
    public String magenta(String origin){
        return "<span style='color:aqua'>"+origin+"</span>";
    }
    public String blue(String origin){
        return "<span style='color:blue'>"+origin+"</span>";
    }
}
