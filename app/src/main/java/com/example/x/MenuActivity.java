package com.example.x;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    public static Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.menuPlasticos);
        NavigationView navigationView = findViewById(R.id.main_navigationview);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,
                    new TiposPlasticoFragment()).commit();
            navigationView.setCheckedItem(R.id.tiposPlastico);
        }
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.registrarPlastico:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,
                        new RegistrarPlasticoFragment()).commit();
                break;
            case R.id.tiposPlastico:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,
                        new TiposPlasticoFragment()).commit();
                break;
            case R.id.huellaPlastico:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,
                        new HuellaPlasticoFragment()).commit();
                break;
            case R.id.tipsPlastico:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,
                        new TipsPlasticoFragment()).commit();
                break;
            case R.id.estadisticaPlastico:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,
                        new EstadisticaPlasticoFragment()).commit();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}