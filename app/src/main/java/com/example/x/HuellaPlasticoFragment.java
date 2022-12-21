package com.example.x;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HuellaPlasticoFragment extends Fragment {

    TextView botellas, limpieza, desechables,respuesta;
    EditText cantBotellas, cantLimpieza, cantDesechables;
    Button btnCalcular;
    double cantidadBotellas, cantidadLimp, cantidadDese;

    public HuellaPlasticoFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_huella_plastico, container, false);

        botellas = view.findViewById(R.id.textViewBotellas);
        limpieza = view.findViewById(R.id.textViewProdLimp);
        desechables = view.findViewById(R.id.textViewProDesech);

        cantBotellas = view.findViewById(R.id.editTextNumBotellas);
        cantLimpieza = view.findViewById(R.id.editTextPrudLimpCant);
        cantDesechables = view.findViewById(R.id.editTextProDesechCant);



        btnCalcular = view.findViewById(R.id.buttonCalcular);
        respuesta = view.findViewById(R.id.textViewRespuesta);

        cantidadBotellas = Double.parseDouble(cantBotellas.getText().toString());
        cantidadLimp = Double.parseDouble(cantLimpieza.getText().toString());
        cantidadDese = Double.parseDouble(cantDesechables.getText().toString());


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double respuestaTotal = calculateBotellas(cantidadBotellas) + calculateLimp(cantidadLimp) + calculateDese(cantidadDese);
                String  respuestaTotalSetear = String.valueOf(respuestaTotal);
                respuesta.setText(respuestaTotalSetear + " Kg");

            }
        });


        return view;
    }

    public double calculateBotellas(double numBotellas){

        numBotellas = Double.parseDouble(cantBotellas.getText().toString());
        return numBotellas*0.5;
    }
    public double calculateLimp(double numLimpieza){

        numLimpieza = Double.parseDouble(cantLimpieza.getText().toString());
        return numLimpieza*0.7;
    }
    public double calculateDese( double numDesechable){

        numDesechable = Double.parseDouble(cantDesechables.getText().toString());
        return numDesechable*0.3;
    }

}