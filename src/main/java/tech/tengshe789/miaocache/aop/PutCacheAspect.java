package tech.tengshe789.miaocache.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
import tech.tengshe789.miaocache.annotation.PutCache;
import tech.tengshe789.miaocache.exception.CacheObjectErrorException;
import tech.tengshe789.miaocache.exception.SetCacheErrorException;

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


    @Pointcut("@annotation(tech.tengshe789.miaocache.annotation.PutCache)")
    public void aspect(){

    }

    @Around("aspect()&&@annotation(anno)")
    public Object interceptor(ProceedingJoinPoint invocation, PutCache anno) throws CacheObjectErrorException, SetCacheErrorException {
        return null;
    }
}
