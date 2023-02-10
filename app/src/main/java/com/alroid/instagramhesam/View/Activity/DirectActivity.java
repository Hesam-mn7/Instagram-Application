package com.alroid.instagramhesam.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.alroid.instagramhesam.Adapter.DirectAdapter;
import com.alroid.instagramhesam.Adapter.PostAdapter;
import com.alroid.instagramhesam.Const;
import com.alroid.instagramhesam.Di.Component.DaggerDirectAdapterComponent;
import com.alroid.instagramhesam.Di.Module.DirectAdapterModule;
import com.alroid.instagramhesam.Model.Room.Entity.Direct;
import com.alroid.instagramhesam.Model.Room.Entity.Post;
import com.alroid.instagramhesam.R;
import com.alroid.instagramhesam.View.Fragment.ChatDirectFragment;
import com.alroid.instagramhesam.View.Fragment.DetailFragment;
import com.alroid.instagramhesam.ViewModel.DirectViewModel;
import com.alroid.instagramhesam.databinding.ActivityDetailsVideoBinding;
import com.alroid.instagramhesam.databinding.ActivityDirectBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DirectActivity extends BaseActivity {
    ActivityDirectBinding binding;

    @Inject
    DirectAdapter directAdapter;

    DirectViewModel directViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDirectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        directViewModel = ViewModelProviders.of(this).get(DirectViewModel.class);

        initRecycleViewDirect();
        initUsername();
        onclickItems();


    }

    private void onclickItems() {
        binding.imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        binding.imgVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "New Video Chat", Snackbar.LENGTH_SHORT).show();

            }
        });
        binding.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"New Message", Snackbar.LENGTH_SHORT).show();
            }
        });
        binding.tvroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Video Chat with Anyone", Snackbar.LENGTH_SHORT).show();

            }
        });
        binding.tvrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Message Requests", Snackbar.LENGTH_SHORT).show();

            }
        });

    }



    private void initRecycleViewDirect() {
//        directAdapter = new DirectAdapter(directViewModel, this);
        DaggerDirectAdapterComponent.builder()
                .directAdapterModule(new DirectAdapterModule(directViewModel,this))
                .build()
                .inject(this);

        binding.recycleViewDirect.setLayoutManager(new LinearLayoutManager(this));
        binding.recycleViewDirect.setAdapter(directAdapter);
        directViewModel.select().observe(this, new Observer<List<Direct>>() {
            @Override
            public void onChanged(List<Direct> directs) {
                directAdapter.setList(directs);
            }
        });

        binding.etsearchDirect.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                directAdapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private void initUsername(){
        Intent intent= getIntent();
        if (intent == null){
            finish();
            return;
        }
        String username = intent.getStringExtra("username");
        binding.tvUsernameeDirect.setText(username);
    }

}