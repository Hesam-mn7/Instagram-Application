package com.alroid.instagramhesam.Di.Module;

import java.util.Random;

import dagger.Module;
import dagger.Provides;

@Module
public class RandomModule {

    @Provides
    public Random provideRandom(){
        return new Random();
    }
}
