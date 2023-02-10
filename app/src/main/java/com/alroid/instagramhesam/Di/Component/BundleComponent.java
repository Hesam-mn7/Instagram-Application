package com.alroid.instagramhesam.Di.Component;

import android.os.Bundle;

import com.alroid.instagramhesam.Di.Module.BundleModule;

import dagger.Component;

@Component(modules = BundleModule.class)
public interface BundleComponent {
    Bundle getBundlee();
}
