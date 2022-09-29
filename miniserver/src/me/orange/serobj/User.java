package me.orange.serobj;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;

public class User implements Serializable {
    private static final long serialVersionUID = 57204390;
    private String nick;
    private String password;
    private String isVIP;
    private String email;
    private String ip;
    private String imgUrl;
    private ArrayList<User> friends;
    public User(){

    }
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsVIP() {
        return isVIP;
    }

    public void setIsVIP(String isVIP) {
        this.isVIP = isVIP;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public ArrayList<User> getFriends() {
        if (friends==null) friends=new ArrayList<>();
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }
    public void addFriend(User u){
        if (friends==null) friends=new ArrayList<>();
        friends.add(u);
    }

    @Override
    public String toString() {
        return "User{" +
                "nick='" + nick + '\'' +
                ", password='" + password + '\'' +
                ", isVIP='" + isVIP + '\'' +
                ", email='" + email + '\'' +
                ", ip=" + ip +
                ", imgUrl='" + imgUrl + '\'' +
                ", friends=" + friends +
                '}';
    }
}
