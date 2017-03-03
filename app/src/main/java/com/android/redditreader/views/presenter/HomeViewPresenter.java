package com.android.redditreader.views.presenter;

public interface HomeViewPresenter {

    void listSubReddit();

    void listSubReddit(String subReddit, String listing);

    void loadMoreThreads();
}
