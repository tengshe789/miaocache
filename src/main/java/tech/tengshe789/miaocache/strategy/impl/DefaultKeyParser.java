package tech.tengshe789.miaocache.strategy.impl;

import org.springframework.stereotype.Service;
import tech.tengshe789.miaocache.strategy.KeyParser;

/**
 * @program: miaocache
 * @description:
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2018-12-21 18:02
 **/
@Service
public class DefaultKeyParser implements KeyParser {

    /**
     * 解析key
     *
     * @param key
     * @return
     */
    @Override
    public String parse(String key) {
        //TODO 巴拉巴拉
        return null;
    }

    @Override
    public Object getResult() {
        return null;
    }
}
