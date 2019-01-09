package tech.tengshe789.miaocache.exception;

import lombok.NoArgsConstructor;

/**
 * @program: miaocache
 * @description: 设置缓存出错
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-01-09 10:20
 **/
@NoArgsConstructor
public class SetCacheErrorException extends Exception {
    private static final long serialVersionUID = 1L;

    public SetCacheErrorException(String message) {
        super(message);
    }

    public SetCacheErrorException(Throwable cause) {
        super(cause);
    }

    public SetCacheErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public SetCacheErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}