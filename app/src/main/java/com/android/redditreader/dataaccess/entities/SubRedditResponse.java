package com.android.redditreader.dataaccess.entities;

import java.util.List;

public class SubRedditResponse {

    private String before;
    private String after;

    private List<Thread> threads;

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }


    public List<Thread> getThreads() {
        return threads;
    }

    public void setThreads(List<Thread> threads) {
        this.threads = threads;
    }
}
