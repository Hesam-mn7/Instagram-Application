package com.alroid.instagramhesam.Di.Module;

import android.os.Bundle;

import dagger.Module;
import dagger.Provides;

@Module
public class BundleModule {

    @Provides
    public Bundle provideBundle(){
        return new Bundle();
    }
}
