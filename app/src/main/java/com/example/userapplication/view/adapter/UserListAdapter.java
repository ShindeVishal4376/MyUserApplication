package com.example.userapplication.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.userapplication.R;
import com.example.userapplication.databinding.ItemUserLayoutBinding;
import com.example.userapplication.model.UserData;
import com.example.userapplication.view.activity.MainActivity;
import com.example.userapplication.viewmodel.ItemUserViewModel;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.AdapterViewModel> {

    Context mContext;
    private List<UserData> mUserList;

    public UserListAdapter(Context context) {
        this.mContext = context;
    }


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
        Glide.with(mContext).load(mUserList.get(position).getAvatar()).placeholder(R.drawable.load).into(holder.mBinding.ivImage);
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

    public void setUserList(List<UserData> userList) {
        mUserList = userList;
        notifyDataSetChanged();
    }

    public void addAll(List<UserData> jsonList) {
        for (UserData result : jsonList) {
            add(result);
        }
    }

    public void add(UserData r) {
        if (mUserList.size() > 0) {
            mUserList.add(r);
            notifyItemInserted(mUserList.size() - 1);
        }
    }

    public class AdapterViewModel extends RecyclerView.ViewHolder {

        public ItemUserLayoutBinding mBinding;

        public AdapterViewModel(ItemUserLayoutBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }

        public void bind(UserData UserData) {
            if (mBinding.getItemUserModel() == null) {
                mBinding.setItemUserModel(new ItemUserViewModel(itemView.getContext(), UserData));
            } else {
                mBinding.getItemUserModel().setUserData(UserData);
            }
        }
    }
}


