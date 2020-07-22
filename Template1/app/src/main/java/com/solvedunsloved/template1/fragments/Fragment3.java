package com.solvedunsloved.template1.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.solvedunsloved.template1.Adapters.RVadapter;
import com.solvedunsloved.template1.R;
import com.solvedunsloved.template1.data.color;
import com.solvedunsloved.template1.jsonAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment3 extends Fragment {

    private RecyclerView recyclerView;
    private RVadapter adapter;
    public Fragment3() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);

        recyclerView = view.findViewById(R.id.rvadapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonAPI jsonapi = retrofit.create(jsonAPI.class);
        Call<List<color>> call = jsonapi.getcolor();

        call.enqueue(new Callback<List<color>>() {
            @Override
            public void onResponse(Call<List<color>> call, Response<List<color>> response) {
                if (!response.isSuccessful()) {
                    Log.d("error", response.toString());
                }
                List<color> colors = response.body();

                for (color color1 : colors) {
                    color newcolor = new color();
                        newcolor.setID(color1.getID());
                        newcolor.setUrl(color1.getUrl());
                        newcolor.setTitle(color1.getTitle());
                }

                adapter = new RVadapter(getActivity().getApplicationContext(),colors);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
    }
            @Override
            public void onFailure(Call<List<color>> call, Throwable t) {
            }
        });
        return view;
    }
}