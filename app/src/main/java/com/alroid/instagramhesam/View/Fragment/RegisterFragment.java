package com.alroid.instagramhesam.View.Fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.alroid.instagramhesam.Const;
//import com.alroid.instagramhesam.Di.Component.DaggerFileComponent;
//import com.alroid.instagramhesam.Di.Component.DaggerRegisterHelperComponent;
//import com.alroid.instagramhesam.Di.Component.DaggerUserComponent;
import com.alroid.instagramhesam.Di.Component.DaggerBottomSheetDialogComponent;
import com.alroid.instagramhesam.Di.Component.DaggerBundleComponent;
import com.alroid.instagramhesam.Di.Component.DaggerFileComponent;
import com.alroid.instagramhesam.Di.Component.DaggerRegisterFragmentComponent;
import com.alroid.instagramhesam.Di.Component.DaggerRegisterHelperComponent;
import com.alroid.instagramhesam.Di.Component.DaggerUserComponent;
import com.alroid.instagramhesam.Di.Component.DaggerUserrComponent;
import com.alroid.instagramhesam.Di.Module.BottomSheetDialogModule;
import com.alroid.instagramhesam.Di.Module.FileModule;
import com.alroid.instagramhesam.Di.Module.UserrModule;
import com.alroid.instagramhesam.Exception.Exceptions;
import com.alroid.instagramhesam.Exception.Helper.RegisterHelper;
import com.alroid.instagramhesam.Model.Room.Entity.Story;
import com.alroid.instagramhesam.Model.Room.Entity.User;
import com.alroid.instagramhesam.R;
import com.alroid.instagramhesam.View.Activity.MainActivity;
import com.alroid.instagramhesam.ViewModel.StoryViewModel;
import com.alroid.instagramhesam.ViewModel.UserViewModel;
import com.alroid.instagramhesam.databinding.FragmentRegisterBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class RegisterFragment extends Fragment {
    FragmentRegisterBinding binding;
    UserViewModel userViewModel;
    StoryViewModel storyViewModel;

    private static final int SELECT_PHOTO = 100;
    private static final int TAKE_PHOTO = 200;

    private String [] permissions = {"android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_PHONE_STATE",
            "android.permission.SYSTEM_ALERT_WINDOW","android.permission.CAMERA"};


    public static RegisterFragment newInstanceCreateAccount(String userName, String password, String phoneNumber, String email )
    {
        //RegisterFragment fragment = new RegisterFragment();
        RegisterFragment fragment = DaggerRegisterFragmentComponent.create().getRegisterFragment();

        //Bundle bundle = new Bundle();
        Bundle bundle = DaggerBundleComponent.create().getBundlee();

        bundle.putString("userName",userName);
        bundle.putString("password",password);
        bundle.putString("phoneNumber",phoneNumber);
        bundle.putString("email",email);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater);

        onClickpicProfile();
        onRegister();

        return binding.getRoot();
    }

    private void onRegister() {
        userViewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);
        storyViewModel = ViewModelProviders.of(getActivity()).get(StoryViewModel.class);

        if (getArguments() != null && getArguments().containsKey("userName")&& getArguments().containsKey("password")
                && getArguments().containsKey("phoneNumber")&& getArguments().containsKey("email")) {

            String userName = getArguments().getString("userName");
            String password = getArguments().getString("password");
            String phoneNumber = getArguments().getString("phoneNumber");
            String email = getArguments().getString("email");

            binding.btnOnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        String name = binding.etName.getText().toString();
                        String bio = binding.etBio.getText().toString();
                        String profilePic = binding.etGetImgId.getText().toString();
                        String myPicStory = "https://s4.uupload.ir/files/untitled01_cjau.png";

                        //RegisterHelper registerHelper = new RegisterHelper();
                        RegisterHelper registerHelper = DaggerRegisterHelperComponent.create().getRegisterHelper();

                        registerHelper.register(name,bio,profilePic);

                        //User user = new User(0,userName,password,name,bio,profilePic,email,phoneNumber);
                        User user = DaggerUserrComponent.builder()
                                .userrModule(new UserrModule(0,userName,password,name,bio,profilePic,email,phoneNumber))
                                .build().getUserees();

                        storyViewModel.insert(new Story(0, "Your Story", profilePic, "1h", myPicStory,"0"))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new CompletableObserver() {
                                    @Override
                                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                                    }

                                    @Override
                                    public void onComplete() {

                                    }

                                    @Override
                                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                                    }
                                });

                        userViewModel.insert(user)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new CompletableObserver() {
                                    @Override
                                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                                    }
                                    @Override
                                    public void onComplete() {
                                        setPrefernce(Const.SHARED_PERF_KEY_REGISTER, "finish");
                                        startActivity(new Intent(getActivity(), MainActivity.class));
                                    }
                                    @Override
                                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                        Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }
                    catch (Exceptions.NameLenghtException ex){
                        binding.outlinedTextFieldName.setError(ex.getMessage());
                    }
                    catch (Exceptions.BioLenghtException ex){
                        binding.outlinedTextFieldBio.setError(ex.getMessage());
                        binding.outlinedTextFieldName.setError(null);
                    }
                    catch (Exceptions.NoProfilePicException ex){
                        Snackbar snackBar = Snackbar.make(v, ex.getMessage(), Snackbar.LENGTH_SHORT);
                        snackBar.setAnchorView(binding.profilePic);
                        snackBar.show();
                    }

                }
            });
        }
    }


    private void onClickpicProfile() {
        binding.profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToGallery();
            }
        });
        binding.textAddProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToGallery();
            }
        });
    }
    private void GoToGallery() {
        int requestCode = 200;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_botton_sheet_select_image, null);

        //final BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
        BottomSheetDialog dialog  = DaggerBottomSheetDialogComponent.builder()
                .bottomSheetDialogModule(new BottomSheetDialogModule(getActivity()))
                .build().getBottomSheetDialog();

        dialog.setContentView(view);
        dialog.show();

        TextView tvTakePhoto = view.findViewById(R.id.tvTakePhoto);
        tvTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camIntent, 200);
            }
        });

        TextView tvFromGallery = view.findViewById(R.id.tvFromGallery);
        tvFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });

        TextView tvCancel = view.findViewById(R.id.tvCancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        if (requestCode == SELECT_PHOTO) {
            if (resultCode == RESULT_OK) {
                Uri selectedImage = imageReturnedIntent.getData();

                Bitmap yourSelectedImage = null;
                try {
                    yourSelectedImage = decodeUri(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                binding.profilePic.setImageBitmap(yourSelectedImage);

                storeImage(yourSelectedImage);

            }
        }

        if (requestCode == TAKE_PHOTO) {
            if (resultCode == RESULT_OK) {
                Bitmap resultPhoto = (Bitmap) imageReturnedIntent.getExtras().get("data");
                binding.profilePic.setImageBitmap(resultPhoto);
                storeImage(resultPhoto);

            }
        }
    }

    private void storeImage(Bitmap image) {
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Log.e("Const.TAG",
                    "Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("Const.TAG", "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.e("Const.TAG", "Error accessing file: " + e.getMessage());
        }
        binding.etGetImgId.setText(pictureFile.toURI().toString());
    }

    private File getOutputMediaFile(){
        File file = ActivityCompat.getDataDir(getActivity());

        // Create a media file name
        File mediaFile;
        String mImageName="MI_ProfilePicInstagramHesam"+ ".jpg";

        String finalPath = file.toString() +"/" +mImageName;
        //mediaFile = new File(finalPath);
        mediaFile = DaggerFileComponent.builder().fileModule(new FileModule(finalPath)).build().getFile();
        return mediaFile;
    }

    private Bitmap decodeUri(Uri selectedImage) throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);
        final int REQUIRED_SIZE = 260;
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE
                    || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);
    }

    public void setPrefernce(String key, String value) {
        SharedPreferences sharedPreferences = getSharedPreferences(Const.SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public SharedPreferences getSharedPreferences(String name, int mode) {
        return getActivity().getSharedPreferences(name, mode);
    }
    private ContentResolver getContentResolver() {
        return getActivity().getContentResolver();
    }

}
