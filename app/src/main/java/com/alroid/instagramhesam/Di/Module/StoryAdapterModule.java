package com.alroid.instagramhesam.Di.Module;

import android.content.Context;

import com.alroid.instagramhesam.Adapter.StoryAdapter;
import com.alroid.instagramhesam.ViewModel.StoryViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class StoryAdapterModule {
    StoryViewModel storyViewModel;
    Context context;

    public StoryAdapterModule(StoryViewModel storyViewModel, Context context) {
        this.storyViewModel = storyViewModel;
        this.context = context;
    }

    @Provides
    public StoryAdapter provideStoryAdapter(){
        return new StoryAdapter(storyViewModel,context);
    }
}
