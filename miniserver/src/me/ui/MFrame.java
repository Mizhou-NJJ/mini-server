package me.ui;

import me.m.mswing.style.Style;
import me.m.mswing.widget.button.MJButton;
import me.m.mswing.widget.loading.*;
import me.m.mswing.widget.panel.LoadingPanel;
import me.util.Font;
import me.util.Source;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MFrame extends JFrame {
    private Container container;
    private int width;
    private int height;

    public MFrame(String title,int width,int height){
        super(title);
        setSize(width,height);
        this.width=width;
        this.height=height;
        setIconImage(new ImageIcon("res/server_32.png").getImage());
//        setResizable(false);
        init();
    }
    private void init(){
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       container=getContentPane();
       container.setBackground(Color.WHITE);
        LoadingPanel loadingPanel=new LoadingPanel();
        EasyShadowConnecting shadowConnecting=new EasyShadowConnecting();
        loadingPanel.setBackground(Style.MColor.PETER_RIVER);
//        loadingPanel.setBounds(0,0,420,450);
        loadingPanel.setLoadingStyle(shadowConnecting);
        loadingPanel.loading();
        container.add(loadingPanel);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Font.loadIndyFont();
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                container.remove(loadingPanel);
                MainPanel.init(width,height,container,MFrame.this);
                container.add(MainPanel.getInstance());
                setVisible(true);
            }
        }).start();
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        this.width=width;
        this.height=height;
    }

}
