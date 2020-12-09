package com.zss.mall.fragment.goods;

import android.util.Log;

import com.zss.kit.FastjsonUtils;
import com.zss.mall.bean.GoodsCategory;
import com.zss.mall.bean.ResultBean;
import com.zss.mall.network.BaseResponse;
import com.zss.ui.mvp.base.BasePresenter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GoodsPresenter extends BasePresenter<GoodsModel, GoodsView> {

    GoodsPresenter(GoodsModel mModel, GoodsView mView) {
        super(mModel, mView);
    }

    void queryTreeCategory() {
//        List<GoodsCategory> categoryList = new ArrayList<>();
//        initCategory(categoryList);
//
//        getView().setCategoryTitle(categoryList);
        Map<String, Object> params = new HashMap<>();
        params.put("parentId", "1");
        getModel().getTreeCategory(params, new BaseResponse() {
            @Override
            public void onSuccess(ResultBean bean) {
                JSONObject data = bean.getJSONObject();
                String jsonList = data.optString("list");
                List<GoodsCategory> categoryList = FastjsonUtils.toList(jsonList, GoodsCategory.class);
                getView().setCategoryTitle(categoryList);
            }
            @Override
            public void onError(String errMsg) {

            }
        });
    }

    private void initCategory(List<GoodsCategory> categoryList){
        GoodsCategory category1 = new GoodsCategory();
        category1.id = (long)1;
        category1.name = "牙膏";
        categoryList.add(category1);
        GoodsCategory category2 = new GoodsCategory();
        category2.id = (long)2;
        category2.name = "洗发液";
        categoryList.add(category1);
        GoodsCategory category3 = new GoodsCategory();
        category3.id = (long)3;
        category3.name = "冰箱";
        categoryList.add(category3);
    }
}