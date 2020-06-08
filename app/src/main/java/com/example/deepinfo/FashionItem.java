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
public class FashionItem extends Fragment {

    RecyclerView fashionList;
    List<ModalClass> productInformation;

    public FashionItem() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fashion_item, container, false);
        fashionList = (RecyclerView) view.findViewById(R.id.fashion_item);
        ProductInfoList productInfoList = new ProductInfoList(productInformation);
        fashionList.setLayoutManager(new LinearLayoutManager(getActivity()));
        fashionList.setAdapter(productInfoList);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        productInformation = new ArrayList<>();
        productInformation.add(new ModalClass("Romani Watch", "F1", "2500",R.drawable.watch1));
        productInformation.add(new ModalClass("Titan Grand Slam", "F2", "2699", R.drawable.watch2));
        productInformation.add(new ModalClass("Quartz Gold", "F3", "1900", R.drawable.watch3));
        productInformation.add(new ModalClass("KS Body Deo", "F4", "250", R.drawable.fashion4));
        productInformation.add(new ModalClass("Boys Hair Wax", "F5", "650", R.drawable.fashion5));
    }
}
