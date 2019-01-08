package tech.tengshe789.miaocache.annotation;

import tech.tengshe789.miaocache.domain.CacheBean;
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
public @interface CacheCreated {

    /**
     *  缓存key
     * @return
     */
    public CacheBean key() default ;

    /**
     * 默认使用redis作为缓存服务器，可以使用二级缓存，即CacheType.BOTH
     * @return
     *          CacheType 缓存类型
     */
    public CacheType type() default CacheType.REDIS;

    /**
     * 缓存描述
     * @return String
     *              32
     */
    public String desc() default "";

    /**
     * 过期时间 默认720s
     * @return
     */
    public int expire() default 720;
}
