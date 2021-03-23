package com.example.userapplication.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userapplication.R;
import com.example.userapplication.databinding.ItemUserLayoutBinding;
import com.example.userapplication.model.JsonResponseDatum;
import com.example.userapplication.viewmodel.ItemUserViewModel;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.AdapterViewModel> {

    Context mContext;
    private List<JsonResponseDatum> mUserList;


    @NonNull
    @Override
    public AdapterViewModel onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        ItemUserLayoutBinding mBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_user_layout, parent, false);
        return new AdapterViewModel(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewModel holder, int position) {
        holder.bind(mUserList.get(position));
        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return mUserList == null ? 0 : mUserList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void setUserList(List<JsonResponseDatum> userList) {
        mUserList = userList;
        notifyDataSetChanged();
    }

    public class AdapterViewModel extends RecyclerView.ViewHolder {

        public ItemUserLayoutBinding mBinding;

        public AdapterViewModel(ItemUserLayoutBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }

        public void bind(JsonResponseDatum jsonResponseDatum) {
            if (mBinding.getItemUserModel() == null) {
                mBinding.setItemUserModel(new ItemUserViewModel(itemView.getContext(), jsonResponseDatum));
            } else {
                mBinding.getItemUserModel().setUserData(jsonResponseDatum);
            }
        }
    }
}


