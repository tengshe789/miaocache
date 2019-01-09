package tech.tengshe789.miaocache.service;

import javax.validation.constraints.NotNull;

/**
 * @program: miaocache
 * @description: Guava 实现
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 **/
public interface GuavaService {

    public String get(@NotNull String key);

    public boolean set (@NotNull String key, String value);

    public boolean set(@NotNull String key, String value , int expireTime) ;

    public boolean delete (@NotNull String key);

}
