package com.alroid.instagramhesam.Di.Module;

import com.alroid.instagramhesam.View.Fragment.RegisterFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class RegisterFragmentModule {

    @Provides
    public RegisterFragment provideRegisterFragment(){
        return new RegisterFragment();
    }
}
