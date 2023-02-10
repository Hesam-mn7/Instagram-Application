package com.alroid.instagramhesam.Di.Module;

import java.io.File;

import dagger.Module;
import dagger.Provides;

@Module
public class FileModule {
    String finalPath ;

    public FileModule(String finalPath) {
        this.finalPath = finalPath;
    }
    @Provides
    public File providefile(){
        return new File(finalPath);
    }
}
