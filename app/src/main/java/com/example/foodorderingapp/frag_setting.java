package com.example.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;


public class frag_setting extends Fragment {

Button btnlogout;
    public frag_setting() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_frag_setting, container, false);

        btnlogout = v.findViewById(R.id.btnlogout);

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getContext(), "LogOut Successfully", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getContext(), MainActivity.class);
                startActivity(i);

            }
        });


        return v;
    }
}