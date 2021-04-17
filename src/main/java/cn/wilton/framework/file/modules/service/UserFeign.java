package cn.wilton.framework.file.modules.service;

import com.vihackerframework.common.api.ViHackerResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户服务
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/1
 */
@FeignClient(value = "userFeignClient",url = "${wilton.oauth.server.url}")
public interface UserFeign {

    @GetMapping("/userServer/internal/userBase")
    ViHackerResult userBase(@RequestParam(value="uid") String uid);
}
