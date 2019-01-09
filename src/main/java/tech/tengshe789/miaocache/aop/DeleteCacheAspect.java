package tech.tengshe789.miaocache.aop;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
import tech.tengshe789.miaocache.annotation.DeleteCache;
import tech.tengshe789.miaocache.constants.KeyPrefixConstants;
import tech.tengshe789.miaocache.domain.CacheBean;
import tech.tengshe789.miaocache.exception.CacheObjectErrorException;
import tech.tengshe789.miaocache.exception.SetCacheErrorException;

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
            throw new CacheObjectErrorException("缓存对象不正确！必须使用CacheBean格式的缓存，请重试！");
        }
    }
}
