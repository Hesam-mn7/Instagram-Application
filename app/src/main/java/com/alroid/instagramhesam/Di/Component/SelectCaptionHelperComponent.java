package com.alroid.instagramhesam.Di.Component;

import com.alroid.instagramhesam.Di.Module.SelectCaptionHelperModule;
import com.alroid.instagramhesam.Exception.Helper.SelectCaptionHelper;
import com.alroid.instagramhesam.View.Activity.SendNewPostActivity;

import dagger.Component;

@Component(modules = SelectCaptionHelperModule.class)
public interface SelectCaptionHelperComponent {
    SelectCaptionHelper getSelectCaptionHelper();
}
