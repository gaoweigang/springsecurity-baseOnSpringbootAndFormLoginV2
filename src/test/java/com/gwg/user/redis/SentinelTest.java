package com.gwg.user.redis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

public class SentinelTest {

    /**
     * 测试master节点是否可以连上
     */
    @Test
    public void testSingleNode() {

        Jedis jedis = new Jedis("192.168.1.107", 6379);
        System.out.println(jedis.set("name", "gaoweo"));
        System.out.println(jedis.get("name"));



    }

    /**
     * 测试sentinel
     */
    @Test
    public void testSentinel(){
        Set<String> sentinels = new HashSet<String>();
        sentinels.add(new HostAndPort("192.168.1.107", 26379).toString());
        JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster", sentinels);
        System.out.println("Current master: " + sentinelPool.getCurrentHostMaster().toString());
        Jedis master = sentinelPool.getResource();
    }
}

