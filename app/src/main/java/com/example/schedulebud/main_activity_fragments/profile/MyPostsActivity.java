package com.example.schedulebud.main_activity_fragments.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.schedulebud.R;
import com.example.schedulebud.main_activity_fragments.home.BackendThreadInfo;
import com.example.schedulebud.main_activity_fragments.home.HomeRecyclerViewAdapter;
import com.example.schedulebud.main_activity_fragments.home.HomeThreadInfo;
import com.example.schedulebud.prefConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyPostsActivity extends AppCompatActivity {

    private RecyclerView myPostsRecyclerView;
    private TextView myPostsErrorTextView;
    private ImageButton myPostsCloseBtn;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_posts);
        myPostsRecyclerView = findViewById(R.id.myPostsRecyclerView);
        myPostsErrorTextView = findViewById(R.id.myPostsErrorTextView);
        myPostsCloseBtn = findViewById(R.id.myPostsCloseBtn);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null) {
            token = (String) bundle.get("token");
        }
        getThreads();
        myPostsCloseBtn.setOnClickListener(view -> finish());
    }

    private void getThreads() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String URL = prefConfig.getServer()+"thread/byuser?batch=1&limit=5";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, response -> {
            Log.i("VOLLEY", response);
            try {
                myPostsErrorTextView.setVisibility(View.GONE);
                JSONObject res = new JSONObject(response);
                JSONArray data = (JSONArray) res.get("data");
                ArrayList<BackendThreadInfo> backendThreadInfoArrayList = new ArrayList<>();
                if (data != null) {
                    if (data.length()==0) {
                        myPostsErrorTextView.setText("No posts yet. Post something now!");
                        myPostsErrorTextView.setVisibility(View.VISIBLE);
                    } else {
                        for (int i=0;i<data.length();i++){
                            JSONObject backendThread = (JSONObject) data.get(i);
                            int thread_id = Integer.parseInt(backendThread.get("thread_id").toString());
                            int user_id = Integer.parseInt(backendThread.get("user_id").toString());
                            String content = backendThread.get("content").toString();
                            int is_anonymous = Integer.parseInt(backendThread.get("is_anonymous").toString());
                            BackendThreadInfo backendThreadInfo = new BackendThreadInfo(thread_id, user_id, content, is_anonymous);
                            backendThreadInfoArrayList.add(backendThreadInfo);
                            updateMyPostsRecyclerView(backendThreadInfoArrayList);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Log.e("VOLLEY", error.toString());
            try {
                String responseBody = new String(error.networkResponse.data, "utf-8");
                JSONObject data = new JSONObject(responseBody);
                String message = data.get("message").toString();
                myPostsErrorTextView.setText("Something went wrong");
                myPostsErrorTextView.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            } catch (UnsupportedEncodingException | JSONException e) {
                e.printStackTrace();
            }
        }) {

            @Override
            public Map<String, String> getHeaders() {
                Map<String,String> params = new HashMap<>();
                params.put("auth-token",token);
                return params;
            }

        };
        Log.d("string", stringRequest.toString());
        requestQueue.add(stringRequest);
    }

    private void updateMyPostsRecyclerView(ArrayList<BackendThreadInfo> backendThreadInfoArrayList) {
        ArrayList<HomeThreadInfo> homeThreadInfoData = new ArrayList<>();

        for (int i = 0; i < backendThreadInfoArrayList.size(); i++) {
            homeThreadInfoData.add(new HomeThreadInfo(backendThreadInfoArrayList.get(i)));
        }

        HomeRecyclerViewAdapter adapter = new HomeRecyclerViewAdapter(getApplicationContext(), homeThreadInfoData);
        myPostsRecyclerView.setHasFixedSize(true);
        myPostsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        myPostsRecyclerView.setAdapter(adapter);
    }
}