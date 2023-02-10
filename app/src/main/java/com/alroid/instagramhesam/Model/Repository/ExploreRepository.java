package com.alroid.instagramhesam.Model.Repository;

import android.app.Application;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.alroid.instagramhesam.Di.Component.DaggerRetrofitComponent;
import com.alroid.instagramhesam.Model.Api.InstagramApi;
import com.alroid.instagramhesam.Model.Room.AppDatabase.ExploreAppDataBase;
import com.alroid.instagramhesam.Model.Room.Dao.ExploreDao;
import com.alroid.instagramhesam.Model.Room.Entity.Explore;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExploreRepository {

    private ExploreDao exploreDao;
    private LiveData<List<Explore>> liveData;
    private InstagramApi api;

    public ExploreRepository(Application application) {
        this.exploreDao = ExploreAppDataBase.getInstance(application).getExploreDao();

        liveData = select();

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(InstagramApi.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
        Retrofit retrofit = DaggerRetrofitComponent.create().getRetrofit();

        api = retrofit.create(InstagramApi.class);
    }

    public Completable insert(Explore explore){
        Completable completable = new Completable() {
            @Override
            protected void subscribeActual(@NonNull CompletableObserver observer) {
                exploreDao.insert(explore);
                observer.onComplete();
            }
        };
        return completable;
    }

    public LiveData<List<Explore>> getExploreFromWebServer(){
        LiveData<List<Explore>> liveData1 = new LiveData<List<Explore>>() {
            @Override
            public void observe(@androidx.annotation.NonNull LifecycleOwner owner, @androidx.annotation.NonNull Observer<? super List<Explore>> observer) {
                super.observe(owner, observer);
                api.insertExplore().enqueue(new Callback<List<Explore>>() {
                    @Override
                    public void onResponse(Call<List<Explore>> call, Response<List<Explore>> response) {
                        if (response.code() == 200) {
                            observer.onChanged(response.body());
                        } else {
                            observer.onChanged(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Explore>> call, Throwable t) {
                        observer.onChanged(null);

                    }
                });
            }
        };
        return liveData1;
    }

    public LiveData<List<Explore>> select() {
        return exploreDao.select();
    }
}
