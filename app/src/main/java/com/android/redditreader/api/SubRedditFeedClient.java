package com.android.redditreader.api;

import com.android.redditreader.common.application.RedditReaderApplication;
import com.android.redditreader.dataaccess.entities.SubRedditResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubRedditFeedClient implements Callback<SubRedditResponse> {

    private SubRedditFeedAPIInterface mSubRedditFeedAPIInterface;
    private SubRedditFeedAPIListener mSubRedditFeedAPIListener;

    public SubRedditFeedClient(SubRedditFeedAPIListener mSubRedditFeedAPIListener) {
        this.mSubRedditFeedAPIListener = mSubRedditFeedAPIListener;
        mSubRedditFeedAPIInterface = RedditReaderApplication.getRetrofit().create(SubRedditFeedAPIInterface.class);
    }

    public void getSubreddit(String subreddit, String listing, int offset, int count, String before, String after) {
        Call<SubRedditResponse> subReddits = mSubRedditFeedAPIInterface.getSubReddits(subreddit, listing, offset, count, before, after);
        subReddits.enqueue(this);
    }

    @Override
    public void onResponse(Call<SubRedditResponse> call, Response<SubRedditResponse> response) {
        mSubRedditFeedAPIListener.onGetSubRedditsSuccess(response.body());
    }

    @Override
    public void onFailure(Call<SubRedditResponse> call, Throwable t) {
        mSubRedditFeedAPIListener.onRequestFail(call.toString());
    }
}
