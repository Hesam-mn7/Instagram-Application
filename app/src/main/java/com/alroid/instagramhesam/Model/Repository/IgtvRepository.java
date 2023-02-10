package com.alroid.instagramhesam.Model.Repository;

import android.app.Application;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.alroid.instagramhesam.Di.Component.DaggerRetrofitComponent;
import com.alroid.instagramhesam.Model.Api.InstagramApi;
import com.alroid.instagramhesam.Model.Room.AppDatabase.ExploreAppDataBase;
import com.alroid.instagramhesam.Model.Room.AppDatabase.IgtvAppDataBase;
import com.alroid.instagramhesam.Model.Room.Dao.ExploreDao;
import com.alroid.instagramhesam.Model.Room.Dao.IgtvDao;
import com.alroid.instagramhesam.Model.Room.Entity.Explore;
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

public class IgtvRepository {

    private IgtvDao igtvDao;
    private LiveData<List<Igtv>> liveData;
    private InstagramApi api;

    public IgtvRepository(Application application) {
        this.igtvDao = IgtvAppDataBase.getInstance(application).getIgtvDao();

        liveData = select();

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(InstagramApi.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
        Retrofit retrofit = DaggerRetrofitComponent.create().getRetrofit();

        api = retrofit.create(InstagramApi.class);
    }

    public Completable insert(Igtv igtv){
        Completable completable = new Completable() {
            @Override
            protected void subscribeActual(@NonNull CompletableObserver observer) {
                igtvDao.insert(igtv);
                observer.onComplete();
            }
        };
        return completable;
    }

    public LiveData<List<Igtv>> getIgtvFromWebServer(){
        LiveData<List<Igtv>> liveData1 = new LiveData<List<Igtv>>() {
            @Override
            public void observe(@androidx.annotation.NonNull LifecycleOwner owner, @androidx.annotation.NonNull Observer<? super List<Igtv>> observer) {
                super.observe(owner, observer);
                api.insertIgtv().enqueue(new Callback<List<Igtv>>() {
                    @Override
                    public void onResponse(Call<List<Igtv>> call, Response<List<Igtv>> response) {
                        if (response.code() == 200) {
                            observer.onChanged(response.body());
                        } else {
                            observer.onChanged(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Igtv>> call, Throwable t) {
                        observer.onChanged(null);

                    }
                });
            }
        };
        return liveData1;
    }

    public LiveData<List<Igtv>> select() {
        return igtvDao.select();
    }
}
