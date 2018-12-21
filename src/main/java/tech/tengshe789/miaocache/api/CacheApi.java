package tech.tengshe789.miaocache.api;

import tech.tengshe789.miaocache.domain.CacheBean;

import java.util.List;

/**
 * @program: miaocache
 * @description: 缓存API
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2018-12-21 11:31
 **/
public interface CacheApi {
    /**
     * 根据key获取对象
     * @param key
     * @return
     */
    public String get(String key);

    /**
     * 保存缓存
     * @param key
     * @param value
     * @param expireMin
     */
    public void set(String key, Object value, int expireMin);

    /**
     * 保存缓存
     * @param key
     * @param value
     * @param expireMin
     * @param desc
     */
    public void set(String key, Object value, int expireMin, String desc);

    /**
     * 移除单个缓存
     * @param key
     * @return
     */
    public Long remove(String key);

    /**
     * 移除多个缓存
     * @param keys
     * @return
     */
    public Long remove(String... keys);

    /**
     * 按前缀移除缓存
     * @param pre
     * @return
     */
    public Long removeByPre(String pre);

    /**
     * 通过前缀获取缓存信息
     * @param pre
     * @return
     */
    public List<CacheBean> getCacheBeanByPre(String pre);

    /**
     * 获取所有缓存对象信息
     * @return
     */
    public List<CacheBean> listAll();
}
