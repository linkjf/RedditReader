package com.android.redditreader.views.presenter;

import com.android.redditreader.api.SubRedditConstant;
import com.android.redditreader.api.SubRedditFeedAPIListener;
import com.android.redditreader.api.SubRedditFeedClient;
import com.android.redditreader.dataaccess.entities.SubRedditResponse;
import com.android.redditreader.views.viewdefinition.HomeView;

public class HomeViewPresenterImp implements HomeViewPresenter, SubRedditFeedAPIListener {

    private HomeView mHomeView;
    private SubRedditFeedClient mSubRedditFeedClient;
    private int mItemCount = 0;
    private String mCurrentSubReddit;
    private String mCurrentListing;
    private String mBefore;
    private String mAfter;

    public HomeViewPresenterImp(HomeView homeView) {
        this.mHomeView = homeView;
        this.mSubRedditFeedClient = new SubRedditFeedClient(this);
    }

    @Override
    public void listSubReddit() {
        mSubRedditFeedClient.getSubreddit(SubRedditConstant.ALL_SUBREDDIT, SubRedditConstant.NEW_LISTING,
                SubRedditConstant.PAGINATION_OFFSET, 0, null, null);
        mHomeView.showLoadingScreen("");

        mCurrentSubReddit = SubRedditConstant.ALL_SUBREDDIT;
        mCurrentListing = SubRedditConstant.NEW_LISTING;
        mItemCount = 0;

    }

    @Override
    public void listSubReddit(String subReddit, String listing) {

        mCurrentSubReddit = subReddit;
        mCurrentListing = listing;
        mItemCount = 0;
        mSubRedditFeedClient.getSubreddit(subReddit, listing, SubRedditConstant.PAGINATION_OFFSET, 0, null, null);
        mHomeView.showLoadingScreen("");
    }

    @Override
    public void loadMoreThreads() {
        mSubRedditFeedClient.getSubreddit(mCurrentSubReddit, mCurrentListing, SubRedditConstant.PAGINATION_OFFSET, mItemCount, null, mAfter);
        mHomeView.showLoadingScreen("");
    }

    @Override
    public void onGetSubRedditsSuccess(SubRedditResponse redditResponse) {
        mAfter = redditResponse.getAfter();
        if (mItemCount == 0) {
            mHomeView.listNewSubRedditThreads(redditResponse);
        } else {
            mHomeView.appendSubRedditThreads(redditResponse);
        }
        mItemCount += SubRedditConstant.PAGINATION_OFFSET;
        mHomeView.hideLoadingScreen();
    }

    @Override
    public void onRequestFail(String error) {
        mHomeView.hideLoadingScreen();
        mHomeView.showError("", error);
    }
}
