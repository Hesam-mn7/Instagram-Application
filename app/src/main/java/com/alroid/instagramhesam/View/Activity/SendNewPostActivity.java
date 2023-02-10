package com.alroid.instagramhesam.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.alroid.instagramhesam.Di.Component.DaggerPosttComponent;
import com.alroid.instagramhesam.Di.Component.DaggerRandomComponent;
import com.alroid.instagramhesam.Di.Component.DaggerSelectCaptionHelperComponent;
import com.alroid.instagramhesam.Di.Module.PosttModule;
import com.alroid.instagramhesam.Di.Module.SelectCaptionHelperModule;
import com.alroid.instagramhesam.Exception.Exceptions;
import com.alroid.instagramhesam.Exception.Helper.SelectCaptionHelper;
import com.alroid.instagramhesam.Model.Room.Entity.Post;
import com.alroid.instagramhesam.Model.Room.Entity.User;
import com.alroid.instagramhesam.R;
import com.alroid.instagramhesam.ViewModel.PostViewModel;
import com.alroid.instagramhesam.ViewModel.UserViewModel;
import com.alroid.instagramhesam.databinding.ActivityCreateAccountBinding;
import com.alroid.instagramhesam.databinding.ActivitySendNewPostBinding;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SendNewPostActivity extends BaseActivity {

    ActivitySendNewPostBinding binding;
    PostViewModel postViewModel;
    UserViewModel userViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySendNewPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        onClickItem();
        GetItemAndPost();

    }

    private void GetItemAndPost() {
        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        //Random r = new Random();
        Random r = DaggerRandomComponent.create().getRandom();

        String numberLike = String.valueOf(r.nextInt(150 - 100 + 1) + 100);
        String numberComments = String.valueOf(r.nextInt(40 - 10 + 1) + 10);
        String time = String.valueOf(r.nextInt(23 - 1 + 1) + 1);

        final Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        String imgPost = intent.getStringExtra("imgPost");

        Uri uriImgProfilePost = Uri.parse(imgPost);
        Picasso.get()
                .load(uriImgProfilePost)
                .placeholder(R.drawable.load_insta)
                .error(R.drawable.error_pic)
                .into(binding.imgPostt);

        userViewModel.select().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                for (User item : users) {
                    String username = item.getUserName();
                    String imgProfile = item.getProfilePic();

                    binding.imgnext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                String caption = binding.etCaption.getText().toString();
                                String myImgProfileLike = "https://s4.uupload.ir/files/eren_bla7.jpg";

                                //SelectCaptionHelper selectCaptionHelper = new SelectCaptionHelper();
                                SelectCaptionHelper selectCaptionHelper = DaggerSelectCaptionHelperComponent
                                        .builder().selectCaptionHelperModule(new SelectCaptionHelperModule())
                                        .build()
                                        .getSelectCaptionHelper();

                                selectCaptionHelper.register(caption);

//                                Post post = new Post(0, username, imgProfile, imgPost
//                                        , "https://i.pinimg.com/originals/b6/6d/22/b66d22a8b57900e75cbab27192cd58a3.jpg"
//                                        , "hesam_mn7", numberLike, caption, numberComments, time, "0", "0", "1");

                                Post post = DaggerPosttComponent.builder().posttModule(new PosttModule(0, username, imgProfile, imgPost
                                        , myImgProfileLike , "hesam_mn7", numberLike, caption, numberComments, time, "0"
                                        , "0", "1")).build().getPostt();

                                postViewModel.insert(post)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new CompletableObserver() {
                                            @Override
                                            public void onSubscribe(@NonNull Disposable d) {

                                            }

                                            @Override
                                            public void onComplete() {
                                                startActivity(new Intent(SendNewPostActivity.this, MainActivity.class));

                                            }

                                            @Override
                                            public void onError(@NonNull Throwable e) {

                                            }
                                        });
                            } catch (Exceptions.CaptionLenghtException ex) {
                                binding.etCaption.setError(ex.getMessage());

                            }
                        }
                    });
                }
            }
        });

    }

    private void onClickItem() {
        binding.tvtag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Tag People", Snackbar.LENGTH_SHORT).show();
            }
        });
        binding.tvAddLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Add Location", Snackbar.LENGTH_SHORT).show();
            }
        });
        binding.imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}