package com.ronanp.retrofitpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
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
        Call<List<RetroPerson>> call = dataApi.getAllPeople();
        call.enqueue(new Callback<List<RetroPerson>>() {
            @Override
            public void onResponse(Call<List<RetroPerson>> call, Response<List<RetroPerson>> response) {
                progressDoalog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<RetroPerson>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void generateDataList(List<RetroPerson> photoList) {
        recyclerView = findViewById(R.id.parent);
        adapter = new ApiAdapter(this,photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}