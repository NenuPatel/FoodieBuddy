package com.example.foodorderingapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.ModelData.MainMenu_listdata;
import com.example.foodorderingapp.R;

import java.util.ArrayList;

public class MainMenulist_Adapter extends RecyclerView.Adapter<MainMenulist_Adapter.ViewHolder> {

    ArrayList<MainMenu_listdata> menulistdata;
    OnItemClickListener menulistener;

    public MainMenulist_Adapter(ArrayList<MainMenu_listdata> listdata) {
        this.menulistdata = listdata;
    }

    public void setOnclickListener(OnItemClickListener listener){
        menulistener = listener;
    }

    public  interface OnItemClickListener{
        void onItemclick(int position);
        void AddToCart(int position);

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listitem = inflater.inflate(R.layout.main_menulayout,parent,false);
        ViewHolder viewHolder = new ViewHolder(listitem,menulistener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Mfood_img.setImageResource(menulistdata.get(position).getMfoodimg());
        holder.M_nametxt.setText(menulistdata.get(position).getMfoodname());
        holder.M_pricetxt.setText(menulistdata.get(position).getMfoodprice());
    }

    @Override
    public int getItemCount() {
        return menulistdata.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        ImageView Mfood_img;
        TextView M_nametxt, M_pricetxt;
        Button addtocartbtn;

        public ViewHolder(@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);
            Mfood_img = itemView.findViewById(R.id.foodimgmain);
            M_nametxt = itemView.findViewById(R.id.food_nametxtmain);
            M_pricetxt = itemView.findViewById(R.id.pricetxtmain);
            addtocartbtn = itemView.findViewById(R.id.addtocart);

            Mfood_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemclick(getAdapterPosition());
                }
            });

            addtocartbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.AddToCart(getAdapterPosition());
                }
            });

        }


    }
}



