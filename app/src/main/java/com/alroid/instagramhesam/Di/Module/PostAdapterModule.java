package com.alroid.instagramhesam.Di.Module;

import android.content.Context;

import com.alroid.instagramhesam.Adapter.PostAdapter;
import com.alroid.instagramhesam.ViewModel.PostViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class PostAdapterModule {

    PostViewModel postViewModel;
    Context context;

    public PostAdapterModule(PostViewModel postViewModel, Context context) {
        this.postViewModel = postViewModel;
        this.context = context;
    }

    @Provides
    public PostAdapter providePostAdapter(){
        return new PostAdapter(postViewModel,context);
    }
}
