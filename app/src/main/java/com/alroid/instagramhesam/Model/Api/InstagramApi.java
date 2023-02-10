package com.alroid.instagramhesam.Model.Api;

import com.alroid.instagramhesam.Model.Room.Entity.Direct;
import com.alroid.instagramhesam.Model.Room.Entity.Explore;
import com.alroid.instagramhesam.Model.Room.Entity.Igtv;
import com.alroid.instagramhesam.Model.Room.Entity.Post;
import com.alroid.instagramhesam.Model.Room.Entity.Story;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InstagramApi {
    String BASE_URL="https://run.mocky.io/";

    @GET("v3/ca36b7c4-6c63-41b7-8c9b-54a37e6db227")
    Call<List<Story>> insertStory();

    @GET("v3/44f7d35d-65ac-4b1c-993f-601be75c9df9")
    Call<List<Post>> insertPost();

    @GET("v3/1645f92a-4abc-4735-9548-658e73e53a3f")
    Call<List<Explore>> insertExplore();

    @GET("v3/9c86bfd3-ab17-4788-98c0-46e9c502bc45")
    Call<List<Igtv>> insertIgtv();

    @GET("v3/012b403c-2313-4232-bcea-3188db5e11c4")
    Call<List<Direct>> insertDirect();
}
