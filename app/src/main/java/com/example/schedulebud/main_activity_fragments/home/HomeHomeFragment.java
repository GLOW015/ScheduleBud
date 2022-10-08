package com.example.schedulebud.main_activity_fragments.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.schedulebud.R;
import com.example.schedulebud.prefConfig;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeHomeFragment extends Fragment {

    private RecyclerView homeRecyclerView;
    private FloatingActionButton btnHomeAdd;

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

        //TODO populate recyclerView
    }
}