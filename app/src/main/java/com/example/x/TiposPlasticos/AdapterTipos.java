package com.example.x.TiposPlasticos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x.R;

import java.util.List;

public class AdapterTipos extends RecyclerView.Adapter <AdapterTipos.RecyclerHolder> {
    // 1- Data source
    private List <ModeloTipoPlastico>listTipos;

    public AdapterTipos(List<ModeloTipoPlastico> listTipos) {
        this.listTipos = listTipos;
    }
// 2- Implementing the methods

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tipos, parent,false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {

        ModeloTipoPlastico myListTipos = listTipos.get(position);
        holder.imagen.setImageResource(myListTipos.getImagen());
        holder.titulo.setText(myListTipos.getTitulo());
        holder.descripcion.setText(myListTipos.getDescripcion());

    }

    @Override
    public int getItemCount() {
        return listTipos.size();
    }

    public static class RecyclerHolder extends RecyclerView.ViewHolder{

        private ImageView imagen;
        private TextView titulo;
        private TextView descripcion;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            imagen=itemView.findViewById(R.id.imgItem);
            titulo=itemView.findViewById(R.id.textTitulo);
            descripcion=itemView.findViewById(R.id.textDescripcion);


        }
    }
}
