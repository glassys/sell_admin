package com.neusoft.dao;

import com.neusoft.domain.Business;

import java.util.List;

public interface BusinessDao {
    //所有商家名单
    public List<Business> listBusiness();

    int saveBusiness(String businessName);
}
