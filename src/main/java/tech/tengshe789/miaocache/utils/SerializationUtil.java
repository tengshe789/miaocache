package tech.tengshe789.miaocache.utils;

import com.alibaba.fastjson.JSON;

/**
 * @program: miaocache
 * @description: 序列化工具
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2018-12-21 12:00
 **/
public class SerializationUtil<T> {
    /**
     *  json转换成bean
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T jsonToBean(String json, Class<T> clazz){
        if(json == null || json.length() <= 0 || clazz == null) {
            return null;
        }
        if(clazz == int.class || clazz == Integer.class) {
            return (T)Integer.valueOf(json);
        }else if(clazz == String.class) {
            return (T)json;
        }else if(clazz == long.class || clazz == Long.class) {
            return  (T)Long.valueOf(json);
        }else {
            return JSON.toJavaObject(JSON.parseObject(json), clazz);
        }
    }

    /**
     * bean 转换成json
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> String beanToJson(T bean) {
        if(bean == null) {
            return null;
        }
        Class<?> clazz = bean.getClass();
        if(clazz == int.class || clazz == Integer.class) {
            return "" + bean;
        }else if(clazz == String.class) {
            return (String)bean;
        }else if(clazz == long.class || clazz == Long.class) {
            return "" + bean;
        }else {
            return JSON.toJSONString(bean);
        }
    }
}
