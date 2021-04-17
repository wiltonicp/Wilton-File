package cn.wilton.framework.file.ranner;

import cn.wilton.framework.file.common.util.WiltonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;

/**
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/31
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StartedUpRunner implements ApplicationRunner {

    private final Environment environment;
    private final ConfigurableApplicationContext context;

    @Override
    public void run(ApplicationArguments args) throws UnknownHostException {
        if (context.isActive()) {
            WiltonUtil.printStartUpBanner(environment);
        }
    }
}
