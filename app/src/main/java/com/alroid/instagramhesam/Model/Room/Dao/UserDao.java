package com.alroid.instagramhesam.Model.Room.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.alroid.instagramhesam.Model.Room.Entity.User;

import java.util.List;


@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Query("DELETE FROM User WHERE id = :id")
    void deleteById(int id);

    @Update
    void update(User user);

    @Query("select * from User order by id desc")
    LiveData<List<User>> select();
}
