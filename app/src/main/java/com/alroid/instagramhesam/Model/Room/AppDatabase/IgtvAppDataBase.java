package com.alroid.instagramhesam.Model.Room.AppDatabase;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.alroid.instagramhesam.Model.Room.Dao.ExploreDao;
import com.alroid.instagramhesam.Model.Room.Dao.IgtvDao;
import com.alroid.instagramhesam.Model.Room.Entity.Igtv;

@Database(entities = Igtv.class , version = 1)
public abstract class IgtvAppDataBase extends RoomDatabase {

    public abstract IgtvDao getIgtvDao();

    private static IgtvAppDataBase instance;

    public static IgtvAppDataBase getInstance(Application application){
        if (instance == null){
            instance = Room.databaseBuilder(application,IgtvAppDataBase.class,"Igtv.DB").build();

        }
        return instance;
    }
}
