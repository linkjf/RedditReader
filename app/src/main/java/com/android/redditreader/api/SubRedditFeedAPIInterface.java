package com.android.redditreader.api;

import com.android.redditreader.dataaccess.entities.SubRedditResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SubRedditFeedAPIInterface {

    @GET("r/{subreddit}/{listing}/.json")
    Call<SubRedditResponse> getSubReddits(@Path("subreddit") String subreddit, @Path("listing") String listing,
                                          @Query("limit") int limit, @Query("count") int count, @Query("before") String before, @Query("after") String after);

}
