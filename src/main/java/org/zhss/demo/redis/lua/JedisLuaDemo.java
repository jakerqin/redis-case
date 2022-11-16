package org.zhss.demo.redis.lua;

import redis.clients.jedis.Jedis;

public class JedisLuaDemo {

    public static void main(String[] args) throws Exception {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        Object result = jedis.eval(
                "redis.call('set','test_key','lua_test_value');" +
                        "return 'success';");
        System.out.println(result);
    }

}
