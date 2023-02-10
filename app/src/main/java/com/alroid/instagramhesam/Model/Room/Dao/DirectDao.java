package com.alroid.instagramhesam.Model.Room.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.alroid.instagramhesam.Model.Room.Entity.Direct;
import com.alroid.instagramhesam.Model.Room.Entity.Explore;

import java.util.List;

@Dao
public interface DirectDao {

    @Insert
    void insert(Direct direct);

    @Query("select * from Direct order by id asc")
    LiveData<List<Direct>> select();
}
