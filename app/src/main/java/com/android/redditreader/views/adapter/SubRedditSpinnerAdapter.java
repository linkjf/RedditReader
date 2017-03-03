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

public class SubRedditSpinnerAdapter extends BaseAdapter {

    private List<String> subReddits;
    private String selectedSort;

    public SubRedditSpinnerAdapter(List<String> subReddits) {
        this.subReddits = subReddits;
    }

    @Override
    public int getCount() {
        return subReddits.size();
    }

    @Override
    public Object getItem(int position) {
        return subReddits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SubReeditViewHolder subreeditViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.subreddit_selected_item, parent, false);
            subreeditViewHolder = new SubReeditViewHolder(convertView);
            convertView.setTag(subreeditViewHolder);
        } else {
            subreeditViewHolder = (SubReeditViewHolder) convertView.getTag();
        }
        subreeditViewHolder.subRedditTitle.setText(subReddits.get(position));
        subreeditViewHolder.subRedditSortTitle.setText(selectedSort);
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        DropDownViewHolder dropDownViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.subreddit_item, parent, false);
            dropDownViewHolder = new DropDownViewHolder(convertView);
            convertView.setTag(dropDownViewHolder);
        } else {
            dropDownViewHolder = (DropDownViewHolder) convertView.getTag();
        }
        dropDownViewHolder.subRedditTitle.setText(subReddits.get(position));
        return convertView;
    }

    public String getSelectedSort() {
        return selectedSort;
    }

    public void setSelectedSort(String selectedSort) {
        this.selectedSort = selectedSort;
        notifyDataSetChanged();
    }

    public class SubReeditViewHolder {

        @BindView(R.id.subreddit_title)
        TextView subRedditTitle;

        @BindView(R.id.subreddit_sort_title)
        TextView subRedditSortTitle;

        SubReeditViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public class DropDownViewHolder {
        @BindView(R.id.subreddit_title)
        TextView subRedditTitle;

        DropDownViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }
}
