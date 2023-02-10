package com.alroid.instagramhesam.Di.Component;

import com.alroid.instagramhesam.Di.Module.IgtvAdapterModule;
import com.alroid.instagramhesam.View.Fragment.IGTVFragment;

import dagger.Component;

@Component(modules = IgtvAdapterModule.class)
public interface IgtvAdapterComponent {
    void injectIgtv(IGTVFragment igtvFragment);
}
