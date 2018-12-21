package tech.tengshe789.miaocache.domain;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @program: miaocache
 * @description: 仿造spring cache 加的简单key，key为 参数信息
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2018-12-21 16:40
 **/
public class SimpleKey implements Serializable {
    public final static SimpleKey INSTANCE = new SimpleKey(new Object[0]);

    /**
     * 调用目标参数
     */
    private final Object[] params;

    private final int hashCode;

    public SimpleKey(Object... elements) {
        Assert.notNull(elements, "Elements must not be null");
        this.params = new Object[elements.length];
        System.arraycopy(elements, 0, this.params, 0, elements.length);
        this.hashCode = Arrays.deepHashCode(this.params);
    }

    @Override
    public boolean equals(Object other) {
        return this == other || other instanceof SimpleKey && Arrays.deepEquals(this.params, ((SimpleKey)other).params);
    }

    @Override
    public final int hashCode() {
        return this.hashCode;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [" + StringUtils.arrayToCommaDelimitedString(this.params) + "]";
    }
}

