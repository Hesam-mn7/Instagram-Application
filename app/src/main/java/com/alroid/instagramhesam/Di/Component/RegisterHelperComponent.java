package com.alroid.instagramhesam.Di.Component;

import com.alroid.instagramhesam.Di.Module.RegisterHelperModule;
import com.alroid.instagramhesam.Exception.Helper.RegisterHelper;

import dagger.Component;

@Component(modules = RegisterHelperModule.class)
public interface RegisterHelperComponent {
    RegisterHelper getRegisterHelper();
}
