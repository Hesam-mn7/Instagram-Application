package com.alroid.instagramhesam.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.alroid.instagramhesam.Model.Repository.ExploreRepository;
import com.alroid.instagramhesam.Model.Room.Entity.Explore;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class ExploreViewModel extends AndroidViewModel {

    private ExploreRepository repository;
    private LiveData<List<Explore>> liveData;

    public ExploreViewModel(@NonNull Application application) {
        super(application);

        repository = new ExploreRepository(application);

        liveData = repository.select();
    }
    public Completable insert(Explore explore){
        return repository.insert(explore);
    }
    public LiveData<List<Explore>> select(){
        return liveData;
    }
    public LiveData<List<Explore>> getExploreFromWebServer(){
        return repository.getExploreFromWebServer();
    }
}
