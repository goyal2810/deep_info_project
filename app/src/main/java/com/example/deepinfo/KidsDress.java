package com.example.deepinfo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class KidsDress extends Fragment {

    RecyclerView kidsList;
    List<ModalClass> productInformation;

    public KidsDress() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_kids_dress, container, false);
        kidsList = (RecyclerView) view.findViewById(R.id.kids_dress);
        ProductInfoList productInfoList = new ProductInfoList(productInformation);
        kidsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        kidsList.setAdapter(productInfoList);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productInformation = new ArrayList<>();
        productInformation.add(new ModalClass("Kids Special Frock", "K2", "699",R.drawable.kid2));
        productInformation.add(new ModalClass("Smart Kid", "K3", "780", R.drawable.kid3));
        productInformation.add(new ModalClass("Kids Frock", "K1", "750", R.drawable.kid1));
        productInformation.add(new ModalClass("Small Kid Denim", "K4", "900", R.drawable.kid4));
        productInformation.add(new ModalClass("Cute Kid Frock", "K5", "650", R.drawable.kid5));
    }
}
