package com.alroid.instagramhesam.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.alroid.instagramhesam.Model.Repository.PostRepository;
import com.alroid.instagramhesam.Model.Room.Entity.Post;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class PostViewModel extends AndroidViewModel {
    private PostRepository repository;
    private LiveData<List<Post>> liveData;

    public PostViewModel(@NonNull Application application) {
        super(application);

        repository = new PostRepository(application);

        liveData = repository.select();
    }
    public Completable insert(Post post){
        return repository.insert(post);
    }

    public Completable deleteById(int id){
        return repository.deleteById(id);
    }

    public Completable updatePost(int id , String caption){
        return repository.updatePost(id,caption);
    }

    public Completable updateLike(int id , String cheekLike){
        return repository.updateLike(id,cheekLike);
    }

    public Completable updateSaved(int id , String cheekSaved){
        return repository.updateSaved(id,cheekSaved);
    }

    public LiveData<List<Post>> select(){
        return liveData;
    }

    public LiveData<List<Post>> selectMyPost(String cheekMyPost){
        return repository.selectMyPost(cheekMyPost);
    }

    public LiveData<List<Post>> getPostFromWebServer(){
        return repository.getPostFromWebServer();
    }
}
