package com.alroid.instagramhesam.Di.Module;

import android.content.Context;

import com.alroid.instagramhesam.Adapter.AdapterChatUser;

import dagger.Module;
import dagger.Provides;

@Module
public class AdapterChatUserModule {
    Context context;

    public AdapterChatUserModule(Context context) {
        this.context = context;
    }

    @Provides
    public AdapterChatUser provideAdapterChatUser(){
        return new AdapterChatUser(context);
    }
}
