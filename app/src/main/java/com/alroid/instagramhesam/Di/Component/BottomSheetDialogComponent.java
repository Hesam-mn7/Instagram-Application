package com.alroid.instagramhesam.Di.Component;

import com.alroid.instagramhesam.Di.Module.BottomSheetDialogModule;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import dagger.Component;

@Component(modules = BottomSheetDialogModule.class)
public interface BottomSheetDialogComponent {
    BottomSheetDialog getBottomSheetDialog();
}
