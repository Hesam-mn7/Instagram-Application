package com.alroid.instagramhesam.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.alroid.instagramhesam.R;
import com.alroid.instagramhesam.View.Fragment.ProfileFragment;
import com.alroid.instagramhesam.ViewModel.PostViewModel;
import com.alroid.instagramhesam.databinding.ActivityEditPostBinding;
import com.alroid.instagramhesam.databinding.ActivitySplashBinding;
import com.squareup.picasso.Picasso;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class EditPostActivity extends BaseActivity {

    ActivityEditPostBinding binding;
    PostViewModel postViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);


        final Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        String usernamePost = intent.getStringExtra("usernamePost");
        String imgPost = intent.getStringExtra("imgPost");
        String timePost = intent.getStringExtra("timePost");
        String caption = intent.getStringExtra("caption");
        String imgProfilePost = intent.getStringExtra("imgProfilePost");
        int id = intent.getIntExtra("id",0);

        binding.tvUsernamePost.setText(usernamePost);
        binding.tvTime.setText(timePost+"h");
        binding.etCaption.setText(caption);
        binding.tvUsernamePost.setText(usernamePost);

        Uri uriimgPost = Uri.parse(imgPost);
        Picasso.get()
                .load(uriimgPost)
                .placeholder(R.drawable.nostory)
                .error(R.drawable.nostory)
                .into(binding.imgPost);

        Uri uriimgPostProfile = Uri.parse(imgProfilePost);
        Picasso.get()
                .load(uriimgPostProfile)
                .placeholder(R.drawable.nostory)
                .error(R.drawable.nostory)
                .into(binding.imgProfilePost);

        binding.imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newCaption = binding.etCaption.getText().toString();

                postViewModel.updatePost(id,newCaption)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                                startActivity(new Intent(EditPostActivity.this, MainActivity.class));
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }
                        });

            }
        });




    }
}