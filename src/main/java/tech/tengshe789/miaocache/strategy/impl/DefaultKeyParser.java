package tech.tengshe789.miaocache.strategy.impl;

import cn.hutool.core.lang.Assert;
import org.springframework.stereotype.Service;
import tech.tengshe789.miaocache.constants.SegmentConstants;
import tech.tengshe789.miaocache.strategy.KeyParser;
import tech.tengshe789.miaocache.utils.SerializationUtil;

/**
 * @program: miaocache
 * @description: 默认key的解析策略
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2018-12-21 18:02
 **/
@Service
public class DefaultKeyParser implements KeyParser {

    @Override
    public <T> T parse(String prefix , String key, Class<T> clazz) {
        Assert.notBlank(prefix);
        Assert.notBlank(key);
        String realKey = prefix + SegmentConstants.SEGMENTATION_SYMBOL_1 + key;
        T t = SerializationUtil.jsonToBean(realKey, clazz);
        return t;
    }
}
