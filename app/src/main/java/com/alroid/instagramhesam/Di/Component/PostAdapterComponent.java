package com.alroid.instagramhesam.Di.Component;

import com.alroid.instagramhesam.Adapter.PostAdapter;
import com.alroid.instagramhesam.Di.Module.PostAdapterModule;
import com.alroid.instagramhesam.View.Fragment.HomeFragment;

import dagger.Component;

@Component(modules = PostAdapterModule.class)
public interface PostAdapterComponent {
    void inject1(HomeFragment homeFragment);
}
