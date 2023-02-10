package com.alroid.instagramhesam.Di.Module;

import com.alroid.instagramhesam.Model.Room.Entity.Post;

import dagger.Module;
import dagger.Provides;

@Module
public class PostModule {

    @Provides
    public Post providePost(){
        return new Post();
    }
}
