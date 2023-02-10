package com.alroid.instagramhesam.Di.Module;

import android.content.Context;

import com.alroid.instagramhesam.Adapter.IgtvAdapter;
import com.alroid.instagramhesam.ViewModel.IgtvViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class IgtvAdapterModule {

    IgtvViewModel igtvViewModel;
    Context context;

    public IgtvAdapterModule(IgtvViewModel igtvViewModel, Context context) {
        this.igtvViewModel = igtvViewModel;
        this.context = context;
    }

    @Provides
    public IgtvAdapter provideIgtvAdapter(){
        return new IgtvAdapter(igtvViewModel,context);
    }
}
