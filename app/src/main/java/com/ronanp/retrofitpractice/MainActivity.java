package com.ronanp.retrofitpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import adapter.ApiAdapter;
import model.RetroPerson;
import network.RetrofitApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ApiAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_row);

        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("loading...");
        progressDoalog.show();

        GetDataApi dataApi = RetrofitApi.getRetrofitInstance().create(GetDataApi.class);
        Call<RetroPerson> call = dataApi.getAllPeople();
        call.enqueue(new Callback<RetroPerson>() {
            @Override
            public void onResponse(Call<RetroPerson> call, Response<RetroPerson> response) {
               // adapter.notifyDataSetChanged();
                progressDoalog.dismiss();
                Log.d("", response.body().toString());
            }

            @Override
            public void onFailure(Call<RetroPerson> call, Throwable t) {
                progressDoalog.dismiss();
                Log.getStackTraceString(t);
                Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void generateDataList(List<RetroPerson> retroPeople) {
        recyclerView =  findViewById(R.id.RecyclerView);
        adapter = new ApiAdapter(this,retroPeople);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}