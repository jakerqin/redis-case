package org.zhss.demo.redis.geo;

import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;

/**
 * 用户与商家的距离计算案例
 */
public class UserShopDistanceDemo {

    private Jedis jedis = new Jedis("127.0.0.1");

    /**
     * 添加一个地理位置
     * @param name
     * @param longitude
     * @param latitude
     */
    public void addLocation(String name, double longitude, double latitude) {
        jedis.geoadd("location_data", longitude, latitude, name);
    }

    /**
     * 获取用户到商家的位置
     * @param user
     * @param shop
     * @return
     */
    public double getDistance(String user, String shop) {
        return jedis.geodist("location_data", user, shop, GeoUnit.KM);
    }

    public static void main(String[] args) {
        UserShopDistanceDemo demo = new UserShopDistanceDemo();
        demo.addLocation("张三", 116.49428833935545, 39.86700462665782);
        demo.addLocation("丫丫小吃店", 116.45961274121092, 39.87517301328063);
        System.out.println("用户到商家的距离为：" + demo.getDistance("张三", "丫丫小吃店"));
    }

}
