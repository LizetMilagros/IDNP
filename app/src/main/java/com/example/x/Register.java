package com.example.x;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.x.entities.Usuario;

import DataBase.DBusuario;

public class Register extends Fragment {

    EditText user,correo, password, confirmPassword;
    Button login, register;
    DBusuario DB;
    Usuario usuario;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public Register() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        user = view.findViewById(R.id.txtName);
        correo = view.findViewById(R.id.txtNameUser);
        password =  view.findViewById(R.id.txtPassword);
        confirmPassword =  view.findViewById(R.id.txtConfirmPassword);
        register =  view.findViewById(R.id.btnRegister);
        DB = new DBusuario(getContext());

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = user.getText().toString();
                String corr = correo.getText().toString();
                String pass = password.getText().toString();
                String repass = confirmPassword.getText().toString();

                if (userName.equals("") || corr.equals("")|| pass.equals("") || repass.equals(""))
                    Toast.makeText(getContext(), "Por favor ingrese todos los campos", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)) {
                        Boolean checkuser = DB.checkusername(corr);
                        if (checkuser == false) {
                            usuario = new Usuario(userName, corr, pass, repass);
                            //Boolean insert = DB.insertData(userName, corr, pass, repass);
                            Boolean insert = DB.insertUsuario(usuario);
                            if (insert == true) {
                                Toast.makeText(getContext(), "Registrado correctamente", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getContext(), "Registro fallido", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "??El usuario ya existe! Por favor, registrese con otro correo", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Las contrase??as no coinciden", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}