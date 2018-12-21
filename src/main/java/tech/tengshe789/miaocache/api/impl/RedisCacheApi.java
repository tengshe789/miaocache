package tech.tengshe789.miaocache.api.impl;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.tengshe789.miaocache.api.CacheApi;
import tech.tengshe789.miaocache.config.JedisConfig;
import tech.tengshe789.miaocache.domain.CacheBean;
import tech.tengshe789.miaocache.service.RedisService;
import tech.tengshe789.miaocache.utils.SerializationUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: miaocache
 * @description:
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2018-12-21 11:32
 **/
@Service
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
        if (StringUtils.isBlank(key)){
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
    public void set(String key, Object value, int expireMin) {

    }

    /**
     * 保存缓存
     *
     * @param key
     * @param value
     * @param expireMin
     * @param desc
     */
    @Override
    public void set(String key, Object value, int expireMin, String desc) {

    }

    /**
     * 移除单个缓存
     *
     * @param key
     * @return
     */
    @Override
    public Long remove(String key) {
        return null;
    }

    /**
     * 移除多个缓存
     *
     * @param keys
     * @return
     */
    @Override
    public Long remove(String... keys) {
        return null;
    }

    /**
     * 按前缀移除缓存
     *
     * @param pre
     * @return
     */
    @Override
    public Long removeByPre(String pre) {
        return null;
    }

    /**
     * 通过前缀获取缓存信息
     *
     * @param pre
     * @return
     */
    @Override
    public List<CacheBean> getCacheBeanByPre(String pre) {
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
}
