package com.alroid.instagramhesam.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.alroid.instagramhesam.Model.Repository.DirectRepository;
import com.alroid.instagramhesam.Model.Repository.IgtvRepository;
import com.alroid.instagramhesam.Model.Room.Entity.Direct;
import com.alroid.instagramhesam.Model.Room.Entity.Igtv;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class DirectViewModel extends AndroidViewModel {

    private DirectRepository repository;
    private LiveData<List<Direct>> liveData;


    public DirectViewModel(@NonNull Application application) {
        super(application);

        repository = new DirectRepository(application);

        liveData = repository.select();
    }

    public Completable insert(Direct direct){
        return repository.insert(direct);
    }
    public LiveData<List<Direct>> select(){
        return liveData;
    }
    public LiveData<List<Direct>> getDirectFromWebServer(){
        return repository.getDirectFromWebServer();
    }

}
