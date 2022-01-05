package com.example.moisturemeterthingspeak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Admin_MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_admin_main);


        bottomNavigationView = findViewById(R.id.bottomNavigation);


            getSupportFragmentManager().beginTransaction().replace(R.id.frame_Container, new HomeFragment()).commit();
            bottomNavigationView.getMenu().findItem(R.id.home).setChecked(true);


            bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment temp = null;
                    switch (item.getItemId()) {

                        case R.id.home:
                            temp = new HomeFragment();
                            break;

                        case R.id.edit:
                            temp = new EditFragment();
                            break;

                        case R.id.profile:
                            temp = new ProfileFragment();
                            break;

                        case R.id.previous_data:
                            temp = new DataFragment();
                            break;

//                        case R.id.readings_admin:
//                            temp = new UserHomeFragment();

                        case R.id.settings:
                            temp = new SettingsFragment();

                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_Container, temp).commit();


                    return true;
                }
            });

        }



}