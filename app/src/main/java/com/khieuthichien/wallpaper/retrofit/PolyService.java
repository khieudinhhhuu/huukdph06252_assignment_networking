package com.khieuthichien.wallpaper.retrofit;

import com.khieuthichien.wallpaper.model.Post;
import com.khieuthichien.wallpaper.modelCategory.Category;
import com.khieuthichien.wallpaper.modelMediaOfPost.MediaOfPost;
import com.khieuthichien.wallpaper.modelPostInCategory.PostInCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PolyService {

    //http://asian.dotplays.com/wp-json/wp/v2/posts?category=18&per_page=5&paging=1

    //http://asian.dotplays.com/wp-json/wp/v2/categories?page=1&%20per_page=10

    //http://asian.dotplays.com/wp-json/wp/v2/posts?categories=26

    //http://asian.dotplays.com/wp-json/wp/v2/media?parent=377

    @GET("wp-json/wp/v2/posts")
    Call<List<Post>> getPostOfCategory(@Query("category") String categoryID,
                                       @Query("per_page") String per_page,
                                       @Query("paging") String paging
    );

    @GET("wp-json/wp/v2/categories")
    Call<List<Category>> getCategory(@Query("page") String page,
                                     @Query("per_page") String per_page
    );

    @GET("wp-json/wp/v2/posts")
    Call<List<PostInCategory>> getPostInCategory(@Query("categories") String categories
    );

    @GET("wp-json/wp/v2/media")
    Call<List<MediaOfPost>> getMediaOfPost(@Query("parent") String parent
    );

}
