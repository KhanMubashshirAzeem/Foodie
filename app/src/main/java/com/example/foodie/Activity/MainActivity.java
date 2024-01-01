package com.example.foodie.Activity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import com.example.foodie.Adapter.BestFoodsAdapter;
import com.example.foodie.Adapter.CategoryAdapter;
import com.example.foodie.Domain.Category;
import com.example.foodie.Domain.Foods;
import com.example.foodie.Domain.Location;
import com.example.foodie.Domain.Price;
import com.example.foodie.Domain.Time;
import com.example.foodie.R;
import com.example.foodie.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initLocation();
        initTime();
        initPrice();
        initBestFood();
        initCategory();

    }

    private void initBestFood() {
        DatabaseReference myRef= database.getReference("Foods");
        binding.progressBarBestFood.setVisibility(View.VISIBLE);
        ArrayList<Foods> list = new ArrayList<>();
        Query query = myRef.orderByChild("BestFood").equalTo(true);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot issue: snapshot.getChildren()){
                    list.add(issue.getValue(Foods.class));
                }
                if (list.size()>0){
                    binding.bestFoodRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL,false));
                    RecyclerView.Adapter adapter = new BestFoodsAdapter(list);
                    binding.bestFoodRecyclerView.setAdapter(adapter);
                }
                binding.progressBarBestFood.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initCategory() {
        DatabaseReference myRef= database.getReference("Category");
        binding.categoryProgressBar.setVisibility(View.VISIBLE);
        ArrayList<Category> list = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot issue: snapshot.getChildren()){
                    list.add(issue.getValue(Category.class));
                }
                if (list.size()>0){
                    binding.categoryRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,4));
                    RecyclerView.Adapter adapter = new CategoryAdapter(list);
                    binding.categoryRecyclerView.setAdapter(adapter);
                }
                binding.categoryProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void initLocation() {
        DatabaseReference myRefDb = database.getReference("Location");
        ArrayList<Location> list = new ArrayList<>();
        myRefDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Location.class));
                    }
                    ArrayAdapter<Location> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.sp_item, list);
                    adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
                    binding.locationSp.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initTime() {
        DatabaseReference myRefDb = database.getReference("Location");
        ArrayList<Time> list = new ArrayList<>();
        myRefDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Time.class));
                    }
                    ArrayAdapter<Time> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.sp_item, list);
                    adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
                    binding.timeSp.setAdapter(adapter);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void initPrice() {
        DatabaseReference myRefDb = database.getReference("Price");
        ArrayList<Price> list = new ArrayList<>();
        myRefDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Price.class));
                    }
                    ArrayAdapter<Price> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.sp_item, list);
                    adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
                    binding.priceSp.setAdapter(adapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }



}