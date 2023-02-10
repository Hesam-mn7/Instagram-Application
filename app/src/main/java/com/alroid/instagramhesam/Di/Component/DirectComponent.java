package com.alroid.instagramhesam.Di.Component;

import com.alroid.instagramhesam.Di.Module.DirectModule;
import com.alroid.instagramhesam.Model.Room.Entity.Direct;

import dagger.Component;

@Component(modules = DirectModule.class)
public interface DirectComponent {
    Direct getDirects();
}
