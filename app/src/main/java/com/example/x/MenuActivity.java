package com.example.x;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView nombreUsuario, nombreCorreo;
    private SharedPreferences preferences;
    private DrawerLayout drawerLayout;
    public static Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //
        NavigationView navView= findViewById(R.id.main_navigationview);
        View headerLayout=navView.getHeaderView(0);
        nombreUsuario = headerLayout.findViewById(R.id.nombreUsuario);
        nombreCorreo = headerLayout.findViewById(R.id.nombreCorreo);

        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);

        int usuario_id = preferences.getInt("usuario_id", 0);
        String usuario_username = preferences.getString("usuario_username", null);
        String usuario_email = preferences.getString("usuario_email", null);

        if(usuario_id>0 && usuario_username!=null && usuario_email!=null){
            nombreUsuario.setText(usuario_username);
            nombreCorreo.setText(usuario_email);
        }
        //

        drawerLayout = findViewById(R.id.menuPlasticos);
        NavigationView navigationView = findViewById(R.id.main_navigationview);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,
                    new misRegistrosFragment()).commit();
            navigationView.setCheckedItem(R.id.misRegistros);
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
            case R.id.misRegistros:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,
                        new misRegistrosFragment()).commit();
                break;
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
            case R.id.cerrarSesion:
                cerrarSesion();
                Intent intent  = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void cerrarSesion(){
        preferences.edit().clear().apply();
    }
}