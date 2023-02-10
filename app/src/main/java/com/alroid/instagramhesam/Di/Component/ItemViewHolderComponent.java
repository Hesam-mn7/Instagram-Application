package com.alroid.instagramhesam.Di.Component;

import com.alroid.instagramhesam.Adapter.AdapterChatUser;
import com.alroid.instagramhesam.Di.Module.ItemViewHolderModule;

import dagger.Component;

@Component(modules = ItemViewHolderModule.class)
public interface ItemViewHolderComponent {
    AdapterChatUser.ItemViewHolder getItemViewHolder();
}
