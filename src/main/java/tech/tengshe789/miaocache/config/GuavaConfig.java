package tech.tengshe789.miaocache.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: miaocache
 * @description: Guava 配置类
 * @url: https://github.com/xujijun/MyJavaStudio/blob/master/src/main/java/com/xjj/cache/guava/GuavaCacheManager.java
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-01-09 14:09
 **/
@Configuration
public class GuavaConfig {

    @Bean("GuavaBuilder")
    public CacheBuilder<Object, Object> initGuava() {
         CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder()
                .maximumSize(100)
                .concurrencyLevel(4);
         return builder;
    }
}
