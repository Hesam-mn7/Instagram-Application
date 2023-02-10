package com.alroid.instagramhesam.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alroid.instagramhesam.Model.Room.Entity.Story;
import com.alroid.instagramhesam.R;
import com.alroid.instagramhesam.View.Activity.StoryActivity;
import com.alroid.instagramhesam.ViewModel.StoryViewModel;
import com.alroid.instagramhesam.databinding.ItemStoryBinding;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.MyViewHolder> {

    private StoryViewModel viewModel;
    private List<Story> list;
    Context context;

    public StoryAdapter(StoryViewModel storyViewModel, Context context) {
        this.list = new ArrayList<>();
        this.viewModel = storyViewModel;
        this.context = context;

    }

    public void setList(List<Story> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemStoryBinding binding = ItemStoryBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Story story = this.list.get(position);

        int id = story.getId();
        String username = story.getUserName();
        String picStory = story.getPicStory();
        String profilePic = story.getProfilePic();
        String time = story.getTime();
        String seenStory = story.getSeenStory();

        holder.binding.usernameStory.setText(username);

        Picasso.get()
                .load(profilePic)
                .placeholder(R.drawable.nostory)
                .error(R.drawable.nostory)
                .into(holder.binding.profilePicStory);


        if (seenStory.equals("0")) {
            holder.binding.profilePicStoryNotSeen.setVisibility(View.VISIBLE);
            holder.binding.profilePicStorySeen.setVisibility(View.INVISIBLE);
        } else if (seenStory.equals("1")) {
            holder.binding.profilePicStorySeen.setVisibility(View.VISIBLE);
            holder.binding.profilePicStoryNotSeen.setVisibility(View.INVISIBLE);
        }

        holder.binding.profilePicStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StoryActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("picStory", picStory);
                intent.putExtra("time", time);
                intent.putExtra("profilePic", profilePic);
                intent.putExtra("seenStory", seenStory);
                intent.putExtra("id", id);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        ItemStoryBinding binding;

        public MyViewHolder(ItemStoryBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;

        }

    }

}
