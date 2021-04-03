package cn.wilton.framework.file;

import cn.wilton.framework.file.common.annotation.EnableWiltonLettuceRedis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动入口
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/12
 */
@EnableWiltonLettuceRedis
@SpringBootApplication
public class WiltonFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(WiltonFileApplication.class, args);
    }
}
