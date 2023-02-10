package com.alroid.instagramhesam.View.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alroid.instagramhesam.Adapter.PostAdapter;
import com.alroid.instagramhesam.Adapter.StoryAdapter;

import com.alroid.instagramhesam.Di.Component.DaggerPostAdapterComponent;
import com.alroid.instagramhesam.Di.Component.DaggerStoryAdapterComponent;
import com.alroid.instagramhesam.Di.Module.PostAdapterModule;
import com.alroid.instagramhesam.Di.Module.StoryAdapterModule;
import com.alroid.instagramhesam.Model.Room.Entity.Post;
import com.alroid.instagramhesam.Model.Room.Entity.Story;
import com.alroid.instagramhesam.Model.Room.Entity.User;
import com.alroid.instagramhesam.View.Activity.DirectActivity;
import com.alroid.instagramhesam.ViewModel.PostViewModel;
import com.alroid.instagramhesam.ViewModel.StoryViewModel;
import com.alroid.instagramhesam.ViewModel.UserViewModel;
import com.alroid.instagramhesam.databinding.FragmentHomeBinding;

import java.util.List;

import javax.inject.Inject;


public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;

    StoryViewModel storyViewModel;
    PostViewModel postViewModel;
    UserViewModel userViewModel;

    StoryAdapter storyAdapter;

    @Inject
    PostAdapter postAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater);

        storyViewModel = ViewModelProviders.of(getActivity()).get(StoryViewModel.class);
        postViewModel = ViewModelProviders.of(getActivity()).get(PostViewModel.class);
        userViewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);

        initRecycleViewPost();

        initRecycleViewStory();

        onclickItem();

        return binding.getRoot();
    }

    private void initRecycleViewPost() {
        //postAdapter = new PostAdapter(postViewModel, getActivity());
        DaggerPostAdapterComponent.builder()
                .postAdapterModule(new PostAdapterModule(postViewModel,getActivity()))
                .build().inject1(this);

        binding.recycleViewPost.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recycleViewPost.setAdapter(postAdapter);
        postViewModel.select().observe(getActivity(), new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                postAdapter.setList(posts);
            }
        });
    }

    private void initRecycleViewStory() {
        //storyAdapter = new StoryAdapter(storyViewModel, getActivity());
        storyAdapter = DaggerStoryAdapterComponent.builder()
                .storyAdapterModule(new StoryAdapterModule(storyViewModel,getActivity()))
                .build().getStoryAdapter();

        binding.recycleViewStory.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        binding.recycleViewStory.setAdapter(storyAdapter);
        storyViewModel.select().observe((LifecycleOwner) getContext(), new Observer<List<Story>>() {
            @Override
            public void onChanged(List<Story> stories) {
                storyAdapter.setList(stories);

            }
        });
    }

    private void onclickItem() {
        userViewModel.select().observe(getActivity(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                for (User item : users){
                    binding.imgsendinsta.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), DirectActivity.class);
                            intent.putExtra("username",item.getUserName());
                            startActivity(intent);

                        }
                    });
                }
            }
        });
    }

}