package com.alroid.instagramhesam.Di.Component;

import com.alroid.instagramhesam.Di.Module.FileModule;

import java.io.File;

import dagger.Component;

@Component(modules = FileModule.class)
public interface FileComponent {
    File getFile();
}
