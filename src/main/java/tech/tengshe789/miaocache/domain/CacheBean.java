package tech.tengshe789.miaocache.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import javax.xml.crypto.Data;

/**
 * @program: miaocache
 * @description:
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2018-12-20 21:47
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CacheBean {
    private String prefix;

    @NotNull
    private String key;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Data expireTime;

    private String description;

    public CacheBean (String key) {
        this.key = key;
    }
}
