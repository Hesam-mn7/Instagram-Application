package com.alroid.instagramhesam.Di.Component;

import com.alroid.instagramhesam.Di.Module.SelectImagePostHelperModule;
import com.alroid.instagramhesam.Exception.Helper.SelectImagePostHelper;

import dagger.Component;

@Component(modules = SelectImagePostHelperModule.class)
public interface SelectImagePostHelperComponent {
    SelectImagePostHelper getSelectImagePostHelper();
}
