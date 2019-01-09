package tech.tengshe789.miaocache.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;

/**
 * @program: miao-cache
 * @description:
 * @author: <a href="mailto:randyvan007@qq.com">tEngSHe789</a>
 * @create: 2018-12-20 21:47
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CacheBean {
    public String prefix;

    @NotNull
    public String key;

    public int expireTime;

    public Class value;
}
