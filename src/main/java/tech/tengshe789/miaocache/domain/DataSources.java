package tech.tengshe789.miaocache.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tech.tengshe789.miaocache.constants.DataSourceConstants;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * @program: miaocache
 * @description: 多数据源信息
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2018-12-21 10:40
 **/
@Slf4j
public class DataSources {

    /**
     * key: 	datasourceID
     * value: 	datasourceName
     */
    @Getter
    @Setter
    private Map<String, String> dataSourceNameMap;

    /**
     *
     */
    @Getter
    @Setter
    private List<Map<String, String>> dataSourceNameList;

    /**
     * key: 	datasourceID
     * value: 	DataSource
     */
    @Getter
    @Setter
    private Map<String, javax.sql.DataSource> dataSourceMap;

    /**
     * 默认帐套
     */
    @Getter
    @Setter
    private String defaultDataSource;

    /**
     * 防止new
     */
    private DataSources() {}

    /**
     * 创建实例
     */
    public static DataSources getInstance() {
        return SingletonDataSources.INSTANCE.getInstance();
    }

    /**
     * 线程安全且不浪费性能的创建DataSources的单例
     */
    private enum SingletonDataSources{

        INSTANCE;

        private DataSources dataSources;

        SingletonDataSources(){
            dataSources = new DataSources();
        }

        public DataSources getInstance (){
            return dataSources;
        }
    }

    /**
     * 获取数据源ID
     * @return
     */
    public String getDataSourceId() {
        String chooseDataSource = null;

        // 从线程里获取
        String threadName = Thread.currentThread().getName();
        if(threadName.startsWith(DataSourceConstants.DATA_SOURCE)) {
            // 线程名包括数据源
            chooseDataSource = threadName.substring(DataSourceConstants.DATA_SOURCE.length());
        } else if(null != RequestContextHolder.getRequestAttributes()) {
            // 获取request，session中包括所选数据源
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            if(null != request) {
                Object userDb = request.getSession().getAttribute(DataSourceConstants.SESSION_DATA_SOURCE);
                if(null != userDb) {
                    // session中存在帐套信息
                    chooseDataSource = (String) userDb;
                }
            }
        }

        if(StringUtils.isBlank(chooseDataSource)) {
            // 系统无法选择数据源，使用默认数据源
            if(null != this.defaultDataSource) {
                return defaultDataSource;
            } else {
                chooseDataSource = DataSourceConstants.DEFAULT_DATA_SOURCE;
            }
        }
        return chooseDataSource;
    }
}
