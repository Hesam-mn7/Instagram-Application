package com.alroid.instagramhesam.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.alroid.instagramhesam.R;
import com.alroid.instagramhesam.ViewModel.StoryViewModel;
import com.alroid.instagramhesam.databinding.ActivityMainBinding;
import com.alroid.instagramhesam.databinding.ActivityStoryBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.schedulers.ComputationScheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class StoryActivity extends BaseActivity {
    ActivityStoryBinding binding;
    StoryViewModel storyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        storyViewModel = ViewModelProviders.of(this).get(StoryViewModel.class);

        setItems();
        onClickShare();

    }

    private void setItems() {
        final Intent intent = getIntent();

        String username = intent.getStringExtra("username");
        String picStory = intent.getStringExtra("picStory");
        String time = intent.getStringExtra("time");
        String profilePic = intent.getStringExtra("profilePic");
        String seenStory = intent.getStringExtra("seenStory");
        int id = intent.getIntExtra("id", 0);

        storyViewModel.updateSeenStory(id, "1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });


        binding.titlestoryy.setText(username);
        binding.timeStory.setText(time);

        Uri uriPicStory = Uri.parse(picStory);
        Picasso.get()
                .load(uriPicStory)
                .placeholder(R.drawable.load_story)
                .error(R.drawable.load_story)
                .into(binding.imgStory);

        Uri uriPicProfileStory = Uri.parse(profilePic);
        Picasso.get()
                .load(uriPicProfileStory)
                .placeholder(R.drawable.nostory)
                .error(R.drawable.nostory)
                .into(binding.imgProfilestoryy);


        final Handler handler = new Handler();
        Timer t = new Timer();

        if (seenStory.equals("0")){

            t.schedule(new TimerTask() {
                public void run() {
                    handler.post(new Runnable() {
                        public void run() {
                            finish();
                        }
                    });
                }
            }, 7000);
        }
        else if(seenStory.equals("1")){
            t.schedule(new TimerTask() {
                public void run() {
                    handler.post(new Runnable() {
                        public void run() {
                            finish();
                        }
                    });
                }
            }, 5000);
        }


    }

    private void onClickShare() {
        binding.imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Share", Snackbar.LENGTH_SHORT).show();
            }
        });
    }


}