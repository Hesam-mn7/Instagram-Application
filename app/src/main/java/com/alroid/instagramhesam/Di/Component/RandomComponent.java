package com.alroid.instagramhesam.Di.Component;

import com.alroid.instagramhesam.Di.Module.RandomModule;

import java.util.Random;

import dagger.Component;
import dagger.Module;

@Component(modules = RandomModule.class)
public interface RandomComponent {
    Random getRandom();
}
