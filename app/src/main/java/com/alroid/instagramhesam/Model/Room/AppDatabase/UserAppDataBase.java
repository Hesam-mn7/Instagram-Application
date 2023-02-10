package com.alroid.instagramhesam.Model.Room.AppDatabase;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.alroid.instagramhesam.Model.Room.Dao.UserDao;
import com.alroid.instagramhesam.Model.Room.Entity.User;

@Database(entities = User.class , version = 1)
public abstract class UserAppDataBase extends RoomDatabase {

    public abstract UserDao getUserDao();

    private static UserAppDataBase instance;

    public static UserAppDataBase getInstance(Application application){
        if (instance == null){
            instance = Room.databaseBuilder(application, UserAppDataBase.class, "User.DB").build();

        }
        return instance;
    }
}
