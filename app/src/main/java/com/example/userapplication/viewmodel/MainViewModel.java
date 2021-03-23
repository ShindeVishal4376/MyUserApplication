package com.example.userapplication.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.userapplication.model.JsonResponseDatum;
import com.example.userapplication.repository.RemoteRepository;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {
    public RemoteRepository mRepository = new RemoteRepository();
    private Observable<JsonResponseDatum> mUserListObservable;
    public MutableLiveData<JsonResponseDatum> mUserListData = new MutableLiveData<>();

    public void getUserList(int mPageNumber, int pageSize5) {
        mUserListObservable = Observable.defer(new Callable<Observable<JsonResponseDatum>>() {
            @Override
            public Observable<JsonResponseDatum> call() throws Exception {
                return mRepository.getUserList(mPageNumber,pageSize5);
            }
        });

        mUserListObservable.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(mUserListObserver);
    }

    Observer<JsonResponseDatum> mUserListObserver = new Observer<JsonResponseDatum>() {
        @Override
        public void onSubscribe(Disposable d) {
            Log.d("error", "suscripbe");
        }

        @Override
        public void onNext(JsonResponseDatum jsonResponseData) {
            mUserListData.setValue(jsonResponseData);
        }

        @Override
        public void onError(Throwable e) {
            Log.d("error", e.getMessage());
        }

        @Override
        public void onComplete() {
            Log.d("error", "completed");
        }
    };

    public MutableLiveData<JsonResponseDatum> getmUserListData() {
        return mUserListData;
    }
}
