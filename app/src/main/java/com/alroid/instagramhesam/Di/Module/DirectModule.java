package com.alroid.instagramhesam.Di.Module;

import com.alroid.instagramhesam.Model.Room.Entity.Direct;

import dagger.Module;
import dagger.Provides;

@Module
public class DirectModule {

    private int id;
    private String userNameDirect;
    private String imgProfileDirect;
    private String textDirect;

    public DirectModule(int id, String userNameDirect, String imgProfileDirect, String textDirect) {
        this.id = id;
        this.userNameDirect = userNameDirect;
        this.imgProfileDirect = imgProfileDirect;
        this.textDirect = textDirect;
    }

    @Provides
    public Direct provideDirect(){
        return new Direct(id,userNameDirect,imgProfileDirect,textDirect);
    }

}
