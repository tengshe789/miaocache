package tech.tengshe789.miaocache.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.tengshe789.miaocache.annotation.CacheCreated;
import tech.tengshe789.miaocache.api.CacheApi;
import tech.tengshe789.miaocache.strategy.impl.DefaultKeyGenerator;
import tech.tengshe789.miaocache.strategy.impl.DefaultKeyParser;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

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
    private DefaultKeyGenerator generator;

    @Autowired
    private CacheApi cacheApi;

    @Pointcut("@annotation(tech.tengshe789.miaocache.annotation.CacheCreated)")
    public void aspect(){

    }

    @Around("aspect()&&@annotation(anno)")
    public Object interceptor(ProceedingJoinPoint invocation, CacheCreated anno){
        MethodSignature methodSignature = (MethodSignature) invocation.getSignature();
        //目标方法名称
        Method method = methodSignature.getMethod();
        Object result = null;
        //目标参数
        Class<?>[] parameterTypes = method.getParameterTypes();
        Object[] args = invocation.getArgs();

        String key = "";
        String value = "";

        try {
            key = generator.generateKey(key);
            value = cacheApi.get(key);
            Type genericReturnType = method.getGenericReturnType();
        }catch (Exception e){
            log.info("获取缓存失败:{}",key);
        }finally {
            if (result == null) {
                try {
                    result = invocation.proceed();
                } catch (Throwable throwable) {
                }
                if (StringUtils.isNotBlank(key)) {
                    cacheApi.set(key, result, anno.expire());
                }
            }
        }
        return result;

    }


}
