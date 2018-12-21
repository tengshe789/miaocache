package tech.tengshe789.miaocache.strategy.impl;

import org.springframework.stereotype.Service;
import tech.tengshe789.miaocache.domain.DefaultKey;
import tech.tengshe789.miaocache.strategy.KeyGenerator;
import tech.tengshe789.miaocache.utils.SerializationUtil;

import java.lang.reflect.Method;


/**
 * @program: miaocache
 * @description: 默认key的生成策略
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2018-12-21 10:37
 **/
@Service
public class DefaultKeyGenerator implements KeyGenerator {

    /**
     * 生成key
     *
     * @param target
     * @param method
     * @param params
     * @return
     */
    @Override
    public String generateKey(Object target, Method method, Object... params) {
        DefaultKey defaultKey = new DefaultKey(target, method, params);
        String json = SerializationUtil.beanToJson(defaultKey);
        return json;
    }
}
