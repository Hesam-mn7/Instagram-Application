package com.alroid.instagramhesam.Di.Module;

import com.alroid.instagramhesam.Exception.Helper.CreateAccountHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class CreateAccountHelperModule {

    @Provides
    public CreateAccountHelper provideCreateAccountHelper(){
        return new CreateAccountHelper();
    }
}
