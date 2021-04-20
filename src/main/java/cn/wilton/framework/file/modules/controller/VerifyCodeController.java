package cn.wilton.framework.file.modules.controller;

import cn.wilton.framework.file.common.util.IdUtils;
import cn.wilton.framework.file.modules.service.IMailService;
import cn.wilton.framework.file.properties.WiltonProperties;
import com.vihackerframework.common.api.ViHackerResult;
import com.vihackerframework.common.exception.ViHackerException;
import com.vihackerframework.common.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


/**
 * 验证码
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/20
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("verify")
public class VerifyCodeController {

    private final RedisService redisService;
    private final IMailService mailService;
    private final WiltonProperties properties;
    private final TemplateEngine templateEngine;

    @GetMapping("sendMailCode")
    public ViHackerResult sendMailCode(String to) throws ViHackerException {
        try {
            Context context = new Context();
            Integer code = IdUtils.getVerifyCode();
            redisService.set(properties.redisKey + to,code);
            context.setVariable("code", code);
            context.setVariable("to", to);
            String emailContent = templateEngine.process("/mail/verify-code", context);
            mailService.sendHtmlMail(to, "确认您的电子邮件地址", emailContent);
            log.info("邮件发送成功");
        }catch (Exception e){
            throw new ViHackerException("邮件发送失败!");
        }
        return ViHackerResult.success();
    }
}
