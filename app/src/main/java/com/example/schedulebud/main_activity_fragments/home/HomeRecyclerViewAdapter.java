package com.example.schedulebud.main_activity_fragments.home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulebud.R;

import java.util.ArrayList;
import java.util.List;

public class HomeRecyclerViewAdapter  extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {

    private ArrayList<HomeThreadInfo> mData;
    private LayoutInflater mInflater;
    private Context context;

    public HomeRecyclerViewAdapter(Context context, ArrayList<HomeThreadInfo> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.homerecyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HomeThreadInfo homeThreadInfo = mData.get(position);
        holder.threadPosterName.setText(Integer.toString(homeThreadInfo.getUser_id()));
        holder.threadTitle.setText(homeThreadInfo.getTitle());
        holder.threadContent.setText(homeThreadInfo.getContent());
        holder.threadLikeCount.setText(Integer.toString(homeThreadInfo.getLike_count()));
        holder.threadCommentCount.setText(Integer.toString(homeThreadInfo.getComment_count()));
        holder.threadPosterPic.setImageResource(R.drawable.ic_baseline_person_24);
        holder.threadLikeBtn.setImageResource(R.drawable.heart_outline);
        holder.threadCommentBtn.setImageResource(R.drawable.ic_baseline_chat_bubble_outline_24);
        holder.threadSettingsBtn.setImageResource(R.drawable.ic_baseline_more_vert_24);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView threadPosterName, threadPostDate, threadTitle, threadContent, threadLikeCount, threadCommentCount;
        ImageView threadPosterPic;
        ImageButton threadSettingsBtn, threadLikeBtn, threadCommentBtn;

        ViewHolder(View itemView) {
            super(itemView);
            threadPosterName = itemView.findViewById(R.id.threadPosterName);
            threadPostDate = itemView.findViewById(R.id.threadPostDate);
            threadTitle = itemView.findViewById(R.id.threadTitle);
            threadContent = itemView.findViewById(R.id.threadContent);
            threadLikeCount = itemView.findViewById(R.id.threadLikeCount);
            threadCommentCount = itemView.findViewById(R.id.threadCommentCount);
            threadPosterPic = itemView.findViewById(R.id.threadPosterPic);
            threadSettingsBtn = itemView.findViewById(R.id.threadSettingsBtn);
            threadLikeBtn = itemView.findViewById(R.id.threadLikeBtn);
            threadCommentBtn = itemView.findViewById(R.id.threadCommentBtn);

            //TODO manage like, comment, edit, delete
        }
    }
}