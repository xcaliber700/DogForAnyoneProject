package com.example.dogforanyone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private  int[] images= {R.drawable.img,R.drawable.img_1,R.drawable.img_2,R.drawable.img_3};
    private String[] breeds = {"Golden retriever", "Labrador", "German shepherd", "Pamerian"};
    private String[] name = {"McKing","Casper","Broly","Lexi"};
    private String[] locs = {"Vancouver","coquitlam","surrey","white rock"};

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recyclerlist,parent,false);
        ListViewHolder viewHolder = new ListViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.dogBreed.setText("Breed: "+breeds[position]);
        holder.dogName.setText("Name: "+name[position]);
        holder.dogImg.setImageResource(images[position]);
        holder.location.setText("Location: "+locs[position]);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{
        ImageView dogImg;
        TextView dogBreed;
        TextView dogName;
        TextView location;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            dogImg = itemView.findViewById(R.id.item_image);
            dogBreed = itemView.findViewById(R.id.item_breed);
            dogName = itemView.findViewById(R.id.item_name);
            location = itemView.findViewById(R.id.item_location);
        }
    }
}
