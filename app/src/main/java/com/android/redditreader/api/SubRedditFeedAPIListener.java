package com.android.redditreader.api;


import com.android.redditreader.dataaccess.entities.SubRedditResponse;

public interface SubRedditFeedAPIListener {

    void onGetSubRedditsSuccess(SubRedditResponse redditResponse);

    void onRequestFail(String error);


}
