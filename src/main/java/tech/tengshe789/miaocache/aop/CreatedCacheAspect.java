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
import tech.tengshe789.miaocache.api.impl.GuavaCacheApi;
import tech.tengshe789.miaocache.api.impl.RedisCacheApi;
import tech.tengshe789.miaocache.constants.CacheType;
import tech.tengshe789.miaocache.annotation.CreatedCache;
import tech.tengshe789.miaocache.constants.KeyPrefixConstants;
import tech.tengshe789.miaocache.domain.CacheBean;
import tech.tengshe789.miaocache.exception.CacheObjectErrorException;
import tech.tengshe789.miaocache.exception.SetCacheErrorException;
import tech.tengshe789.miaocache.strategy.KeyGenerator;
import tech.tengshe789.miaocache.utils.SerializationUtil;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Method;

/**
 * @program: miao-cache
 * @description: 生成缓存注解拦截
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2018-12-21 17:29
 **/
@Slf4j
@Aspect
@Service
public class CreatedCacheAspect {

    @Autowired
    @Qualifier("DefaultKeyGenerator")
    KeyGenerator generator;

    @Autowired
    private RedisCacheApi redisApi;

    @Autowired
    private GuavaCacheApi guavaApi;

    @Pointcut("@annotation(tech.tengshe789.miaocache.annotation.CreatedCache)")
    public void aspect(){

    }

    @Around("aspect()&&@annotation(anno)")
    public void interceptor(ProceedingJoinPoint invocation, CreatedCache anno) throws CacheObjectErrorException, SetCacheErrorException {
        //获取目标方法签名
        MethodSignature methodSignature = (MethodSignature) invocation.getSignature();
        //获取目标方法名称
        Method method = methodSignature.getMethod();
        //获取目标方法的参数类型
        Class<?>[] parameterTypes = method.getParameterTypes();
        //获取目标方法体参数
        Object[] args = invocation.getArgs();
        //获取目标方法的对象
        Object target = invocation.getTarget();

        CacheBean cacheBean = new CacheBean();
        String prefix = anno.keyPrefix();
        @NotNull String key = anno.key();
        int expireTime = anno.expire();

        if (target.equals(CacheBean.class)){
            if (StrUtil.isBlank(prefix)) {
                prefix = KeyPrefixConstants.DEFAULT_PREFIX;
            }
            cacheBean.setPrefix(prefix);
            cacheBean.setKey(key);
            cacheBean.setExpireTime(expireTime);
        }else {
            throw new CacheObjectErrorException("缓存对象不正确！必须使用CacheBean格式的缓存，请重试！");
        }

        CacheType cacheType = anno.getCacheType();
        if (cacheType.equals(CacheType.LOCAL)){
            if (!setKeyByLocal(cacheBean)){
                throw new SetCacheErrorException("设置本地缓存出错！");
            }
        }
        if (cacheType.equals(CacheType.REDIS)){
            if (!setKeyByRedis(cacheBean)){
                throw new SetCacheErrorException("设置Redis缓存出错！");
            }
        }
        if (cacheType.equals(CacheType.BOTH)){
            if (!setKeyByLocal(cacheBean)){
                throw new SetCacheErrorException("设置本地缓存出错！");
            }
            if (!setKeyByRedis(cacheBean)){
                throw new SetCacheErrorException("设置Redis缓存出错！");
            }
        }
    }

    private boolean setKeyByRedis(CacheBean cacheBean) {
        @NotNull String key = cacheBean.getKey();
        String prefix = cacheBean.getPrefix();
        Class value = cacheBean.getValue();
        String realValue = SerializationUtil.beanToJson(value);
        String realKey = generator.generateKey(prefix, key);
        int expireTime = cacheBean.getExpireTime();
        redisApi.set(realKey,realValue,expireTime);
        return true;
    }

    private boolean setKeyByLocal (CacheBean cacheBean) {
        @NotNull String key = cacheBean.getKey();
        String prefix = cacheBean.getPrefix();
        Class value = cacheBean.getValue();
        String realValue = SerializationUtil.beanToJson(value);
        String realKey = generator.generateKey(prefix, key);
        int expireTime = cacheBean.getExpireTime();
        guavaApi.set(realKey,realValue,expireTime);
        return true;
    }


}
