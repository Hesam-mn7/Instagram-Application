package com.alroid.instagramhesam.Di.Component;

import com.alroid.instagramhesam.Di.Module.UserModule;
import com.alroid.instagramhesam.Model.Room.Entity.User;

import dagger.Component;

@Component(modules = UserModule.class)
public interface UserComponent {
    User getUsers();

}
