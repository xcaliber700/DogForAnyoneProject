package com.example.dogforanyone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.zip.Inflater;

public class dogOptionListFragment extends Fragment {

    RecyclerView recyclerView;
    ListAdapter adapter;

    public dogOptionListFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        System.out.println("Eneterererrerererer");
        View rootView = inflater.inflate(R.layout.fragment_dog_option_list, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerID);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        adapter = new ListAdapter();
        recyclerView.setAdapter(adapter);
        return rootView;
    }
}