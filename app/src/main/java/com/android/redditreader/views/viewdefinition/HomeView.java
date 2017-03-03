package com.android.redditreader.views.viewdefinition;

import com.android.redditreader.dataaccess.entities.SubRedditResponse;

public interface HomeView extends BasicUIView {

    void listNewSubRedditThreads(SubRedditResponse threads);

    void appendSubRedditThreads(SubRedditResponse threads);
}
