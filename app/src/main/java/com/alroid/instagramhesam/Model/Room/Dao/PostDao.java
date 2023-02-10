package com.alroid.instagramhesam.Model.Room.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.alroid.instagramhesam.Model.Room.Entity.Post;

import java.util.List;

@Dao
public interface PostDao {

    @Insert
    void insert(Post post);

    @Query("DELETE FROM Post WHERE id = :id")
    void deleteById(int id);

    @Query("UPDATE Post SET caption= :caption WHERE id LIKE :id ")
    void updatePost(int id , String caption);

    @Query("UPDATE Post SET cheekLike= :cheekLike WHERE id LIKE :id ")
    void updateLike(int id , String cheekLike);

    @Query("UPDATE Post SET cheekSaved= :cheekSaved WHERE id LIKE :id ")
    void updateSaved(int id , String cheekSaved);

    @Query("select * from Post order by id desc")
    LiveData<List<Post>> select();

    @Query("select * from Post where cheekMyPost =:cheekMyPost order by id desc")
    LiveData<List<Post>> selectMyPost(String cheekMyPost);
}
