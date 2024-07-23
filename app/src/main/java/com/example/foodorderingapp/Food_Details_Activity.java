package com.example.foodorderingapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodorderingapp.ModelData.FoodItem_DetailModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Food_Details_Activity extends AppCompatActivity {

    ImageView back_arrow, detailfoodimg;
    TextView detailfoodname,descriptiontxtview,quantitynumber,detailpricetxt;
    ImageView plus_icon,minus_icon;
    int qty ;
    Button addtocartbtn;
    DatabaseReference databaseReference ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);


        back_arrow = findViewById(R.id.back_arrow);
        detailfoodimg = findViewById(R.id.detailfoodimg);
        detailfoodname = findViewById(R.id.detailfoodname);
        descriptiontxtview = findViewById(R.id.descriptiontxtview);
        quantitynumber = findViewById(R.id.quantitynumber);
        detailpricetxt = findViewById(R.id.pricetxt);
        plus_icon = findViewById(R.id.plus_icon);
        minus_icon = findViewById(R.id.minus_icon);

        databaseReference = FirebaseDatabase.getInstance().getReference("Cart");
        
        qty = Integer.parseInt(quantitynumber.getText().toString());
        addtocartbtn = findViewById(R.id.addtocart);

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        String foodname = getIntent().getStringExtra("ItemName");
        String foodImage = getIntent().getStringExtra("ItemImge");
        String foodDetail = getIntent().getStringExtra("ItemDetail");
        String foodPrice = getIntent().getStringExtra("ItemPrice");

        detailfoodname.setText(foodname);
        Picasso.get().load(foodImage).into(detailfoodimg);
        detailpricetxt.setText(foodPrice);
        descriptiontxtview.setText(foodDetail);



        addtocartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

         addToCart(foodname,foodImage,foodDetail,qty,foodPrice);

            }
        });

        plus_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qty++;
                quantitynumber.setText(String.valueOf(qty));
            }
        });

        minus_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (qty > 1) {
                    qty--;
                    quantitynumber.setText(String.valueOf(qty));

                } else {
                    Toast.makeText(Food_Details_Activity.this, "Item is not  < 1", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


    private void addToCart(String foodname, String foodImage, String foodDetail, int qty, String foodPrice) {

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long count = snapshot.getChildrenCount();
                String id = "i" + (count + 1);

                FoodItem_DetailModel foodItem = new FoodItem_DetailModel(foodname,foodImage,foodDetail,qty,foodPrice);

                databaseReference.child(id).setValue(foodItem).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(Food_Details_Activity.this, "Added to Cart Successfully", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Toast.makeText(Food_Details_Activity.this, "Failed to Add to Cart", Toast.LENGTH_SHORT).show();

                        }
                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Food_Details_Activity.this, "Database Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

}