package tech.tengshe789.miaocache.strategy.impl;

import org.springframework.stereotype.Service;
import tech.tengshe789.miaocache.constants.SegmentConstants;
import tech.tengshe789.miaocache.strategy.KeyGenerator;
import tech.tengshe789.miaocache.utils.SerializationUtil;

/**
 * @program: miao-cache
 * @description: 默认key的生成策略
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2018-12-21 10:37
 **/
@Service("DefaultKeyGenerator")
public class DefaultKeyGenerator implements KeyGenerator {

    @Override
    public String generateKey(String prefix , String key) {
        String realKey = prefix + SegmentConstants.SEGMENTATION_SYMBOL_1 + key;
        String json = SerializationUtil.beanToJson(realKey);
        return json;
    }
}
