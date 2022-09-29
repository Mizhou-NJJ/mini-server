package me.ui;

import java.awt.*;

/**
 *  承载消息
 */
public class PublicPanelInfo {
    private String title;
    private int defPort;
    private String btnLaunchName;
    private int frameWidth;
    private int frameHeight;
    private Component root;
    private Container container;
    private int type;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDefPort() {
        return defPort;
    }

    public void setDefPort(int defPort) {
        this.defPort = defPort;
    }

    public String getBtnLaunchName() {
        return btnLaunchName;
    }

    public void setBtnLaunchName(String btnLaunchName) {
        this.btnLaunchName = btnLaunchName;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public Component getRoot() {
        return root;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public void setRoot(Component root) {
        this.root = root;
    }

    public Container getContainer() {
        return container;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
