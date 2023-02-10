package com.alroid.instagramhesam.Model.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.alroid.instagramhesam.Model.Room.AppDatabase.UserAppDataBase;
import com.alroid.instagramhesam.Model.Room.Dao.UserDao;
import com.alroid.instagramhesam.Model.Room.Entity.User;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import retrofit2.Retrofit;

public class UserRepository {

    private UserDao userDao;
    private LiveData<List<User>> liveData;

    public UserRepository(Application application) {
        this.userDao = UserAppDataBase.getInstance(application).getUserDao();

        liveData = select();

    }

    public Completable insert(User user){
        Completable completable = new Completable() {
            @Override
            protected void subscribeActual(@NonNull CompletableObserver observer) {
                userDao.insert(user);
                observer.onComplete();
            }
        };
        return completable;
    }

    public Completable update(User user){
        Completable completable = new Completable() {
            @Override
            protected void subscribeActual(@NonNull CompletableObserver observer) {
                userDao.update(user);
                observer.onComplete();
            }
        };
        return completable;
    }
    public Completable deleteById(int id){
        Completable completable = new Completable() {
            @Override
            protected void subscribeActual(@NonNull CompletableObserver observer) {
                userDao.deleteById(id);
                observer.onComplete();
            }
        };
        return completable;
    }

    public LiveData<List<User>> select() {
        return userDao.select();
    }


}
