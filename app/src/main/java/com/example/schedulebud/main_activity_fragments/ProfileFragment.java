package com.example.schedulebud.main_activity_fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.schedulebud.R;
import com.example.schedulebud.account_activities.LoginActivity;
import com.example.schedulebud.prefConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class ProfileFragment extends Fragment {

    private ImageView profileImageView;
    private TextView profileName, profileUsername, profileEmail, profilePassword;
    private Button profileAccountBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated (View view,
                               Bundle savedInstanceState) {
        profileImageView = view.findViewById(R.id.profileImageView);
        profileName = view.findViewById(R.id.profileName);
        profileUsername = view.findViewById(R.id.profileUsername);
        profileEmail = view.findViewById(R.id.profileEmail);
        profilePassword = view.findViewById(R.id.profilePassword);
        profileAccountBtn = view.findViewById(R.id.profileAccountBtn);
        setProfileAccountBtn();
        setNames();
    }

    @Override
    public void onResume () {
        super.onResume();
        setProfileAccountBtn();
        setNames();
    }

    private void setProfileAccountBtn () {
        String token = prefConfig.loadLoginTokenFromPref(getContext());
        boolean noLoginToken = token.equals("");
        if(noLoginToken) {
            profileAccountBtn.setText("LOGIN");
        } else {
            profileAccountBtn.setText("LOGOUT");
        }
        profileAccountBtn.setOnClickListener(view -> {
            if (noLoginToken) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                intent.putExtra("enableBack", noLoginToken);
                startActivity(intent);
            } else {
                logout(token);
            }
        });
    }

    private void setNames() {
        String email = prefConfig.loadEmailFromPref(getContext());
        profileName.setText(email);
        profileUsername.setText(email);
        profileEmail.setText(email);
    }

    public void logout(String token) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String URL = prefConfig.getServer()+"user/logout";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {
            Log.i("VOLLEY", response);
            try {
                JSONObject res = new JSONObject(response);
                String message = res.get("message").toString();
                prefConfig.saveLoginTokenPref(getContext(),"");
                prefConfig.saveEmailPref(getContext(),"guest");
                Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(getContext(), LoginActivity.class);
            intent.putExtra("enableBack", false);
            startActivity(intent);
        }, error -> {
            Log.e("VOLLEY", error.toString());
            try {
                String responseBody = new String(error.networkResponse.data, "utf-8");
                JSONObject res = new JSONObject(responseBody);
                String message = res.get("message").toString();
                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
            } catch (UnsupportedEncodingException | JSONException e) {
                e.printStackTrace();
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("auth-token",token);
                return params;
            }
        };
        Log.d("string", stringRequest.toString());
        requestQueue.add(stringRequest);
    }

}