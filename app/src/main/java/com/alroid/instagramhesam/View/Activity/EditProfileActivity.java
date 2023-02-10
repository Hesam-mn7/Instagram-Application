package com.alroid.instagramhesam.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.alroid.instagramhesam.Di.Component.DaggerFileComponent;
import com.alroid.instagramhesam.Di.Component.DaggerUserComponent;
import com.alroid.instagramhesam.Di.Module.FileModule;
import com.alroid.instagramhesam.Exception.Exceptions;
import com.alroid.instagramhesam.Model.Room.Entity.User;
import com.alroid.instagramhesam.R;
import com.alroid.instagramhesam.ViewModel.UserViewModel;
import com.alroid.instagramhesam.databinding.ActivityEditProfileBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class EditProfileActivity extends BaseActivity {
    ActivityEditProfileBinding binding;

    UserViewModel userViewModel;

    private static final int SELECT_PHOTO = 100;
    private static final int TAKE_PHOTO = 200;


    private String[] permissions = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_PHONE_STATE", "android.permission.SYSTEM_ALERT_WINDOW", "android.permission.CAMERA"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        onClickpicProfile();
        setUserAndEdit();
        onClickItems();


    }

    private void onClickItems() {
        binding.imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.swichtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Switch to Professional Account", Snackbar.LENGTH_SHORT).show();
            }
        });

        binding.personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Personal Information Settings", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void setUserAndEdit() {
        userViewModel.select().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                for (User item : users) {
                    int id = item.getId();
                    String password = item.getPassword();

                    binding.etName.setText(item.getName());
                    binding.etUserName.setText(item.getUserName());
                    binding.etEmail.setText(item.getEmail());
                    binding.etPhoneNumber.setText(item.getPhoneNumber());
                    binding.etBio.setText(item.getBio());
                    binding.etGetImgId.setText(item.getProfilePic());

                    Picasso.get()
                            .load(item.getProfilePic())
                            .placeholder(R.drawable.nostory)
                            .error(R.drawable.nostory)
                            .into(binding.profilePic);

                    binding.imgnext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                String name = binding.etName.getText().toString();
                                String username = binding.etUserName.getText().toString();
                                String email = binding.etEmail.getText().toString();
                                String phoneNumber = binding.etPhoneNumber.getText().toString();
                                String bio = binding.etBio.getText().toString();
                                String profilePic = binding.etGetImgId.getText().toString();

                                EditProfileHelper editProfileHelper = new EditProfileHelper();
                                editProfileHelper.register(name, bio, profilePic, username, email, phoneNumber);

                                User user = new User(id, username, password, name, bio, profilePic, email, phoneNumber);
                                userViewModel.update(user)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new CompletableObserver() {
                                            @Override
                                            public void onSubscribe(@NonNull Disposable d) {

                                            }

                                            @Override
                                            public void onComplete() {
                                                onBackPressed();
//                                                Intent intent = new Intent(EditProfileActivity.this,MainActivity.class);
//                                                startActivity(intent);

                                            }

                                            @Override
                                            public void onError(@NonNull Throwable e) {

                                            }
                                        });


                            } catch (Exceptions.NameLenghtException ex) {
                                binding.outlinedTextFieldName.setError(ex.getMessage());
                            } catch (Exceptions.UsernameWeakLenghtException ex) {
                                binding.outlinedTextFieldUserName.setError(ex.getMessage());
                                binding.outlinedTextFieldName.setError(null);
                            } catch (Exceptions.EmailWeakLenghtException ex) {
                                binding.outlinedTextFieldUserName.setError(null);
                                binding.outlinedTextFieldName.setError(null);
                                binding.outlinedTextFieldEmail.setError(ex.getMessage());
                            } catch (Exceptions.PhoneNumberLenghtException ex) {
                                binding.outlinedTextFieldUserName.setError(null);
                                binding.outlinedTextFieldName.setError(null);
                                binding.outlinedTextFieldEmail.setError(null);
                                binding.outlinedTextFieldPhoneNumber.setError(ex.getMessage());
                            } catch (Exceptions.BioLenghtException ex) {
                                binding.outlinedTextFieldUserName.setError(null);
                                binding.outlinedTextFieldName.setError(null);
                                binding.outlinedTextFieldEmail.setError(null);
                                binding.outlinedTextFieldPhoneNumber.setError(null);
                                binding.outlinedTextFieldBio.setError(ex.getMessage());
                            } catch (Exceptions.NoProfilePicException ex) {
                                Snackbar snackBar = Snackbar.make(v, ex.getMessage(), Snackbar.LENGTH_SHORT);
                                snackBar.setAnchorView(binding.profilePic);
                                snackBar.show();
                            }


                        }

                    });
                }
            }
        });
    }

    private void onClickpicProfile() {
        binding.profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToGallery();
            }
        });
        binding.textChangeProfilePic.setOnClickListener(new View.OnClickListener() {
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

        View view = LayoutInflater.from(this).inflate(R.layout.fragment_botton_sheet_select_image, null);

        final BottomSheetDialog dialog = new BottomSheetDialog(this);
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

    private File getOutputMediaFile() {
        File file = ActivityCompat.getDataDir(this);

        // Create a media file name
        File mediaFile;
        String mImageName = "MI_ProfilePicInstagramHesam" + ".jpg";

        String finalPath = file.toString() + "/" + mImageName;
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

    public static class EditProfileHelper {
        public boolean register(String name, String bio, String profilePic
                , String username, String email, String phoneNumbr) {

            User user = DaggerUserComponent.create().getUsers();
            //User user = new User();

            user.setName(name);
            user.setBio(bio);
            user.setProfilePic(profilePic);
            user.setPhoneNumber(phoneNumbr);
            user.setEmail(email);
            user.setUserName(username);
            return true;
        }
    }
}