package tech.tengshe789.miaocache.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;

/**
 * @program: miaocache
 * @description:
 * @author: tEngSHe789
 * @create: 2018-12-21 08:54
 **/
@Slf4j
public class PropertiesUtil {

    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Getter
    private final Properties properties;


    public PropertiesUtil(String... path) {
        properties = loadProperties(path);
    }


    /**
     *  从System中拿到Property
     * @param str
     * @return
     */
    public String getProperty(String str) {
        String sysProperty = System.getProperty(str);
        if (!sysProperty.isEmpty()){
            return sysProperty;
        }
        if (!properties.containsKey(str)){
            throw new NoSuchElementException();
        }
        return "";
    }

    /**
     * 加载多个Properties
     * @param path
     * @return
     */
    private Properties loadProperties(String... path) {
        Properties properties = null;

        for (String file : path) {
            log.debug("读取配置文件:{}", file);
            Resource resource = resourceLoader.getResource(file);

            InputStream inputStream = null;
            try {
                inputStream = resource.getInputStream();
                properties.load(inputStream);
            } catch (IOException e) {
                log.error("读取配置文件{}出错", file);
            } finally {
                try {
                    inputStream.close();
                } catch (IOException ee) {
                }
            }
        }
        return properties;
    }

}
