package tech.tengshe789.miaocache.exception;

import lombok.NoArgsConstructor;

/**
 * @program: miao-cache
 * @description: 没找到缓存异常
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2019-01-09 09:58
 **/
@NoArgsConstructor
public class NoneCacheErrorException extends Exception {
    private static final long serialVersionUID = 1L;

    public NoneCacheErrorException(String message) {
        super(message);
    }

    public NoneCacheErrorException(Throwable cause) {
        super(cause);
    }

    public NoneCacheErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoneCacheErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
