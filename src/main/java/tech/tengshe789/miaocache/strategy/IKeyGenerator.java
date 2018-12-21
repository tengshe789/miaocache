package tech.tengshe789.miaocache.strategy;

import java.lang.reflect.Method;

/**
 * @program: miaocache
 * @description: 生成key
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 **/
public abstract class IKeyGenerator {

    /**
     * 生成key
     * @param target
     * @param method
     * @param params
     * @return
     */
    public abstract String buildKey(Object target, Method method, Object... params);

}
