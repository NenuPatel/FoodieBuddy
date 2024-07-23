package com.example.foodorderingapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNav ;
//   public String P_Name,P_email,P_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNav   = findViewById(R.id.bottom_navigation);

        try {

            if(savedInstanceState == null){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new frag_homemenu()).commit();
            }

            BottomNavigationView.OnNavigationItemSelectedListener navListener  = new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment selected = null;
                    if(item.getItemId() == R.id.home_nav){

                        selected = new frag_homemenu();

                    } else if (item.getItemId() == R.id.search_nav) {

                        selected = new frag_search();

                    } else if (item.getItemId() == R.id.trolly_nav) {

                        selected = new frag_cart();

                    } else if (item.getItemId() == R.id.setting_nav) {

                        selected = new frag_setting();

                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selected).commit();

                    return true;
                }
            };
            bottomNav.setOnNavigationItemSelectedListener(navListener);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }


}