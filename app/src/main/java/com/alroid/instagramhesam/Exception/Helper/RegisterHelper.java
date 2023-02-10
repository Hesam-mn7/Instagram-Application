package com.alroid.instagramhesam.Exception.Helper;

import com.alroid.instagramhesam.Di.Component.DaggerUserComponent;
import com.alroid.instagramhesam.Model.Room.Entity.User;

public class RegisterHelper {
    public boolean register(String name ,String bio , String profilePic){

        User user = DaggerUserComponent.create().getUsers();
        //User user = new User();
        user.setName(name);
        user.setBio(bio);
        user.setProfilePic(profilePic);
        return true;
    }
}
