package org.zhss.demo.redis.hyperloglog;

import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 基于HyperLogLog统计UV的案例
 */
public class HyperLogLogUVDemo {

    private Jedis jedis = new Jedis("127.0.0.1");

    /**
     * 初始化uv数据
     */
    public void initUVData() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = dateFormat.format(new Date());

        for(int i = 0; i < 1358; i++) {
            for(int j = 0; j < 10; j++) {
                jedis.pfadd("hyperloglog_uv_" + today, String.valueOf((i + 1)));
            }
        }
    }

    /**
     * 获取uv值
     * @return
     */
    public long getUV() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = dateFormat.format(new Date());

        return jedis.pfcount("hyperloglog_uv_" + today);
    }

    public static void main(String[] args) throws Exception {
        HyperLogLogUVDemo demo = new HyperLogLogUVDemo();
        demo.initUVData();
        long uv = demo.getUV();
        System.out.println("今天uv的值是：" + uv);
    }

}
