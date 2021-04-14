package cn.wilton.framework.file.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/31
 */
@Slf4j
public class WiltonUtil {

    public static void printStartUpBanner(Environment environment) throws UnknownHostException {
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Login: \thttp://{}:{}\n" +
                        "----------------------------------------------------------",
                environment.getProperty("spring.application.name"),
                InetAddress.getLocalHost().getHostAddress(),
                environment.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                environment.getProperty("server.port"));
    }
}
