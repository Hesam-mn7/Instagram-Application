package com.alroid.instagramhesam.Model.Room.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Igtv {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String usernameIgtv;

    @ColumnInfo
    private String captionIgtv;

    @ColumnInfo
    private String imgProfileIgtv;

    @ColumnInfo
    private String imgIgtv;

    @ColumnInfo
    private String videoUrlIgtv;

    public Igtv() {

    }

    @Ignore
    public Igtv(int id, String usernameIgtv, String captionIgtv
            , String imgProfileIgtv, String imgIgtv, String videoUrlIgtv) {
        this.id = id;
        this.usernameIgtv = usernameIgtv;
        this.captionIgtv = captionIgtv;
        this.imgProfileIgtv = imgProfileIgtv;
        this.imgIgtv = imgIgtv;
        this.videoUrlIgtv = videoUrlIgtv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsernameIgtv() {
        return usernameIgtv;
    }

    public void setUsernameIgtv(String usernameIgtv) {
        this.usernameIgtv = usernameIgtv;
    }

    public String getCaptionIgtv() {
        return captionIgtv;
    }

    public void setCaptionIgtv(String captionIgtv) {
        this.captionIgtv = captionIgtv;
    }

    public String getImgProfileIgtv() {
        return imgProfileIgtv;
    }

    public void setImgProfileIgtv(String imgProfileIgtv) {
        this.imgProfileIgtv = imgProfileIgtv;
    }

    public String getImgIgtv() {
        return imgIgtv;
    }

    public void setImgIgtv(String imgIgtv) {
        this.imgIgtv = imgIgtv;
    }

    public String getVideoUrlIgtv() {
        return videoUrlIgtv;
    }

    public void setVideoUrlIgtv(String videoUrlIgtv) {
        this.videoUrlIgtv = videoUrlIgtv;
    }
}
