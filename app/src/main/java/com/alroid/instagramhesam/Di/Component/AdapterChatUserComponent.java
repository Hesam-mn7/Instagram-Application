package com.alroid.instagramhesam.Di.Component;

import com.alroid.instagramhesam.Di.Module.AdapterChatUserModule;
import com.alroid.instagramhesam.View.Fragment.ChatDirectFragment;

import dagger.Component;

@Component(modules = AdapterChatUserModule.class)
public interface AdapterChatUserComponent {
    void Inject(ChatDirectFragment fragment);
}
