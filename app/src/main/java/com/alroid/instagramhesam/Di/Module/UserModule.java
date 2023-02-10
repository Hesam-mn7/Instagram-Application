package com.alroid.instagramhesam.Di.Module;

import com.alroid.instagramhesam.Model.Room.Entity.User;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {

    @Provides
    public User provideUser(){
        return new User();
    }
}
