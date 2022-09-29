package me.ui;

import me.bs.d.TaskMgr;
import me.bs.d.W;
import me.bs.server.Server;
import me.dao.SaveAsRAM;
import me.m.mswing.style.Style;
import me.m.mswing.widget.button.MJButton;
import me.m.mswing.widget.jtext.MJTextField;
import me.tcpip.TCPServer;
import me.util.Config;
import me.util.Source;
import org.omg.CORBA.PUBLIC_MEMBER;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PublicPanel extends JPanel {
    public final static int TCP_CHAT=1;
    public final static int TCP_GAME=2;
    public final static int UDP_CHAT=3;
    private TCPServer tcpServer;
    private PublicPanelInfo info;
    private MJTextField fieldPort;
    private MJTextField logPathField;
    private MJTextField jwA;
    private MJButton btnStart;
    private MJButton back;
    private static String areaTCP;
    private Terminal terminal;
    private JPanel terminalPanel;
    private static PublicPanel publicPanel;
    private static boolean isTCPChatStart=false; // 服务器是否以启动
    private static boolean isTCPGameStart=false;
    private static boolean isUDPChatStart=false;
    private static boolean isTCPChatStarting=false; // 服务器是否在启动中
    private static boolean isTCPGameStarting=false;
    private static boolean isUDPChatStarting=false;
    private static int whatServer;
    private JLabel labelTitle;
    private MJButton clearTerminal;
    private boolean isTerminalShow;
    int x=0;
    int y=0;
    private PublicPanel(PublicPanelInfo info){
        super();
        this.info=info;
        init();
    }
    static {

    }
    public static PublicPanel in(PublicPanelInfo info){
       publicPanel=new PublicPanel(info);
       return publicPanel;
    }
    public static PublicPanel getInstance(){
        return publicPanel;
    }
    private void init(){

//        setBackground(Style.MColor.BRIGHT_LILAC);
        setBackground(Style.MColor.WHITE);
        setLayout(null);
        labelTitle=new JLabel(info.getTitle(),JLabel.CENTER);
        labelTitle.setFont(Source.jfOpenTTF().deriveFont(20f));
        labelTitle.setBounds(x,30,info.getFrameWidth(),70);
        y+=100;
        labelTitle.setForeground(Style.MColor.PETER_RIVER);
        add(labelTitle);
        //-----------------------------
        JPanel panelEditor=new JPanel();
        panelEditor.setBackground(Style.MColor.WHITE);
        panelEditor.setLayout(null);
        panelEditor.setBounds(0,100,info.getFrameWidth(),110);
        y+=110;
        JLabel labelPort=new JLabel("PORT:",JLabel.CENTER);
        labelPort.setBounds(100,0,80,30);
        fieldPort=new MJTextField();
        fieldPort.setBounds(180,0,100,30);
        fieldPort.setRadius(10);
        //---log field
        JLabel labelLog=new JLabel("LOG-PATH:",JLabel.CENTER);
        labelLog.setBounds(100,40,80,30);
        logPathField =new MJTextField();
        logPathField.setRadius(10);
        logPathField.setBounds(180,40,100,30);
        JLabel labelJwA=new JLabel("API-A:",JLabel.CENTER);
        labelJwA.setBounds(100,80,80,30);
        jwA=new MJTextField();
        jwA.setRadius(10);
        jwA.setBounds(180,80,100,30);
        panelEditor.add(labelPort);
        panelEditor.add(fieldPort);
        panelEditor.add(labelLog);
        panelEditor.add(logPathField);
        panelEditor.add(labelJwA);
        panelEditor.add(jwA);
        add(panelEditor);
        //--------start btn
        btnStart=new MJButton("START",this);
        btnStart.setForeground(Style.MColor.WHITE);
        btnStart.setBackground(Style.MColor.BLUE_MARTINA);
        btnStart.setBounds(120,y+20,180,30);
        btnStart.transition(Style.MColor.PETER_RIVER, Style.MColor.WHITE);
        btnStart.setBorderRadius(10);
        btnStart.addActionListener(new StartServerAction());
//        btnStart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        y+=20;
        y+=30;
        add(btnStart);
        //---------back btn
        back=new MJButton("BACK",this);
        back.setFont(Source.jfOpenTTF().deriveFont(10f));
        back.setBounds(1,0,35,20);
        back.setBorderRadius(4);
        back.setBorderBold(1f);
        back.setBorderColor(Style.MColor.PETER_RIVER);
        back.setForeground(Style.MColor.PETER_RIVER);
        back.setBackground(Style.MColor.WHITE);
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                info.getContainer().remove(PublicPanel.this);
                info.getContainer().add(MainPanel.getInstance());
                info.getContainer().repaint();
                info.getRoot().repaint();
                info.getRoot().setVisible(true);
            }
        });
//        back.transition(Style.MColor.PETER_RIVER, Style.MColor.WHITE);
//        back.setBorderRadius(30);
        add(back);
        // ------------------Terminal
        terminalPanel=new JPanel();
        terminalPanel.setLayout(null);
        terminalPanel.setBorder(BorderFactory.createLineBorder(Style.MColor.PETER_RIVER));
        terminalPanel.setBackground(Style.MColor.WHITE);
        terminalPanel.setBounds(0,y+30+200,info.getFrameWidth()-16,info.getFrameHeight()-y-70);
//        terminalPanel.setBackground(Style.MColor.FUEL_TOWN);
//        MJButton clearTerminal=new MJButton("I",terminalPanel);
//        clearTerminal.setForeground(Style.MColor.WHITE);
//        clearTerminal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        clearTerminal.setBounds(info.getFrameWidth()-38,0,20,20);
        JLabel terminalLabel=new JLabel("<html><b>Terminal</b></html>",JLabel.LEFT);
        terminalLabel.setBounds(0,0,100,20);
        terminalLabel.setForeground(Style.MColor.PETER_RIVER);
//        terminalPanel.add(clearTerminal);
        terminalPanel.add(terminalLabel);
        terminal=Terminal.getInstance();
        JScrollPane scrollPane=new JScrollPane(terminal,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0,20,info.getFrameWidth()-17,info.getFrameHeight()-y-91);
        scrollPane.setBackground(Style.MColor.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUI(new NewScrollBarUI());
        Terminal.scrollPane=scrollPane;
        // add to terminalPanel
        terminalPanel.add(scrollPane);
        add(terminalPanel);
    }
    private void terminalUp(){
        if (!isTerminalShow) {
            int py = y + 30;
            int w = info.getFrameWidth() - 16;
            int h = info.getFrameHeight() - y - 70;
            int ph = y + 230;
            while (py < ph) {
                terminalPanel.setBounds(0, ph -= 5, w, h);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            clearTerminal = new MJButton("I", terminalPanel);
            clearTerminal.setForeground(Style.MColor.WHITE);
            clearTerminal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            clearTerminal.setBounds(info.getFrameWidth() - 38, 0, 20, 20);
            clearTerminal.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Terminal.isDebug(!Terminal.getIsDebg());
                    terminal.clear();
                }
            });
            terminalPanel.add(clearTerminal);
            terminalPanel.repaint();
            isTerminalShow=true;
        }
    }
   private void terminalDown(){
        if (isTerminalShow) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int py = y + 30;
                    int w = info.getFrameWidth() - 16;
                    int h = info.getFrameHeight() - y - 70;
                    int ph = y + 230;
                    while (py < ph) {
                        terminalPanel.setBounds(0, py += 5, w, h);
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // 移除按钮
                    if (clearTerminal != null)
                        terminalPanel.remove(clearTerminal);
                }
            }).start();
            isTerminalShow=false;
        }
    }
    public void reload(PublicPanelInfo i){
        // 重新加载标题
        labelTitle.setText(i.getTitle());
        btnStart.setText("START");
        fieldPort.setText(String.valueOf(i.getDefPort()));
        switch (i.getType()){
            case PublicPanel.TCP_CHAT:
                // 设置终端信息
                whatServer=TCP_CHAT;
//                terminal.defaultAddInTerminalData(terminal.getTcpChatData().toString(),Terminal.TCP_CHAT);
                if (isTCPChatStart){// 服务器是否已经启动
                    btnStart.setText("STOP");
                }else if (isTCPChatStarting){ //如果服务正在启动中
                    btnStart.setText("STARTING...");
                }else {
                    terminalDown();
                }
                break;
            case PublicPanel.TCP_GAME:
                whatServer=TCP_GAME;
//                terminal.defaultAddInTerminalData(terminal.getTcpGameData(),Terminal.TCP_GAME);
                if (isTCPGameStart){
                    btnStart.setText("STOP");
                }else if (isTCPGameStarting){
                    btnStart.setText("STARTING...");
                }else { // 服务未启动
                    terminalDown();
                }
                break;
            case PublicPanel.UDP_CHAT:
                whatServer=UDP_CHAT;
//                terminal.defaultAddInTerminalData(terminal.getUdpChatData(),Terminal.UDP_CHAT);
                if (isUDPChatStart){
                    btnStart.setText("STOP");
                }else if(isUDPChatStarting){
                    btnStart.setText("STARTING...");
                }else{
                    terminalDown();
                }
               break;
        }
    }
    class StartServerAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //开启服务启用线程
           new Thread(new Runnable() {
               // 首先上移动面板
               @Override
               public void run() {
                   switch (whatServer){
                       case TCP_CHAT:
                           // 更改按钮文字
                           if (isTCPChatStart){
                               btnStart.setText("STOPPING...");
                               terminal.defaultAddInTerminalData("Stopping the server...",Terminal.TCP_CHAT);
                               if (tcpServer.closeServer()){
                                   terminal.defaultAddInTerminalData(terminal.green("The server has stopped"),Terminal.TCP_CHAT);
                                   btnStart.setText("START");
                                   isTCPChatStart=false;
                                   try {
                                       Thread.sleep(2000);
                                   } catch (InterruptedException ex) {
                                       ex.printStackTrace();
                                   }
//                                   terminalDown();
                               }else{
                                   terminal.defaultAddInTerminalData(terminal.red("Server shutdown failed"),Terminal.TCP_CHAT);
                                   terminal.defaultAddInTerminalData(terminal.orange("正在序列化数据到"+ Config.SER_PATH+"..."),Terminal.TCP_CHAT);
                                   // 序列化
                                   terminal.defaultAddInTerminalData(terminal.orange("共 "+ SaveAsRAM.getInstance().getMapSize()+" 条数据"),Terminal.TCP_CHAT);
                                   SaveAsRAM.getInstance().saveAsFile();
                                   terminal.defaultAddInTerminalData(terminal.green("序列化完成,可正常关闭程序..."),Terminal.TCP_CHAT);
                                   try {
                                       Thread.sleep(3000);
//                                       System.exit(0);
                                   } catch (InterruptedException ex) {
                                       ex.printStackTrace();
                                   }
                               }
                               repaint();
                           }else if(isTCPChatStarting){
                               return;
                           }else { // 服务未启动
                               terminalUp();
                               btnStart.setText("STARTING...");
//                               isTCPChatStarting=true;
                               terminal.defaultAddInTerminalData("Starting the server...",Terminal.TCP_CHAT);
                               int port= Integer.parseInt(fieldPort.getText());
                               tcpServer=new TCPServer(port);
                               if (tcpServer.startServer()){
                                   terminal.defaultAddInTerminalData(terminal.green("Server started successfully"),Terminal.TCP_CHAT);
                                   btnStart.setText("STOP");
                                   isTCPChatStart=true;
//                                   isUDPChatStarting=false;
                               }else {
                                   terminal.defaultAddInTerminalData("Server startup failure",Terminal.TCP_CHAT);
                                   btnStart.setText("START");
                               }
                               repaint();
                   }
                   break;
                       case TCP_GAME:
                           if (isTCPGameStart){ // 如果服务已经启动
                               btnStart.setText("STOP");
                               terminal.defaultAddInTerminalData(terminal.red("无法关闭服务器..."),Terminal.TCP_GAME);
                               terminal.defaultAddInTerminalData(terminal.orange("正在保存任务状态..."),Terminal.TCP_GAME);
                               terminal.defaultAddInTerminalData(terminal.green("任务状态保存成功! 5s后关闭..."),Terminal.TCP_GAME);
                               new Thread(new Runnable() {
                                   @Override
                                   public void run() {
                                       try {
                                           Thread.sleep(5000);
                                           System.exit(0);
                                       } catch (InterruptedException ex) {
                                           ex.printStackTrace();
                                       }
                                   }
                               }).start();
                               TaskMgr.saveStatus();
                           }else if(isTCPGameStarting){ // 服务正在启动
                               return;
                           }else { // 服务未启动
                               terminalUp();
                               btnStart.setText("STOP");
                               isTCPGameStarting=true;
                               isTCPGameStart = true;
                               terminal.defaultAddInTerminalData("Starting the server...",Terminal.TCP_GAME);
                               int port= Integer.parseInt(fieldPort.getText());
                               Server server =Server.getInstance();
                               server.startServer(port);
                           }
                           break;
                       case UDP_CHAT:
                           if (isUDPChatStart){
                               btnStart.setText("START");
                           }else if(isUDPChatStarting){
                               return;
                           }else { // 服务未启动
                               terminalUp();
                               btnStart.setText("STARTING...");
                               isUDPChatStarting=true;
                               terminal.defaultAddInTerminalData("Starting the server...",Terminal.TCP_CHAT);
                           }
                           break;
                   }

                  // 开启服务


               }
           }).start();
        }
    }
}
