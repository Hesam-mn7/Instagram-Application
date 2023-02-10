package com.alroid.instagramhesam.Di.Component;

import com.alroid.instagramhesam.Di.Module.RegisterFragmentModule;
import com.alroid.instagramhesam.View.Fragment.RegisterFragment;

import dagger.Component;

@Component(modules = RegisterFragmentModule.class)
public interface RegisterFragmentComponent {
    RegisterFragment getRegisterFragment();
}
