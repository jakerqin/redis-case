package org.zhss.demo.redis.hyperloglog;

import redis.clients.jedis.Jedis;

/**
 * 垃圾内容过滤案例
 */
public class GarbageContentFilterDemo {

    private Jedis jedis = new Jedis("127.0.0.1");

    /**
     * 判断当前内容是否是垃圾内容
     * @param content
     * @return
     */
    public Boolean isGarbageContent(String content) {
        return jedis.pfadd("hyperloglog_contennt", content) == 0;
    }

    public static void main(String[] args) {
        GarbageContentFilterDemo demo = new GarbageContentFilterDemo();

        String content = "正常的内容";
        System.out.println("是否为垃圾内容：" + (demo.isGarbageContent(content) ? "是" : "否"));

        content = "垃圾内容";
        System.out.println("是否为垃圾内容：" + (demo.isGarbageContent(content) ? "是" : "否"));
        content = "垃圾内容";
        System.out.println("是否为垃圾内容：" + (demo.isGarbageContent(content) ? "是" : "否"));
    }

}
