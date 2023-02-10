package com.alroid.instagramhesam.View.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.room.ColumnInfo;

import com.alroid.instagramhesam.Adapter.ExploreAdapter;
import com.alroid.instagramhesam.Const;
import com.alroid.instagramhesam.Di.Component.DaggerExploreAdapterComponent;
import com.alroid.instagramhesam.Di.Component.DaggerExploreComponent;
import com.alroid.instagramhesam.Di.Module.ExploreAdapterModule;
import com.alroid.instagramhesam.Model.Room.Entity.Explore;
import com.alroid.instagramhesam.ViewModel.ExploreViewModel;
import com.alroid.instagramhesam.databinding.FragmentExploreBinding;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static android.content.Context.MODE_PRIVATE;


public class ExploreFragment extends Fragment {
    FragmentExploreBinding binding;

    ExploreViewModel exploreViewModel;

    @Inject
    ExploreAdapter exploreAdapter;

    public interface CallbackFragmentExplore
    {
        void onclickExploreToDetail(int id , String usernamePost, String imgProfilePost
                ,String imgPost , String numberLike , String caption , String numberComments , String timePost , String cheekMyPost );
    }
    CallbackFragmentExplore listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof CallbackFragmentExplore) {
            listener = (CallbackFragmentExplore) context;
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentExploreBinding.inflate(inflater);

        exploreViewModel = ViewModelProviders.of(getActivity()).get(ExploreViewModel.class);

        initRecycleViewExplore();

        return binding.getRoot();
    }

    private void initRecycleViewExplore() {
        //exploreAdapter = new ExploreAdapter(exploreViewModel, getActivity(),listener);
        DaggerExploreAdapterComponent.builder()
                .exploreAdapterModule(new ExploreAdapterModule(exploreViewModel,getActivity(),listener))
                .build().inject(this);

        binding.recycleViewExplore.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recycleViewExplore.setAdapter(exploreAdapter);
        exploreViewModel.select().observe(getActivity(), new Observer<List<Explore>>() {
            @Override
            public void onChanged(List<Explore> explores) {
                exploreAdapter.setList(explores);
            }
        });
    }


}