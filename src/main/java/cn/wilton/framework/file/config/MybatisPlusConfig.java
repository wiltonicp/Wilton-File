package cn.wilton.framework.file.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus配置类
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/16
 */
@Configuration
@MapperScan("cn.wilton.framework.file.modules.mapper")
public class MybatisPlusConfig {

}
