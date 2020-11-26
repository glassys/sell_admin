package com.neusoft.dao.impl;

import com.neusoft.dao.FoodDao;
import com.neusoft.domain.Business;
import com.neusoft.domain.Food;
import com.neusoft.utils.JDBCUtils;
import jdk.nashorn.internal.scripts.JD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDaoImpl implements FoodDao {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    @Override
    public List<Food> listFoodByBusinessId(Integer businessId) {
        ArrayList<Food> list = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select * from food WHERE 1=1");
        if (businessId !=null && !businessId.equals("")){
            sql.append(" and businessId like '"+businessId+"'");
        }
        try {
            conn = JDBCUtils.getConnection();
            pst = conn.prepareStatement(sql.toString());
            rs = pst.executeQuery();
            while (rs.next()){
                Food food = new Food();
                food.setBusinessId(rs.getInt("businessId"));
                food.setFoodExplain(rs.getString("foodExplain"));
                food.setFoodId(rs.getInt("foodId"));
                food.setFoodPrice(rs.getDouble("foodPrice"));
                food.setFoodName(rs.getString("foodName"));
                list.add(food);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int saveFood(Food food) {
        int foodId = 0;
        String sql = "insert into food(foodId, foodName) values (?, ?)";
        try {
            conn = JDBCUtils.getConnection();
            // 要设置返回自增长的键
            pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setInt(1, foodId);
            pst.executeUpdate();
            // 同时获取自增长的id值  一行一列
            rs = pst.getGeneratedKeys();
            if (rs.next()){
                foodId= rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pst,conn);
        }
        return foodId;
    }

    @Override
    public int updateFood(Food food) {
        int result = 0;
        String sql = "update business set businessName = ?,businessAddress=? , businessExplain =?, starPrice=?, deliveryPrice = ? where businessId = ?";
        try {
            conn =  JDBCUtils.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setInt(1,food.getFoodId());
            pst.setString(2,food.getFoodName());
            pst.setString(3,food.getFoodExplain());
            pst.setDouble(4,food.getFoodPrice());
            pst.setDouble(5,food.getBusinessId());
            result = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int removeFood(Integer foodId) {
        int result = 0;
        String sql = "delete from business where foodId = ?";
        try {
            conn = JDBCUtils.getConnection();
            // 开启事务
            conn.setAutoCommit(false);
            pst = conn.prepareStatement(sql);
            pst.setInt(1, foodId);
            result = pst.executeUpdate();

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pst, conn);
        }

        return result;
    }

    @Override
    public Food getFoodById(Integer foodId) {
        Food food = null;
        String sql = "select * from business where businessId = ?";
        try {
            conn = JDBCUtils.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setInt(1, foodId);
            rs = pst.executeQuery();
            while (rs.next()){
                food = new Food();
                food.setBusinessId(rs.getInt("businessId"));
                food.setFoodExplain(rs.getString("foodExplain"));
                food.setFoodId(rs.getInt("foodId"));
                food.setFoodPrice(rs.getDouble("foodPrice"));
                food.setFoodName(rs.getString("foodName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs, pst, conn);
        }
        return food;
    }
}
