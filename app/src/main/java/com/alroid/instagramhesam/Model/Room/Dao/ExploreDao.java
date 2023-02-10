package com.alroid.instagramhesam.Model.Room.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.alroid.instagramhesam.Model.Room.Entity.Explore;
import com.alroid.instagramhesam.Model.Room.Entity.Story;

import java.util.List;


@Dao
public interface ExploreDao {

    @Insert
    void insert(Explore explore);

    @Query("select * from Explore order by id asc")
    LiveData<List<Explore>> select();
}
