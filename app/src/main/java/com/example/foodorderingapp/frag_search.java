package com.example.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.Adapters.MainMenulist_Adapter;
import com.example.foodorderingapp.ModelData.MainMenu_listdata;

import java.util.ArrayList;

public class frag_search extends Fragment {
    RecyclerView recycleview_mainmenu;
    SearchView searchView;

    ArrayList<MainMenu_listdata> searchList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_frag_search, container, false);

        recycleview_mainmenu = v.findViewById(R.id.recycleview_mainmenu);

        searchView = v.findViewById(R.id.searchView);

        ArrayList<MainMenu_listdata> mylistdata =new ArrayList<>();

        mylistdata.add(new MainMenu_listdata(R.drawable.pizza,"Pizza","₹250"));
        mylistdata.add(new MainMenu_listdata(R.drawable.burger,"Burger","₹150"));
        mylistdata.add(new MainMenu_listdata(R.drawable.coffee,"Coffee","₹70"));
        mylistdata.add(new MainMenu_listdata(R.drawable.french_fryf,"French Fry","₹100"));
        mylistdata.add(new MainMenu_listdata(R.drawable.juis,"Juice","₹75"));
        mylistdata.add(new MainMenu_listdata(R.drawable.popcorn_c,"PopCorn","₹50"));

        recycleview_mainmenu.setHasFixedSize(true);
        recycleview_mainmenu.setLayoutManager(new LinearLayoutManager(v.getContext()));
        MainMenulist_Adapter adapter = new MainMenulist_Adapter(mylistdata);
        recycleview_mainmenu.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchList = new ArrayList<>();

                if(query.length()>0){
                    for (int i = 0; i <mylistdata.size(); i++) {
                        if(mylistdata.get(i).getMfoodname().toUpperCase().contains(query.toUpperCase())){

                            MainMenu_listdata data = new MainMenu_listdata(0,"Food","0");
                            data.setMfoodname(mylistdata.get(i).getMfoodname());
                            data.setMfoodimg(mylistdata.get(i).getMfoodimg());
                            data.setMfoodprice(mylistdata.get(i).getMfoodprice());
                            searchList.add(data);
                        }
                    }
                    recycleview_mainmenu.setLayoutManager(new LinearLayoutManager(v.getContext()));
                    MainMenulist_Adapter adapter = new MainMenulist_Adapter(searchList);
                    recycleview_mainmenu.setAdapter(adapter);

                }else{
                    recycleview_mainmenu.setHasFixedSize(true);
                    recycleview_mainmenu.setLayoutManager(new LinearLayoutManager(v.getContext()));
                    MainMenulist_Adapter adapter = new MainMenulist_Adapter(mylistdata);
                    recycleview_mainmenu.setAdapter(adapter);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList = new ArrayList<>();

                if(newText.length()>0){
                    for (int i = 0; i <mylistdata.size(); i++) {
                        if(mylistdata.get(i).getMfoodname().toUpperCase().contains(newText.toUpperCase())){

                            MainMenu_listdata data = new MainMenu_listdata(0,"Food","0");
                            data.setMfoodname(mylistdata.get(i).getMfoodname());
                            data.setMfoodimg(mylistdata.get(i).getMfoodimg());
                            data.setMfoodprice(mylistdata.get(i).getMfoodprice());
                            searchList.add(data);
                        }
                    }
                    recycleview_mainmenu.setLayoutManager(new LinearLayoutManager(v.getContext()));
                    MainMenulist_Adapter adapter = new MainMenulist_Adapter(searchList);
                    recycleview_mainmenu.setAdapter(adapter);

                }else{
                    recycleview_mainmenu.setHasFixedSize(true);
                    recycleview_mainmenu.setLayoutManager(new LinearLayoutManager(v.getContext()));
                    MainMenulist_Adapter adapter = new MainMenulist_Adapter(mylistdata);
                    recycleview_mainmenu.setAdapter(adapter);
                }
                return false;
            }
        });

        adapter.setOnclickListener(new MainMenulist_Adapter.OnItemClickListener() {
            @Override
            public void onItemclick(int position) {
                Intent intent = new Intent(getContext(), Food_Details_Activity.class);
                intent.putExtra("ItemName",mylistdata.get(position).Mfoodname);
                intent.putExtra("ItemImge",mylistdata.get(position).Mfoodimg);
                intent.putExtra("ItemDetail",mylistdata.get(position).Mfoodprice);
                startActivity(intent);
            }




            @Override
            public void AddToCart(int position) {
                Intent i = new Intent(getContext(), frag_cart.class);
                startActivity(i);
            }
        });
        return v;
    }
}