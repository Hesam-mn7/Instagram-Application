package com.alroid.instagramhesam.Di.Module;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.alroid.instagramhesam.Model.Room.Entity.Igtv;

import dagger.Module;
import dagger.Provides;

@Module
public class IgtvModule {

    private int id;
    private String usernameIgtv;
    private String captionIgtv;
    private String imgProfileIgtv;
    private String imgIgtv;
    private String videoUrlIgtv;

    public IgtvModule(int id, String usernameIgtv, String captionIgtv, String imgProfileIgtv, String imgIgtv, String videoUrlIgtv) {
        this.id = id;
        this.usernameIgtv = usernameIgtv;
        this.captionIgtv = captionIgtv;
        this.imgProfileIgtv = imgProfileIgtv;
        this.imgIgtv = imgIgtv;
        this.videoUrlIgtv = videoUrlIgtv;
    }

    @Provides
    public Igtv provideIgtv(){
        return new Igtv(id,usernameIgtv,captionIgtv,imgProfileIgtv,imgIgtv,videoUrlIgtv);
    }
}
