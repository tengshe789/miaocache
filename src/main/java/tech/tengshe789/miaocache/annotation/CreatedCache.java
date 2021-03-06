package tech.tengshe789.miaocache.annotation;

import org.springframework.lang.Nullable;
import tech.tengshe789.miaocache.constants.CacheType;
import tech.tengshe789.miaocache.constants.KeyPrefixConstants;
import tech.tengshe789.miaocache.strategy.KeyGenerator;
import tech.tengshe789.miaocache.strategy.impl.DefaultKeyGenerator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: miaocache
 * @description: 创建缓存
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2018-12-20 21:21
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE,ElementType.METHOD})
public @interface CreatedCache {
    /**
     * key
     * @return
     */
    public String key() default "";

    @Nullable
    public String keyPrefix() default KeyPrefixConstants.DEFAULT_PREFIX;

    /**
     * key的value
     * @return
     */
    public String value() default "";

    /**
     * key的过期时间
     * 单位： s
     * @return
     */
    public int expire() default 720;

    /**
     * 缓存类型
     * @return
     */
    public CacheType getCacheType() default CacheType.BOTH;
}
