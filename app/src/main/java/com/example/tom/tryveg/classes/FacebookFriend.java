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
}
