package tech.tengshe789.miaocache.api.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import tech.tengshe789.miaocache.api.CacheApi;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: miaocache
 * @description:
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2018-12-21 11:32
 **/
@Service
public class RedisCache implements CacheApi {

}
