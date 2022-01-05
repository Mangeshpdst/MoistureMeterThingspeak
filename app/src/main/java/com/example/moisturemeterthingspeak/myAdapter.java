package com.example.moisturemeterthingspeak;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder>{

    ArrayList<dataModel> dataHolder;

    public myAdapter(ArrayList<dataModel> dataHolder) {
        this.dataHolder = dataHolder;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.moisture.setText(dataHolder.get(position).getMoisture_Value());
        holder.time.setText(dataHolder.get(position).getTime());
        holder.date.setText(dataHolder.get(position).getDate());
        holder.location.setText(dataHolder.get(position).getLocation());
        holder.lot_no.setText(dataHolder.get(position).getLot());
        holder.bail.setText(dataHolder.get(position).getBail());
    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        TextView moisture, time, date, location, lot_no, bail;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            moisture=itemView.findViewById(R.id.moisture_readingc);
            time=itemView.findViewById(R.id.time1);
            date=itemView.findViewById(R.id.date);
            location=itemView.findViewById(R.id.location);
            lot_no=itemView.findViewById(R.id.lot_no);
            bail=itemView.findViewById(R.id.bail1);

        }
    }
}
