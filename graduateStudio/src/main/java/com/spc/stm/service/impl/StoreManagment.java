package com.spc.stm.service.impl;

import com.spc.stm.dao.StoreManagmentDao;
import com.spc.stm.entity.StoreInfo;
import com.spc.stm.service.IStoreManagment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StoreManagment implements IStoreManagment {
    
    @Autowired
    StoreManagmentDao storeManagmentDao;
    @Override
    public void saveStoreInfo(StoreInfo sto) {
        storeManagmentDao.insert(sto);
    }

    @Override
    public void updateStoreInfo(StoreInfo sto) {
        storeManagmentDao.updateByPrimaryKey(sto);
    }

    @Override
    public void removeStoreInfo(StoreInfo sto) {
        storeManagmentDao.deleteByPrimaryKey(sto.getSid());
    }

    @Override
    public StoreInfo getStoreInfoById(String id) {
        System.out.print("1");

        StoreInfo  result =  storeManagmentDao.selectByPrimaryKey(Long.valueOf(id));
        System.out.print("2");
        return result;
    }

    @Override
    public List<StoreInfo> listStoreInfoByIds(List<String> ids) {

        return null;
    }
}
