package tech.tengshe789.miaocache.annotation;

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
}
