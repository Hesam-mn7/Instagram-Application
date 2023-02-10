package com.alroid.instagramhesam.Di.Module;

import android.content.Context;

import com.alroid.instagramhesam.Adapter.DirectAdapter;
import com.alroid.instagramhesam.ViewModel.DirectViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class DirectAdapterModule {

    DirectViewModel directViewModel;
    Context context;

    public DirectAdapterModule(DirectViewModel directViewModel, Context context) {
        this.directViewModel = directViewModel;
        this.context = context;
    }

    @Provides
    public DirectAdapter provideDirectAdapter(){
        return new DirectAdapter(directViewModel,context);
    }
}
