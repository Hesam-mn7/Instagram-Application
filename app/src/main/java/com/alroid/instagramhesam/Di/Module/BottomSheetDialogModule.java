package com.alroid.instagramhesam.Di.Module;

import android.content.Context;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import dagger.Module;
import dagger.Provides;

@Module
public class BottomSheetDialogModule {

    Context context;

    public BottomSheetDialogModule(Context context) {
        this.context = context;
    }

    @Provides
    public BottomSheetDialog getBottomSheetDialog(){
        return new BottomSheetDialog(context);
    }
}
