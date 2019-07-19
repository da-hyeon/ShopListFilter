package com.hdh.shoplistfilter.ui.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.widget.ListView;

import com.hdh.shoplistfilter.Constans;
import com.hdh.shoplistfilter.MyApplication;
import com.hdh.shoplistfilter.adapter.ShopListAdapter;
import com.hdh.shoplistfilter.data.model.Shop;
import com.hdh.shoplistfilter.data.model.ShopList;
import com.hdh.shoplistfilter.ui.filter.FilterActivity;
import com.hdh.shoplistfilter.ui.search.ShopSearchActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;
    private Context mContext;
    private Activity mActivity;
    private ShopList mShopList;

    private String[] ageStatus;
    private String[] styleStatus;

    private long mLastTime;

    MainPresenter(MainContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        mShopList = MyApplication.getShopListInstance();
    }

    /**
     * 저장되어있는 필터 정보를 가져옴.
     */
    @Override
    public void loadFilterData() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(Constans.FILTER_SAVE, MODE_PRIVATE);
        ageStatus = shopAgeSetting(Objects.requireNonNull(sharedPreferences.getString(Constans.FILTER_AGE_SAVE, "0"))
                .replace(" ", "")
                .replace("[", "")
                .replace("]", ""));


        styleStatus = shopStyleSetting(Objects.requireNonNull(sharedPreferences.getString(Constans.FILTER_STYLE_SAVE, "0"))
                .replace(" ", "")
                .replace("[", "")
                .replace("]", ""));
    }

    /**
     * 뒤로가기 클릭 이벤트 처리
     */
    @Override
    public void onBackPressed() {
        if (mLastTime + 2000 < System.currentTimeMillis() || mLastTime == 0) {
            mView.showToast("한 번 더 누르면 종료됩니다.");
            mLastTime = System.currentTimeMillis();
        } else {
            mActivity.finish();
        }
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
            boolean filterSwitch = false;
            Label:
            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);

                int shopRank = Integer.parseInt(jo_inside.getString("0"));
                String shopName = jo_inside.getString("n");
                String shopAddress = jo_inside.getString("u");
                String[] shopStyle = jo_inside.getString("S").split(",");
                String[] shopAge = shopAgeSetting(jo_inside.getString("A").replace("[", "").replace("]", ""));
                String shopImageURL = imageAddressSetting(jo_inside.getString("u"));

                //저장된 필터 데이터가 없거나
                //아무것도 선택하지 않았을 경우
                if (ageStatus == null || styleStatus == null ||
                        (filterZeroCheck(ageStatus) && filterZeroCheck(styleStatus))) {
                    mShopList.getShopArrayList().add(new Shop(shopRank, shopName, shopAddress, shopStyle, shopAge, shopImageURL));
                    if(!filterSwitch) {
                        //필터 기본상태로 바꾸기
                        mView.changeFilterButtonStatus(false);
                        filterSwitch = true;
                    }
                } else {
                    if(!filterSwitch) {
                        //필터 선택상태로 바꾸기
                        mView.changeFilterButtonStatus(true);
                        filterSwitch = true;
                    }
                    //연령대만 선택
                    if (!filterZeroCheck(ageStatus) && filterZeroCheck(styleStatus)) {
                        for (String ageSelected : ageStatus) {
                            if (ageSelected.equals("0"))
                                continue;

                            for (int ageIndex = 0; ageIndex < ageStatus.length; ageIndex++) {
                                if (shopAge != null && ageSelected.equals(shopAge[ageIndex])) {
                                    mShopList.getShopArrayList().add(new Shop(shopRank, shopName, shopAddress, shopStyle, shopAge, shopImageURL));
                                    continue Label;
                                }
                            }
                        }
                    }
                    //스타일만 선택한 경우
                    else if (filterZeroCheck(ageStatus) && !filterZeroCheck(styleStatus)) {
                        boolean inputCheck = false;

                        for (String styleSelected : styleStatus) {
                            if (styleSelected.equals("0"))
                                continue;

                            for (String s : shopStyle) {
                                if (styleSelected.equals(s)) {

                                    if (!inputCheck) {
                                        mShopList.getShopArrayList().add(new Shop(shopRank, shopName, shopAddress, shopStyle, shopAge, shopImageURL));
                                        int currentIndex = mShopList.getShopArrayList().size() - 1;
                                        mShopList.getShopArrayList().get(currentIndex).setFilterStyleCount(mShopList.getShopArrayList().get(currentIndex).getFilterStyleCount() + 1);
                                        inputCheck = true;
                                    } else {
                                        int currentIndex = mShopList.getShopArrayList().size() - 1;
                                        mShopList.getShopArrayList().get(currentIndex).setFilterStyleCount(mShopList.getShopArrayList().get(currentIndex).getFilterStyleCount() + 1);
                                    }
                                }
                            }
                        }
                    }
                    //연령대 스타일 모두 선택한 경우
                    else {
                        boolean inputCheck = false;

                        for (String ageSelected : ageStatus) {
                            if (ageSelected.equals("0"))
                                continue;

                            for (int ageIndex = 0; ageIndex < ageStatus.length; ageIndex++) {
                                if (shopAge != null && ageSelected.equals(shopAge[ageIndex])) {

                                    for (String styleSelected : styleStatus) {
                                        if (styleSelected.equals("0"))
                                            continue;

                                        for (String s : shopStyle) {
                                            if (styleSelected.equals(s)) {

                                                if (!inputCheck) {
                                                    mShopList.getShopArrayList().add(new Shop(shopRank, shopName, shopAddress, shopStyle, shopAge, shopImageURL));
                                                    int currentIndex = mShopList.getShopArrayList().size() - 1;
                                                    mShopList.getShopArrayList().get(currentIndex).setFilterStyleCount(mShopList.getShopArrayList().get(currentIndex).getFilterStyleCount() + 1);
                                                    inputCheck = true;
                                                } else {
                                                    int currentIndex = mShopList.getShopArrayList().size() - 1;
                                                    mShopList.getShopArrayList().get(currentIndex).setFilterStyleCount(mShopList.getShopArrayList().get(currentIndex).getFilterStyleCount() + 1);
                                                }
                                                //mShopList.getShopArrayList().add(new Shop(shopRank, shopName, shopAddress, shopStyle, shopAge, shopImageURL));
                                                //continue Label;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            //Log.d("Count", mShopList.getShopArrayList().get(0).getShopName());
            //Log.d("ageCount", mShopList.getShopArrayList().get(0).getFilterAgeCount() + "");
            //Log.d("styleCount", mShopList.getShopArrayList().get(0).getFilterStyleCount() + "");
            mView.changeWeekText(mShopList.getWeek());

            //랭킹순으로 정렬
            Collections.sort(mShopList.getShopArrayList());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setListView(ListView listView) {
        ShopListAdapter mShopListAdapter = new ShopListAdapter(mContext, mShopList.getShopArrayList());
        listView.setAdapter(mShopListAdapter);
    }

    /**
     * 필터 클릭 이벤트 처리
     */
    @Override
    public void clickFilter() {
        mView.moveActivity(new Intent(mContext, FilterActivity.class));
    }

    /**
     * 검색 클릭 이벤트 처리
     */
    @Override
    public void clickSearch() {
        mView.moveActivity(new Intent(mContext, ShopSearchActivity.class));
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
     * shopStyle 세팅
     */
    private String[] shopStyleSetting(String shopStyle) {
        String[] tempShopStyle = shopStyle.split(",");

        if (tempShopStyle.length < 3) {
            return null;
        }

        if (tempShopStyle[0].equals("1")) {
            tempShopStyle[0] = "페미닌";
        }
        if (tempShopStyle[1].equals("1")) {
            tempShopStyle[1] = "모던시크";
        }
        if (tempShopStyle[2].equals("1")) {
            tempShopStyle[2] = "심플베이직";
        }
        if (tempShopStyle[3].equals("1")) {
            tempShopStyle[3] = "러블리";
        }
        if (tempShopStyle[4].equals("1")) {
            tempShopStyle[4] = "유니크";
        }
        if (tempShopStyle[5].equals("1")) {
            tempShopStyle[5] = "미시스타일";
        }
        if (tempShopStyle[6].equals("1")) {
            tempShopStyle[6] = "캠퍼스룩";
        }
        if (tempShopStyle[7].equals("1")) {
            tempShopStyle[7] = "빈티지";
        }
        if (tempShopStyle[8].equals("1")) {
            tempShopStyle[8] = "섹시글램";
        }
        if (tempShopStyle[9].equals("1")) {
            tempShopStyle[9] = "스쿨룩";
        }
        if (tempShopStyle[10].equals("1")) {
            tempShopStyle[10] = "로맨틱";
        }
        if (tempShopStyle[11].equals("1")) {
            tempShopStyle[11] = "오피스룩";
        }
        if (tempShopStyle[12].equals("1")) {
            tempShopStyle[12] = "럭셔리";
        }
        if (tempShopStyle[13].equals("1")) {
            tempShopStyle[13] = "헐리웃스타일";
        }

        return tempShopStyle;
    }

    /**
     * 필터에 선택된 수가 0인지 체크
     */
    private boolean filterZeroCheck(String[] arr) {
        for (String s : arr) {
            if (!s.equals("0"))
                return false;
        }
        return true;
    }
}
