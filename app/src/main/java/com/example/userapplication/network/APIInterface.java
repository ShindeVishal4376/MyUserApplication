package com.example.userapplication.network;

import com.example.userapplication.model.JsonResponseDatum;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("/users")
    Observable<List<JsonResponseDatum>> getUserList();
}
