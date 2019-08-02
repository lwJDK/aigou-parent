package org.li;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public enum  JedisUtil {
    INSTANCE;
    private static JedisPool pool = null;
    static{
        //创建连接池配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //进行四个配置
        jedisPoolConfig.setMaxTotal(10);//高峰期时，最大连接数
        jedisPoolConfig.setMaxIdle(2);//空闲时，最大连接数
        jedisPoolConfig.setMaxWaitMillis(2000L);//最大等待时间
        jedisPoolConfig.setTestOnBorrow(true);//获取连接时，测试连接是否通畅
        //创建连接池对象
        pool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379, 2000, "root");
    }

    //对外提供一个获取连接的方法
    public Jedis getJedis(){
        return pool.getResource();
    }

    //对外提供一个释放连接的方法
    public void closeJedis(Jedis jedis){
        if(null != jedis){
            jedis.close();
        }
    }
}
