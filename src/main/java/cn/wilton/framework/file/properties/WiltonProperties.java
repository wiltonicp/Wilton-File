package cn.wilton.framework.file.properties;

import cn.wilton.framework.file.common.util.SecurityUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.File;

/**
 *
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/15
 */
@Data
@SpringBootConfiguration
@ConfigurationProperties(prefix = "wilton.upload")
public class WiltonProperties {

    public String path;

    @Value("${wilton.redisKey}")
    public String redisKey;

    public String getUserPath(){
        return this.path + File.separator + SecurityUtil.getLoginUser().getSpaceCode() + File.separator;
    }
}
