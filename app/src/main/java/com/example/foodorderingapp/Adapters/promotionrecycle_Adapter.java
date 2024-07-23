package com.example.foodorderingapp.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.ModelData.promotion_list;

import java.util.ArrayList;


public class promotionrecycle_Adapter extends RecyclerView.Adapter<promotionrecycle_Adapter.ViewHolder> {

    ArrayList<promotion_list> promo_listdata;

    public promotionrecycle_Adapter(ArrayList<promotion_list> listdata){

        this.promo_listdata = listdata;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View list =inflater.inflate(R.layout.promotion_recycleview_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(list);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.PromotionImg.setImageResource(promo_listdata.get(position).getPromotionimg());
    }

    @Override
    public int getItemCount() {
        return promo_listdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView PromotionImg;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            PromotionImg = itemView.findViewById(R.id.promotion_img);

        }
    }
}

