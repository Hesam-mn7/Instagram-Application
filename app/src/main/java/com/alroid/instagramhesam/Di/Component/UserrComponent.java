package com.alroid.instagramhesam.Di.Component;

import com.alroid.instagramhesam.Di.Module.UserrModule;
import com.alroid.instagramhesam.Model.Room.Entity.User;

import dagger.Component;

@Component(modules = UserrModule.class)
public interface UserrComponent {
    User getUserees();
}
