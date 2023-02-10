package com.alroid.instagramhesam.Di.Module;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.alroid.instagramhesam.Model.Room.Entity.Story;

import dagger.Module;
import dagger.Provides;

@Module
public class StoryModule {

    private int id;
    private String userName;
    private String profilePic;
    private String time;
    private String picStory;
    private String seenStory;

    public StoryModule(int id, String userName, String profilePic, String time, String picStory, String seenStory) {
        this.id = id;
        this.userName = userName;
        this.profilePic = profilePic;
        this.time = time;
        this.picStory = picStory;
        this.seenStory = seenStory;
    }

    @Provides
    public Story provideStory(){
        return new Story(id,userName,profilePic,time,picStory,seenStory);
    }
}
