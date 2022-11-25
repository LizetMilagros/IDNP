package com.example.x;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton mBtnOne, mBtnTwo, mBtnThree, mBtnFour,mBtnFive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mBtnOne = findViewById(R.id.ButtonRegistrarPlastico);
        mBtnTwo = findViewById(R.id.ButtonTiposPlastico);
        mBtnThree = findViewById(R.id.ButtonHuellaPlastico);
        mBtnFour = findViewById(R.id.ButtonEstadistica);
        mBtnFive = findViewById(R.id.ButtonTips);

        mBtnOne.setOnClickListener(this);
        mBtnTwo.setOnClickListener(this);
        mBtnThree.setOnClickListener(this);
        mBtnFour.setOnClickListener(this);
        mBtnFive.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.ButtonRegistrarPlastico:
                RegistrarPlasticoFragment firstFragment = new RegistrarPlasticoFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.LinearLayout,firstFragment,firstFragment.getTag()).commit();
                break;

            case R.id.ButtonTiposPlastico:
                TiposPlasticoFragment secondFragment = new TiposPlasticoFragment();
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                fragmentManager1.beginTransaction().replace(R.id.LinearLayout,secondFragment,secondFragment.getTag()).commit();
                break;

            case R.id.ButtonHuellaPlastico:
                HuellaPlasticoFragment thirdFragment = new HuellaPlasticoFragment();
                FragmentManager fragmentManager2 = getSupportFragmentManager();
                fragmentManager2.beginTransaction().replace(R.id.LinearLayout,thirdFragment,thirdFragment.getTag()).commit();
                break;

            case R.id.ButtonEstadistica:
                EstadisticaPlasticoFragment fourFragment = new EstadisticaPlasticoFragment();
                FragmentManager fragmentManager3 = getSupportFragmentManager();
                fragmentManager3.beginTransaction().replace(R.id.LinearLayout,fourFragment,fourFragment.getTag()).commit();
                break;

            case R.id.ButtonTips:
                TipsPlasticoFragment fiveFragment = new TipsPlasticoFragment();
                FragmentManager fragmentManager4 = getSupportFragmentManager();
                fragmentManager4.beginTransaction().replace(R.id.LinearLayout,fiveFragment,fiveFragment.getTag()).commit();
                break;

        }
    }
}