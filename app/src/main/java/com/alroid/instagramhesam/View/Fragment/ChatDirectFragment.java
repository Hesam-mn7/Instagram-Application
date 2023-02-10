package com.alroid.instagramhesam.View.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alroid.instagramhesam.Adapter.AdapterChatUser;
import com.alroid.instagramhesam.Di.Component.DaggerAdapterChatUserComponent;
import com.alroid.instagramhesam.Di.Component.DaggerLinearLayoutManagerComponent;
import com.alroid.instagramhesam.Di.Module.AdapterChatUserModule;
import com.alroid.instagramhesam.Di.Module.LinearLayoutManagerModule;
import com.alroid.instagramhesam.Model.Room.Entity.Message;
import com.alroid.instagramhesam.Model.Room.Entity.User;
import com.alroid.instagramhesam.R;
import com.alroid.instagramhesam.View.Activity.CallActivity;
import com.alroid.instagramhesam.ViewModel.UserViewModel;
import com.alroid.instagramhesam.databinding.FragmentChatDirectBinding;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;


public class ChatDirectFragment extends Fragment {

    FragmentChatDirectBinding binding;

    @Inject
    AdapterChatUser chatAdapter;

    UserViewModel userViewModel;

    public static ChatDirectFragment newInstanceChatDirect(int id, String userNameDirect, String imgProfileDirect) {
        ChatDirectFragment fragment = new ChatDirectFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userNameDirect", userNameDirect);
        bundle.putString("imgProfileDirect", imgProfileDirect);
        bundle.putInt("id", id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentChatDirectBinding.inflate(inflater);

        initInformation();
        onclickItems();
        setupComponent();

        return binding.getRoot();
    }

    private void initInformation() {
        if (getArguments() != null && getArguments().containsKey("userNameDirect") && getArguments().containsKey("imgProfileDirect")
                && getArguments().containsKey("id")) {

            String userNameDirect = getArguments().getString("userNameDirect");
            String imgProfileDirect = getArguments().getString("imgProfileDirect");
            binding.etMessage2.setText(imgProfileDirect);

            binding.tvUsernameeDirect.setText(userNameDirect);
            Uri uriImgProfilePost = Uri.parse(imgProfileDirect);
            Picasso.get()
                    .load(uriImgProfilePost)
                    .placeholder(R.drawable.nostory)
                    .error(R.drawable.nostory)
                    .into(binding.imgProfileChat);
        }
    }

    public void setupComponent() {
        //LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        LinearLayoutManager layoutManager = DaggerLinearLayoutManagerComponent.builder()
                .linearLayoutManagerModule(new LinearLayoutManagerModule(getActivity()))
                .build().getLinearLayoutManager();

        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setHasFixedSize(true);

        //chatAdapter = new AdapterChatUser(getActivity());
        DaggerAdapterChatUserComponent.builder().adapterChatUserModule(new AdapterChatUserModule(getActivity())).build().Inject(this);
        binding.recyclerView.setAdapter(chatAdapter);

        userViewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);
        userViewModel.select().observe(getActivity(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                for (User item : users) {
                    String name = item.getName();
                    chatAdapter.insertItem(new Message(chatAdapter.getItemCount(), "Hey " + name + ", you can talk to me", binding.etMessage2.getText().toString(), false));
                    chatAdapter.insertItem(new Message(chatAdapter.getItemCount(), "Ok sure\uD83D\uDE09", binding.etMessage2.getText().toString(), true));
                }
            }
        });

        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendChat();
            }
        });
        binding.etMessage.addTextChangedListener(contentWatcher);

        binding.imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    private void sendChat() {
        final String msg = binding.etMessage.getText().toString();
        if (msg.isEmpty()) return;
        chatAdapter.insertItem(new Message(chatAdapter.getItemCount(), msg, binding.etMessage2.getText().toString(), true));
        binding.etMessage.setText("");
        binding.recyclerView.scrollToPosition(chatAdapter.getItemCount() - 1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                chatAdapter.insertItem(new Message(chatAdapter.getItemCount(), msg, binding.etMessage2.getText().toString(), false));
                binding.recyclerView.scrollToPosition(chatAdapter.getItemCount() - 1);
            }
        }, 1000);
    }

    private TextWatcher contentWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable etd) {
            if (etd.toString().trim().length() == 0) {
                binding.btnSend.setVisibility(View.INVISIBLE);
                binding.imgmircrofon.setVisibility(View.VISIBLE);
                binding.imgImogi.setVisibility(View.VISIBLE);
                binding.imgbackground.setVisibility(View.VISIBLE);
            } else {
                binding.btnSend.setVisibility(View.VISIBLE);
                binding.imgmircrofon.setVisibility(View.INVISIBLE);
                binding.imgImogi.setVisibility(View.INVISIBLE);
                binding.imgbackground.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        }
    };

    private void onclickItems() {
        binding.constChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        binding.imgVideoChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CallActivity.class);
                intent.putExtra("Username",binding.tvUsernameeDirect.getText());
                intent.putExtra("profilePic",binding.etMessage2.getText().toString());
                startActivity(intent);
            }
        });
        binding.imgCallChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CallActivity.class);
                intent.putExtra("Username",binding.tvUsernameeDirect.getText());
                intent.putExtra("profilePic",binding.etMessage2.getText().toString());
                startActivity(intent);
            }
        });
        binding.imgmircrofon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Voice", Snackbar.LENGTH_SHORT).show();
            }
        });
        binding.imgbackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Select pic", Snackbar.LENGTH_SHORT).show();
            }
        });
        binding.imgImogi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Emogi", Snackbar.LENGTH_SHORT).show();
            }
        });

    }

}
