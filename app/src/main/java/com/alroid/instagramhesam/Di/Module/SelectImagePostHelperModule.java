package com.alroid.instagramhesam.Di.Module;

import com.alroid.instagramhesam.Exception.Helper.SelectImagePostHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class SelectImagePostHelperModule {

    @Provides
    public SelectImagePostHelper provideSelectImagePostHelper(){
        return new SelectImagePostHelper();
    }
}
