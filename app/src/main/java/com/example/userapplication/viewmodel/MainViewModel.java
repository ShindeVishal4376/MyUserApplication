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
    private Observable<List<JsonResponseDatum>> mUserListObservable;
    public MutableLiveData<List<JsonResponseDatum>> mUserListData = new MutableLiveData<>();

    public void getUserList() {
        mUserListObservable = Observable.defer(new Callable<Observable<List<JsonResponseDatum>>>() {
            @Override
            public Observable<List<JsonResponseDatum>> call() throws Exception {
                return mRepository.getUserList();
            }
        });

        mUserListObservable.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(mUserListObserver);
    }

    Observer<List<JsonResponseDatum>> mUserListObserver = new Observer<List<JsonResponseDatum>>() {
        @Override
        public void onSubscribe(Disposable d) {
            Log.d("error", "suscripbe");
        }

        @Override
        public void onNext(List<JsonResponseDatum> jsonResponseData) {
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

    public MutableLiveData<List<JsonResponseDatum>> getmUserListData() {
        return mUserListData;
    }
}
