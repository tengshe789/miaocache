package tech.tengshe789.miaocache.annotation;

import tech.tengshe789.miaocache.constants.CacheConstants;
import tech.tengshe789.miaocache.strategy.KeyParser;
import tech.tengshe789.miaocache.strategy.impl.DefaultKeyParser;

/**
 * @program: miaocache
 * @description: 拿到缓存数据
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-01-08 18:11
 **/
public @interface GetCache {
    /**
     * key
     * @return
     */
    public String keyPrefix();

    /**
     * key的描述
     * @return
     */
    public String desc() default "";

    /**
     * 默认key的解析器
     * @return
     */
    public Class<? extends KeyParser> parser() default DefaultKeyParser .class;
}
