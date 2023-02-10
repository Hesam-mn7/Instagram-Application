package com.alroid.instagramhesam.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.alroid.instagramhesam.Model.Repository.ExploreRepository;
import com.alroid.instagramhesam.Model.Repository.IgtvRepository;
import com.alroid.instagramhesam.Model.Room.Entity.Explore;
import com.alroid.instagramhesam.Model.Room.Entity.Igtv;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class IgtvViewModel extends AndroidViewModel {

    private IgtvRepository repository;
    private LiveData<List<Igtv>> liveData;

    public IgtvViewModel(@NonNull Application application) {
        super(application);

        repository = new IgtvRepository(application);

        liveData = repository.select();
    }

    public Completable insert(Igtv igtv){
        return repository.insert(igtv);
    }
    public LiveData<List<Igtv>> select(){
        return liveData;
    }
    public LiveData<List<Igtv>> getIgtvFromWebServer(){
        return repository.getIgtvFromWebServer();
    }

}
