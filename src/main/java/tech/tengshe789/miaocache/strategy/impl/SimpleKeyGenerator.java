package tech.tengshe789.miaocache.strategy.impl;

import tech.tengshe789.miaocache.domain.SimpleKey;
import tech.tengshe789.miaocache.strategy.KeyGenerator;
import tech.tengshe789.miaocache.utils.SerializationUtil;

import java.lang.reflect.Method;

/**
 * @program: miaocache
 * @description: 参考spring cache 写的 简单的key生成器
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2018-12-21 16:43
 **/
public class SimpleKeyGenerator implements KeyGenerator {
    private SimpleKeyGenerator() {
    }

    public static Object generateKey(Object... params) {
        if (params.length == 0) {
                return SimpleKey.INSTANCE;
        } else {
            if (params.length == 1) {
                Object param = params[0];
                if (param != null && !param.getClass().isArray()) {
                    return param;
                }
            }
            return new SimpleKey(params);
        }
    }

    @Override
    public String generateKey(Object o, Method method, Object... params) {
        Object keyObj = generateKey(params);
        return SerializationUtil.beanToJson(keyObj);
    }
}


