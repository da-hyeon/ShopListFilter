package com.hdh.shoplistfilter.ui.search;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hdh.shoplistfilter.adapter.ShopListAdapter;
import com.hdh.shoplistfilter.data.model.Shop;
import com.hdh.shoplistfilter.data.model.ShopList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

public class ShopSearchPresenter implements ShopSearchContract.Presenter {

    private ShopSearchContract.View mView;
    private Context mContext;
    private Activity mActivity;

    private ShopListAdapter mShopListAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private ShopList mShopList, mShopSearchList;

    ShopSearchPresenter(ShopSearchContract.View mView, Context mContext, Activity mActivity) {
        this.mView = mView;
        this.mContext = mContext;
        this.mActivity = mActivity;
        mShopList = new ShopList();
        mShopSearchList = new ShopList();
        mLinearLayoutManager = new LinearLayoutManager(mContext);
    }


    /**
     * Json 가져오기
     */
    @Override
    public void getJson() {
        try {
            mShopList.getShopArrayList().clear();

            JSONObject obj = new JSONObject(loadJSONFromAsset());
            mShopList.setWeek(obj.getString("week"));
            JSONArray m_jArry = obj.getJSONArray("list");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);

                int shopRank = Integer.parseInt(jo_inside.getString("0"));
                String shopName = jo_inside.getString("n");
                String shopAddress = jo_inside.getString("u");
                String[] shopStyle = jo_inside.getString("S").split(",");
                String[] shopAge = shopAgeSetting(jo_inside.getString("A").replace("[", "").replace("]", ""));
                String shopImageURL = imageAddressSetting(jo_inside.getString("u"));

                mShopList.getShopArrayList().add(new Shop(shopRank, shopName, shopAddress, shopStyle, shopAge, shopImageURL));
            }

            //랭킹순으로 정렬
            Collections.sort(mShopList.getShopArrayList());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * ListView Adapter 설정
     */
    @Override
    public void setListView(RecyclerView recyclerView) {
        if (mShopListAdapter == null) {
            mShopListAdapter = new ShopListAdapter(mContext, mShopSearchList.getShopArrayList());
            recyclerView.setLayoutManager(mLinearLayoutManager);
            recyclerView.setAdapter(mShopListAdapter);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext() , mLinearLayoutManager.getOrientation());
            recyclerView.addItemDecoration(dividerItemDecoration);
        } else {
            mShopListAdapter.notifyDataSetChanged();
        }
    }

    /**
     * EditText 의 Text 변경시 호출
     */
    @Override
    public void onTextChanged(String s) {
        mShopSearchList.getShopArrayList().clear();
        if (!s.equals("")) {
            for (int i = 0; i < mShopList.getShopArrayList().size(); i++) {
                if (mShopList.getShopArrayList().get(i).getShopName().contains(s)) {
                    mShopSearchList.getShopArrayList().add(mShopList.getShopArrayList().get(i));
                }
            }
        }
        if (mShopSearchList.getShopArrayList().size() == 0) {
            mView.changeLayout(false);
        } else {
            mView.changeLayout(true);
        }
    }


    /**
     * JsonFile Load
     */
    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = mContext.getAssets().open("shop_list.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                json = new String(buffer, StandardCharsets.UTF_8);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    /**
     * ImageURL 세팅
     */
    private String imageAddressSetting(String url) {
        String[] tempURL = url.split("\\.");
        if (tempURL[0].equals("http://www")) {
            return "https://cf.shop.s.zigzag.kr/images/" + tempURL[1] + ".jpg";
        } else {
            String temp = tempURL[0].replace("http://", "");
            return "https://cf.shop.s.zigzag.kr/images/" + temp + ".jpg";
        }
    }

    /**
     * shopAge 세팅
     */
    private String[] shopAgeSetting(String shopAge) {
        String[] tempShopAge = shopAge.split(",");

        if (tempShopAge.length < 3) {
            return null;
        }

        if (tempShopAge[0].equals("1")) {
            tempShopAge[0] = "10대";
        }
        if (tempShopAge[1].equals("1")) {
            tempShopAge[1] = "20대초반";
        }
        if (tempShopAge[2].equals("1")) {
            tempShopAge[2] = "20대중반";
        }
        if (tempShopAge[3].equals("1")) {
            tempShopAge[3] = "20대후반";
        }
        if (tempShopAge[4].equals("1")) {
            tempShopAge[4] = "30대초반";
        }
        if (tempShopAge[5].equals("1")) {
            tempShopAge[5] = "30대중반";
        }
        if (tempShopAge[6].equals("1")) {
            tempShopAge[6] = "30대후반";
        }

        return tempShopAge;
    }

    /**
     * 닫기 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickDismiss() {
        mView.removeActivity();
    }
}
