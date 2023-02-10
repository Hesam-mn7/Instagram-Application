package com.alroid.instagramhesam.Di.Component;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.alroid.instagramhesam.Di.Module.LinearLayoutManagerModule;

import dagger.Component;

@Component(modules = LinearLayoutManagerModule.class)
public interface LinearLayoutManagerComponent {
    LinearLayoutManager getLinearLayoutManager();
}
