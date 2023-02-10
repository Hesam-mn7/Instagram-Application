package com.alroid.instagramhesam.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alroid.instagramhesam.Di.Component.DaggerRandomComponent;
import com.alroid.instagramhesam.Model.Room.Entity.Igtv;
import com.alroid.instagramhesam.R;
import com.alroid.instagramhesam.View.Activity.DetailsVideoActivity;
import com.alroid.instagramhesam.ViewModel.IgtvViewModel;
import com.alroid.instagramhesam.databinding.ItemIgtvBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IgtvAdapter extends RecyclerView.Adapter<IgtvAdapter.MyViewHolder> {

    private IgtvViewModel viewModel;
    private List<Igtv> list;

    Context context;

    public IgtvAdapter(IgtvViewModel igtvViewModel, Context context) {
        this.list = new ArrayList<>();
        this.viewModel = igtvViewModel;
        this.context = context;

    }

    public void setList(List<Igtv> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemIgtvBinding binding = ItemIgtvBinding
                .inflate(LayoutInflater.from(parent.getContext())
                        , parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Igtv igtv = this.list.get(position);

        int id = igtv.getId();
        String usernameIgtv = igtv.getUsernameIgtv();
        String captionIgtv = igtv.getCaptionIgtv();
        String imgProfileIgtv = igtv.getImgProfileIgtv();
        String imgIgtv = igtv.getImgIgtv();
        String videoUrlIgtv = igtv.getVideoUrlIgtv();

        //Random r = new Random();
        Random r = DaggerRandomComponent.create().getRandom();

        String numberView = String.valueOf(r.nextInt(900 - 10 + 1) + 10);
        String numberComments = String.valueOf(r.nextInt(999 - 10 + 1) + 10);
        String timePost = String.valueOf(r.nextInt(23 - 1 + 1) + 1);

        holder.binding.tvUsernameIgtv.setText(usernameIgtv);
        holder.binding.tvCaptionIgtv.setText(captionIgtv);
        holder.binding.tvViewsIgtv.setText(numberView+"k views");

        Uri uriImgIgtv = Uri.parse(imgIgtv);
        Picasso.get()
                .load(uriImgIgtv)
                .placeholder(R.drawable.nostory)
                .error(R.drawable.nostory)
                .into(holder.binding.imgIgtv);

        holder.binding.cardIgtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsVideoActivity.class);
                intent.putExtra("usernameIgtv",usernameIgtv);
                intent.putExtra("captionIgtv",captionIgtv);
                intent.putExtra("imgProfileIgtv",imgProfileIgtv);
                intent.putExtra("imgIgtv",imgIgtv);
                intent.putExtra("videoUrlIgtv",videoUrlIgtv);
                intent.putExtra("numberView",numberView);
                intent.putExtra("numberComments",numberComments);
                intent.putExtra("timePost",timePost);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ItemIgtvBinding binding;
        public MyViewHolder(@NonNull ItemIgtvBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
