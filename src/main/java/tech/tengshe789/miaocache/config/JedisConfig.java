package tech.tengshe789.miaocache.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import tech.tengshe789.miaocache.utils.PropertiesUtil;

import javax.annotation.PostConstruct;

/**
 * @program: miaocache
 * @description: Jedis配置类
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2018-12-20 22:20
 **/
@Configuration
public class JedisConfig {
    @Autowired
    Environment environment;

    private String maxActive;
    private String maxIdle;
    private String maxWait;
    private String host;
    private String password;
    private String timeout;
    private String database;
    private String port;
    private String enable;
    private String sysName;
    //TODO 可以继续定义数据

    /**
     * 使用PropertiesUtil或者environment读取配置
     */
    @PostConstruct
    public void loadRedisConfiguration(){
        PropertiesUtil properties = new PropertiesUtil("application.properties");
        host = properties.getProperty("redis.host");
        if(host.isEmpty()){
            host = environment.getProperty("redis.host");
            maxActive = environment.getProperty("redis.pool.maxActive");
            maxIdle  = environment.getProperty("redis.pool.maxIdle");
            maxWait = environment.getProperty("redis.pool.maxWait");
            password = environment.getProperty("redis.password");
            timeout = environment.getProperty("redis.timeout");
            database = environment.getProperty("redis.database");
            port = environment.getProperty("redis.port");
            sysName = environment.getProperty("redis.sysName");
            enable = environment.getProperty("redis.enable");
        } else{
            maxActive = properties.getProperty("redis.pool.maxActive");
            maxIdle  = properties.getProperty("redis.pool.maxIdle");
            maxWait = properties.getProperty("redis.pool.maxWait");
            password = properties.getProperty("redis.password");
            timeout = properties.getProperty("redis.timeout");
            database = properties.getProperty("redis.database");
            port = properties.getProperty("redis.port");
            sysName = properties.getProperty("redis.sysName");
            enable = properties.getProperty("redis.enable");
        }
    }

    /**
     * jedis连接池工厂
     * @return
     */
    @Bean("JedisPool")
    public JedisPool jedisPoolFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
        poolConfig.setMaxIdle(Integer.parseInt(maxIdle));
        // 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
        // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
        poolConfig.setMaxTotal(Integer.parseInt(maxActive));
        // 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
        poolConfig.setMaxWaitMillis(Integer.parseInt(maxWait));
        poolConfig.setTestOnBorrow(true);

        JedisPool jp = null;
        if (StringUtils.isBlank(this.password)){
            jp = new JedisPool(poolConfig,
                    this.host,
                    Integer.parseInt(this.port),
                    Integer.parseInt(this.timeout));
        }else {
            jp = new JedisPool(poolConfig,
                    this.host,
                    Integer.parseInt(this.port),
                    Integer.parseInt(this.timeout),
                    this.password,
                    Integer.parseInt(this.database));
        }
        return jp;
    }

}
