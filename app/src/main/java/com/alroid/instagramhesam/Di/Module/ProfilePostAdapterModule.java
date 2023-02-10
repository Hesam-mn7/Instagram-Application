package com.alroid.instagramhesam.Di.Module;

import android.content.Context;

import com.alroid.instagramhesam.Adapter.ProfilePostAdapter;
import com.alroid.instagramhesam.View.Fragment.ProfileFragment;
import com.alroid.instagramhesam.ViewModel.PostViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class ProfilePostAdapterModule {
    PostViewModel postViewModel;
    Context context;
    ProfileFragment.CallbackFragmentProfile listener;

    public ProfilePostAdapterModule(PostViewModel postViewModel, Context context, ProfileFragment.CallbackFragmentProfile listener) {
        this.postViewModel = postViewModel;
        this.context = context;
        this.listener = listener;
    }

    @Provides
    public ProfilePostAdapter provideProfilePostAdapter(){
        return new ProfilePostAdapter(postViewModel,context,listener);
    }
}
