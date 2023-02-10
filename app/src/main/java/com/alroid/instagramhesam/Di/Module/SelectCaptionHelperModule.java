package com.alroid.instagramhesam.Di.Module;

import com.alroid.instagramhesam.Exception.Helper.SelectCaptionHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class SelectCaptionHelperModule {
    @Provides
    public SelectCaptionHelper provideSelectCaptionHelper(){
        return new SelectCaptionHelper();
    }
}
