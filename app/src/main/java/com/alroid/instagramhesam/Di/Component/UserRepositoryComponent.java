package com.alroid.instagramhesam.Di.Component;

import com.alroid.instagramhesam.Di.Module.UserRepositoryModule;
import com.alroid.instagramhesam.Model.Repository.UserRepository;

import dagger.Component;

@Component(modules = UserRepositoryModule.class)
public interface UserRepositoryComponent {
    UserRepository getUserRepository();
}
