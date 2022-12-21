package com.example.x;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;

public class EstadisticaPlasticoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_estadistica_plastico,container,false);
        Integer[] a = {10,20,60,40,35,30,5,};
        String[] horizontal = {"PET","PEHD","PVC","PEBD","PP","PS","Otro"};

        List<Integer> vertical = new ArrayList<Integer>();
        vertical.add(10);
        vertical.add(20);
        vertical.add(60);
        vertical.add(40);
        vertical.add(35);
        vertical.add(30);
        vertical.add(5);

        ViewBarChart grafico = view.findViewById(R.id.grafico);
        grafico.setDatos(horizontal,vertical);

        return  view;
    }
}