package com.alroid.instagramhesam.Exception.Helper;

import com.alroid.instagramhesam.Di.Component.DaggerPostComponent;
import com.alroid.instagramhesam.Model.Room.Entity.Post;
import com.alroid.instagramhesam.Model.Room.Entity.User;

public class SelectImagePostHelper {
    public boolean register(String postPic){
        //Post post = new Post();
        Post post = DaggerPostComponent.create().getPosts();

        post.setImgPost(postPic);
        return true;
    }
}
