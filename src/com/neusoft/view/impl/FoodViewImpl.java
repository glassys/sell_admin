package com.neusoft.view.impl;

import com.neusoft.dao.FoodDao;
import com.neusoft.dao.impl.FoodDaoImpl;
import com.neusoft.domain.Business;
import com.neusoft.domain.Food;
import com.neusoft.view.FoodView;

import java.util.List;

public class FoodViewImpl implements FoodView {

    @Override
    public List<Food> showFoodList(Integer businessId) {
        FoodDao dao = new FoodDaoImpl();
        List<Food> list = dao.listFoodByBusinessId(businessId);
        System.out.println("食品编号"+"\t"+"食品名称"+"\t"+"食品详情"+"\t"+"食品价格"+"\t"+"商家编号");
        for (Food b :list)

    }

    @Override
    public void saveFood(Integer businessId) {

    }

    @Override
    public void updateFood(Integer businessId) {

    }

    @Override
    public void removeFood(Integer businessId) {

    }
}
