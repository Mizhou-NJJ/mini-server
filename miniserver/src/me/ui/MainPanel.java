package me.ui;

import me.m.mswing.style.Style;
import me.m.mswing.widget.button.MJButton;
import me.util.Source;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {
    private MJButton btnTcpChat;
    private MJButton btnUdpChat;
    private MJButton btnTcpGame;
    private JPanel main;
    private int width;
    private int height;
    private Container container;
    private Frame frame;
    private static MainPanel mainPanel;
    private int px,py;
    public static MainPanel getInstance(){
        return mainPanel;
    }
    static {
    }
    public static void init(int width,int height,Container c,Frame frame){
        mainPanel=new MainPanel(width,height,c,frame);
    }
    private MainPanel(int w,int h,Container c,Frame f){
        this.width=w;
        this.height=h;
        this.container=c;
        this.frame=f;
        setLayout(null);
        addMainPanel();
    }
    private void addMainPanel(){
        setBackground(Style.MColor.WHITE);
        JLabel labelTitle=new JLabel("A-B-C-D",JLabel.CENTER);
        labelTitle.setFont(Source.jfOpenTTF().deriveFont(20f));
        labelTitle.setForeground(Style.MColor.PETER_RIVER);
        labelTitle.setBounds(0,py,width,100);
        py+=100;
        //-------------------button---------------
        JPanel panelBtn=new JPanel();
        panelBtn.setLayout(null);
        panelBtn.setBackground(Style.MColor.WHITE);
        panelBtn.setBounds(0,py,width,250); // 设置按钮面板的宽高位置
//        panelBtn.setBackground(Style.MColor.FUEL_TOWN);
        btnTcpChat=new MJButton("TCP-CHAT-S",panelBtn);
        btnTcpChat.setBorderRadius(100);
        btnTcpChat.setForeground(Style.MColor.WHITE);
        btnTcpChat.transition(Style.MColor.PETER_RIVER, Style.MColor.WHITE);
        btnTcpChat.setBounds(148,0,100,100);
        //btnUDPChat
        btnUdpChat=new MJButton("UDP-CHAT-S",panelBtn);
        btnUdpChat.setBorderRadius(100);
        btnUdpChat.setForeground(Style.MColor.WHITE);
        btnUdpChat.transition(Style.MColor.PETER_RIVER, Style.MColor.WHITE);
        btnTcpGame=new MJButton("SPIDER-S",panelBtn);
        btnUdpChat.setBounds(210,100,100,100);
        btnTcpGame.setBounds(90,100,100,100);
        btnTcpGame.setBorderRadius(100);
        btnTcpGame.transition(Style.MColor.PETER_RIVER, Style.MColor.WHITE);
        btnTcpGame.setForeground(Style.MColor.WHITE);

        panelBtn.add(btnTcpChat);
        panelBtn.add(btnUdpChat);
        panelBtn.add(btnTcpGame);
        //------------------------------------------------------
        add(panelBtn);
        add(labelTitle);
//        container.add(main);
        setVisible(true);
        addAction();
    }
    private void addAction(){
        btnUdpChat.addActionListener(new UDPChatAction());
        btnTcpChat.addActionListener(new TCPChatAction());
        btnTcpGame.addActionListener(new TCPGameAction());
    }

    class TCPChatAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            PublicPanel pp=null;
            PublicPanelInfo info=new PublicPanelInfo();
            info.setTitle("TCP CHAT SERVER");
            info.setFrameWidth(width);
            info.setFrameHeight(height);
            info.setRoot(frame);
            info.setType(PublicPanel.TCP_CHAT);
            info.setContainer(container);
            info.setDefPort(8888);
            if (PublicPanel.getInstance()==null) {
                pp=PublicPanel.in(info);
            }else {
                pp=PublicPanel.getInstance();
            }
            pp.reload(info);
            container.remove(mainPanel);
            container.add(pp);
            container.repaint();
            frame.setVisible(true);
        }
    }
    class UDPChatAction implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
    class TCPGameAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            PublicPanel pp=null;
            PublicPanelInfo info=new PublicPanelInfo();
            info.setTitle("SPIDER-SERVER");
            info.setFrameWidth(width);
            info.setFrameHeight(height);
            info.setRoot(frame);
            info.setType(PublicPanel.TCP_GAME);
            info.setContainer(container);
            info.setDefPort(9999);
            if (PublicPanel.getInstance()==null) {
                pp=PublicPanel.in(info);
            }else {
                pp=PublicPanel.getInstance();
            }
            pp.reload(info);
            container.remove(mainPanel);
            container.add(pp);
            container.repaint();
            frame.setVisible(true);
        }
    }
}
