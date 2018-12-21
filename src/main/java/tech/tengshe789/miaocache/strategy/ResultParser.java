package tech.tengshe789.miaocache.strategy;

import java.lang.reflect.Method;

/**
 * @program: miaocache
 * @description: 解析缓存
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 **/
public interface ResultParser {

    public Object parse (Object target, Method method, Object... params) ;

}
