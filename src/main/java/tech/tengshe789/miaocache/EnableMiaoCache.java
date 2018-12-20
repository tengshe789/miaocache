package tech.tengshe789.miaocache;

import org.springframework.context.annotation.Import;
import tech.tengshe789.miaocache.config.AutoConfiguration;

import java.lang.annotation.*;

/**
 * @program: miaocache
 * @description: 入口
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AutoConfiguration.class)
@Documented
@Inherited
public @interface EnableMiaoCache {
}
