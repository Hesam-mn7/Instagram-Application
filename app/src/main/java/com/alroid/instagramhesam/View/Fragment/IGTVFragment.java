package com.alroid.instagramhesam.View.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alroid.instagramhesam.Adapter.ExploreAdapter;
import com.alroid.instagramhesam.Adapter.IgtvAdapter;
import com.alroid.instagramhesam.Const;
import com.alroid.instagramhesam.Di.Component.DaggerIgtvAdapterComponent;
import com.alroid.instagramhesam.Di.Module.IgtvAdapterModule;
import com.alroid.instagramhesam.Model.Room.Entity.Explore;
import com.alroid.instagramhesam.Model.Room.Entity.Igtv;
import com.alroid.instagramhesam.ViewModel.ExploreViewModel;
import com.alroid.instagramhesam.ViewModel.IgtvViewModel;
import com.alroid.instagramhesam.databinding.FragmentIgtvBinding;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static android.content.Context.MODE_PRIVATE;

public class IGTVFragment extends Fragment {
    FragmentIgtvBinding binding;

    IgtvViewModel igtvViewModel;

    @Inject
    IgtvAdapter igtvAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentIgtvBinding.inflate(inflater);

        igtvViewModel = ViewModelProviders.of(getActivity()).get(IgtvViewModel.class);
        initRecycleViewIgtv();

        return binding.getRoot();

    }

    private void initRecycleViewIgtv() {
        //igtvAdapter = new IgtvAdapter(igtvViewModel, getActivity());
        DaggerIgtvAdapterComponent.builder()
                .igtvAdapterModule(new IgtvAdapterModule(igtvViewModel,getActivity()))
                .build().injectIgtv(this);

        binding.recycleViewIgtv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        binding.recycleViewIgtv.setAdapter(igtvAdapter);
        igtvViewModel.select().observe(getActivity(), new Observer<List<Igtv>>() {
            @Override
            public void onChanged(List<Igtv> igtvs) {
                igtvAdapter.setList(igtvs);
            }
        });
    }

}
