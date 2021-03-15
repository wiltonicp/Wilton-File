package cn.wilton.framework.file.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/15
 */
@Data
@SpringBootConfiguration
@ConfigurationProperties(prefix = "wilton")
public class WiltonProperties {
}
