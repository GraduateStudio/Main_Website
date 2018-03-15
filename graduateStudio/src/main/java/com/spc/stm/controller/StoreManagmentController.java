/**
 * 
 */
package com.spc.stm.controller;

import com.spc.stm.entity.StoreInfo;
import com.spc.stm.service.IStoreManagment;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/storemanagerment")
public class StoreManagmentController {

	@Autowired
	IStoreManagment storeManagment;

	@ApiOperation(value = "根据ID获取店铺名称")
	@RequestMapping(value = "/store/get", method = RequestMethod.GET)
	public StoreInfo getStoreById(@RequestParam("storeId") String storeId) {
		StoreInfo result = storeManagment.getStoreInfoById(storeId);
		return result;
	}

}
