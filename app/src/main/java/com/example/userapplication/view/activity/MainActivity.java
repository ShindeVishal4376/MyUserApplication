package com.example.userapplication.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidstudy.networkmanager.Monitor;
import com.androidstudy.networkmanager.Tovuti;
import com.example.userapplication.Interface.UserListInfo;
import com.example.userapplication.R;
import com.example.userapplication.RoomDatabase.AppDatabase;
import com.example.userapplication.RoomDatabase.entity.AddUserList;
import com.example.userapplication.databinding.ActivityMainBinding;
import com.example.userapplication.model.JsonResponseDatum;

import com.example.userapplication.model.UserData;
import com.example.userapplication.utility.Constants;
import com.example.userapplication.utility.Utils;
import com.example.userapplication.view.adapter.UserListAdapter;
import com.example.userapplication.viewmodel.MainViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements UserListInfo, SwipeRefreshLayout.OnRefreshListener {
    ActivityMainBinding mBinding;
    MainViewModel mViewModel;
    List<UserData> mUserList = new ArrayList<>();
    List<AddUserList> mLocalUserList = new ArrayList<>();
    Snackbar snackbar;
    private LinearLayoutManager mLayoutManager;
    UserListAdapter mAdapter;
    private AppDatabase mDatabase;
    private UserListInfo mUserListListener;
    private Boolean isScrolling = false;
    private int mCurrentItems, mTotalItems, mScrolledOutItems;
    private int mPageNumber = 1;
    TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupBindings();
        initView();
        setView();
        setObserver();
        mBinding.swipeRefreshLayout.setOnRefreshListener(this);

        mBinding.rvUserList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mCurrentItems = mLayoutManager.getChildCount();
                mTotalItems = mLayoutManager.getItemCount();
                mScrolledOutItems = mLayoutManager.findFirstVisibleItemPosition();
                if (isScrolling && (mCurrentItems + mScrolledOutItems == mTotalItems)) {
                    isScrolling = false;
                    mPageNumber++;
                    mViewModel.getUserList(mPageNumber, Constants.PAGE_SIZE_5);
                }
            }
        });

    }

    private void initView() {
        mLayoutManager = (new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mBinding.rvUserList.setLayoutManager(mLayoutManager);
        mAdapter = new UserListAdapter(this);
        mBinding.rvUserList.setAdapter(mAdapter);
        mUserListListener = this;
        mDatabase = AppDatabase.getInstance(this);
    }


    @SuppressLint("RestrictedApi")
    private void setView() {
        Tovuti.from(getApplicationContext()).monitor(new Monitor.ConnectivityListener() {
            @Override
            public void onConnectivityChanged(int connectionType, boolean isConnected, boolean isFast) {
                // TODO: Handle the connection...
                if (isConnected) {
                    mPageNumber = 1;
                    mViewModel.getUserList(mPageNumber, Constants.PAGE_SIZE_5);
                    onInternetAlert();
                } else {
                    onNoInternetAlert();
                    mUserList.clear();
                    mLocalUserList.clear();
                    mLocalUserList = mDatabase.addUserListDao().getAllAddUserList();
                    for (int i = 0; i < mLocalUserList.size(); i++) {
                        UserData userData = new UserData();
                        userData.setFirstName(mLocalUserList.get(i).getFirstname());
                        userData.setLastName(mLocalUserList.get(i).getLastname());
                        userData.setAvatar(mLocalUserList.get(i).getAvtar());
                        userData.setEmail(mLocalUserList.get(i).getEmail());
                        mUserList.add(userData);
                        mAdapter.setUserList(mUserList);
                    }
                }
            }
        });
    }

    private void onInternetAlert() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rootLayout);
        snackbar = Snackbar.make(relativeLayout, "", Snackbar.LENGTH_LONG);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        View snackView = getLayoutInflater().inflate(R.layout.my_snackbar, null);
        layout.addView(snackView, 0);
        tvMessage = snackbar.getView().findViewById(R.id.message);
        tvMessage.setText(getResources().getString(R.string.internet_connected));
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.greeen));
        snackbar.show();
    }

    private void onNoInternetAlert() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rootLayout);
        snackbar = Snackbar.make(relativeLayout, "", Snackbar.LENGTH_LONG);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        View snackView = getLayoutInflater().inflate(R.layout.my_snackbar, null);
        layout.addView(snackView, 0);
        tvMessage = snackbar.getView().findViewById(R.id.message);
        tvMessage.setText(getResources().getString(R.string.no_internet_alert));
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        snackbar.show();
    }

    private void setObserver() {
        mViewModel.getmUserListData().observe(this, new Observer<JsonResponseDatum>() {
            @Override
            public void onChanged(JsonResponseDatum jsonResponseData) {
                mBinding.swipeRefreshLayout.setRefreshing(false);
                if (jsonResponseData.getData().size() > 0) {
                    mUserList.clear();
                    if (mPageNumber == 1) {
                        mDatabase.addUserListDao().deleteByAll();
                        mUserList.addAll(jsonResponseData.getData());
                        mAdapter.setUserList(jsonResponseData.getData());
                    } else {
                        mUserList.addAll(jsonResponseData.getData());
                        mAdapter.addAll(mUserList);
                    }

                    for (int i = 0; i < jsonResponseData.getData().size(); i++) {
                        AddUserList addUserList = new AddUserList();
                        addUserList.setFirstname(jsonResponseData.getData().get(i).getFirstName());
                        addUserList.setLastname(jsonResponseData.getData().get(i).getLastName());
                        addUserList.setEmail(jsonResponseData.getData().get(i).getEmail());
                        addUserList.setAvtar(jsonResponseData.getData().get(i).getAvatar());
                        mLocalUserList.add(addUserList);
                        mDatabase.addUserListDao().insertAddUserList(addUserList);
                    }
                }
            }
        });

        mViewModel.getIsProgressVisible().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    mBinding.rotateloading.start();
                } else {
                    mBinding.rotateloading.stop();
                }
            }
        });
    }

    private void setupBindings() {
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setLifecycleOwner(this);
        mBinding.setMainModel(mViewModel);
    }

    @Override
    public void getDisplayUserList(List<AddUserList> addUserList) {
        if (addUserList.size() != 0) {
//            JsonArray jsonArray = new JsonArray(addUserList)
//            mUserList.addAll(addUserList.get(0).);
        }
    }

    @Override
    public void onRefresh() {
        if (Utils.isNetworkConnected(getApplicationContext())) {
            mPageNumber = 1;
            mViewModel.getUserList(mPageNumber, Constants.PAGE_SIZE_5);
        } else {
            mBinding.swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_internet_alert), Toast.LENGTH_LONG).show();
        }
    }
}