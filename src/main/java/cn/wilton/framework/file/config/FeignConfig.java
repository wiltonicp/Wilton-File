package cn.wilton.framework.file.config;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/1
 */
@Configuration
public class FeignConfig {

    /**
     * 如需使用spring系的注解（RequestMapping、RequestParam等）可以不加如下 Contract 配置，
     * 或者返回 SpringMvcContract。
     */
    @Bean
    public Contract feignContract() {
        Contract.Default defaultContract = new Contract.Default();
        return defaultContract;
    }
}
