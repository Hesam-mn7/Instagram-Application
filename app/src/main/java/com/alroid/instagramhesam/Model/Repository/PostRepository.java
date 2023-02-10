package com.alroid.instagramhesam.Model.Repository;

import android.app.Application;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.alroid.instagramhesam.Di.Component.DaggerRetrofitComponent;
import com.alroid.instagramhesam.Model.Api.InstagramApi;
import com.alroid.instagramhesam.Model.Room.AppDatabase.PostAppDataBase;
import com.alroid.instagramhesam.Model.Room.AppDatabase.StoryAppDataBase;
import com.alroid.instagramhesam.Model.Room.Dao.PostDao;
import com.alroid.instagramhesam.Model.Room.Entity.Post;
import com.alroid.instagramhesam.Model.Room.Entity.Story;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostRepository {

    private PostDao postDao;
    private LiveData<List<Post>> liveData;
    private InstagramApi api;

    public PostRepository(Application application) {
        this.postDao = PostAppDataBase.getInstance(application).getPostDao();
        liveData = select();

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(InstagramApi.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
        Retrofit retrofit = DaggerRetrofitComponent.create().getRetrofit();

        api = retrofit.create(InstagramApi.class);
    }

    public Completable insert(Post post) {
        Completable completable = new Completable() {
            @Override
            protected void subscribeActual(@NonNull CompletableObserver observer) {
                postDao.insert(post);
                observer.onComplete();
            }
        };
        return completable;
    }

    public Completable deleteById(int id) {
        Completable completable = new Completable() {
            @Override
            protected void subscribeActual(@NonNull CompletableObserver observer) {
                postDao.deleteById(id);
                observer.onComplete();
            }
        };
        return completable;
    }

    public Completable updatePost(int id , String caption) {
        Completable completable = new Completable() {
            @Override
            protected void subscribeActual(@NonNull CompletableObserver observer) {
                postDao.updatePost(id,caption);
                observer.onComplete();
            }
        };
        return completable;
    }

    public Completable updateLike(int id , String cheekLike){
        Completable completable = new Completable() {
            @Override
            protected void subscribeActual(@NonNull CompletableObserver observer) {
                postDao.updateLike(id,cheekLike);
                observer.onComplete();
            }
        };
        return completable;
    }

    public Completable updateSaved(int id , String cheekSaved){
        Completable completable = new Completable() {
            @Override
            protected void subscribeActual(@NonNull CompletableObserver observer) {
                postDao.updateSaved(id,cheekSaved);
                observer.onComplete();
            }
        };
        return completable;
    }

    public LiveData<List<Post>> getPostFromWebServer() {

        LiveData<List<Post>> liveData = new LiveData<List<Post>>() {
            @Override
            public void observe(@androidx.annotation.NonNull LifecycleOwner owner, @androidx.annotation.NonNull Observer<? super List<Post>> observer) {
                super.observe(owner, observer);
                api.insertPost().enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        if (response.code() == 200) {
                            observer.onChanged(response.body());
                        } else {
                            observer.onChanged(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {
                        observer.onChanged(null);
                    }
                });
            }
        };

        return liveData;

    }


    public LiveData<List<Post>> select() {
        return postDao.select();
    }

    public LiveData<List<Post>> selectMyPost(String cheekMyPost){
        return postDao.selectMyPost(cheekMyPost);
    }
}
