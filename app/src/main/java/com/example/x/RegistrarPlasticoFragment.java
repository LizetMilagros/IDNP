package com.example.x;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import DataBase.DBusuario;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class RegistrarPlasticoFragment extends Fragment {

    int SELEC_IMAGEN = 200;

    View view;
    Button aceptar;
    EditText Descripcion,quantidad;
    DBusuario DB;
    ImageButton foto;
    private Spinner spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_registrar_plastico, container, false);

        Descripcion = view.findViewById(R.id.Descripcion);
        DB = new DBusuario(getContext());
        aceptar= view.findViewById(R.id.btnRegister);
        quantidad= view.findViewById(R.id.Cantidad);
        foto=view.findViewById(R.id.foto);
        //ver las opciones del combo box
        spinner=(Spinner)view.findViewById(R.id.spinner);
        String [] opciones ={"tipo1","tipo2","tipo3"};
        ArrayAdapter <String> adapter=new ArrayAdapter <String>(view.getContext(),android.R.layout.simple_spinner_item,opciones);
        spinner.setAdapter(adapter);

        //Guardar toda la info textual
        aceptar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String descripcion = Descripcion.getText().toString();
                String tipo = spinner.getSelectedItem().toString();
                String cantidad= quantidad.getText().toString();
                if (descripcion.equals("")||cantidad.equals(""))
                    Toast.makeText(getContext(), "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
                /*else if(DB.buscarrepetidos(cantidad)){
                    Toast.makeText(getContext(), "Ya existe", Toast.LENGTH_SHORT).show();
                }*/
                else {
                    DB.registrodeplastico(descripcion,cantidad, tipo);
                    Toast.makeText(getContext(), "Registrado correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //para abrir la galería
        foto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent galeria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(galeria, SELEC_IMAGEN);
            }
        });
        return view;
    }
}