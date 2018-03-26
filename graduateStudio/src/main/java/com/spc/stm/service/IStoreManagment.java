package com.spc.stm.service;

import com.spc.stm.entity.StoreInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IStoreManagment {
    void saveStoreInfo(StoreInfo sto);
    void updateStoreInfo(StoreInfo sto);
    void removeStoreInfo(StoreInfo sto);
    StoreInfo getStoreInfoById(String id);
    List<StoreInfo> listStoreInfoByIds(List<String> ids);
    List<StoreInfo> listAll();
}
