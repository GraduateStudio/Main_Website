package com.spc.stm.service.impl;

import com.spc.stm.dao.StoreManagmentDao;
import com.spc.stm.entity.StoreInfo;
import com.spc.stm.service.IStoreManagment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        StoreInfo  result =  storeManagmentDao.selectByPrimaryKey(Long.valueOf(id));
        return result;
    }

    @Override
    public List<StoreInfo> listStoreInfoByIds(List<String> ids) {
        List<StoreInfo> result = new ArrayList<>();
        for(String id : ids){
            StoreInfo  info =  storeManagmentDao.selectByPrimaryKey(Long.valueOf(id));
            if(null!=info){
                result.add(info);
            }
        }
        return result;
    }

    @Override
    public List<StoreInfo> listAll() {
        List<StoreInfo> result =  storeManagmentDao.listStoreAll();
        return result;
    }
}
