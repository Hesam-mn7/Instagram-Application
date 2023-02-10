package com.alroid.instagramhesam.Di.Module;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.alroid.instagramhesam.Model.Room.Entity.Explore;

import dagger.Module;
import dagger.Provides;

@Module
public class ExploreModule {

    private int id;
    private String imgExplore1;
    private String usernameExplore1;
    private String imgExplore2;
    private String usernameExplore2;
    private String imgExplore3;
    private String usernameExplore3;
    private String imgExplore4;
    private String usernameExplore4;
    private String imgExplore5;
    private String usernameExplore5;
    private String imgExplore6;
    private String usernameExplore6;
    private String imgExplore7;
    private String usernameExplore7;
    private String imgExplore8;
    private String usernameExplore8;
    private String imgExplore9;
    private String usernameExplore9;

    public ExploreModule(int id, String imgExplore1, String usernameExplore1, String imgExplore2,
                         String usernameExplore2, String imgExplore3, String usernameExplore3,
                         String imgExplore4, String usernameExplore4, String imgExplore5, String usernameExplore5
            , String imgExplore6, String usernameExplore6, String imgExplore7, String usernameExplore7
            , String imgExplore8, String usernameExplore8, String imgExplore9, String usernameExplore9) {

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

    @Provides
    public Explore provideExplore(){
        return new Explore(id,imgExplore1,usernameExplore1,imgExplore2,usernameExplore2
                ,imgExplore3,usernameExplore3,imgExplore4,usernameExplore4
        ,imgExplore5,usernameExplore5,imgExplore6,usernameExplore6,imgExplore7
                ,usernameExplore7,imgExplore8,usernameExplore8,imgExplore9,usernameExplore9);
    }
}
