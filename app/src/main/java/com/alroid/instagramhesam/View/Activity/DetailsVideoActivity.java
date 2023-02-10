package com.alroid.instagramhesam.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alroid.instagramhesam.R;
import com.alroid.instagramhesam.databinding.ActivityDetailsVideoBinding;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;


public class DetailsVideoActivity extends BaseActivity {

    ActivityDetailsVideoBinding binding;

    String videoUrlIgtv;

    SimpleExoPlayer player;
    boolean playWhenReady = true;
    int currentWindows = 0;
    long currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        String usernameIgtv = intent.getStringExtra("usernameIgtv");
        String captionIgtv = intent.getStringExtra("captionIgtv");
        String imgProfileIgtv = intent.getStringExtra("imgProfileIgtv");
        String numberView = intent.getStringExtra("numberView");
        String numberComments = intent.getStringExtra("numberComments");
        String timePost = intent.getStringExtra("timePost");
        videoUrlIgtv = intent.getStringExtra("videoUrlIgtv");

        TextView tvUsernameDetail = binding.playerView.findViewById(R.id.tvUsernameDetail);
        tvUsernameDetail.setText(usernameIgtv);

        TextView tvCaptionDetail = binding.playerView.findViewById(R.id.tvCaptionDetail);
        tvCaptionDetail.setText(captionIgtv);

        TextView tvViewsDetail = binding.playerView.findViewById(R.id.tvViewsDetail);
        tvViewsDetail.setText(numberView + "k views");

        TextView tvCommentDetail = binding.playerView.findViewById(R.id.tvCommentDetail);
        tvCommentDetail.setText(numberComments + " comments");

        TextView tvtimeDetail = binding.playerView.findViewById(R.id.tvtimeDetail);
        tvtimeDetail.setText(timePost + "h");

        Uri uriImgProfileIgtv = Uri.parse(imgProfileIgtv);
        ImageView imgProfile = binding.playerView.findViewById(R.id.imgProfileDetail);
        Picasso.get()
                .load(uriImgProfileIgtv)
                .placeholder(R.drawable.load_insta)
                .error(R.drawable.error_pic)
                .into(imgProfile);

        TextView tvFollowDetail = binding.playerView.findViewById(R.id.tvFollowDetail);
        tvFollowDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Follow", Snackbar.LENGTH_SHORT).show();
            }
        });

        ImageView imgdislike = binding.playerView.findViewById(R.id.imgdislikeDetail);
        ImageView imglike = binding.playerView.findViewById(R.id.imglikeDetail);

        imgdislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgdislike.setVisibility(View.INVISIBLE);
                imglike.setVisibility(View.VISIBLE);
            }
        });

        imglike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgdislike.setVisibility(View.VISIBLE);
                imglike.setVisibility(View.INVISIBLE);
            }
        });

        ImageView imgbackDetail = binding.playerView.findViewById(R.id.imgbackDetail);
        imgbackDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView chat = binding.playerView.findViewById(R.id.chatDetail);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Comments", Snackbar.LENGTH_SHORT).show();

            }
        });

        ImageView sendDetail = binding.playerView.findViewById(R.id.sendDetail);
        sendDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Uername : " + usernameIgtv + "\n" + "Caption : " + captionIgtv);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        initPlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        release();
    }

    private void initPlayer() {
        player = ExoPlayerFactory.newSimpleInstance(this);
        binding.playerView.setPlayer(player);

        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindows, currentPosition);

        player.prepare(buildMediaSource(Uri.parse(videoUrlIgtv)), false, false);
    }


    private void release() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            currentWindows = player.getCurrentWindowIndex();
            currentPosition = player.getCurrentPosition();

            player.release();
            player = null;
        }
    }

    private MediaSource buildMediaSource(Uri videoUrlIgtv) {
        DataSource.Factory factory = new DefaultDataSourceFactory(this, "my app");
        return new ProgressiveMediaSource.Factory(factory).createMediaSource(videoUrlIgtv);
    }

}