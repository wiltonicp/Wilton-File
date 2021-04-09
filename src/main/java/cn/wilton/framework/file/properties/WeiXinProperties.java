package cn.wilton.framework.file.properties;

import cn.wilton.framework.file.common.util.SecurityUtil;
import lombok.Data;
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
@ConfigurationProperties(prefix = "wilton.weixin")
public class WeiXinProperties {

    public String appId;

    public String appSecret;

    public String token;

    public String encodingAESKey;
}
