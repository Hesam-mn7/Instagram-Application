package com.alroid.instagramhesam.Model.Room.Entity;

import java.io.Serializable;

public class Message implements Serializable{
    private long id;
    private String content;
    private String imgProfileChatt;
    private boolean fromMe;

    public Message(long id, String content, String imgProfileChatt, boolean fromMe) {
        this.id = id;
        this.content = content;
        this.imgProfileChatt = imgProfileChatt;
        this.fromMe = fromMe;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgProfileChatt() {
        return imgProfileChatt;
    }

    public void setImgProfileChatt(String imgProfileChatt) {
        this.imgProfileChatt = imgProfileChatt;
    }

    public boolean isFromMe() {
        return fromMe;
    }

    public void setFromMe(boolean fromMe) {
        this.fromMe = fromMe;
    }
}