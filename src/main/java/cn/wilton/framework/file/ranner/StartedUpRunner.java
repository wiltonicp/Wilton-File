package cn.wilton.framework.file.ranner;

import cn.wilton.framework.file.common.constant.WiltonConstant;
import cn.wilton.framework.file.common.util.WiltonUtil;
import cn.wilton.framework.file.properties.WiltonProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.UnknownHostException;

/**
 * <p>
 *
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/31
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StartedUpRunner implements ApplicationRunner {

    private final ConfigurableApplicationContext context;
    private final Environment environment;
    private final WiltonProperties properties;

    @Override
    public void run(ApplicationArguments args) throws UnknownHostException {
        if (context.isActive()) {
            WiltonUtil.printStartUpBanner(environment);
        }
    }
}
