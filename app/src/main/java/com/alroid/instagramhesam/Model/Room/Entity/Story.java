package com.alroid.instagramhesam.Model.Room.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Story {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String userName;

    @ColumnInfo
    private String profilePic;

    @ColumnInfo
    private String time;

    @ColumnInfo
    private String picStory;

    @ColumnInfo
    private String seenStory;

    public Story(int id, String userName, String profilePic
            , String time, String picStory, String seenStory) {
        this.id = id;
        this.userName = userName;
        this.profilePic = profilePic;
        this.time = time;
        this.picStory = picStory;
        this.seenStory = seenStory;
    }

    @Ignore

    public Story() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPicStory() {
        return picStory;
    }

    public void setPicStory(String picStory) {
        this.picStory = picStory;
    }

    public String getSeenStory() {
        return seenStory;
    }

    public void setSeenStory(String seenStory) {
        this.seenStory = seenStory;
    }
}
