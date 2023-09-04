package com.example.a12;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link album#newInstance} factory method to
 * create an instance of this fragment.
 */
public class album extends Fragment {
    private RecyclerView recyclerView;
    private ImageListAdapter1 adapter;
    private List<ImageItem> imageList;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public album() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment album.
     */
    // TODO: Rename and change types and number of parameters
    public static album newInstance(String param1, String param2) {
        album fragment = new album();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album, container, false);

        recyclerView = view.findViewById(R.id.albumiRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        imageList = new ArrayList<>();
        imageList.add(new ImageItem(R.drawable.lavacake, "Lava cake"));
        imageList.add(new ImageItem(R.drawable.tiramisu, "Tiramisu"));
        imageList.add(new ImageItem(R.drawable.cheesecake, "Cheesecake s malinama"));

        adapter = new ImageListAdapter1(getContext(), imageList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}