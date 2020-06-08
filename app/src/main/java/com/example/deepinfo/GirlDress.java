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
public class GirlDress extends Fragment {

    RecyclerView fashionList;
    List<ModalClass> productInformation;

    public GirlDress() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_girl_dress, container, false);
        fashionList = (RecyclerView) view.findViewById(R.id.girl_dress_list);
        ProductInfoList productInfoList = new ProductInfoList(productInformation);
        fashionList.setLayoutManager(new LinearLayoutManager(getActivity()));
        fashionList.setAdapter(productInfoList);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productInformation = new ArrayList<>();
        productInformation.add(new ModalClass("Women Designer Kurti", "W1", "750",R.drawable.girl2));
        productInformation.add(new ModalClass("New Denim Work", "W2", "699", R.drawable.girl3));
        productInformation.add(new ModalClass("Printed Dark ", "W3", "750", R.drawable.girl4));
        productInformation.add(new ModalClass("Preety Women", "W4", "1399", R.drawable.girl5));
        productInformation.add(new ModalClass("Women Kurti", "W1", "650", R.drawable.girl1));
    }
}
