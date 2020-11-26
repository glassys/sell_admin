package com.neusoft.view.impl;

import com.neusoft.dao.BusinessDao;
import com.neusoft.dao.FoodDao;
import com.neusoft.dao.impl.BusinessDaoImpl;
import com.neusoft.dao.impl.FoodDaoImpl;
import com.neusoft.domain.Business;
import com.neusoft.domain.Food;
import com.neusoft.view.FoodView;

import java.util.List;
import java.util.Scanner;

public class FoodViewImpl implements FoodView {

    private Scanner input =  new Scanner(System.in);
    @Override
    public List<Food> showFoodList(Integer businessId) {
        FoodDao dao = new FoodDaoImpl();
        List<Food> list = dao.listFoodByBusinessId(businessId);
        System.out.println("食品编号"+"\t"+"食品名称"+"\t"+"食品详情"+"\t"+"食品价格"+"\t"+"商家编号");
        for (Food b :list){
            System.out.println(b.getFoodId() +"\t"+b.getFoodName()+"\t"+b.getFoodExplain()+"\t"+b.getFoodPrice()+"\t"+b.getBusinessId());
        }
        return list;
    }

    @Override
    public void saveFood(Integer businessId) {

        System.out.println("请输入新食品名称");
        String FoodName = input.next();
        System.out.println("请输入新食品详情");
        String FoodExplain = input.next();
        System.out.println("请输入新食品价格");
        Double FoodPrice = input.nextDouble();
        FoodDao dao = new FoodDaoImpl();
        Food food = new Food();
        food.setFoodName(FoodName);
        food.setFoodExplain(FoodExplain);
        food.setFoodPrice(FoodPrice);
        food.setBusinessId(businessId);
        int FoodId = dao.saveFood(food);
        // 根据id进行查询， 然后进行回显
        if (FoodId >0){
            System.out.println("保存成功");
            food = dao.getFoodById(FoodId);
            System.out.println(food);
        }else {
            System.out.println("新建食品失败");
        }
    }

    @Override
    public void updateFood(Integer businessId) {
        System.out.println("请输入要修改的食品编号");
        int foodId = 0;
        foodId =input.nextInt();
        FoodDao dao = new FoodDaoImpl();
        Food food = dao.getFoodById(foodId);
        System.out.println("请输入食品新名称");
        String FoodName = input.next();
        System.out.println("请输入食品新详情");
        String FoodExplain = input.next();
        System.out.println("请输入食品新价格");
        Double FoodPrice = input.nextDouble();

        food.setFoodName(FoodName);
        food.setFoodExplain(FoodExplain);
        food.setFoodPrice(FoodPrice);
        food.setBusinessId(businessId);

        if (foodId >0){
            System.out.println("保存成功");
            System.out.println(food);
        }else {
            System.out.println("查找失败");
        }
    }

    @Override
    public void removeFood(Integer businessId) {
        System.out.println("请输入要删除的食品id");
        int id = input.nextInt();
        FoodDao dao = new FoodDaoImpl();
        System.out.println("确认要删除吗(y/n)");
        if (input.next().equals("y")){
            int i = dao.removeFood(id);
            if (i == 1){
                System.out.println("删除成功");
            }else{
                System.out.println("删除失败");
            }
        }
    }
}
