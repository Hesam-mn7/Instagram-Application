package com.alroid.instagramhesam.Di.Module;

import android.content.Context;


import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class DataSourceFactoryModule {
    Context context;
    String a;

    public DataSourceFactoryModule(Context context, String a) {
        this.context = context;
        this.a = a;
    }

    @Provides
    public DefaultDataSourceFactory providefactory(){
        return new DefaultDataSourceFactory(context,a);
    }
}
