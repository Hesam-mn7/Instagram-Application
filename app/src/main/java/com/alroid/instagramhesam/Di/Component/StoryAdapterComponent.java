package com.alroid.instagramhesam.Di.Component;

import com.alroid.instagramhesam.Adapter.StoryAdapter;
import com.alroid.instagramhesam.Di.Module.StoryAdapterModule;
import com.alroid.instagramhesam.View.Fragment.HomeFragment;

import dagger.Component;

@Component(modules = StoryAdapterModule.class)
public interface StoryAdapterComponent {
    StoryAdapter getStoryAdapter();
}
