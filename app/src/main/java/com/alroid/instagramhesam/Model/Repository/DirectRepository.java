package com.alroid.instagramhesam.Model.Repository;

import android.app.Application;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.alroid.instagramhesam.Di.Component.DaggerRetrofitComponent;
import com.alroid.instagramhesam.Model.Api.InstagramApi;
import com.alroid.instagramhesam.Model.Room.AppDatabase.DirectAppDataBase;
import com.alroid.instagramhesam.Model.Room.AppDatabase.IgtvAppDataBase;
import com.alroid.instagramhesam.Model.Room.Dao.DirectDao;
import com.alroid.instagramhesam.Model.Room.Dao.IgtvDao;
import com.alroid.instagramhesam.Model.Room.Entity.Direct;
import com.alroid.instagramhesam.Model.Room.Entity.Igtv;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DirectRepository {
    private DirectDao directDao;
    private LiveData<List<Direct>> liveData;
    private InstagramApi api;

    public DirectRepository(Application application) {
        this.directDao = DirectAppDataBase.getInstance(application).getDirectDao();

        liveData = select();

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(InstagramApi.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
        Retrofit retrofit = DaggerRetrofitComponent.create().getRetrofit();

        api = retrofit.create(InstagramApi.class);
    }

    public Completable insert(Direct direct){
        Completable completable = new Completable() {
            @Override
            protected void subscribeActual(@NonNull CompletableObserver observer) {
                directDao.insert(direct);
                observer.onComplete();
            }
        };
        return completable;
    }

    public LiveData<List<Direct>> getDirectFromWebServer(){
        LiveData<List<Direct>> liveData1 = new LiveData<List<Direct>>() {
            @Override
            public void observe(@androidx.annotation.NonNull LifecycleOwner owner, @androidx.annotation.NonNull Observer<? super List<Direct>> observer) {
                super.observe(owner, observer);
                api.insertDirect().enqueue(new Callback<List<Direct>>() {
                    @Override
                    public void onResponse(Call<List<Direct>> call, Response<List<Direct>> response) {
                        if (response.code() == 200) {
                            observer.onChanged(response.body());
                        } else {
                            observer.onChanged(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Direct>> call, Throwable t) {
                        observer.onChanged(null);
                    }
                });
            }
        };
        return liveData1;
    }

    public LiveData<List<Direct>> select() {
        return directDao.select();
    }
}
