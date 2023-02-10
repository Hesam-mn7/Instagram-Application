package com.alroid.instagramhesam.Exception.Helper;

import com.alroid.instagramhesam.Di.Component.DaggerUserComponent;
import com.alroid.instagramhesam.Model.Room.Entity.User;

public class CreateAccountHelper {

    public boolean registerCreateAccount(String userName ,String password ,String phoneNumber ,String email){

        User user = DaggerUserComponent.create().getUsers();
        //User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        return true;
    }
}
