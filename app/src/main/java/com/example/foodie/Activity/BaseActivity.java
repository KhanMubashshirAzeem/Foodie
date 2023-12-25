package com.example.foodie.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.foodie.R;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class BaseActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase database;
    public String TAG="khan_Foodie";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        getWindow().setStatusBarColor(Color.parseColor("#FFE4B5"));




    }
}