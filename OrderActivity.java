package com.example.easyorder;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class OrderActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navigationView= findViewById(R.id.mNavigation_view);

        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout= findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new CreateOrderFragment()).commit();

        }
    }
    @Override
    public void onBackPressed(){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.input_order:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new CreateOrderFragment()).commit();
                break;
            case R.id.delete_order:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new DeleteOrderFragment()).commit();
                break;
            case R.id.show_order:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new ShowOrderFragment()).commit();
                break;
            case R.id.edit_account:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new SetUserFragment()).commit();
                break;
            case R.id.logout_account:
                Intent i= new Intent(OrderActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
