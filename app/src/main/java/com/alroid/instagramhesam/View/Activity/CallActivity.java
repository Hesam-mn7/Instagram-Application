package com.alroid.instagramhesam.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.alroid.instagramhesam.R;
import com.alroid.instagramhesam.databinding.ActivityCallBinding;
import com.alroid.instagramhesam.databinding.ActivitySplashBinding;
import com.squareup.picasso.Picasso;

public class CallActivity extends BaseActivity {
    ActivityCallBinding binding;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCallBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.internal_ring);
        mediaPlayer.start();

        binding.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
                else {
                    mediaPlayer.start();
                }
            }
        });
        binding.microfon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
                else {
                    mediaPlayer.start();
                }
            }
        });
        Intent intent= getIntent();
        if (intent == null){
            finish();
            return;
        }
        String username = intent.getStringExtra("Username");
        String profilePic = intent.getStringExtra("profilePic");

        binding.tvUsernameCall.setText(username);

        Uri uriImgProfilePost = Uri.parse(profilePic);
        Picasso.get()
                .load(uriImgProfilePost)
                .placeholder(R.drawable.nostory)
                .error(R.drawable.nostory)
                .into(binding.imgCall);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();

    }
}