package com.alroid.instagramhesam.View.Fragment;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.alroid.instagramhesam.Di.Component.DaggerBottomSheetDialogComponent;
import com.alroid.instagramhesam.Di.Component.DaggerFileComponent;
import com.alroid.instagramhesam.Di.Component.DaggerSelectImagePostHelperComponent;
import com.alroid.instagramhesam.Di.Module.BottomSheetDialogModule;
import com.alroid.instagramhesam.Di.Module.FileModule;
import com.alroid.instagramhesam.Exception.Exceptions;
import com.alroid.instagramhesam.Exception.Helper.SelectImagePostHelper;
import com.alroid.instagramhesam.R;
import com.alroid.instagramhesam.View.Activity.SendNewPostActivity;
import com.alroid.instagramhesam.databinding.FragmentNewPostBinding;
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
import java.util.UUID;

import static android.app.Activity.RESULT_OK;
import static android.media.MediaRecorder.VideoSource.CAMERA;


public class NewPostFragment extends Fragment {

    FragmentNewPostBinding binding;
    private static final int SELECT_PHOTO = 100;
    private static final int TAKE_PHOTO = 200;


    private String [] permissions = {"android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_PHONE_STATE",
            "android.permission.SYSTEM_ALERT_WINDOW","android.permission.CAMERA"};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNewPostBinding.inflate(inflater);

        onClickpicProfile();
        getPicAndSendToSendNewPostActivity();
        onclickItems();

        return binding.getRoot();
    }

    private void onclickItems() {

        binding.imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        binding.tvStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(v, "Opening Story", Snackbar.LENGTH_SHORT);
                snackbar.setAnchorView(binding.linearBorder);
                snackbar.show();
            }
        });
        binding.tvLive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(v, "Opening Live", Snackbar.LENGTH_SHORT);
                snackbar.setAnchorView(binding.linearBorder);
                snackbar.show();
            }
        });
    }

    private void getPicAndSendToSendNewPostActivity() {
        binding.imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String imgPost = binding.etGetImgId.getText().toString();

                    //SelectImagePostHelper selectImagePostHelper = new SelectImagePostHelper();
                    SelectImagePostHelper selectImagePostHelper = DaggerSelectImagePostHelperComponent.create().getSelectImagePostHelper();
                    selectImagePostHelper.register(imgPost);

                    Intent intent = new Intent(getActivity(), SendNewPostActivity.class);
                    intent.putExtra("imgPost", imgPost);
                    startActivity(intent);

                } catch (Exceptions.NoPicException ex) {
                    Snackbar snackBar = Snackbar.make(v, ex.getMessage(), Snackbar.LENGTH_SHORT);
                    snackBar.setAnchorView(binding.textAddProfilePic);
                    snackBar.show();
                }

            }
        });
    }


    private void onClickpicProfile() {
        binding.image.setOnClickListener(new View.OnClickListener() {
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

                Picasso.get()
                        .load(selectedImage)
                        .fit().centerInside()
                        .placeholder(R.drawable.load_insta)
                        .error(R.drawable.error_pic)
                        .into(binding.image);

                storeImage(yourSelectedImage);

            }
        }
        if (requestCode == TAKE_PHOTO) {
            if (resultCode == RESULT_OK) {
                Bitmap resultPhoto = (Bitmap) imageReturnedIntent.getExtras().get("data");
                binding.image.setImageBitmap(resultPhoto);
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

    private File getOutputMediaFile() {
        File file = ActivityCompat.getDataDir(getActivity());

        // Create a media file name
        File mediaFile;
        String mImageName = "MI_" + generateString() + ".jpg";

        String finalPath = file.toString() + "/" + mImageName;
        //mediaFile = new File(finalPath);
        mediaFile = DaggerFileComponent.builder().fileModule(new FileModule(finalPath)).build().getFile();
        return mediaFile;
    }

    private ContentResolver getContentResolver() {
        return getActivity().getContentResolver();
    }

    public static String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }



}