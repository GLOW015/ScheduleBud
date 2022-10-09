package com.example.schedulebud.main_activity_fragments.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.schedulebud.R;
import com.example.schedulebud.prefConfig;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeHomeFragment extends Fragment {

    private RecyclerView homeRecyclerView;
    private FloatingActionButton btnHomeAdd;
    private TextView homeErrorTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_home, container, false);
    }

    @Override
    public void onViewCreated (View view,
                               Bundle savedInstanceState) {
        homeRecyclerView = view.findViewById(R.id.homeRecyclerView);
        btnHomeAdd = view.findViewById(R.id.btnHomeAdd);
        homeErrorTextView = view.findViewById(R.id.homeErrorTextView);

        btnHomeAdd.setOnClickListener(view1 -> {
            String token = prefConfig.loadLoginTokenFromPref(getContext());
            if (token.equals("")) {
                Toast.makeText(getContext(),"Please login", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(getContext(), PostThreadActivity.class);
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });

        getThreads();
    }

    private void getThreads() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String URL = prefConfig.getServer()+"thread/?batch=1&limit=5";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, response -> {
            Log.i("VOLLEY", response);
            try {
                homeErrorTextView.setVisibility(View.GONE);
                JSONObject res = new JSONObject(response);
                JSONArray data = (JSONArray) res.get("data");
                ArrayList<BackendThreadInfo> backendThreadInfoArrayList = new ArrayList<>();
                if (data != null) {
                    if (data.length()==0) {
                        homeErrorTextView.setText("No posts yet. Be the first to post!");
                        homeErrorTextView.setVisibility(View.VISIBLE);
                    } else {
                        for (int i=0;i<data.length();i++){
                            JSONObject backendThread = (JSONObject) data.get(i);
                            int thread_id = Integer.parseInt(backendThread.get("thread_id").toString());
                            int user_id = Integer.parseInt(backendThread.get("user_id").toString());
                            String content = backendThread.get("content").toString();
                            int is_anonymous = Integer.parseInt(backendThread.get("is_anonymous").toString());
                            BackendThreadInfo backendThreadInfo = new BackendThreadInfo(thread_id, user_id, content, is_anonymous);
                            backendThreadInfoArrayList.add(backendThreadInfo);
                        }
                    }
                    updateHomeRecyclerView(backendThreadInfoArrayList);
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
                homeErrorTextView.setText("Something went wrong");
                homeErrorTextView.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
            } catch (UnsupportedEncodingException | JSONException e) {
                e.printStackTrace();
            }
        }) {

/*            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("batch", "1");
                params.put("limit", "50");
                return params;
            }*/

        };
        Log.d("string", stringRequest.toString());
        requestQueue.add(stringRequest);
    }

    private void updateHomeRecyclerView(ArrayList<BackendThreadInfo> backendThreadInfoArrayList) {
        ArrayList<HomeThreadInfo> homeThreadInfoData = new ArrayList<>();

        for (int i = 0; i < backendThreadInfoArrayList.size(); i++) {
            homeThreadInfoData.add(new HomeThreadInfo(backendThreadInfoArrayList.get(i)));
        }

        HomeRecyclerViewAdapter adapter = new HomeRecyclerViewAdapter(getContext(), homeThreadInfoData);
        homeRecyclerView.setHasFixedSize(true);
        homeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        homeRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        getThreads();
        super.onResume();
    }
}