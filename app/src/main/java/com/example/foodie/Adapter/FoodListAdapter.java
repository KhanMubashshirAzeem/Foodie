package com.example.foodie.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.foodie.Domain.Foods;
import com.example.foodie.R;

import java.util.ArrayList;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.viewHolder> {

    ArrayList<Foods> items;
    Context context;

    public FoodListAdapter(ArrayList<Foods> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public FoodListAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_holder_list_food, parent, false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodListAdapter.viewHolder holder, int position) {
        holder.titleTxt.setText(items.get(position).getTitle());
        holder.timeTxt.setText(items.get(position).getTimeValue() + " min");
        holder.priceTxt.setText("$" + items.get(position).getPrice());
        holder.starTxt.setText("" + items.get(position).getStar());

        Glide.with(context).load(items.get(position).getImagePath()).transform(new CenterCrop(), new RoundedCorners(30)).into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt, starTxt, priceTxt, timeTxt;
        ImageView pic;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxtLF);
            starTxt = itemView.findViewById(R.id.ratingTxtLF);
            priceTxt = itemView.findViewById(R.id.priceTxtLF);
            timeTxt = itemView.findViewById(R.id.timeTxtLF);
            pic = itemView.findViewById(R.id.imgLF);
        }
    }

}
