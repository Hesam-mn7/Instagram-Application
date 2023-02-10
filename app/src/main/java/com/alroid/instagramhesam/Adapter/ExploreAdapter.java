package com.alroid.instagramhesam.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alroid.instagramhesam.Di.Component.DaggerRandomComponent;
import com.alroid.instagramhesam.Model.Room.Entity.Explore;
import com.alroid.instagramhesam.R;
import com.alroid.instagramhesam.View.Fragment.ExploreFragment;
import com.alroid.instagramhesam.ViewModel.ExploreViewModel;
import com.alroid.instagramhesam.databinding.ItemExploreBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.MyViewHolder> {

    private ExploreViewModel viewModel;
    private List<Explore> list;
    private ExploreFragment.CallbackFragmentExplore listeners;
    Context context;

    public ExploreAdapter(ExploreViewModel viewModel, Context context , ExploreFragment.CallbackFragmentExplore listener) {
        this.viewModel = viewModel;
        this.list = new ArrayList<>();
        this.context = context;
        this.listeners = listener;

    }

    public void setList(List<Explore> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemExploreBinding binding = ItemExploreBinding
                .inflate(LayoutInflater.from(parent.getContext())
                        , parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Explore explore = this.list.get(position);

        //Random r = new Random();
        Random r = DaggerRandomComponent.create().getRandom();

        String numberLike = String.valueOf(r.nextInt(999 - 100 + 1) + 100);
        String numberComments = String.valueOf(r.nextInt(300 - 10 + 1) + 10);
        String timePost = String.valueOf(r.nextInt(23 - 1 + 1) + 1);
        String cheekMyPost = "0";
        String caption = "Have a nice day \uD83C\uDF39";
        String imgProfilePost = "https://www.transparentpng.com/thumb/logo-instagram/JFyofc-logo-instagram-background-png.png";
        int id = 0;


        String imgExplore1 = explore.getImgExplore1();
        String usernameExplore1 = explore.getUsernameExplore1();

        String imgExplore2 = explore.getImgExplore2();
        String usernameExplore2 = explore.getUsernameExplore2();

        String imgExplore3 = explore.getImgExplore3();
        String usernameExplore3 = explore.getUsernameExplore3();

        String imgExplore4 = explore.getImgExplore4();
        String usernameExplore4 = explore.getUsernameExplore4();

        String imgExplore5 = explore.getImgExplore5();
        String usernameExplore5 = explore.getUsernameExplore5();

        String imgExplore6 = explore.getImgExplore6();
        String usernameExplore6 = explore.getUsernameExplore6();

        String imgExplore7 = explore.getImgExplore7();
        String usernameExplore7 = explore.getUsernameExplore7();

        String imgExplore8 = explore.getImgExplore8();
        String usernameExplore8 = explore.getUsernameExplore8();

        String imgExplore9 = explore.getImgExplore9();
        String usernameExplore9 = explore.getUsernameExplore9();

        Picasso.get()
                .load(imgExplore1)
                .placeholder(R.drawable.nostory)
                .error(R.drawable.nostory)
                .into(holder.binding.imgExplore1);

        Picasso.get()
                .load(imgExplore2)
                .placeholder(R.drawable.nostory)
                .error(R.drawable.nostory)
                .into(holder.binding.imgExplore2);

        Picasso.get()
                .load(imgExplore3)
                .placeholder(R.drawable.nostory)
                .error(R.drawable.nostory)
                .into(holder.binding.imgExplore3);

        Picasso.get()
                .load(imgExplore4)
                .placeholder(R.drawable.nostory)
                .error(R.drawable.nostory)
                .into(holder.binding.imgExplore4);

        Picasso.get()
                .load(imgExplore5)
                .placeholder(R.drawable.nostory)
                .error(R.drawable.nostory)
                .into(holder.binding.imgExplore5);

        Picasso.get()
                .load(imgExplore6)
                .placeholder(R.drawable.nostory)
                .error(R.drawable.nostory)
                .into(holder.binding.imgExplore6);

        Picasso.get()
                .load(imgExplore7)
                .placeholder(R.drawable.nostory)
                .error(R.drawable.nostory)
                .into(holder.binding.imgExplore7);

        Picasso.get()
                .load(imgExplore8)
                .placeholder(R.drawable.nostory)
                .error(R.drawable.nostory)
                .into(holder.binding.imgExplore8);

        Picasso.get()
                .load(imgExplore9)
                .placeholder(R.drawable.nostory)
                .error(R.drawable.nostory)
                .into(holder.binding.imgExplore9);

        holder.binding.imgExplore1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onclickExploreToDetail(id,usernameExplore1,imgProfilePost
                        ,imgExplore1,numberLike,caption, numberComments , timePost , cheekMyPost);

            }
        });
        holder.binding.imgExplore2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onclickExploreToDetail(id,usernameExplore2,imgProfilePost
                        ,imgExplore2,numberLike,caption, numberComments , timePost , cheekMyPost);
            }
        });
        holder.binding.imgExplore3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onclickExploreToDetail(id,usernameExplore3,imgProfilePost
                        ,imgExplore3,numberLike,caption, numberComments , timePost , cheekMyPost);
            }
        });
        holder.binding.imgExplore4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onclickExploreToDetail(id,usernameExplore4,imgProfilePost
                        ,imgExplore4,numberLike,caption, numberComments , timePost , cheekMyPost);
            }
        });
        holder.binding.imgExplore5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onclickExploreToDetail(id,usernameExplore5,imgProfilePost
                        ,imgExplore5,numberLike,caption, numberComments , timePost , cheekMyPost);
            }
        });
        holder.binding.imgExplore6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onclickExploreToDetail(id,usernameExplore6,imgProfilePost
                        ,imgExplore6,numberLike,caption, numberComments , timePost , cheekMyPost);
            }
        });
        holder.binding.imgExplore7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onclickExploreToDetail(id,usernameExplore7,imgProfilePost
                        ,imgExplore7,numberLike,caption, numberComments , timePost , cheekMyPost);
            }
        });
        holder.binding.imgExplore8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onclickExploreToDetail(id,usernameExplore8,imgProfilePost
                        ,imgExplore8,numberLike,caption, numberComments , timePost , cheekMyPost);
            }
        });
        holder.binding.imgExplore9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onclickExploreToDetail(id,usernameExplore9,imgProfilePost
                        ,imgExplore9,numberLike,caption, numberComments , timePost , cheekMyPost);
            }
        });
    }


    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ItemExploreBinding binding;

        public MyViewHolder(@NonNull ItemExploreBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
