package tech.tengshe789.miaocache.strategy;

/**
 * @program: miaocache
 * @description: 生成key
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 **/
public interface KeyGenerator {

    /**
     * 生成key
     * @param key
     * @return
     */
    public String generateKey(String prefix , String key);

}
