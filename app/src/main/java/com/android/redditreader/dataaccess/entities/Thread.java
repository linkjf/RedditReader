package com.android.redditreader.dataaccess.entities;

public class Thread {

    private String id;
    private String title;
    private String subreddit;
    private String subreddit_name_prefixed;
    private String score;
    private String num_comments;
    private String domain;
    private String thumbnail;
    private String post_hint;
    private String author;
    private long created;
    private long created_utc;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public String getSubreddit_name_prefixed() {
        return subreddit_name_prefixed;
    }

    public String getScore() {
        return score;
    }

    public String getNum_comments() {
        return num_comments;
    }

    public String getDomain() {
        return domain;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getPost_hint() {
        return post_hint;
    }

    public String getAuthor() {
        return author;
    }

    public long getCreated() {
        return created;
    }

    public long getCreated_utc() {
        return created_utc;
    }
}
