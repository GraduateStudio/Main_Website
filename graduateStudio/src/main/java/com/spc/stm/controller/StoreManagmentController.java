/**
 * 
 */
package com.spc.stm.controller;

import com.spc.stm.entity.StoreInfo;
import com.spc.stm.service.IStoreManagment;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE}, origins="*")
@RequestMapping(value="/storemanagerment")
public class StoreManagmentController {

	@Autowired
	IStoreManagment storeManagment;

	@ApiOperation(value = "根据ID获取店铺信息")
	@RequestMapping(value = "/store/get", method = RequestMethod.GET)
	public StoreInfo getStoreById(@RequestParam("storeId") String storeId) {
		StoreInfo result = storeManagment.getStoreInfoById(storeId);
		return result;
	}

    @ApiOperation(value = "根据ID列表获取店铺信息")
    @RequestMapping(value = "/store/list", method = RequestMethod.POST)
    public List<StoreInfo> listStoreById(@RequestBody List<String> storeIds) {
        List<StoreInfo> result = storeManagment.listStoreInfoByIds(storeIds);
        return result;
    }
    @ApiOperation(value = "根据ID列表获取全部店铺信息")
    @RequestMapping(value = "/store/listAll", method = RequestMethod.GET)
    public List<StoreInfo> listStoreById() {
        List<StoreInfo> result = storeManagment.listAll();
        return result;
    }

    @ApiOperation(value = "新增店铺信息")
    @RequestMapping(value = "/store/add", method = RequestMethod.POST)
    public StoreInfo saveStoreById(@RequestBody StoreInfo storeInfo) {
        storeManagment.saveStoreInfo(storeInfo);
        return storeInfo;
    }

    @ApiOperation(value = "删除店铺信息")
    @RequestMapping(value = "/store/delete", method = RequestMethod.DELETE)
    public void removeStoreById(@RequestBody StoreInfo storeInfo) {
        storeManagment.removeStoreInfo(storeInfo);
    }

    @ApiOperation(value = "删除店铺信息")
    @RequestMapping(value = "/store/update", method = RequestMethod.PUT)
    public void updateStoreById(@RequestBody StoreInfo storeInfo) {
        storeManagment.updateStoreInfo(storeInfo);
    }


}
