package com.example.x;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.x.Adapter.RecyclerViewTipsAdapter;
import com.example.x.entities.TipsModel;


public class TipsPlasticoFragment extends Fragment {

    RecyclerView recyclerViewTips;
    TipsModel[] listaTips;
    RecyclerViewTipsAdapter adapter;

    public TipsPlasticoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_tips_plastico, container, false);


        listaTips = new TipsModel[]{
                new TipsModel("Cuando hagas las compras para tu hogar, elige bolsas de papel o tela. Las bolsas de plástico tardan años en degradarse y contaminan mucho más. ",R.drawable.bolsaecologica),
                new TipsModel("Desconecta los aparatos electrónicos que no estás usando. La televisión, microondas, computadoras, videojuegos y demás electrodomésticos.",R.drawable.desconectar),
                new TipsModel("Ahorra agua. Disminuir el uso de agua es más sencillo de lo que parece, acciones sencillas pueden hacer una gran diferencia.",R.drawable.agua),
                new TipsModel("Recicla; separar los residuos que generan en tu hogar es la mejor forma para disminuir el impacto al medio ambiente.",R.drawable.reciclaje),
                new TipsModel("Opta por la luz natural, o en su defecto, por luces LED, son la mejor forma para iluminar tu hogar ya que son de bajo consumo energético.",R.drawable.luzled)
        };
        recyclerViewTips = (RecyclerView) vista.findViewById(R.id.recyclerViewTips2);
        recyclerViewTips.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecyclerViewTipsAdapter(listaTips);
        recyclerViewTips.setAdapter(adapter);
        return vista;
    }
}