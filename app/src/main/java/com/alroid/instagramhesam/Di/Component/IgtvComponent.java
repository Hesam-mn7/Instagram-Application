package com.alroid.instagramhesam.Di.Component;

import com.alroid.instagramhesam.Di.Module.IgtvModule;
import com.alroid.instagramhesam.Model.Room.Entity.Igtv;

import dagger.Component;

@Component(modules = IgtvModule.class)
public interface IgtvComponent {
    Igtv getIgtvs();
}
