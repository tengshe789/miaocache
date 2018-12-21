package tech.tengshe789.miaocache.strategy.impl;

import tech.tengshe789.miaocache.strategy.IKeyGenerator;

import java.lang.reflect.Method;

/**
 * @program: miaocache
 * @description:
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2018-12-21 10:37
 **/
public class DefaultKeyGenerator extends IKeyGenerator {

    /**
     * 数据源ID
     */
    private String dataSourceId;

    /**
     * 调用目标对象全类名
     */
    private String targetClassName;

    /**
     * 调用目标方法名称
     */
    private String methodName;

    /**
     * 调用目标参数
     */
    private Object[] params;

//    private final int hashCode;

    /**
     * 生成key
     *
     * @param target
     * @param method
     * @param params
     * @return
     */
    @Override
    public String buildKey(Object target, Method method, Object... params) {
        return null;
    }
}
