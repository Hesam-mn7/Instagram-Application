package com.alroid.instagramhesam.Model.Room.AppDatabase;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.alroid.instagramhesam.Model.Room.Dao.ExploreDao;
import com.alroid.instagramhesam.Model.Room.Entity.Explore;

@Database(entities = Explore.class , version = 1)
public abstract class ExploreAppDataBase extends RoomDatabase {

    public abstract ExploreDao getExploreDao();

    private static ExploreAppDataBase instance;

    public static ExploreAppDataBase getInstance(Application application){
        if (instance == null){
            instance = Room.databaseBuilder(application,ExploreAppDataBase.class,"Explore.DB").build();

        }
        return instance;
    }




}
