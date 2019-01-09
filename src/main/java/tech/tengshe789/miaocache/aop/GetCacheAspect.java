package tech.tengshe789.miaocache.aop;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import tech.tengshe789.miaocache.annotation.GetCache;
import tech.tengshe789.miaocache.api.impl.GuavaCacheApi;
import tech.tengshe789.miaocache.api.impl.RedisCacheApi;
import tech.tengshe789.miaocache.constants.CacheType;
import tech.tengshe789.miaocache.constants.KeyPrefixConstants;
import tech.tengshe789.miaocache.domain.CacheBean;
import tech.tengshe789.miaocache.exception.CacheObjectErrorException;
import tech.tengshe789.miaocache.exception.SetCacheErrorException;
import tech.tengshe789.miaocache.strategy.KeyGenerator;
import tech.tengshe789.miaocache.utils.SerializationUtil;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Method;

/**
 * @program: miaocache
 * @description: 查询缓存注解拦截
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-01-09 10:29
 **/
@Slf4j
@Aspect
@Service
public class GetCacheAspect {
    @Autowired
    @Qualifier("DefaultKeyGenerator")
    KeyGenerator generator;

    @Autowired
    private RedisCacheApi redisApi;

    @Autowired
    private GuavaCacheApi guavaApi;

    @Pointcut("@annotation(tech.tengshe789.miaocache.annotation.GetCache)")
    public void aspect(){

    }

    @Around("aspect()&&@annotation(anno)")
    public Object interceptor(ProceedingJoinPoint invocation, GetCache anno) throws CacheObjectErrorException, SetCacheErrorException {
        //获取目标方法的对象
        Object target = invocation.getTarget();
        //获取目标方法签名
        MethodSignature methodSignature = (MethodSignature) invocation.getSignature();
        //获取目标方法名称
        Method method = methodSignature.getMethod();

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
            throw new CacheObjectErrorException("缓存对象不正确！必须使用CacheBean格式的缓存，请重试！");
        }
        Object cache = getCacheByLocal(cacheBean,method);
        //从redis中查询
        if (cache == null){
            cache = getCacheByRedis(cacheBean,method);
        }
        return cache;
    }

    private Object getCacheByRedis(CacheBean cacheBean, Method method) {
        @NotNull String key = cacheBean.getKey();
        String prefix = cacheBean.getPrefix();
        String objJson = redisApi.get(generator.generateKey(prefix, key));
        return SerializationUtil.jsonToBean(objJson,method.getClass());
    }

    private Object getCacheByLocal(CacheBean cacheBean, Method method) {
        @NotNull String key = cacheBean.getKey();
        String prefix = cacheBean.getPrefix();
        return SerializationUtil.jsonToBean(guavaApi.get(generator.generateKey(prefix,key)),method.getClass());
    }

}
