package cn.wilton.framework.file.modules.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liming
 * @create 2022/5/5 15:43
 */
@Api(tags = "test")
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public void hello(){
        System.out.println("hello");
    }
}
