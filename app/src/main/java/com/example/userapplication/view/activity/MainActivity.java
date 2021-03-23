package com.example.userapplication.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.AbsListView;
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
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements UserListInfo {
    ActivityMainBinding mBinding;
    MainViewModel mViewModel;
    List<UserData> mUserList = new ArrayList<>();
    List<AddUserList> mLocalUserList = new ArrayList<>();

    private LinearLayoutManager mLayoutManager;
    UserListAdapter mAdapter;
    private AppDatabase mDatabase;
    JSONObject jsonObjectAddressResponse = null;
    JSONObject jsonObjectCompanyResponse = null;
    private UserListInfo mUserListListener;
    private Boolean isScrolling = false;
    private int mCurrentItems, mTotalItems, mScrolledOutItems;
    private int mPageNumber = 1;
    private static final int PAGE_SIZE = 4;
    private int mUserListCount = 0, mUserOffsetCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupBindings();
        initView();
        setView();
        setObserver();

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

                if (!Utils.isNetworkConnected(getApplicationContext())) {

                    if (isScrolling && (mCurrentItems + mScrolledOutItems == mTotalItems)) {
                        isScrolling = false;
                        mPageNumber++;
                        mViewModel.getUserList(mPageNumber, Constants.PAGE_SIZE_5);
//                        mPageNumber++;

                     /*   Address address = null;
                        Company company = null;*/

                       /* if (mUserListCount >= 0) {
                            mUserListCount -= 4;
                            mLocalUserList = mDatabase.addUserListDao().getAllAddUserList(mUserOffsetCount);
                            mUserOffsetCount += 4;
                            for (int i = 0; i < mLocalUserList.size(); i++) {
                                JsonResponseDatum jsonResponseDatum = new JsonResponseDatum();
                               *//* jsonResponseDatum.setId(mLocalUserList.get(i).getUserid());
                                jsonResponseDatum.setName(mLocalUserList.get(i).getName());
                                jsonResponseDatum.setUsername(mLocalUserList.get(i).getUsername());
                                jsonResponseDatum.setEmail(mLocalUserList.get(i).getEmail());
                                jsonResponseDatum.setPhone(mLocalUserList.get(i).getPhone());
                                jsonResponseDatum.setWebsite(mLocalUserList.get(i).getWebsite());*//*
                                try {
                                    JSONObject jsonObjectAddress = new JSONObject(mLocalUserList.get(i).getAddress());
                                    Gson gson = new Gson();
                                //    address = gson.fromJson(jsonObjectAddress.toString(), Address.class);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                             //   jsonResponseDatum.setAddress(address);
                                try {
                                    JSONObject jsonObjectCompany = new JSONObject(mLocalUserList.get(i).getCompany());
                                    Gson gson = new Gson();
                                 //   company = gson.fromJson(jsonObjectCompany.toString(), Company.class);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                             //   jsonResponseDatum.setCompany(company);
                                mUserList.add(jsonResponseDatum);
                                mAdapter.setUserList(mUserList);
                            }
                        }*/
                    }

                } else {

                }
            }
        });

    }

    private void initView() {
        mLayoutManager = (new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mBinding.rvUserList.setLayoutManager(mLayoutManager);
        mAdapter = new UserListAdapter();
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
                    mViewModel.getUserList(mPageNumber, Constants.PAGE_SIZE_5);
                    mUserListCount = 0;
                    mUserOffsetCount = 0;
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_internet_alert), Toast.LENGTH_LONG).show();
                 /*   Address address = null;
                    Company company = null;*/
                    mUserList.clear();
                    mUserListCount = mDatabase.addUserListDao().getAllAddUserListCount().size() - 4;
                    mUserOffsetCount = 4;
                    mLocalUserList = mDatabase.addUserListDao().getAllAddUserList(0);
                    for (int i = 0; i < mLocalUserList.size(); i++) {
                        JsonResponseDatum jsonResponseDatum = new JsonResponseDatum();
                    /*    jsonResponseDatum.setId(mLocalUserList.get(i).getUserid());
                        jsonResponseDatum.setName(mLocalUserList.get(i).getName());
                        jsonResponseDatum.setUsername(mLocalUserList.get(i).getUsername());
                        jsonResponseDatum.setEmail(mLocalUserList.get(i).getEmail());
                        jsonResponseDatum.setPhone(mLocalUserList.get(i).getPhone());
                        jsonResponseDatum.setWebsite(mLocalUserList.get(i).getWebsite());*/
                        try {
                            JSONObject jsonObjectAddress = new JSONObject(mLocalUserList.get(i).getAddress());
                            Gson gson = new Gson();
                            //    address = gson.fromJson(jsonObjectAddress.toString(), Address.class);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //jsonResponseDatum.setAddress(address);
                        try {
                            JSONObject jsonObjectCompany = new JSONObject(mLocalUserList.get(i).getCompany());
                            Gson gson = new Gson();
                            // company = gson.fromJson(jsonObjectCompany.toString(), Company.class);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //  jsonResponseDatum.setCompany(company);
                      /*  mUserList.add(jsonResponseDatum);
                        mAdapter.setUserList(mUserList);*/
                    }
                }
            }
        });

        if (Utils.isNetworkConnected(this)) {

        } else {

        }
    }

    private void setObserver() {
        mViewModel.getmUserListData().observe(this, new Observer<JsonResponseDatum>() {
            @Override
            public void onChanged(JsonResponseDatum jsonResponseData) {

                if (jsonResponseData.getData().size() > 0) {
                    mUserList.clear();
                    if (mPageNumber == 1) {
                        //   mBinding.tvEmptyMsg.setVisibility(View.GONE);
                        // mBinding.rvCategoryProductList.setVisibility(View.VISIBLE);
                        mUserList.addAll(jsonResponseData.getData());
                        mAdapter.setUserList(jsonResponseData.getData());
                    } else {
                        mUserList.addAll(jsonResponseData.getData());
                        mAdapter.addAll(mUserList);
                    }
                }
              /*  if (jsonResponseData.size() > 0) {
                    mUserList.clear();
                    mUserList.addAll(jsonResponseData);
                    mAdapter.setUserList(mUserList);
                  *//*  mDatabase.addUserListDao().deleteByAll();
                    for (int i = 0; i < mUserList.size(); i++) {
                        AddUserList addUserList = new AddUserList();
                      *//**//*  addUserList.setUserid(mUserList.get(i).getId());
                        addUserList.setName(mUserList.get(i).getName());
                        addUserList.setUsername(mUserList.get(i).getUsername());
                        addUserList.setEmail(mUserList.get(i).getEmail());
                        addUserList.setPhone(mUserList.get(i).getPhone());
                        addUserList.setWebsite(mUserList.get(i).getWebsite());*//**//*
                     *//**//*  try {
                            jsonObjectAddressResponse = new JSONObject(new Gson().toJson(mUserList.get(i).getAddress()));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*//**//*
                        addUserList.setAddress(jsonObjectAddressResponse.toString());
                       *//**//* try {
                            jsonObjectCompanyResponse = new JSONObject(new Gson().toJson(mUserList.get(i).getCompany()));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*//**//*
                        addUserList.setCompany(jsonObjectCompanyResponse.toString());
                        mLocalUserList.add(addUserList);
                        mDatabase.addUserListDao().insertAddUserList(addUserList);
                    }*//*
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.internet_connected), Toast.LENGTH_SHORT).show();
                }*/
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
}