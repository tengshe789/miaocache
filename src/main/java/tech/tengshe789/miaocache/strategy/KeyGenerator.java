package tech.tengshe789.miaocache.strategy;

import java.lang.reflect.Method;

/**
 * @program: miaocache
 * @description: 生成key
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 **/
public interface KeyGenerator {

    /**
     * 生成key
     * @param target
     * @param method
     * @param params
     * @return
     */
    public String generateKey(Object target, Method method, Object... params);

}
