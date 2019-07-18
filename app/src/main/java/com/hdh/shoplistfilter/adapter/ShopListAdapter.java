package com.hdh.shoplistfilter.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hdh.shoplistfilter.Constans;
import com.hdh.shoplistfilter.R;
import com.hdh.shoplistfilter.data.model.Shop;
import com.hdh.shoplistfilter.databinding.ItemShopBinding;
import com.hdh.shoplistfilter.ui.web.ShopWebViewActivity;

import java.util.ArrayList;
import java.util.Arrays;


public class ShopListAdapter extends BaseAdapter {


    private ItemShopBinding mBinding;
    private Context mContext;

    private ArrayList<Shop> mShopArrayList;

    public ShopListAdapter(Context mContext, ArrayList<Shop> mMyGroupArrayList) {
        this.mContext = mContext;
        this.mShopArrayList = mMyGroupArrayList;
    }

    @Override
    public int getCount() {
        return mShopArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mShopArrayList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_shop, parent, false);
            v = mBinding.getRoot();
        } else {
            mBinding = (ItemShopBinding) v.getTag();
        }


        mBinding.tvShopRankText.setText(String.valueOf(mShopArrayList.get(position).getShopRank()));
        String shopType = "";
        if (!mShopArrayList.get(position).getShopAge()[0].equals("0")) {
            shopType += "10대";
        }

        if (!mShopArrayList.get(position).getShopAge()[1].equals("0") ||
                !mShopArrayList.get(position).getShopAge()[2].equals("0") ||
                !mShopArrayList.get(position).getShopAge()[3].equals("0") ) {
            if (!shopType.equals("")){
                shopType += " ";
            }
            shopType += "20대";
        }

        if (!mShopArrayList.get(position).getShopAge()[4].equals("0") ||
                !mShopArrayList.get(position).getShopAge()[5].equals("0") ||
                !mShopArrayList.get(position).getShopAge()[6].equals("0") ) {
            if (!shopType.equals("")){
                shopType += " ";
            }
            shopType += "30대";
        }

        if (shopType.equals("10대 20대 30대"))
            shopType = "모두";

        Glide.with(mContext)
                .load(mShopArrayList.get(position).getShopImageURL())
                .apply(new RequestOptions().centerCrop().circleCrop())
                .into(mBinding.ivShopImage);

        mBinding.tvShopRank.setText(String.valueOf(position+1));
        mBinding.tvShopName.setText(mShopArrayList.get(position).getShopName());
        mBinding.tvShopAge.setText(shopType);
        mBinding.tvShopStyle.setText(Arrays.toString(mShopArrayList.get(position).getShopStyle()).replace("[","").replace("]","").replace(",", ""));

        mBinding.clView.setOnClickListener(click->{
            Intent intent = new Intent(mContext, ShopWebViewActivity.class);
            intent.putExtra(Constans.SHOP_URL, mShopArrayList.get(position).getShopAddress());
            intent.putExtra(Constans.SHOP_NAME, mShopArrayList.get(position).getShopName());
            mContext.startActivity(intent);
        });

        v.setTag(mBinding);
        return v;

    }
}
