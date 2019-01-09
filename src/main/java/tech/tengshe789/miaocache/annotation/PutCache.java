package tech.tengshe789.miaocache.annotation;

import tech.tengshe789.miaocache.constants.CacheType;

/**
 * @program: miaocache
 * @description:
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-01-09 10:31
 **/
public @interface PutCache {
    /**
     *  keyPrefix
     * @return
     */
    public String keyPrefix();

    /**
     * key
     * @return
     */
    public String key();

    /**
     * 缓存类型
     * @return
     */
    public CacheType getCacheType() default CacheType.BOTH;
}
