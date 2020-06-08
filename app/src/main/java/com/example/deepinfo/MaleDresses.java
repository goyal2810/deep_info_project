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
public class MaleDresses extends Fragment {

    RecyclerView maleDress;
    List<ModalClass> productInformation;

    public MaleDresses() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_male_dresses, container, false);
        maleDress = (RecyclerView) view.findViewById(R.id.male_dress);
        ProductInfoList productInfoList = new ProductInfoList(productInformation);
        maleDress.setLayoutManager(new LinearLayoutManager(getActivity()));
        maleDress.setAdapter(productInfoList);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productInformation = new ArrayList<>();
        productInformation.add(new ModalClass("Men Rock Shirt", "M2", "1399",R.drawable.shirt2));
        productInformation.add(new ModalClass("Men Half Shirt", "M3", "2699", R.drawable.shirt3));
        productInformation.add(new ModalClass("Stylish Jacket", "M4", "1900", R.drawable.shirt4));
        productInformation.add(new ModalClass("Men Denim Shirt", "M1", "1399", R.drawable.shirt1));
        productInformation.add(new ModalClass("Sparkle Shirt", "M5", "650", R.drawable.shirt5));
    }
}
