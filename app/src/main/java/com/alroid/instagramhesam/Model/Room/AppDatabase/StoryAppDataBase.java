package com.alroid.instagramhesam.Model.Room.AppDatabase;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.alroid.instagramhesam.Model.Room.Dao.StoryDao;
import com.alroid.instagramhesam.Model.Room.Entity.Story;

@Database(entities = Story.class , version = 1)
public abstract class StoryAppDataBase extends RoomDatabase {

    public abstract StoryDao getStoryDao();

    private static StoryAppDataBase instance;

    public static StoryAppDataBase getInstance(Application application){
        if (instance == null){
            instance = Room.databaseBuilder(application, StoryAppDataBase.class, "Story.DB").build();

        }
        return instance;
    }
}
