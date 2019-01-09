package tech.tengshe789.miaocache.service.impl;

import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import tech.tengshe789.miaocache.service.GuavaService;

import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;

/**
 * @program: miaocache
 * @description:
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-01-09 14:45
 **/
public class GuavaServiceImpl implements GuavaService {
    @Autowired
    @Qualifier("GuavaBuilder")
    private CacheBuilder<String,String> builder;

    @Override
    public String get(@NotNull String key) {
        String value = builder.build().asMap().get(key);
        return value;
    }

    @Override
    public boolean set(@NotNull String key, String value) {
        builder.build().put(key,value);
        return true;
    }

    @Override
    public boolean set(@NotNull String key, String value , int expireTime) {
        builder.expireAfterWrite(expireTime, TimeUnit.SECONDS)
                .build()
                .put(key,value);
        return true;
    }

    @Override
    public boolean delete(@NotNull String key) {
        builder.build().invalidate(key);
        return true;
    }
}
