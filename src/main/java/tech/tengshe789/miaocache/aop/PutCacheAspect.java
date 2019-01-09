package tech.tengshe789.miaocache.aop;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import tech.tengshe789.miaocache.annotation.PutCache;
import tech.tengshe789.miaocache.api.impl.GuavaCacheApi;
import tech.tengshe789.miaocache.api.impl.RedisCacheApi;
import tech.tengshe789.miaocache.constants.CacheType;
import tech.tengshe789.miaocache.constants.KeyPrefixConstants;
import tech.tengshe789.miaocache.domain.CacheBean;
import tech.tengshe789.miaocache.exception.CacheObjectErrorException;
import tech.tengshe789.miaocache.exception.SetCacheErrorException;
import tech.tengshe789.miaocache.strategy.KeyGenerator;

import javax.validation.constraints.NotNull;

/**
 * @program: miaocache
 * @description: 更新缓存注解拦截
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-01-09 10:29
 **/
@Slf4j
@Aspect
@Service
public class PutCacheAspect {
    @Autowired
    @Qualifier("DefaultKeyGenerator")
    KeyGenerator generator;

    @Autowired
    private RedisCacheApi redisApi;

    @Autowired
    private GuavaCacheApi guavaApi;


    @Pointcut("@annotation(tech.tengshe789.miaocache.annotation.PutCache)")
    public void aspect(){

    }

    @Around("aspect()&&@annotation(anno)")
    public Object interceptor(ProceedingJoinPoint invocation, PutCache anno) throws CacheObjectErrorException, SetCacheErrorException {
        Object target = invocation.getTarget();
        CacheBean cacheBean = new CacheBean();
        String prefix = anno.keyPrefix();
        String key = anno.key();
        if (target.equals(CacheBean.class)) {
            cacheBean.setPrefix(prefix);
            cacheBean.setKey(key);
        }else {
            throw new CacheObjectErrorException();
        }
        CacheType cacheType = anno.getCacheType();
        if (cacheType.equals(CacheType.BOTH)) {
            putCacheViaLoacl(cacheBean);
            putCacheViaRedis(cacheBean);
        }
        if (cacheType.equals(CacheType.LOCAL)) {
            putCacheViaLoacl(cacheBean);
        }
        if (cacheType.equals(CacheType.REDIS)) {
            putCacheViaRedis(cacheBean);
        }
        return null;
    }

    private void putCacheViaRedis(CacheBean cacheBean) {
        //TODO redis put操作

    }

    private boolean putCacheViaLoacl(CacheBean cacheBean) {
        return false;
    }
}
