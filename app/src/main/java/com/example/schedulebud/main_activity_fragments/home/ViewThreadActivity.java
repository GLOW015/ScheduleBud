package com.example.schedulebud.main_activity_fragments.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.RecoverySystem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.schedulebud.R;
import com.google.gson.Gson;

public class ViewThreadActivity extends AppCompatActivity {

    private ImageButton viewThreadBackBtn, viewThreadLikeBtn, viewThreadCommentBtn;
    private ImageView viewThreadProfilePic, viewThreadCommentPic;
    private TextView viewThreadPosterName, viewThreadPostedDate, viewThreadTitle, viewThreadContent, viewThreadLikeCount;
    private RecyclerView viewThreadRecyclerView;
    private EditText viewThreadCommentEditText;
    private HomeThreadInfo homeThreadInfo;
    private boolean liked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_thread);

        viewThreadBackBtn = findViewById(R.id.viewThreadBackBtn);
        viewThreadLikeBtn = findViewById(R.id.viewThreadLikeBtn);
        viewThreadCommentBtn = findViewById(R.id.viewThreadCommentBtn);
        viewThreadProfilePic = findViewById(R.id.viewThreadProfilePic);
        viewThreadCommentPic = findViewById(R.id.viewThreadCommentPic);
        viewThreadPosterName = findViewById(R.id.viewThreadPosterName);
        viewThreadPostedDate = findViewById(R.id.viewThreadPostedDate);
        viewThreadTitle = findViewById(R.id.viewThreadTitle);
        viewThreadContent = findViewById(R.id.viewThreadContent);
        viewThreadLikeCount = findViewById(R.id.viewThreadLikeCount);
        viewThreadRecyclerView = findViewById(R.id.viewThreadRecyclerView);
        viewThreadCommentEditText = findViewById(R.id.viewThreadCommentEditText);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null) {
            String json = (String) bundle.get("homeThreadInfo");
            Gson gson = new Gson();
            homeThreadInfo = gson.fromJson(json, HomeThreadInfo.class);
        }

        if (homeThreadInfo.getLike_count()==1) {
            viewThreadLikeBtn.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.red, null));
            liked = true;
        } else {
            viewThreadLikeBtn.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.black, null));
            liked = false;
        }

        viewThreadBackBtn.setOnClickListener(view -> finish());

        viewThreadLikeBtn.setOnClickListener(view -> {
            if (liked) {
                viewThreadLikeBtn.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.black, null));
                int likeCount = Integer.parseInt(viewThreadLikeCount.getText().toString()) - 1;
                viewThreadLikeCount.setText(Integer.toString(likeCount));
                homeThreadInfo.setLike_count(likeCount);
                //TODO connect to backend
            } else {
                viewThreadLikeBtn.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.red, null));
                int likeCount = Integer.parseInt(viewThreadLikeCount.getText().toString()) + 1;
                viewThreadLikeCount.setText(Integer.toString(likeCount));
                homeThreadInfo.setLike_count(likeCount);
                //TODO connect to backend
            }
            liked = !liked;
        });

        viewThreadCommentBtn.setOnClickListener(view -> {
            //TODO connect to backend
            String comment = viewThreadCommentEditText.getText().toString();
        });

        viewThreadPosterName.setText(Integer.toString(homeThreadInfo.getUser_id()));
        viewThreadTitle.setText(homeThreadInfo.getTitle());
        viewThreadContent.setText(homeThreadInfo.getContent());
        viewThreadLikeCount.setText(Integer.toString(homeThreadInfo.getLike_count()));

        //TODO populate viewThreadRecyclerView with thread comments
    }
}