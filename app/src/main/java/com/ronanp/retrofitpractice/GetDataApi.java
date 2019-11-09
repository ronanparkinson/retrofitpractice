package com.ronanp.retrofitpractice;

import java.util.List;

import model.RetroPerson;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataApi {

    @GET("/apitest")
    Call<List<RetroPerson>> getAllPeople();
}
