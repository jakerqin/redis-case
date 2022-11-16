package org.zhss.demo.redis.geo;

import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * 查找附近的人案例
 */
public class NearbyShopsDemo {

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
     * 查找附近5公里内的店铺
     * @return
     */
    public List<GeoRadiusResponse> getNearbyShops() {
        return jedis.georadiusByMember("location_data", "张三", 5.0, GeoUnit.KM);
    }

    public static void main(String[] args) {
        NearbyShopsDemo demo = new NearbyShopsDemo();

        List<String> nearbyShops = new ArrayList<String>();

        List<GeoRadiusResponse> results = demo.getNearbyShops();
        for(GeoRadiusResponse result : results) {
            String name = result.getMemberByString();
            if(!name.equals("张三")) {
                nearbyShops.add(name);
            }
        }

        System.out.println("附近5公里内的商家：" + nearbyShops);
    }

}
