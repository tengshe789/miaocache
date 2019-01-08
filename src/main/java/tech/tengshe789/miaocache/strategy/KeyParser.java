package tech.tengshe789.miaocache.strategy;

import java.lang.reflect.Method;

/**
 * @program: miaocache
 * @description: 解析KEy
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 **/
public interface KeyParser {

    /**
     * 解析key
     * @param prefix
     * @param realKey
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T parse (String prefix , String realKey , Class<T> clazz) ;

}
