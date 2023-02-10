package com.alroid.instagramhesam.Model.Room.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.alroid.instagramhesam.Exception.Exceptions;

@Entity
public class Post {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String UsernamePost;

    @ColumnInfo
    private String imgProfilePost;

    @ColumnInfo
    private String imgPost;

    @ColumnInfo
    private String imgProfileLike;

    @ColumnInfo
    private String usernameLike;

    @ColumnInfo
    private String numberLike;

    @ColumnInfo
    private String caption;

    @ColumnInfo
    private String numberComments;

    @ColumnInfo
    private String timePost;

    @ColumnInfo
    private String cheekLike;

    @ColumnInfo
    private String cheekSaved;

    @ColumnInfo
    private String cheekMyPost;

    public Post(){

    }

    public Post(int id, String usernamePost, String imgProfilePost, String imgPost, String imgProfileLike
            , String usernameLike, String numberLike, String caption, String numberComments, String timePost
            , String cheekLike, String cheekSaved, String cheekMyPost) {
        this.id = id;
        UsernamePost = usernamePost;
        this.imgProfilePost = imgProfilePost;
        this.imgPost = imgPost;
        this.imgProfileLike = imgProfileLike;
        this.usernameLike = usernameLike;
        this.numberLike = numberLike;
        this.caption = caption;
        this.numberComments = numberComments;
        this.timePost = timePost;
        this.cheekLike = cheekLike;
        this.cheekSaved = cheekSaved;
        this.cheekMyPost = cheekMyPost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsernamePost() {
        return UsernamePost;
    }

    public void setUsernamePost(String usernamePost) {
        UsernamePost = usernamePost;
    }

    public String getImgProfilePost() {
        return imgProfilePost;
    }

    public void setImgProfilePost(String imgProfilePost) {
        this.imgProfilePost = imgProfilePost;
    }

    public String getImgPost() {
        return imgPost;
    }

    public void setImgPost(String imgPost) {
        if (imgPost.isEmpty())
            throw new Exceptions.NoPicException();
        this.imgPost = imgPost;
    }

    public String getImgProfileLike() {
        return imgProfileLike;
    }

    public void setImgProfileLike(String imgProfileLike) {
        this.imgProfileLike = imgProfileLike;
    }

    public String getUsernameLike() {
        return usernameLike;
    }

    public void setUsernameLike(String usernameLike) {
        this.usernameLike = usernameLike;
    }

    public String getNumberLike() {
        return numberLike;
    }

    public void setNumberLike(String numberLike) {
        this.numberLike = numberLike;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        if (caption.isEmpty())
            throw new Exceptions.CaptionLenghtException();
        this.caption = caption;
    }

    public String getNumberComments() {
        return numberComments;
    }

    public void setNumberComments(String numberComments) {
        this.numberComments = numberComments;
    }

    public String getTimePost() {
        return timePost;
    }

    public void setTimePost(String timePost) {
        this.timePost = timePost;
    }

    public String getCheekLike() {
        return cheekLike;
    }

    public void setCheekLike(String cheekLike) {
        this.cheekLike = cheekLike;
    }

    public String getCheekSaved() {
        return cheekSaved;
    }

    public void setCheekSaved(String cheekSaved) {
        this.cheekSaved = cheekSaved;
    }

    public String getCheekMyPost() {
        return cheekMyPost;
    }

    public void setCheekMyPost(String cheekMyPost) {
        this.cheekMyPost = cheekMyPost;
    }
}
