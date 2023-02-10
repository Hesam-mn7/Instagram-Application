package com.alroid.instagramhesam.Di.Component;

import com.alroid.instagramhesam.Di.Module.ExploreAdapterModule;
import com.alroid.instagramhesam.View.Fragment.ExploreFragment;

import dagger.Component;

@Component(modules = ExploreAdapterModule.class)
public interface ExploreAdapterComponent {
    void inject(ExploreFragment exploreFragment);
}
