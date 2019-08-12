package com.khieuthichien.wallpaper.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.khieuthichien.wallpaper.R;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView nav_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerlayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        nav_menu = findViewById(R.id.nav_menu);
        nav_menu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.itemLatest:
                        Intent intent1 = new Intent(MainActivity.this, LatestActivity.class);
                        startActivity(intent1);
                        Toast.makeText(MainActivity.this, "Latest", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.itemCategory:
                        Intent intent2 = new Intent(MainActivity.this, CategoryActivity.class);
                        startActivity(intent2);
                        Toast.makeText(MainActivity.this, "Category", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.itemGIFs:
                        Toast.makeText(MainActivity.this, "GIFs", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.itemFavorites:
                        Intent intent4 = new Intent(MainActivity.this, MyFavoriteActivity.class);
                        startActivity(intent4);
                        Toast.makeText(MainActivity.this, "Favorites", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.itemRateApp:
                        Toast.makeText(MainActivity.this, "Rate App", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.itemMoreApp:
                        Toast.makeText(MainActivity.this, "More App", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.itemAboutUs:
                        Intent intent7 = new Intent(MainActivity.this, AboutUsActivity.class);
                        startActivity(intent7);
                        Toast.makeText(MainActivity.this, "About Us", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.itemSetting:
                        Toast.makeText(MainActivity.this, "Setting", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.itemPrivacyPolice:
                        Toast.makeText(MainActivity.this, "Privacy Police", Toast.LENGTH_SHORT).show();
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = findViewById(R.id.drawerlayout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



}
