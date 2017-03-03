package com.android.redditreader.views.adapter;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.android.redditreader.dataaccess.entities.Thread;

import java.util.List;

public class ThreadListDiffCallback extends DiffUtil.Callback {

    private List<Thread> mOldList;
    private List<Thread> mNewList;

    public ThreadListDiffCallback(List<Thread> mOldList, List<Thread> mNewList) {
        this.mOldList = mOldList;
        this.mNewList = mNewList;
    }

    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return this.mOldList.get(oldItemPosition).getId().equals(mNewList.get(newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return this.mOldList.get(oldItemPosition).equals(mNewList.get(newItemPosition));
    }


    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);

//        Thread oldThread = mOldList.get(oldItemPosition);
//        Thread newThread = mNewList.get(newItemPosition);
//        Bundle difference = new Bundle();
//
//        if (newThread.getTitle().equals(oldThread.getTitle())) {
//            difference.putString(SubRedditConstant.KEY_TITLE, newThread.getTitle());
//        }
//
//        if (difference.size() == 0) {
//            return null;
//        }
//        return difference;

    }
}
