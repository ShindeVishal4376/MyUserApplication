package com.example.userapplication.network;

import com.example.userapplication.model.JsonResponseDatum;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("users")
    Observable<JsonResponseDatum> getUserList(@Query("page") int mPageNumber,@Query("per_page") int pageSize5);
}
