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
import tech.tengshe789.miaocache.annotation.DeleteCache;
import tech.tengshe789.miaocache.api.impl.GuavaCacheApi;
import tech.tengshe789.miaocache.api.impl.RedisCacheApi;
import tech.tengshe789.miaocache.constants.KeyPrefixConstants;
import tech.tengshe789.miaocache.domain.CacheBean;
import tech.tengshe789.miaocache.exception.CacheObjectErrorException;
import tech.tengshe789.miaocache.exception.SetCacheErrorException;
import tech.tengshe789.miaocache.strategy.KeyGenerator;

import javax.validation.constraints.NotNull;

/**
 * @program: miao-cache
 * @description: 清空缓存注解拦截
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-01-09 10:30
 **/
@Slf4j
@Aspect
@Service
public class DeleteCacheAspect {
    @Autowired
    @Qualifier("DefaultKeyGenerator")
    KeyGenerator generator;

    @Autowired
    private RedisCacheApi redisApi;

    @Autowired
    private GuavaCacheApi guavaApi;


    @Pointcut("@annotation(tech.tengshe789.miaocache.annotation.DeleteCache)")
    public void aspect(){

    }

    @Around("aspect()&&@annotation(anno)")
    public void interceptor(ProceedingJoinPoint invocation, DeleteCache anno) throws CacheObjectErrorException, SetCacheErrorException {
        //获取目标方法的对象
        Object target = invocation.getTarget();

        CacheBean cacheBean = new CacheBean();
        String prefix = anno.keyPrefix();
        String key = anno.key();

        if (target.equals(CacheBean.class)){
            if (StrUtil.isBlank(prefix)) {
                prefix = KeyPrefixConstants.DEFAULT_PREFIX;
            }
            cacheBean.setPrefix(prefix);
            cacheBean.setKey(key);
        }else {
            throw new CacheObjectErrorException();
        }

        clearCacheViaLocal(cacheBean);
        clearCacheViaRedis(cacheBean);
    }

    private void clearCacheViaRedis(CacheBean cacheBean) {
        @NotNull String key = cacheBean.getKey();
        String prefix = cacheBean.getPrefix();
        String realKey = generator.generateKey(prefix,key);
        redisApi.remove(realKey);
    }

    private long clearCacheViaLocal(CacheBean cacheBean) {
        @NotNull String key = cacheBean.getKey();
        String prefix = cacheBean.getPrefix();
        String realKey = generator.generateKey(prefix,key);
        return guavaApi.remove(key);
    }
}
