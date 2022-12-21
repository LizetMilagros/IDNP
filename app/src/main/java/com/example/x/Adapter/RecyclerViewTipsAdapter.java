package com.example.x.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x.R;
import com.example.x.entities.TipsModel;

public class RecyclerViewTipsAdapter extends RecyclerView.Adapter<RecyclerViewTipsAdapter.MyViewHolder>{

    // 1- Data source
    private TipsModel[] listTips;

    public RecyclerViewTipsAdapter(TipsModel[] listTips){
        this.listTips = listTips;
    }



    // 2- View Holder Class

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.imageView = itemView.findViewById(R.id.imageViewTips);
            this.textView = itemView.findViewById(R.id.textViewTips);


        }

    }

    // 3- Implementing the methods

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.recyclerview_tips,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewTipsAdapter.MyViewHolder holder, int position) {
        final TipsModel myListTips = listTips[position];
        holder.textView.setText(listTips[position].getTitle());
        holder.imageView.setImageResource(listTips[position].getImage());
    }



    @Override
    public int getItemCount() {
        return listTips.length;
    }
}
