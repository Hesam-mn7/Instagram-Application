package com.alroid.instagramhesam.Di.Component;

import com.alroid.instagramhesam.Di.Module.CreateAccountHelperModule;
import com.alroid.instagramhesam.Exception.Helper.CreateAccountHelper;

import dagger.Component;


@Component(modules = CreateAccountHelperModule.class)
public interface CreateAccountHelperComponent {
    CreateAccountHelper getCreateAccountHelper();
}
