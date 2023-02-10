package com.alroid.instagramhesam.Model.Room.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Direct {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String userNameDirect;

    @ColumnInfo
    private String imgProfileDirect;

    @ColumnInfo
    private String textDirect;

    public Direct(int id, String userNameDirect
            , String imgProfileDirect, String textDirect) {
        this.id = id;
        this.userNameDirect = userNameDirect;
        this.imgProfileDirect = imgProfileDirect;
        this.textDirect = textDirect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserNameDirect() {
        return userNameDirect;
    }

    public void setUserNameDirect(String userNameDirect) {
        this.userNameDirect = userNameDirect;
    }

    public String getImgProfileDirect() {
        return imgProfileDirect;
    }

    public void setImgProfileDirect(String imgProfileDirect) {
        this.imgProfileDirect = imgProfileDirect;
    }

    public String getTextDirect() {
        return textDirect;
    }

    public void setTextDirect(String textDirect) {
        this.textDirect = textDirect;
    }
}
