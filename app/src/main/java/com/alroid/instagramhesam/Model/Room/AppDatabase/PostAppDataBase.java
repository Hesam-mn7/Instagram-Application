package com.alroid.instagramhesam.Model.Room.AppDatabase;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.alroid.instagramhesam.Model.Room.Dao.PostDao;
import com.alroid.instagramhesam.Model.Room.Entity.Post;

@Database(entities = Post.class , version = 1)
public abstract class PostAppDataBase extends RoomDatabase {

    public abstract PostDao getPostDao();

    private static PostAppDataBase instance;

    public static PostAppDataBase getInstance(Application application){
        if (instance == null){
            instance = Room.databaseBuilder(application, PostAppDataBase.class, "Post.DB").build();

        }
        return instance;
    }
}
