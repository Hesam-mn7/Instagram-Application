package com.alroid.instagramhesam.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.alroid.instagramhesam.Di.Component.DaggerItemViewHolderComponent;
import com.alroid.instagramhesam.Di.Module.ItemViewHolderModule;
import com.alroid.instagramhesam.Model.Room.Entity.Message;
import com.alroid.instagramhesam.R;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;


public class AdapterChatUser extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int CHAT_ME = 100;
    private final int CHAT_YOU = 200;

    private List<Message> items = new ArrayList<>();

    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, Message obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public AdapterChatUser(Context context) {
        ctx = context;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView text_content;
        public View lyt_parent;
        public ImageView imageView;

        public ItemViewHolder(View v) {
            super(v);
            text_content = v.findViewById(R.id.text_content);
            lyt_parent = v.findViewById(R.id.lyt_parent);
            imageView = v.findViewById(R.id.imgProfilechatt);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == CHAT_ME) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_me, parent, false);
            //vh = new ItemViewHolder(v);
            vh = DaggerItemViewHolderComponent
                    .builder()
                    .itemViewHolderModule(new ItemViewHolderModule(v))
                    .build()
                    .getItemViewHolder();
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_you, parent, false);
           // vh = new ItemViewHolder(v);
            vh = DaggerItemViewHolderComponent
                    .builder()
                    .itemViewHolderModule(new ItemViewHolderModule(v))
                    .build()
                    .getItemViewHolder();

        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ItemViewHolder) {
            final Message m = items.get(position);
            ItemViewHolder vItem = (ItemViewHolder) holder;
            vItem.text_content.setText(m.getContent());

            Picasso.get()
                    .load(m.getImgProfileChatt())
                    .placeholder(R.drawable.nostory)
                    .error(R.drawable.nostory)
                    .into(vItem.imageView);

            vItem.lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(view, m, position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return this.items.get(position).isFromMe() ? CHAT_ME : CHAT_YOU;
    }

    public void insertItem(Message item) {
        this.items.add(item);
        notifyItemInserted(getItemCount());
    }

    public void setItems(List<Message> items) {
        this.items = items;
    }
}