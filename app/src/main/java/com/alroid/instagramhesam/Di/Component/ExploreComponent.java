package com.alroid.instagramhesam.Di.Component;

import com.alroid.instagramhesam.Di.Module.ExploreModule;
import com.alroid.instagramhesam.Model.Room.Entity.Explore;

import dagger.Component;

@Component(modules = ExploreModule.class)
public interface ExploreComponent {
    Explore getExplores();
}
