package com.example.schedulebud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.schedulebud.databinding.ActivityMainBinding;
import com.example.schedulebud.fragments.home.HomeFragment;
import com.example.schedulebud.fragments.ProfileFragment;
import com.example.schedulebud.fragments.home.HomeHomeFragment;
import com.example.schedulebud.fragments.schedule.ScheduleFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch(item.getItemId()) {
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.schedule:
                    replaceFragment(new ScheduleFragment());
                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    break;

            }

            return true;
        });
        /**
         * Check for no login preference
         */
        if (!prefConfig.loadNoLoginFromPref(this)) {
            /**
             * Check for existing login token
             */
            if (prefConfig.loadLoginTokenFromPref(this)==0) {
                // no login token present
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}