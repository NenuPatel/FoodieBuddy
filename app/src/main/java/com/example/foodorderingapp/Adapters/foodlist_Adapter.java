package com.example.foodorderingapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.ModelData.foodlistData_model;
import com.example.foodorderingapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class foodlist_Adapter extends RecyclerView.Adapter<foodlist_Adapter.ViewHolder> {

    ArrayList<foodlistData_model> listdata;
    OnItemClickListener mListener;

    public foodlist_Adapter(ArrayList<foodlistData_model> listdata) {
        this.listdata = listdata;
    }

    public  void setOnClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    public interface OnItemClickListener{


        void OnImageClick(int position);

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem= inflater.inflate(R.layout.recycler_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem,mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        holder.img.setImageResource(listdata.get(position).getFoodimg());
        holder.nametxt.setText(listdata.get(position).getFoodname());
        holder.pricetxt.setText((listdata.get(position).getFoodprice()));
        holder.detailtxt.setText(listdata.get(position).getFooddetail());
        Picasso.get().load(listdata.get(position).getFoodimg()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView nametxt, pricetxt,detailtxt;
//        Button addtocartbtn;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.foodimg);
            nametxt = itemView.findViewById(R.id.food_nametxt);
            pricetxt = itemView.findViewById(R.id.pricetxt);
            detailtxt = itemView.findViewById(R.id.detailtxt);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnImageClick(getAdapterPosition());
                }
            });


        }
    }
}
