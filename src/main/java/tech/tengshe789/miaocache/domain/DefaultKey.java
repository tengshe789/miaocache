package tech.tengshe789.miaocache.domain;

import org.apache.commons.lang3.ArrayUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @program: miaocache
 * @description:  默认key 类名+方法名+参数信息
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2018-12-21 16:57
 **/
public class DefaultKey{
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

    private final int hashCode;

    /**
     * 缓存键值
     * @param target
     * @param method
     * @param params
     */
    public DefaultKey(Object target, Method method, Object[] params) {
        this.dataSourceId = DataSources.getInstance().getDataSourceId();
        this.targetClassName = target.getClass().getName();
        this.methodName = generatorMethodName(method);
        if (ArrayUtils.isNotEmpty(params)) {
            this.params = new Object[params.length];
            for (int i = 0; i < params.length; i++) {
                this.params[i] = params[i];
            }
        }
        this.hashCode = generatorHashCode();
    }


    private String generatorMethodName(Method method) {
        StringBuilder builder = new StringBuilder(method.getName());
        Class<?>[] types = method.getParameterTypes();
        if (ArrayUtils.isNotEmpty(types)) {
            builder.append("(");
            for (Class<?> type : types) {
                String name = type.getName();
                builder.append(name + ",");
            }
            builder.append(")");
        }
        return builder.toString();
    }

    private int generatorHashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + hashCode;
        result = prime * result + ((dataSourceId == null) ? 0 : dataSourceId.hashCode());
        result = prime * result + ((targetClassName == null) ? 0 : targetClassName.hashCode());
        result = prime * result + ((methodName == null) ? 0 : methodName.hashCode());
        result = prime * result + Arrays.hashCode(params);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        DefaultKey other = (DefaultKey) obj;
        if (this.hashCode != other.hashCode) {
            return false;
        }
        if(this.dataSourceId == null && other.dataSourceId != null) {
            return false;
        } else if(! this.dataSourceId.equals(other.dataSourceId)) {
            return false;
        }
        if (this.targetClassName == null && other.targetClassName != null) {
            return false;
        } else if (! this.targetClassName.equals(other.targetClassName)) {
            return false;
        }
        if (this.methodName == null && other.methodName != null) {
            return false;
        } else if (! this.methodName.equals(other.methodName)) {
            return false;
        }
        if (! Arrays.equals(this.params, other.params)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }
}

