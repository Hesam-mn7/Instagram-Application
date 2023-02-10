package com.alroid.instagramhesam.Di.Component;

import com.alroid.instagramhesam.Adapter.DirectAdapter;
import com.alroid.instagramhesam.Di.Module.DirectAdapterModule;
import com.alroid.instagramhesam.View.Activity.DirectActivity;

import dagger.Component;

@Component(modules = DirectAdapterModule.class)
public interface DirectAdapterComponent {
    void inject(DirectActivity directActivity);
}
