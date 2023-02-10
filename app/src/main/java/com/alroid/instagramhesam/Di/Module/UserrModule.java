package com.alroid.instagramhesam.Di.Module;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.alroid.instagramhesam.Model.Room.Entity.User;

import dagger.Module;
import dagger.Provides;

@Module
public class UserrModule {
    private int id;
    private String userName;
    private String password;
    private String name;
    private String bio;
    private String profilePic;
    private String email;
    private String phoneNumber;

    public UserrModule(int id, String userName, String password, String name, String bio, String profilePic, String email, String phoneNumber) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.bio = bio;
        this.profilePic = profilePic;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Provides
    public User provideUserr(){
        return new User(id,userName,password,name,bio,profilePic,email,phoneNumber);
    }
}
