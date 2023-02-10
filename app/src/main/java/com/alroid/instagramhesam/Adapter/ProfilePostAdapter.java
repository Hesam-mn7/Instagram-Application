package com.alroid.instagramhesam.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alroid.instagramhesam.Model.Room.Entity.Post;
import com.alroid.instagramhesam.R;
import com.alroid.instagramhesam.View.Fragment.ProfileFragment;
import com.alroid.instagramhesam.ViewModel.PostViewModel;
import com.alroid.instagramhesam.databinding.ItemProfilePostBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProfilePostAdapter extends RecyclerView.Adapter<ProfilePostAdapter.MyViewHolder> {

    private PostViewModel viewModel;
    private List<Post> list;
    Context context;
    private ProfileFragment.CallbackFragmentProfile listeners;

    public ProfilePostAdapter(PostViewModel viewModel, Context context , ProfileFragment.CallbackFragmentProfile listener) {
        this.viewModel = viewModel;
        this.context = context;
        this.list = new ArrayList<>();
        this.listeners = listener;

    }

    public void setList(List<Post> list) {
        this.list = list;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProfilePostBinding binding = ItemProfilePostBinding
                .inflate(LayoutInflater.from(parent.getContext())
                );
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Post post = this.list.get(position);

        int id = post.getId();
        String usernamePost = post.getUsernamePost();
        String imgProfilePost = post.getImgProfilePost();
        String imgPost = post.getImgPost();
        String imgProfileLike = post.getImgProfileLike();
        String usernameLike = post.getUsernameLike();
        String numberLike = post.getNumberLike();
        String caption = post.getCaption();
        String numberComments = post.getNumberComments();
        String timePost = post.getTimePost();
        String cheekLike = post.getCheekLike();
        String cheekSaved = post.getCheekSaved();
        String cheekMyPost = post.getCheekMyPost();

        Picasso.get()
                .load(imgPost)
                .placeholder(R.drawable.nostory)
                .error(R.drawable.nostory)
                .into(holder.binding.myimg);

        holder.binding.myimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onclickProfileToDetail(id,usernamePost,imgProfilePost
                        ,imgPost,numberLike,caption, numberComments , timePost , cheekMyPost );
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ItemProfilePostBinding binding;
        public MyViewHolder(@NonNull ItemProfilePostBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
