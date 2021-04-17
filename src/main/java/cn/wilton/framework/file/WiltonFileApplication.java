package cn.wilton.framework.file;

import com.vihackerframework.common.annotation.EnableVihackerLettuceRedis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动入口
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/12
 */
@EnableVihackerLettuceRedis
@SpringBootApplication
public class WiltonFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(WiltonFileApplication.class, args);
    }
}
