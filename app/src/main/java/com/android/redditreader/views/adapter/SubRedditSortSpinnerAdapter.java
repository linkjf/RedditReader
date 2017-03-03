package com.android.redditreader.views.adapter;

import android.com.redditreader.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubRedditSortSpinnerAdapter extends BaseAdapter {

    private List<String> subRedditsSort;

    public SubRedditSortSpinnerAdapter(List<String> subRedditsSort) {
        this.subRedditsSort = subRedditsSort;
    }

    @Override
    public int getCount() {
        return subRedditsSort.size();
    }

    @Override
    public Object getItem(int position) {
        return subRedditsSort.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.subreddit_sort_selected_item, parent, false);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        SubRedditSortViewHolder subRedditSortViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.subreddit_sort_item, parent, false);
            subRedditSortViewHolder = new SubRedditSortViewHolder(convertView);
            convertView.setTag(subRedditSortViewHolder);
        } else {
            subRedditSortViewHolder = (SubRedditSortViewHolder) convertView.getTag();
        }
        subRedditSortViewHolder.subRedditSortTitle.setText(subRedditsSort.get(position));
        return convertView;
    }

    public class SubRedditSortViewHolder {

        @BindView(R.id.sort_title)
        TextView subRedditSortTitle;

        public SubRedditSortViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
