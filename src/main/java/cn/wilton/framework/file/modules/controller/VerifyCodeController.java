package cn.wilton.framework.file.modules.controller;

import cn.wilton.framework.file.modules.service.IMailService;
import com.vihackerframework.common.api.ViHackerResult;
import com.vihackerframework.common.exception.ViHackerException;
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

    private final IMailService mailService;
    private final TemplateEngine templateEngine;

    @GetMapping("sendMailCode")
    public ViHackerResult sendMailCode() throws ViHackerException {
        try {
            Context context = new Context();
            context.setVariable("project", "IJPay");
            context.setVariable("author", "Javen");
            context.setVariable("url", "https://github.com/Javen205/IJPay");
            String emailContent = templateEngine.process("/mail/verify-code", context);
            mailService.sendHtmlMail("wilton.icp@gmail.com", "确认您的电子邮件地址", emailContent);
            log.info("邮件发送成功");
        }catch (Exception ex){
            throw new ViHackerException("邮件发送失败!");
        }
        return ViHackerResult.success();
    }
}
