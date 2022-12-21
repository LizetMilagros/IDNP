package com.example.x;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.x.entities.Usuario;

import DataBase.DBusuario;

public class Login extends Fragment {

    EditText email, password;
    Button login, register;
    DBusuario DB;
    CallbackFragment callbackFragment;

    SharedPreferences preferences;
    Usuario usuario;

    @Override
    public void onAttach(@NonNull Context context) {
        preferences = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        super.onAttach(context);
    }

    public Login() {
        // constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        email = view.findViewById(R.id.txtUser);
        password =  view.findViewById(R.id.txtPassword);
        login =  view.findViewById(R.id.btnLogin);
        register =  view.findViewById(R.id.btnRegister);
        DB = new DBusuario(getContext());

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callbackFragment!=null){
                    callbackFragment.changeFragment();
                }

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String correo = email.getText().toString();
                String pass = password.getText().toString();

                if(correo.equals("")||pass.equals(""))
                    Toast.makeText(getContext(), "Por favor ingrese todos los campos", Toast.LENGTH_SHORT).show();
                else{
                    usuario = DB.checkemailpassword(correo, pass);
                    if(usuario != null){
                        Toast.makeText(getContext(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putInt("usuario_id", usuario.getId());
                        editor.putString("usuario_username", usuario.getUsername());
                        editor.putString("usuario_email", usuario.getEmail());
                        //editor.putString("usuario_password", usuario.getPassword());
                        editor.apply();

                        Intent intent  = new Intent(getContext(), MenuActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getContext(), "Credenciales no válidas", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    public void setCallbackFragment(CallbackFragment callbackFragment){
        this.callbackFragment = callbackFragment;
    }

}