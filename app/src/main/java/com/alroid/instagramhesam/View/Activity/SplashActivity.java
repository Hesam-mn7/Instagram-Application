package com.alroid.instagramhesam.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.alroid.instagramhesam.Const;
import com.alroid.instagramhesam.Di.Component.DaggerDirectComponent;
import com.alroid.instagramhesam.Di.Component.DaggerExploreComponent;
import com.alroid.instagramhesam.Di.Component.DaggerIgtvComponent;
import com.alroid.instagramhesam.Di.Component.DaggerPosttComponent;
import com.alroid.instagramhesam.Di.Component.DaggerStoryComponent;
import com.alroid.instagramhesam.Di.Module.DirectModule;
import com.alroid.instagramhesam.Di.Module.ExploreModule;
import com.alroid.instagramhesam.Di.Module.IgtvModule;
import com.alroid.instagramhesam.Di.Module.PosttModule;
import com.alroid.instagramhesam.Di.Module.StoryModule;
import com.alroid.instagramhesam.Model.Room.Entity.Direct;
import com.alroid.instagramhesam.Model.Room.Entity.Explore;
import com.alroid.instagramhesam.Model.Room.Entity.Igtv;
import com.alroid.instagramhesam.Model.Room.Entity.Post;
import com.alroid.instagramhesam.Model.Room.Entity.Story;
import com.alroid.instagramhesam.Model.Room.Entity.User;
import com.alroid.instagramhesam.R;
import com.alroid.instagramhesam.ViewModel.DirectViewModel;
import com.alroid.instagramhesam.ViewModel.ExploreViewModel;
import com.alroid.instagramhesam.ViewModel.IgtvViewModel;
import com.alroid.instagramhesam.ViewModel.PostViewModel;
import com.alroid.instagramhesam.ViewModel.StoryViewModel;
import com.alroid.instagramhesam.ViewModel.UserViewModel;
import com.alroid.instagramhesam.databinding.ActivityMainBinding;
import com.alroid.instagramhesam.databinding.ActivitySplashBinding;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SplashActivity extends BaseActivity {
    ActivitySplashBinding binding;

    StoryViewModel storyViewModel;
    PostViewModel postViewModel;
    UserViewModel userViewModel;
    DirectViewModel directViewModel;
    IgtvViewModel igtvViewModel;
    ExploreViewModel exploreViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        storyViewModel = ViewModelProviders.of(this).get(StoryViewModel.class);
        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        directViewModel = ViewModelProviders.of(this).get(DirectViewModel.class);
        igtvViewModel = ViewModelProviders.of(this).get(IgtvViewModel.class);
        exploreViewModel = ViewModelProviders.of(this).get(ExploreViewModel.class);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isNetworkAvailable()) {

            GetPostFromWebServer();
            GetStoryFromWebServer();
            GetDirectFromWebServer();
            getExploreFromWebServer();
            GetIgtvFromWebServer();

            final Handler handler = new Handler();
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                public void run() {
                    handler.post(new Runnable() {
                        public void run() {
                            Intent intent = new Intent(SplashActivity.this, CreateAccountActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            }, 3000);
        } else {
            return;
        }
    }

    private void GetPostFromWebServer() {
        String result = getSharedPreferencePost(Const.SHARED_PERF_KEY_POST);
        if (result == null) {
            postViewModel.getPostFromWebServer().observe(this, new Observer<List<Post>>() {
                @Override
                public void onChanged(List<Post> posts) {
                    for (Post item : posts) {

//                        Post post = new Post(item.getId(), item.getUsernamePost(), item.getImgProfilePost(), item.getImgPost()
//                                , item.getImgProfileLike(), item.getUsernameLike(), item.getNumberLike(), item.getCaption()
//                                , item.getNumberComments(), item.getTimePost(), item.getCheekLike(), item.getCheekSaved(), item.getCheekMyPost());

                        Post post = DaggerPosttComponent.builder().posttModule(new PosttModule(item.getId(), item.getUsernamePost(), item.getImgProfilePost(), item.getImgPost()
                                , item.getImgProfileLike(), item.getUsernameLike(), item.getNumberLike(), item.getCaption()
                                , item.getNumberComments(), item.getTimePost(), item.getCheekLike(), item.getCheekSaved(), item.getCheekMyPost())).build().getPostt();

                        postViewModel.insert(post).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                                setPreferncePost(Const.SHARED_PERF_KEY_POST, "finish");
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                            }
                        });
                    }
                }
            });
        }
    }

    private void GetStoryFromWebServer() {
        String result = getSharedPreferenceStory(Const.SHARED_PERF_KEY_STORY);
        if (result == null) {
            storyViewModel.getStoryFromWebServer().observe(this, new Observer<List<Story>>() {
                @Override
                public void onChanged(List<Story> stories) {
                    for (Story item : stories) {
                        //Story story = new Story(item.getId(), item.getUserName(), item.getProfilePic(), item.getTime(), item.getPicStory(), item.getSeenStory());

                        Story story = DaggerStoryComponent.builder()
                                .storyModule(new StoryModule(item.getId(), item.getUserName(), item.getProfilePic()
                                        , item.getTime(), item.getPicStory(), item.getSeenStory())).build().getStoryy();

                        storyViewModel.insert(story).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                                setPrefernceStory(Const.SHARED_PERF_KEY_STORY, "finish");

                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                Toast.makeText(SplashActivity.this, "error", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                }
            });
        }
    }

    private void GetDirectFromWebServer() {
        String result = getSharedPreferenceDirect(Const.SHARED_PERF_KEY_DIRECT);
        if (result == null) {
            directViewModel.getDirectFromWebServer().observe(this, new Observer<List<Direct>>() {
                @Override
                public void onChanged(List<Direct> directs) {
                    for (Direct item : directs) {

//                        Direct direct = new Direct(item.getId(), item.getUserNameDirect(), item.getImgProfileDirect(), item.getTextDirect());
                        Direct direct = DaggerDirectComponent.builder()
                                .directModule(new DirectModule(item.getId(), item.getUserNameDirect(), item.getImgProfileDirect()
                                        , item.getTextDirect())).build().getDirects();

                        directViewModel.insert(direct)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new CompletableObserver() {
                                    @Override
                                    public void onSubscribe(@NonNull Disposable d) {

                                    }

                                    @Override
                                    public void onComplete() {
                                        setPrefernceDirect(Const.SHARED_PERF_KEY_DIRECT, "finish");

                                    }

                                    @Override
                                    public void onError(@NonNull Throwable e) {

                                    }
                                });
                    }
                }
            });
        }
    }

    private void GetIgtvFromWebServer() {
        String result = getSharedPreferenceIgtv(Const.SHARED_PERF_KEY_IGTV);
        if (result == null) {
            igtvViewModel.getIgtvFromWebServer().observe(this, new Observer<List<Igtv>>() {
                @Override
                public void onChanged(List<Igtv> igtvs) {
                    for (Igtv item : igtvs) {

//                        Igtv igtv = new Igtv(item.getId(), item.getUsernameIgtv(), item.getCaptionIgtv()
//                                , item.getImgProfileIgtv(), item.getImgIgtv(), item.getVideoUrlIgtv());

                        Igtv igtv = DaggerIgtvComponent.builder().igtvModule(new IgtvModule(item.getId(), item.getUsernameIgtv(), item.getCaptionIgtv()
                                , item.getImgProfileIgtv(), item.getImgIgtv(), item.getVideoUrlIgtv())).build().getIgtvs();

                        igtvViewModel.insert(igtv)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new CompletableObserver() {
                                    @Override
                                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                                    }

                                    @Override
                                    public void onComplete() {
                                        setPrefernceIgtv(Const.SHARED_PERF_KEY_IGTV, "finish");

                                    }

                                    @Override
                                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                                    }
                                });
                    }
                }
            });
        }

    }

    private void getExploreFromWebServer() {
        String result = getSharedPreferenceExplore(Const.SHARED_PERF_KEY_EXPLORE);
        if (result == null) {
            exploreViewModel.getExploreFromWebServer().observe(this, new Observer<List<Explore>>() {
                @Override
                public void onChanged(List<Explore> explores) {
                    for (Explore item : explores) {

//                        Explore explore = new Explore(item.getId(), item.getImgExplore1(), item.getUsernameExplore1()
//                                , item.getImgExplore2(), item.getUsernameExplore2(), item.getImgExplore3(), item.getUsernameExplore3(), item.getImgExplore4()
//                                , item.getUsernameExplore4(), item.getImgExplore5(), item.getUsernameExplore5(), item.getImgExplore6(), item.getUsernameExplore6()
//                                , item.getImgExplore7(), item.getUsernameExplore7(), item.getImgExplore8(), item.getUsernameExplore8(), item.getImgExplore9()
//                                , item.getUsernameExplore9());

                        Explore explore = DaggerExploreComponent.builder().exploreModule(new ExploreModule(item.getId(), item.getImgExplore1(), item.getUsernameExplore1()
                                        , item.getImgExplore2(), item.getUsernameExplore2(), item.getImgExplore3(), item.getUsernameExplore3(), item.getImgExplore4()
                                        , item.getUsernameExplore4(), item.getImgExplore5(), item.getUsernameExplore5(), item.getImgExplore6(), item.getUsernameExplore6()
                                        , item.getImgExplore7(), item.getUsernameExplore7(), item.getImgExplore8(), item.getUsernameExplore8(), item.getImgExplore9()
                                        , item.getUsernameExplore9())).build().getExplores();

                        exploreViewModel.insert(explore)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new CompletableObserver() {
                                    @Override
                                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                                    }

                                    @Override
                                    public void onComplete() {
                                        setPrefernceExplore(Const.SHARED_PERF_KEY_EXPLORE, "finish");

                                    }

                                    @Override
                                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                                    }
                                });
                    }

                }
            });
        }
    }

    public void setPreferncePost(String key, String value) {
        SharedPreferences sharedPreferences = getSharedPreferences(Const.SHARED_PREF_NAME_POST, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getSharedPreferencePost(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(Const.SHARED_PREF_NAME_POST, MODE_PRIVATE);
        if (!sharedPreferences.contains(key)) {
            return null;
        }
        return sharedPreferences.getString(key, null);
    }

    public void setPrefernceDirect(String key, String value) {
        SharedPreferences sharedPreferences = getSharedPreferences(Const.SHARED_PREF_NAME_DIRECT, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getSharedPreferenceDirect(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(Const.SHARED_PREF_NAME_DIRECT, MODE_PRIVATE);
        if (!sharedPreferences.contains(key)) {
            return null;
        }
        return sharedPreferences.getString(key, null);
    }

    public void setPrefernceStory(String key, String value) {
        SharedPreferences sharedPreferences = getSharedPreferences(Const.SHARED_PREF_NAME_STORY, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getSharedPreferenceStory(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(Const.SHARED_PREF_NAME_STORY, MODE_PRIVATE);
        if (!sharedPreferences.contains(key)) {
            return null;
        }
        return sharedPreferences.getString(key, null);
    }

    public void setPrefernceIgtv(String key, String value) {
        SharedPreferences sharedPreferences = getSharedPreferences(Const.SHARED_PREF_NAME_IGTV, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getSharedPreferenceIgtv(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(Const.SHARED_PREF_NAME_IGTV, MODE_PRIVATE);
        if (!sharedPreferences.contains(key)) {
            return null;
        }
        return sharedPreferences.getString(key, null);
    }

    public void setPrefernceExplore(String key, String value) {
        SharedPreferences sharedPreferences = getSharedPreferences(Const.SHARED_PREF_NAME_EXPLORE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getSharedPreferenceExplore(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(Const.SHARED_PREF_NAME_EXPLORE, MODE_PRIVATE);
        if (!sharedPreferences.contains(key)) {
            return null;
        }
        return sharedPreferences.getString(key, null);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}