package com.android.redditreader.views.adapter;

import android.com.redditreader.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.redditreader.dataaccess.entities.Thread;
import com.android.redditreader.views.adapter.viewholder.ThreadViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SubRedditAdapter extends RecyclerView.Adapter<ThreadViewHolder> {

    private List<Thread> mThreads;

    public SubRedditAdapter() {
        mThreads = new ArrayList<>();
    }

    @Override
    public ThreadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thread_item, parent, false);
        return new ThreadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ThreadViewHolder holder, int position) {
        holder.bindThread(mThreads.get(position));
    }

    @Override
    public int getItemCount() {
        return mThreads.size();
    }

    public void update(List<Thread> threads) {
        mThreads.addAll(threads);
        notifyDataSetChanged();
    }

    public void append(List<Thread> threads) {
        for (Thread thread : threads) {
            if (!mThreads.contains(thread)) {
                mThreads.add(thread);
                notifyItemInserted(mThreads.indexOf(thread));
            }
        }
    }

    public void clear() {
        mThreads.clear();
        notifyDataSetChanged();
    }
}
