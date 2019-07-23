package com.hdh.shoplistfilter.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hdh.shoplistfilter.Constans;
import com.hdh.shoplistfilter.data.model.Shop;
import com.hdh.shoplistfilter.databinding.ItemShopBinding;
import com.hdh.shoplistfilter.ui.web.ShopWebViewActivity;

import java.util.ArrayList;
import java.util.Arrays;


public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ShopListViewHolder> {

    private Context mContext;
    private ArrayList<Shop> mShopArrayList;

    class ShopListViewHolder extends RecyclerView.ViewHolder {

        ItemShopBinding binding;

        ShopListViewHolder(ItemShopBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public ShopListAdapter(Context mContext, ArrayList<Shop> mShopArrayList) {
        this.mContext = mContext;
        this.mShopArrayList = mShopArrayList;
    }


    @NonNull
    @Override
    public ShopListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ShopListViewHolder(ItemShopBinding.inflate(LayoutInflater.from(parent.getContext()) , parent , false));
    }


    @Override
    public void onBindViewHolder(@NonNull ShopListViewHolder viewHolder, int position) {

        viewHolder.binding.tvShopRankText.setText(String.valueOf(mShopArrayList.get(position).getShopRank()));
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
                .into(viewHolder.binding.ivShopImage);

        viewHolder.binding.tvShopRank.setText(String.valueOf(position+1));
        viewHolder.binding.tvShopName.setText(mShopArrayList.get(position).getShopName());
        viewHolder.binding.tvShopAge.setText(shopType);
        viewHolder.binding.tvShopStyle.setText(Arrays.toString(mShopArrayList.get(position).getShopStyle()).replace("[","").replace("]","").replace(",", ""));

        viewHolder.binding.clView.setOnClickListener(click->{
            Intent intent = new Intent(mContext, ShopWebViewActivity.class);
            intent.putExtra(Constans.SHOP_URL, mShopArrayList.get(position).getShopAddress());
            intent.putExtra(Constans.SHOP_NAME, mShopArrayList.get(position).getShopName());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return (mShopArrayList != null ? mShopArrayList.size() : 0);
    }
}
