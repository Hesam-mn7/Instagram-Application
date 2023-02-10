package com.alroid.instagramhesam.Di.Component;

import com.alroid.instagramhesam.Di.Module.PosttModule;
import com.alroid.instagramhesam.Model.Room.Entity.Post;

import dagger.Component;

@Component(modules = PosttModule.class)
public interface PosttComponent {
    Post getPostt();
}
