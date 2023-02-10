package com.alroid.instagramhesam.Di.Module;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;

@Module
public class LinearLayoutManagerModule {

    Context context;

    public LinearLayoutManagerModule(Context context) {
        this.context = context;
    }

    @Provides
    public LinearLayoutManager provideLinearLayoutManager(){
        return new LinearLayoutManager(context);
    }
}
