package com.alroid.instagramhesam.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.alroid.instagramhesam.Model.Repository.StoryRepository;
import com.alroid.instagramhesam.Model.Room.Entity.Story;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class StoryViewModel extends AndroidViewModel {
    private StoryRepository repository;
    private LiveData<List<Story>> liveData;

    public StoryViewModel(@NonNull Application application) {
        super(application);

        repository = new StoryRepository(application);

        liveData = repository.select();
    }
    public Completable insert(Story story){
        return repository.insert(story);
    }
    public LiveData<List<Story>> select(){
        return liveData;
    }
    public LiveData<List<Story>> getStoryFromWebServer(){
        return repository.getStoryFromWebServer();
    }
    public Completable updateSeenStory(int id , String seenStory){
        return repository.updateSeenStory(id,seenStory);
    }
}
