package com.alroid.instagramhesam.Di.Component;

import com.alroid.instagramhesam.Di.Module.StoryModule;
import com.alroid.instagramhesam.Model.Room.Entity.Story;

import dagger.Component;

@Component(modules = StoryModule.class)
public interface StoryComponent {
    Story getStoryy();
}
