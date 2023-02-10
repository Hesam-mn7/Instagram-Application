package com.alroid.instagramhesam.Di.Component;

import com.alroid.instagramhesam.Di.Module.ProfilePostAdapterModule;
import com.alroid.instagramhesam.View.Fragment.ProfileFragment;

import dagger.Component;

@Component(modules = ProfilePostAdapterModule.class)
public interface ProfilePostAdapterComponent {
    void injectProfilePost(ProfileFragment profileFragment);
}
