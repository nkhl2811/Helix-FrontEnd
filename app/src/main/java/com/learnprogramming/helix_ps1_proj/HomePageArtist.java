package com.learnprogramming.helix_ps1_proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener;

import java.util.ArrayList;

public class HomePageArtist extends AppCompatActivity implements OnNavigationItemSelectedListener {
    private static final String TAG = "HomePageArtist";
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    private ArrayList<String> streamNames = new ArrayList<>();
    private ArrayList<String> imageURLs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_artist);

        drawerLayout = findViewById(R.id.drawer_layout_2);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        if (drawerLayout != null) {
            drawerLayout.addDrawerListener(toggle);
        }
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        getImages();
    }

    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }

    private void getImages(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");



        imageURLs.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        streamNames.add("Trondheim");

        imageURLs.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        streamNames.add("Portugal");

        imageURLs.add("https://i.redd.it/j6myfqglup501.jpg");
        streamNames.add("Rocky Mountain National Park");


        imageURLs.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        streamNames.add("Mahahual");

        imageURLs.add("https://i.redd.it/k98uzl68eh501.jpg");
        streamNames.add("Frozen Lake");


        imageURLs.add("https://i.redd.it/glin0nwndo501.jpg");
        streamNames.add("White Sands Desert");

        imageURLs.add("https://i.redd.it/obx4zydshg601.jpg");
        streamNames.add("Austrailia");

        imageURLs.add("https://i.imgur.com/ZcLLrkY.jpg");
        streamNames.add("Washington");

        initRecyclerView();

    }

    private  void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: initRecyclerView");

        LinearLayoutManager layoutManager1a = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerView1a = findViewById(R.id.recyclerView1a);
        recyclerView1a.setLayoutManager(layoutManager1a);
        HomeRecyclerViewAdapter homeRecyclerViewAdaptera = new HomeRecyclerViewAdapter(streamNames,imageURLs,this);
        recyclerView1a.setAdapter(homeRecyclerViewAdaptera);



        LinearLayoutManager layoutManager3a = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerView3a = findViewById(R.id.recyclerView3a);
        recyclerView3a.setLayoutManager(layoutManager3a);
        HomeRecyclerViewAdapter homeRecyclerViewAdapter3a = new HomeRecyclerViewAdapter(streamNames,imageURLs,this);
        recyclerView3a.setAdapter(homeRecyclerViewAdapter3a);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.nav_home:
                break;
            case R.id.nav_calendar:
                Intent intent = new Intent(this,Calendar.class);
                startActivity(intent);
                break;
            case R.id.nav_settings:
                Intent intent1 = new Intent(this,Settings.class);
                startActivity(intent1);
                break;
            case R.id.nav_logout:
                SharedPreferences saveAuthToken = getSharedPreferences("saveAuthToken", MODE_PRIVATE);
                saveAuthToken.edit().putString("authToken", "default").apply();
                Intent intent2 = new Intent(this,LoginActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_createevent:
                Intent intent3 = new Intent(this,CreateEvent.class);
                startActivity(intent3);
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}