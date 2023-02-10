package com.alroid.instagramhesam.Di.Module;

import android.app.Application;

import com.alroid.instagramhesam.Model.Repository.UserRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class UserRepositoryModule {
    Application application;

    public UserRepositoryModule(Application application) {
        this.application = application;
    }
    @Provides
    public UserRepository ProvideUserRepository(){
        return new UserRepository(application);
    }
}
