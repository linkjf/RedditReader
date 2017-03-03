package com.android.redditreader.views.activity;

import android.com.redditreader.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.android.redditreader.api.SubRedditConstant;
import com.android.redditreader.dataaccess.entities.SubRedditResponse;
import com.android.redditreader.views.adapter.SubRedditAdapter;
import com.android.redditreader.views.adapter.SubRedditSortSpinnerAdapter;
import com.android.redditreader.views.adapter.SubRedditSpinnerAdapter;
import com.android.redditreader.views.presenter.HomeViewPresenter;
import com.android.redditreader.views.presenter.HomeViewPresenterImp;
import com.android.redditreader.views.viewdefinition.HomeView;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements HomeView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.thread_recycler_view)
    RecyclerView mThreadRecyclerView;
    @BindView(R.id.sub_reedit_spinner)
    Spinner subRedditSpinner;
    @BindView(R.id.sort_spinner)
    Spinner sortSpinner;

    private LinearLayoutManager mLayoutManager;
    private SubRedditAdapter mSubRedditAdapter;
    private HomeViewPresenter mHomeViewPresenter;
    private SubRedditSpinnerAdapter subRedditSpinnerAdapter;

    private boolean loading = true;
    private int visibleItemCount;
    private int totalItemCount;
    private int firstVisibleItem;
    private int previousTotal;
    private int visibleThreshold = 5;
    private boolean mUserIsInteracting = false;
    private boolean mCanLoadMore = true;

    private String mSelectedSubreddit = SubRedditConstant.DEFAULT_SUBREDDIT;
    private String mSelectedSubredditSort = SubRedditConstant.DEFAULT_SORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        buildThreadList();
        buildSubRedditSpinner();
        buildSortSpinner();
        buildPresenter();
        requestSubReddit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void buildThreadList() {
        mLayoutManager = new LinearLayoutManager(this);
        mThreadRecyclerView.setLayoutManager(mLayoutManager);
        mThreadRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = mThreadRecyclerView.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (mCanLoadMore && !loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                    mHomeViewPresenter.loadMoreThreads();
                    loading = true;
                    mCanLoadMore = false;
                }
            }
        });
        mSubRedditAdapter = new SubRedditAdapter();
        mThreadRecyclerView.setAdapter(mSubRedditAdapter);
    }

    private void buildSubRedditSpinner() {
        subRedditSpinnerAdapter = new SubRedditSpinnerAdapter(Arrays.asList(getResources().getStringArray(R.array.default_subreddits)));
        subRedditSpinnerAdapter.setSelectedSort(mSelectedSubredditSort);
        subRedditSpinner.setAdapter(subRedditSpinnerAdapter);
        subRedditSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (mUserIsInteracting) {
                    mSelectedSubreddit = subRedditSpinner.getItemAtPosition(position).toString().toLowerCase();
                    requestSubReddit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void buildSortSpinner() {
        SubRedditSortSpinnerAdapter subRedditSortSpinnerAdapter = new SubRedditSortSpinnerAdapter(Arrays.asList(getResources().getStringArray(R.array.default_subreddits_sort)));
        sortSpinner.setAdapter(subRedditSortSpinnerAdapter);
        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (mUserIsInteracting) {
                    String selectedItem = sortSpinner.getItemAtPosition(position).toString();
                    subRedditSpinnerAdapter.setSelectedSort(selectedItem);
                    mSelectedSubredditSort = selectedItem;
                    requestSubReddit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void buildPresenter() {
        mHomeViewPresenter = new HomeViewPresenterImp(this);
    }

    private void requestSubReddit() {
        mHomeViewPresenter.listSubReddit(mSelectedSubreddit.toLowerCase(), mSelectedSubredditSort.toLowerCase());
        mSubRedditAdapter.clear();
        mCanLoadMore = false;
    }

    private void resetScroll() {
        visibleItemCount = 0;
        totalItemCount = 0;
        firstVisibleItem = 0;
        previousTotal = 0;
        loading = false;
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        this.mUserIsInteracting = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void listNewSubRedditThreads(SubRedditResponse threads) {
        mSubRedditAdapter.update(threads.getThreads());
        resetScroll();
        mCanLoadMore = true;
    }

    @Override
    public void appendSubRedditThreads(SubRedditResponse threads) {
        mSubRedditAdapter.append(threads.getThreads());
        mCanLoadMore = true;
    }

    @Override
    public void showLoadingScreen(String title) {

    }

    @Override
    public void hideLoadingScreen() {

    }

    @Override
    public void showError(String title, String error) {

    }

    @Override
    public void success() {

    }
}
