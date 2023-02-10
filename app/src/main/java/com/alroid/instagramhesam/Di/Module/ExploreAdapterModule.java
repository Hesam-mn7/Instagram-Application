package com.alroid.instagramhesam.Di.Module;

import android.content.Context;

import com.alroid.instagramhesam.Adapter.ExploreAdapter;
import com.alroid.instagramhesam.View.Fragment.ExploreFragment;
import com.alroid.instagramhesam.ViewModel.ExploreViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class ExploreAdapterModule {
    ExploreViewModel viewModel;
    Context context;
    ExploreFragment.CallbackFragmentExplore listener;

    public ExploreAdapterModule(ExploreViewModel viewModel, Context context, ExploreFragment.CallbackFragmentExplore listener) {
        this.viewModel = viewModel;
        this.context = context;
        this.listener = listener;
    }

    @Provides
    public ExploreAdapter provideExploreAdapter(){
        return new ExploreAdapter(viewModel,context,listener);
    }

}
