package com.alroid.instagramhesam.Di.Component;

import com.alroid.instagramhesam.Di.Module.PostModule;
import com.alroid.instagramhesam.Model.Room.Entity.Post;

import dagger.Component;
import dagger.Module;

@Component(modules = PostModule.class)
public interface PostComponent {
    Post getPosts();
}
