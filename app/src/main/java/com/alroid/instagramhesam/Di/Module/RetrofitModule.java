package com.alroid.instagramhesam.Di.Module;

import com.alroid.instagramhesam.Model.Api.InstagramApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    @Provides
    public Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(InstagramApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
