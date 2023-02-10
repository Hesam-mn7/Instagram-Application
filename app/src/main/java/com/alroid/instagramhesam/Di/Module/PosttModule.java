package com.alroid.instagramhesam.Di.Module;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.alroid.instagramhesam.Model.Room.Entity.Post;

import dagger.Module;
import dagger.Provides;

@Module
public class PosttModule {

    private int id;
    private String UsernamePost;
    private String imgProfilePost;
    private String imgPost;
    private String imgProfileLike;
    private String usernameLike;
    private String numberLike;
    private String caption;
    private String numberComments;
    private String timePost;
    private String cheekLike;
    private String cheekSaved;
    private String cheekMyPost;

    public PosttModule(int id, String usernamePost, String imgProfilePost
            , String imgPost, String imgProfileLike, String usernameLike
            , String numberLike, String caption, String numberComments
            , String timePost, String cheekLike, String cheekSaved, String cheekMyPost) {
        this.id = id;
        UsernamePost = usernamePost;
        this.imgProfilePost = imgProfilePost;
        this.imgPost = imgPost;
        this.imgProfileLike = imgProfileLike;
        this.usernameLike = usernameLike;
        this.numberLike = numberLike;
        this.caption = caption;
        this.numberComments = numberComments;
        this.timePost = timePost;
        this.cheekLike = cheekLike;
        this.cheekSaved = cheekSaved;
        this.cheekMyPost = cheekMyPost;
    }

    @Provides
    public Post providepostt(){
        return new Post(id,UsernamePost,imgProfilePost
                ,imgPost,imgProfileLike,usernameLike
                ,numberLike,caption,numberComments,timePost
                ,cheekLike,cheekSaved,cheekMyPost);
    }

}
