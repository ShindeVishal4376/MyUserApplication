package com.example.userapplication.repository;

import com.example.userapplication.model.JsonResponseDatum;
import com.example.userapplication.network.APIClient;
import com.example.userapplication.network.APIInterface;

import java.util.List;

import io.reactivex.Observable;

public class RemoteRepository {

    public APIInterface mApiService = APIClient.getClient().create(APIInterface.class);

    public Observable<JsonResponseDatum> getUserList(int mPageNumber, int pageSize5) {
        return mApiService.getUserList(mPageNumber,pageSize5);
    }
}
