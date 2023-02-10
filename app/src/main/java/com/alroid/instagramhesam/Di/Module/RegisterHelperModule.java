package com.alroid.instagramhesam.Di.Module;

import com.alroid.instagramhesam.Exception.Helper.RegisterHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class RegisterHelperModule {

    @Provides
    public RegisterHelper provideRegisterHelper(){
        return new RegisterHelper();
    }
}
