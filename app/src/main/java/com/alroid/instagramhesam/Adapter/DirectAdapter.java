package com.alroid.instagramhesam.Adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.alroid.instagramhesam.Model.Room.Entity.Direct;
import com.alroid.instagramhesam.Model.Room.Entity.Igtv;
import com.alroid.instagramhesam.R;
import com.alroid.instagramhesam.View.Fragment.ChatDirectFragment;
import com.alroid.instagramhesam.ViewModel.DirectViewModel;
import com.alroid.instagramhesam.ViewModel.IgtvViewModel;
import com.alroid.instagramhesam.databinding.ItemDirectBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DirectAdapter extends RecyclerView.Adapter<DirectAdapter.MyViewHolder> implements Filterable {

    private DirectViewModel viewModel;
    private List<Direct> list;
    private List<Direct> temp;


    Context context;

    public DirectAdapter(DirectViewModel directViewModel, Context context) {
        this.list = new ArrayList<>();
        this.viewModel = directViewModel;
        this.context = context;
        this.temp = new ArrayList<>();

    }

    public void setList(List<Direct> list) {
        this.list = list;
        this.temp = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDirectBinding binding = ItemDirectBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new DirectAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Direct direct = this.list.get(position);

        int id = direct.getId();
        String userNameDirect = direct.getUserNameDirect();
        String imgProfileDirect = direct.getImgProfileDirect();
        String textDirect = direct.getTextDirect();

        holder.binding.tvUsernameDirect.setText(userNameDirect);
        holder.binding.tvTextDirect.setText(textDirect);

        Uri uriImgProfileDirect = Uri.parse(imgProfileDirect);
        Picasso.get()
                .load(uriImgProfileDirect)
                .placeholder(R.drawable.nostory)
                .error(R.drawable.nostory)
                .into(holder.binding.imgProfileDirect);

        holder.binding.itemDirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ChatDirectFragment fragment = ChatDirectFragment.newInstanceChatDirect(id,userNameDirect,imgProfileDirect);
                FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.fragmentChat, fragment, "fragmentChat");
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ItemDirectBinding binding;
        public MyViewHolder(@NonNull ItemDirectBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence fiterRequest) {
                FilterResults filterResults = new FilterResults();

                List<Direct> filterlist= new ArrayList<>();
                for(Direct item : temp){
                    if(item.getUserNameDirect().contains(fiterRequest)){
                        filterlist.add(item);
                    }

                }

                filterResults.values=filterlist;
                filterResults.count=filterlist.size();

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                list= (List<Direct>) results.values;
                notifyDataSetChanged();

            }
        };
    }
}
