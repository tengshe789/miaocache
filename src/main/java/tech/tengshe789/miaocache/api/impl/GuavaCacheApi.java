package tech.tengshe789.miaocache.api.impl;

import cn.hutool.core.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.tengshe789.miaocache.api.CacheApi;
import tech.tengshe789.miaocache.domain.CacheBean;
import tech.tengshe789.miaocache.service.GuavaService;

import java.util.List;

/**
 * @program: miaocache
 * @description: Guava 接口
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-01-09 18:39
 **/
@Service("GuavaCacheApi")
public class GuavaCacheApi implements CacheApi {
    @Autowired
    GuavaService guavaService;
    /**
     * 根据key获取对象
     *
     * @param key
     * @return
     */
    @Override
    public String get(String key) {
        Assert.notBlank(key);
        guavaService.get(key);
        return null;
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
        Assert.notBlank(key);
        Assert.notBlank(value);
        if (expireMin < 0) {
            expireMin = 0;
        }
        guavaService.set(key,value,expireMin);
    }

    /**
     * 移除单个缓存
     *
     * @param key
     * @return
     */
    @Override
    public Long remove(String key) {
        return guavaService.delete(key);
    }

    /**
     * 移除多个缓存
     *
     * @param keys
     * @return
     */
    @Override
    @Deprecated
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
    @Deprecated
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
    @Deprecated
    public List<CacheBean> getCacheBeanByPre(String pre) {
        return null;
    }

    /**
     * 获取所有缓存对象信息
     *
     * @return
     */
    @Override
    @Deprecated
    public List<CacheBean> listAll() {
        return null;
    }
}
