package com.example.tom.tryveg.classes;

/**
 * Created by tom on 29-Jul-16.
 */
public class FacebookFriend {
    public String Name;
    public String ThumbnailUrl;
    public boolean bIsVegeterian;
    public boolean bIsFollowed;
    public User User;
    public int FacebookID;

    public FacebookFriend(String name, String url, boolean bIsVegeterian, com.example.tom.tryveg.classes.User user) {
        Name = name;
        ThumbnailUrl = url;
        this.bIsVegeterian = bIsVegeterian;
        if (bIsVegeterian) {
            User = user;
        }
        else {
            this.User = null;
        }
    }

    public FacebookFriend(String name, String url, boolean bIsVegeterian) {
        Name = name;
        ThumbnailUrl = url;
        this.bIsVegeterian = bIsVegeterian;
    }

    public FacebookFriend(String s, String s1, boolean b, User user, int i) {
        this(s,s1,b,user);
        this.FacebookID = i;
    }

    public FacebookFriend(String name, String url, boolean bIsVegeterian, int i) {
        this(name,url,bIsVegeterian);
        this.FacebookID = i;
    }
}
