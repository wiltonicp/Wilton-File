package cn.wilton.framework.file.modules.controller;

import cn.wilton.framework.file.modules.service.IShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/17
 */
@RestController
@RequiredArgsConstructor
public class ResourcesController {

    private final IShareService shareService;

}
