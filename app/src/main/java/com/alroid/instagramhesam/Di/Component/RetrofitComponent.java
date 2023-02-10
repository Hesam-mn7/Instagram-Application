package com.alroid.instagramhesam.Di.Component;

import com.alroid.instagramhesam.Di.Module.RetrofitModule;

import dagger.Component;
import retrofit2.Retrofit;

@Component(modules = RetrofitModule.class)
public interface RetrofitComponent {
    Retrofit getRetrofit();
}
