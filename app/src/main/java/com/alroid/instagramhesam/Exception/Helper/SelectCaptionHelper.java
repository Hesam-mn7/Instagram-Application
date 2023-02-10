package com.alroid.instagramhesam.Exception.Helper;

import com.alroid.instagramhesam.Di.Component.DaggerPostComponent;
import com.alroid.instagramhesam.Model.Room.Entity.Post;

public class SelectCaptionHelper {
    public boolean register(String caption){

        //Post post = new Post();
        Post post = DaggerPostComponent.create().getPosts();
        post.setCaption(caption);
        return true;
    }
}
