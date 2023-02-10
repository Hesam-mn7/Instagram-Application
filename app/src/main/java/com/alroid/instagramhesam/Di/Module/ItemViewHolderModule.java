package com.alroid.instagramhesam.Di.Module;

import android.view.View;

import com.alroid.instagramhesam.Adapter.AdapterChatUser;

import dagger.Module;
import dagger.Provides;

@Module
public class ItemViewHolderModule {
    View v;

    public ItemViewHolderModule(View v) {
        this.v = v;
    }

    @Provides
    public AdapterChatUser.ItemViewHolder provideItemViewHolder(){
        return new AdapterChatUser.ItemViewHolder(v);
    }
}
