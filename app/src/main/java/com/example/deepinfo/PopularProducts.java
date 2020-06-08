package com.example.deepinfo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopularProducts extends Fragment {
    RecyclerView popluarProducts;
    List<ModalClass> productInformation;

    public PopularProducts() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_popular_products, container, false);
        popluarProducts = (RecyclerView) view.findViewById(R.id.popular_product_list);
        ProductInfoList productInfoList = new ProductInfoList(productInformation);
        popluarProducts.setLayoutManager(new LinearLayoutManager(getActivity()));
        popluarProducts.setAdapter(productInfoList);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productInformation = new ArrayList<>();
        productInformation.add(new ModalClass("Romani Watch", "F1", "2500",R.drawable.watch1));
        productInformation.add(new ModalClass("Titan Grand Slam", "F2", "2699", R.drawable.watch2));
        productInformation.add(new ModalClass("Kids Frock", "K1", "750", R.drawable.kid1));
        productInformation.add(new ModalClass("Men Denim Shirt", "M1", "1399", R.drawable.shirt1));
        productInformation.add(new ModalClass("Women Kurti", "W1", "650", R.drawable.girl1));
    }
}
