package com.example.moisturemeterthingspeak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Starting_Activity extends AppCompatActivity {


    BottomNavigationView userBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_starting);

        userBottomNavigation = findViewById(R.id.bottom_navigation_user);


        getSupportFragmentManager().beginTransaction().replace(R.id.user_container, new UserHomeFragment()).commit();
        userBottomNavigation.getMenu().findItem(R.id.user_home).setChecked(true);


        userBottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment temp = null;
                switch (item.getItemId()) {

                    case R.id.user_home:
                        temp = new UserHomeFragment();
                        break;

                    case R.id.user_activity:
                        temp = new UserActivityFragment();
                        break;

                    case R.id.user_profile:
                        temp = new UserProfileFragment();
                        break;

                    case R.id.user_settings:
                        temp = new UserSettingsFragment();

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.user_container, temp).commit();



                return true;
            }
        });

    }


}