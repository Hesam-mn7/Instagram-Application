package com.alroid.instagramhesam.Model.Room.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.alroid.instagramhesam.Model.Room.Entity.Story;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface StoryDao {

    @Insert
    void insert(Story story);

    @Query("select * from Story order by id desc")
    LiveData<List<Story>> select();

    @Query("UPDATE Story SET seenStory= :seenStory WHERE id LIKE :id ")
    void updateSeenStory(int id , String seenStory);
}
