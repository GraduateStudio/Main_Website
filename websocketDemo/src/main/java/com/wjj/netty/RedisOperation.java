package com.wjj.netty;

import redis.clients.jedis.Jedis;

import java.util.List;

public class RedisOperation {

    public static String getAllMessage(Jedis jedis) {
        long len = jedis.llen("historyy");
        List<String> allkey = jedis.lrange("historyy",0,len);
        StringBuffer messages = new StringBuffer();
        for (String message : allkey) {
            messages.append(message);
        }
        return messages.toString();
    }

    public static String saveMessage(Jedis jedis, String key, String msg) {
        try {
            jedis.set(key, msg);
            jedis.lpush("historyy",key + "!!A!A!A!!sOmeBodySay:" + jedis.get(key)+"!!A!A!A!!sOmeBodySay:");
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }
}



