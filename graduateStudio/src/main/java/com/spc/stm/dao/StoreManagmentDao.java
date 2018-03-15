package com.spc.stm.dao;

import com.spc.stm.entity.StoreInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreManagmentDao {

    int deleteByPrimaryKey(Long sid);

    int insert(StoreInfo record);

    int insertSelective(StoreInfo record);

    StoreInfo selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(StoreInfo record);

    int updateByPrimaryKey(StoreInfo record);
}