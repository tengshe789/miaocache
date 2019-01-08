package tech.tengshe789.miaocache.aop;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.tengshe789.miaocache.annotation.CacheCreated;
import tech.tengshe789.miaocache.constants.CacheType;
import tech.tengshe789.miaocache.annotation.CreatedCache;
import tech.tengshe789.miaocache.api.CacheApi;
import tech.tengshe789.miaocache.strategy.KeyGenerator;
import tech.tengshe789.miaocache.strategy.KeyParser;
import tech.tengshe789.miaocache.strategy.impl.DefaultKeyParser;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: miaocache
 * @description: 生成缓存注解拦截
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2018-12-21 17:29
 **/
@Slf4j
@Aspect
@Service
public class CacheCreatedAspect {

    @Autowired
    private DefaultKeyParser parser;

    @Autowired
    private CacheApi cacheApi;

    /**
     * 生成表
     */
    ConcurrentHashMap<String, KeyGenerator> generatorMap = new ConcurrentHashMap<>();

    /**
     * 解析表
     */
    ConcurrentHashMap<String, KeyParser> parserMap = new ConcurrentHashMap<>();

    @Pointcut("@annotation(tech.tengshe789.miaocache.annotation.CreatedCache)")
    public void aspect(){

    }

    @Around("aspect()&&@annotation(anno)")
    public Object interceptor(ProceedingJoinPoint invocation, CreatedCache anno){
        //获取目标方法签名
        MethodSignature methodSignature = (MethodSignature) invocation.getSignature();
        //获取目标方法名称
        Method method = methodSignature.getMethod();
        //获取目标方法的参数类型
        Class<?>[] parameterTypes = method.getParameterTypes();
        //获取目标方法体参数
        Object[] args = invocation.getArgs();

        String key = "";
        String value = "";
        Object result = null;

        try {
            key = getKey(anno,parameterTypes,args);
            //TODO 拿去redis中的缓存
        }catch (Exception e){
            log.info("获取缓存失败:{}",key);
        }finally {
            if (result == null) {
                try {
                    result = invocation.proceed();
                } catch (Throwable throwable) {
                }
                if (StrUtil.isNotBlank(key)) {
                    cacheApi.set(key, result, anno.expire());
                }
            }
        }
        return result;

    }

    private String getKey(CreatedCache anno, Class<?>[] parameterTypes, Object[] args) {
        String key = null;
        CacheType cacheType = anno.getCacheType();
        if (cacheType.equals(CacheType.LOCAL)){
//            return getKeyByLocal();
        }
        //TODO 本地拿不到，则去redis中取

        return null;

    }

    private String getKeyByLocal (CreatedCache anno, Class<?>[] parameterTypes, Object[] args) {
        //拿到key的构造器类型
        String className = anno.generator().getName();
        //判断key是否在本地缓存中
        KeyGenerator keyGenerator = null;
        if (generatorMap.contains(className)) {
            keyGenerator = generatorMap.get(className);

        }
        //TODO 返回本地缓存
//        return keyGenerator.generateKey()
        return null;
    }


}
