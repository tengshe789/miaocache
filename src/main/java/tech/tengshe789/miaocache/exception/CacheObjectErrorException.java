/*
 *
 *      Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the pig4cloud.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: lengleng (wangiegie@gmail.com)
 *
 */

package tech.tengshe789.miaocache.exception;

import lombok.NoArgsConstructor;

/**
 * @program: miao-cache
 * @description: 缓存对象出错异常
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2018-12-20 22:47
 **/
public class CacheObjectErrorException extends Exception {
	private static final long serialVersionUID = 1L;

	private static final String CACHE_OBJ_ERR_MSG = "缓存对象不正确！必须使用CacheBean格式的缓存，请重试！";

	public CacheObjectErrorException() {
		super(CACHE_OBJ_ERR_MSG);
	}

	public CacheObjectErrorException(String message) {
		super(message);
	}

	public CacheObjectErrorException(Throwable cause) {
		super(cause);
	}

	public CacheObjectErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public CacheObjectErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
