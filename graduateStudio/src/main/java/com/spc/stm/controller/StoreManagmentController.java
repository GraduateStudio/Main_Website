/**
 *
 */
package com.spc.stm.controller;

import com.spc.config.Props;
import com.spc.stm.entity.StoreInfo;
import com.spc.stm.service.IStoreManagment;
import com.spc.utils.SshUtil;
import com.spc.websocket.WebSocketHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.List;


@RestController
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, origins = "*")
@RequestMapping(value = "/storemanagerment")
public class StoreManagmentController {
    static int count = 0;
    @Autowired
    Props props;
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

    @ApiOperation(value = "redis")
    @RequestMapping(value = "/redis/save", method = RequestMethod.POST)
    String saveRedis(@RequestParam("id") String id) {
        Jedis jedis = new Jedis("47.106.107.117", 6379);
        jedis.auth("Admin@1234");
        jedis.set(id, id);
        System.out.println(jedis.get(id));
        jedis.disconnect();
        return id;
    }

    @ApiOperation(value = "获取树莓派温度传感器")
    @GetMapping(value = "/getTemp")
    List<String> getTemp() {
        String hostname = "192.168.0.110";
        String username = props.getSshmap().get(hostname).split(",")[0];
        String password = props.getSshmap().get(hostname).split(",")[1];
        String command = "cat /usr1/wjj/wjj.txt";
        List<String> result = SshUtil.doSshCommand(hostname, username, password, command);
        return result;
    }

    @ApiOperation(value = "获取树莓派光电计数")
    @GetMapping(value = "/doCount")
    Integer getCount(@RequestParam("string")String s) {
        System.out.println(s);
        System.out.print(++count);
        WebSocketHandler.channelGroup.writeAndFlush(new TextWebSocketFrame("!!A!A!A!!nowgdConnectCount:"+count));
        return count;
    }

}
