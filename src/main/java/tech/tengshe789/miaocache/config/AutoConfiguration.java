package tech.tengshe789.miaocache.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @program: miaocache
 * @description: 开启AOP拦截
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2018-12-20 21:10
 **/
@ComponentScan({"tech.tengshe789.miaocache"})
@EnableAspectJAutoProxy
public class AutoConfiguration {
}
