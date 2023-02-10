package com.alroid.instagramhesam.Model.Room.AppDatabase;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.alroid.instagramhesam.Model.Room.Dao.DirectDao;
import com.alroid.instagramhesam.Model.Room.Entity.Direct;

@Database(entities = Direct.class , version = 1)
public abstract class DirectAppDataBase extends RoomDatabase {

    public abstract DirectDao getDirectDao();

    private static DirectAppDataBase instance;

    public static DirectAppDataBase getInstance(Application application){
        if (instance == null){
            instance = Room.databaseBuilder(application,DirectAppDataBase.class,"Direct.DB").build();

        }
        return instance;
    }
}
