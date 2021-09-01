package cn.wilton.framework.file;

import cn.wilton.framework.file.websocket.WebSocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 启动入口
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/12
 */
@SpringBootApplication
public class WiltonFileApplication {

    public static void main(String[] args) {
        //SpringApplication.run(WiltonFileApplication.class, args);

        SpringApplication springApplication = new SpringApplication(WiltonFileApplication.class);
        ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);
        //解决WebSocket不能注入的问题
        WebSocketServer.setApplicationContext(configurableApplicationContext);
    }
}
