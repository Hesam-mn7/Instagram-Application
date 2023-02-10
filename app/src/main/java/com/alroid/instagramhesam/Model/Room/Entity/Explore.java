package com.alroid.instagramhesam.Model.Room.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Explore {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String imgExplore1;

    @ColumnInfo
    private String usernameExplore1;

    @ColumnInfo
    private String imgExplore2;

    @ColumnInfo
    private String usernameExplore2;

    @ColumnInfo
    private String imgExplore3;

    @ColumnInfo
    private String usernameExplore3;

    @ColumnInfo
    private String imgExplore4;

    @ColumnInfo
    private String usernameExplore4;

    @ColumnInfo
    private String imgExplore5;

    @ColumnInfo
    private String usernameExplore5;

    @ColumnInfo
    private String imgExplore6;

    @ColumnInfo
    private String usernameExplore6;

    @ColumnInfo
    private String imgExplore7;

    @ColumnInfo
    private String usernameExplore7;

    @ColumnInfo
    private String imgExplore8;

    @ColumnInfo
    private String usernameExplore8;

    @ColumnInfo
    private String imgExplore9;

    @ColumnInfo
    private String usernameExplore9;

    public Explore() {

    }

    @Ignore
    public Explore(int id, String imgExplore1, String usernameExplore1, String imgExplore2, String usernameExplore2, String imgExplore3,
                   String usernameExplore3, String imgExplore4, String usernameExplore4, String imgExplore5, String usernameExplore5,
                   String imgExplore6, String usernameExplore6, String imgExplore7, String usernameExplore7, String imgExplore8,
                   String usernameExplore8, String imgExplore9, String usernameExplore9) {
        this.id = id;
        this.imgExplore1 = imgExplore1;
        this.usernameExplore1 = usernameExplore1;
        this.imgExplore2 = imgExplore2;
        this.usernameExplore2 = usernameExplore2;
        this.imgExplore3 = imgExplore3;
        this.usernameExplore3 = usernameExplore3;
        this.imgExplore4 = imgExplore4;
        this.usernameExplore4 = usernameExplore4;
        this.imgExplore5 = imgExplore5;
        this.usernameExplore5 = usernameExplore5;
        this.imgExplore6 = imgExplore6;
        this.usernameExplore6 = usernameExplore6;
        this.imgExplore7 = imgExplore7;
        this.usernameExplore7 = usernameExplore7;
        this.imgExplore8 = imgExplore8;
        this.usernameExplore8 = usernameExplore8;
        this.imgExplore9 = imgExplore9;
        this.usernameExplore9 = usernameExplore9;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgExplore1() {
        return imgExplore1;
    }

    public void setImgExplore1(String imgExplore1) {
        this.imgExplore1 = imgExplore1;
    }

    public String getUsernameExplore1() {
        return usernameExplore1;
    }

    public void setUsernameExplore1(String usernameExplore1) {
        this.usernameExplore1 = usernameExplore1;
    }

    public String getImgExplore2() {
        return imgExplore2;
    }

    public void setImgExplore2(String imgExplore2) {
        this.imgExplore2 = imgExplore2;
    }

    public String getUsernameExplore2() {
        return usernameExplore2;
    }

    public void setUsernameExplore2(String usernameExplore2) {
        this.usernameExplore2 = usernameExplore2;
    }

    public String getImgExplore3() {
        return imgExplore3;
    }

    public void setImgExplore3(String imgExplore3) {
        this.imgExplore3 = imgExplore3;
    }

    public String getUsernameExplore3() {
        return usernameExplore3;
    }

    public void setUsernameExplore3(String usernameExplore3) {
        this.usernameExplore3 = usernameExplore3;
    }

    public String getImgExplore4() {
        return imgExplore4;
    }

    public void setImgExplore4(String imgExplore4) {
        this.imgExplore4 = imgExplore4;
    }

    public String getUsernameExplore4() {
        return usernameExplore4;
    }

    public void setUsernameExplore4(String usernameExplore4) {
        this.usernameExplore4 = usernameExplore4;
    }

    public String getImgExplore5() {
        return imgExplore5;
    }

    public void setImgExplore5(String imgExplore5) {
        this.imgExplore5 = imgExplore5;
    }

    public String getUsernameExplore5() {
        return usernameExplore5;
    }

    public void setUsernameExplore5(String usernameExplore5) {
        this.usernameExplore5 = usernameExplore5;
    }

    public String getImgExplore6() {
        return imgExplore6;
    }

    public void setImgExplore6(String imgExplore6) {
        this.imgExplore6 = imgExplore6;
    }

    public String getUsernameExplore6() {
        return usernameExplore6;
    }

    public void setUsernameExplore6(String usernameExplore6) {
        this.usernameExplore6 = usernameExplore6;
    }

    public String getImgExplore7() {
        return imgExplore7;
    }

    public void setImgExplore7(String imgExplore7) {
        this.imgExplore7 = imgExplore7;
    }

    public String getUsernameExplore7() {
        return usernameExplore7;
    }

    public void setUsernameExplore7(String usernameExplore7) {
        this.usernameExplore7 = usernameExplore7;
    }

    public String getImgExplore8() {
        return imgExplore8;
    }

    public void setImgExplore8(String imgExplore8) {
        this.imgExplore8 = imgExplore8;
    }

    public String getUsernameExplore8() {
        return usernameExplore8;
    }

    public void setUsernameExplore8(String usernameExplore8) {
        this.usernameExplore8 = usernameExplore8;
    }

    public String getImgExplore9() {
        return imgExplore9;
    }

    public void setImgExplore9(String imgExplore9) {
        this.imgExplore9 = imgExplore9;
    }

    public String getUsernameExplore9() {
        return usernameExplore9;
    }

    public void setUsernameExplore9(String usernameExplore9) {
        this.usernameExplore9 = usernameExplore9;
    }
}
