package com.android.redditreader.views.adapter.viewholder;

import android.com.redditreader.R;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.redditreader.common.utils.DateUtil;
import com.android.redditreader.dataaccess.entities.Thread;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThreadViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.thread_info)
    TextView mThreadInfo;

    @BindView(R.id.thread_title)
    TextView mThreadTitle;

    @BindView(R.id.thread_score)
    TextView mThreadScore;

    @BindView(R.id.thread_comments)
    TextView mThreadComments;

    @BindView(R.id.thread_thumbnail)
    ImageView mThreadThumbnail;

    private String bulletPointSymbol;

    public ThreadViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        bulletPointSymbol = itemView.getResources().getString(R.string.bullet_point_symbol);
    }

    public void bindThread(Thread thread) {
        mThreadTitle.setText(thread.getTitle());
        mThreadInfo.setText(getThreadInfoString(thread));
        mThreadScore.setText(thread.getScore());
        mThreadComments.setText(thread.getNum_comments());
        Picasso.with(itemView.getContext())
                .load(thread.getThumbnail())
                .fit()
                .centerCrop()
                .into(mThreadThumbnail);
    }

    private String getThreadInfoString(Thread thread) {
        return thread.getSubreddit_name_prefixed()
                + " " + bulletPointSymbol + " "
                + DateUtil.getRelativeTimeString(thread.getCreated_utc())
                + " " + bulletPointSymbol + " "
                + thread.getDomain();
    }
}
