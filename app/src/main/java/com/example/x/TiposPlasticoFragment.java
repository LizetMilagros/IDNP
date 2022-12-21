package com.example.x;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x.TiposPlasticos.AdapterTipos;
import com.example.x.TiposPlasticos.ModeloTipoPlastico;

import java.util.ArrayList;
import java.util.List;

public class TiposPlasticoFragment extends Fragment {

    private List<ModeloTipoPlastico> items;
    private AdapterTipos adapter;
    private  RecyclerView lista;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_tipos_plastico, container, false);

        lista = (RecyclerView) vista.findViewById(R.id.tiposPlasticos);
        initValues();
        return vista;
    }


    private void initValues(){
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        lista.setLayoutManager(manager);

        items = getItems();
        adapter = new AdapterTipos(items);
        lista.setAdapter(adapter);
    }
    private List<ModeloTipoPlastico> getItems(){
        List<ModeloTipoPlastico> iteimList= new ArrayList<>();
        iteimList.add(new ModeloTipoPlastico("PET o PETE (tereftalato de polietileno)","Este tipo de plástico es el tereftalato de polietileno, que es transparente y no transpira. Es uno de los más reciclados, ya que se encuentra en envolturas de plástico, botellas plásticas, envases de alimentos, etcétera.", R.mipmap.tipo1));
        iteimList.add(new ModeloTipoPlastico("HDPE (polietileno de alta densidad)","Este otro plástico es el llamado polietileno de alta densidad. Se encuentra en productos como los tetrabriks (por lo que se reciclan en el contendor del plástico, otros envases de alimentos, envases de productos cosméticos, de limpieza, algunas tuberías, etcétera.", R.mipmap.tipo2));
        iteimList.add(new ModeloTipoPlastico("PVC (policloruro de vinilo)","Este tipo de plastico resulta ser uno de los plásticos más peligrosos para la salud y el medio ambiente, se utiliza para hacer tuberías, canalones, cables, algunas botellas y garrafas, algunos paquetes de alimentos, botellas de detergente líquido, conos de tráfico, etcétera", R.mipmap.tipo3));
        iteimList.add(new ModeloTipoPlastico("LDPE (Polietileno de baja densidad)","Es un plástico reciclable que se usa en bolsas de congelados, bolsas de basura, papel de cocina transparente o film, botellas de plástico blando, etc. ", R.mipmap.tipo4));
        iteimList.add(new ModeloTipoPlastico("PP (Polipropileno)","Este tipo de plastico está muy presente en la construcción y la industria automovilística, pero también en las pajitas para beber y las tapas y los tapones plásticos de algunos envases.  ", R.mipmap.tipo5));
        iteimList.add(new ModeloTipoPlastico("PS (Poliestireno)","Este tipo de plastico lo encontramos algunos juguetes, cubiertos, embalajes y corcho blanco (muy conocido por la marca Porexpán y por Unicel) que es usado para empaquetar y proteger productos electrónicos y electrodomésticos. ", R.mipmap.tipo6));
        iteimList.add(new ModeloTipoPlastico("Otros plásticos","los componentes no se conocen del todo, por lo que no pueden reciclarse y eso hace de ellos un tipo de plástico muy contaminante. También se incluyen en esta categoría algunos tipos de plásticos de composición mixta. Se usan para los discos compactos, recipientes de embutidos, envases de pasta dentífrica, platos para cocinar en el microondas…", R.mipmap.tipo7));
        return iteimList;
    }

}