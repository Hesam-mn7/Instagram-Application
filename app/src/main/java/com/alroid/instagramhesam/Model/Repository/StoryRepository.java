package com.alroid.instagramhesam.Model.Repository;

import android.app.Application;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.alroid.instagramhesam.Di.Component.DaggerRetrofitComponent;
import com.alroid.instagramhesam.Model.Api.InstagramApi;
import com.alroid.instagramhesam.Model.Room.AppDatabase.StoryAppDataBase;
import com.alroid.instagramhesam.Model.Room.Dao.StoryDao;
import com.alroid.instagramhesam.Model.Room.Entity.Story;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StoryRepository {

    private StoryDao storyDao;
    private LiveData<List<Story>> liveData;
    private InstagramApi api;

    public StoryRepository(Application application){
        this.storyDao = StoryAppDataBase.getInstance(application).getStoryDao();
        liveData = select();

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(InstagramApi.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
        Retrofit retrofit = DaggerRetrofitComponent.create().getRetrofit();

        api = retrofit.create(InstagramApi.class);
    }

    public Completable insert(Story story) {
        Completable completable = new Completable() {
            @Override
            protected void subscribeActual(@NonNull CompletableObserver observer) {
                storyDao.insert(story);
                observer.onComplete();
            }
        };
        return completable;
    }

    public LiveData<List<Story>> getStoryFromWebServer(){

        LiveData<List<Story>> liveData = new LiveData<List<Story>>() {
            @Override
            public void observe(@androidx.annotation.NonNull LifecycleOwner owner, @androidx.annotation.NonNull Observer<? super List<Story>> observer) {
                super.observe(owner, observer);
                api.insertStory().enqueue(new Callback<List<Story>>() {
                    @Override
                    public void onResponse(Call<List<Story>> call, Response<List<Story>> response) {
                        if (response.code() == 200) {
                            observer.onChanged(response.body());
                        } else {
                            observer.onChanged(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Story>> call, Throwable t) {
                        observer.onChanged(null);
                    }
                });
            }
        };

        return liveData;

    }


    public LiveData<List<Story>> select() {
        return storyDao.select();
    }

    public Completable updateSeenStory(int id , String seenStory) {
        Completable completable = new Completable() {
            @Override
            protected void subscribeActual(@NonNull CompletableObserver observer) {
                storyDao.updateSeenStory(id,seenStory);
                observer.onComplete();
            }
        };
        return completable;
    }
}
