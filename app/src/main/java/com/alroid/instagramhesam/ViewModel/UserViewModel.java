package com.alroid.instagramhesam.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

//import com.alroid.instagramhesam.Di.Component.DaggerUserRepositoryComponent;
import com.alroid.instagramhesam.Di.Component.DaggerUserRepositoryComponent;
import com.alroid.instagramhesam.Di.Module.UserRepositoryModule;
import com.alroid.instagramhesam.Model.Repository.UserRepository;
import com.alroid.instagramhesam.Model.Room.Entity.User;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class UserViewModel extends AndroidViewModel {

    private UserRepository repository;
    private LiveData<List<User>> liveData;

    public UserViewModel(@NonNull Application application) {
        super(application);

        repository = new UserRepository(application);
//        repository = DaggerUserRepositoryComponent.builder()
//                .userRepositoryModule(new UserRepositoryModule(application))
//                .build().getUserRepository();

        liveData = repository.select();
    }

    public Completable insert(User user){
        return repository.insert(user);
    }
    public Completable update(User user){
        return repository.update(user);
    }
    public Completable deleteById(int id){
        return repository.deleteById(id);
    }
    public LiveData<List<User>> select(){
        return liveData;
    }
}
