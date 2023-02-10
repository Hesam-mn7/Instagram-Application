package com.alroid.instagramhesam.Model.Room.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.alroid.instagramhesam.Model.Room.Entity.Explore;
import com.alroid.instagramhesam.Model.Room.Entity.Igtv;

import java.util.List;

@Dao
public interface IgtvDao {

    @Insert
    void insert(Igtv igtv);

    @Query("select * from Igtv order by id asc")
    LiveData<List<Igtv>> select();
}
