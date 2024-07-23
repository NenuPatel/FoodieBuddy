package com.example.foodorderingapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodorderingapp.Adapters.foodlist_Adapter;
import com.example.foodorderingapp.Adapters.promotionrecycle_Adapter;
import com.example.foodorderingapp.ModelData.foodlistData_model;
import com.example.foodorderingapp.ModelData.promotion_list;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class frag_homemenu extends Fragment {

    RecyclerView recycleview_popular,recycleview_promotion;
    TextView textView;
    ArrayList<foodlistData_model> mylistdata;
    String url = "https://foododerappproject.000webhostapp.com/newapi.php";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_frag_homemenu, container, false);


        recycleview_popular = v.findViewById(R.id.recycleview_popular);

        recycleview_promotion = v.findViewById(R.id.recycleview_promotion);
        textView = v.findViewById(R.id.textview);

        mylistdata =new ArrayList<>();


        recycleview_popular.setHasFixedSize(true);
        recycleview_popular.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager.HORIZONTAL,false));

        ArrayList<promotion_list> promoListdata = new ArrayList<>();

        promoListdata.add(new promotion_list(R.drawable.fruit_juice_promotion));
        promoListdata.add(new promotion_list(R.drawable.fast_food_promotion));
        promoListdata.add(new promotion_list(R.drawable.healthy_food_prmotion));
        promoListdata.add(new promotion_list(R.drawable.promotion_food_img));
        promoListdata.add(new promotion_list(R.drawable.cookie_promotion));
        promoListdata.add(new promotion_list(R.drawable.pancake_promotion));

        recycleview_promotion.setHasFixedSize(true);
        promotionrecycle_Adapter promoAdapter = new promotionrecycle_Adapter(promoListdata);
        recycleview_promotion.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager.HORIZONTAL,false));
        recycleview_promotion.setAdapter(promoAdapter);

        try {
            fetch();

        }catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return  v;
    }


    void fetch(){
        RequestQueue queue = Volley.newRequestQueue(getContext());
//        String url = "https://jsonplaceholder.typicode.com/posts";

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                recycleview_popular.setVisibility(View.VISIBLE);
                for(int x = 0; x<response.length(); x++){
                    try {
                        JSONObject object = response.getJSONObject(x);
                        String Name = object.getString("name");
                        String Price = object.getString("price");
                        String Imageurl = object.getString("img");
                        String Detail = object.getString("detail");
//                        int qty = object.getInt("qty");

                        mylistdata.add(new foodlistData_model(Imageurl,Name,Price,Detail));
                    } catch (JSONException e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                    popularRecycle_indetail();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonObjectRequest);

    }


    void popularRecycle_indetail(){
        foodlist_Adapter adapter = new foodlist_Adapter(mylistdata);
        recycleview_popular.setAdapter(adapter);
        adapter.setOnClickListener(new foodlist_Adapter.OnItemClickListener() {
            @Override
            public void OnImageClick(int position) {
                Intent intent = new Intent(getContext(), Food_Details_Activity.class);
                intent.putExtra("ItemName",mylistdata.get(position).getFoodname());
                intent.putExtra("ItemImge",mylistdata.get(position).getFoodimg());
                intent.putExtra("ItemDetail",mylistdata.get(position).getFooddetail());
                intent.putExtra("ItemPrice",mylistdata.get(position).getFoodprice());
                startActivity(intent);

            }


        });

    }

}