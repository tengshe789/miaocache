package tech.tengshe789.miaocache.api.impl;


import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.tengshe789.miaocache.api.CacheApi;
import tech.tengshe789.miaocache.config.JedisConfig;
import tech.tengshe789.miaocache.domain.CacheBean;
import tech.tengshe789.miaocache.service.RedisService;
import tech.tengshe789.miaocache.strategy.KeyParser;
import tech.tengshe789.miaocache.utils.SerializationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @program: miaocache
 * @description: Redis 接口
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2018-12-21 11:32
 **/
@Service("RedisCacheApi")
public class RedisCacheApi implements CacheApi {
    @Autowired
    JedisConfig jedisConfig;

    @Autowired
    RedisService redisService;

    /**
     * 根据key获取对象
     *
     * @param key
     * @return
     */
    @Override
    public String get(String key) {
        if (!isEnabled()){
            return null;
        }
        if (StrUtil.isBlank(key)){
            return null;
        }
        String jsonValue = redisService.get(key);
        return jsonValue;
    }

    /**
     * 保存缓存
     *
     * @param key
     * @param value
     * @param expireMin
     */
    @Override
    public void set(String key, String value, int expireMin) {
        if (!isEnabled()){
            return;
        }
        redisService.set(key,(String) value,expireMin);
    }

    /**
     * 移除单个缓存
     *
     * @param key
     * @return
     */
    @Override
    public Long remove(String key) {
        if (!isEnabled()){
            return 0L;
        }
        return redisService.decr(key);
    }

    /**
     * 移除多个缓存
     *
     * @param keys
     * @return
     */
    @Override
    public Long remove(String... keys) {
        if (!isEnabled()){
            return 0L;
        }
        Long del = redisService.del(keys);
        return del;
    }

    /**
     * 按前缀移除缓存
     *
     * @param pre
     * @return
     */
    @Override
    public Long removeByPre(String pre) {
        if (!isEnabled()){
            return 0L;
        }
        Long delPre = redisService.delPre(pre);
        return delPre;
    }

    /**
     * 通过前缀获取缓存信息
     *
     * @param pre
     * @return
     */
    @Override
    public List<CacheBean> getCacheBeanByPre(String pre) {
        Set<String> preSet = redisService.getByPre(pre);
        preSet.forEach(s -> {

        });
        return null;
    }

    /**
     * 获取所有缓存对象信息
     *
     * @return
     */
    @Override
    public List<CacheBean> listAll() {
        return null;
    }

    public boolean isEnabled() {
        return Boolean.parseBoolean(jedisConfig.getEnable());
    }
}
