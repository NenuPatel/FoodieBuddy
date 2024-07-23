package com.example.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.Adapters.CartAdapter;
import com.example.foodorderingapp.ModelData.CartData_Model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class frag_cart extends Fragment {

    RecyclerView cartRecycleview;
    TextView totalAmountTextView;
    ArrayList<CartData_Model> cartdata;
    Button proccedbtn;
    CartAdapter cartAdapter;
      DatabaseReference databaseReference;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_frag_cart, container, false);

        cartRecycleview = v.findViewById(R.id.cartRecycleview);
        totalAmountTextView = v.findViewById(R.id.totalAmountTextView);

        proccedbtn = v.findViewById(R.id.proccedbtn);



        cartdata = new ArrayList<>();

        cartRecycleview.setHasFixedSize(true);
        databaseReference = FirebaseDatabase.getInstance().getReference("Cart");

        cartRecycleview.setLayoutManager(new LinearLayoutManager(getContext()));


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot C : snapshot.getChildren()){

                    if(C.exists()){
                        final String Cimg = C.child("image").getValue(String.class);
                        final String Cname = C.child("name").getValue(String.class);
                        final String Cprice = C.child("price").getValue(String.class);
                        final int Cquantity = C.child("quantity").getValue(Integer.class);
                        final String Cdetail = C.child("id").child("detail").getValue(String.class);

                        CartData_Model cartDataModel = new CartData_Model(Cname,Cprice,Cimg,Cquantity,Cdetail);


                        cartdata.add(cartDataModel);
                    }
                        cartAdapter = new CartAdapter(cartdata, getContext());
                        cartRecycleview.setAdapter(cartAdapter);

                    calculateAndDisplayTotal(snapshot);


                }
                cartAdapter.notifyDataSetChanged();
                
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity().getApplicationContext(), "Database Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        proccedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "Your Order Placed Successfully", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(), OrderPlacedMsg.class);
                startActivity(i);
}
        });

        return v;
    }

    private void calculateAndDisplayTotal(DataSnapshot snapshot) {
        if(snapshot.exists()){
            double total = 0.0;
            for (CartData_Model item : cartdata) {
                total += Double.parseDouble(item.getCarItemPrice().replace("₹", "")) * item.getCartQuantity();
            }
            totalAmountTextView.setText("Total: ₹" + String.format("%.2f", total));
        }
        else{
            totalAmountTextView.setText("Total: ₹0");
            Toast.makeText(getContext(), "There are no item in Cart", Toast.LENGTH_SHORT).show();
        }
     
    }

}