package cn.wilton.framework.file.modules.service.impl;

import cn.wilton.framework.file.modules.service.IMailService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/20
 */
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements IMailService {

    @Value("${spring.mail.from}")
    private String from;
    @Value("${spring.mail.from-name}")
    private String fromName;
    private final JavaMailSender mailSender;

    /**
     * 发送文本邮件
     * @param to
     * @param subject
     * @param content
     */
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

    @Override
    public void sendSimpleMail(String to, String subject, String content, String... cc) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setCc(cc);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

    /**
     * 发送HTML邮件
     * @param to
     * @param subject
     * @param content
     */
    @Override
    @SneakyThrows
    public void sendHtmlMail(String to, String subject, String content) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(new InternetAddress(from,fromName,"UTF-8"));
        helper.setTo(to);

        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }

    @Override
    @SneakyThrows
    public void sendHtmlMail(String to, String subject, String content, String... cc) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(new InternetAddress(from,fromName,"UTF-8"));
        helper.setTo(to);
        helper.setCc(cc);
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }

    /**
     * 发送带附件的邮件
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    @Override
    @SneakyThrows
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(new InternetAddress(from,fromName,"UTF-8"));
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        FileSystemResource file = new FileSystemResource(new File(filePath));
        String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
        helper.addAttachment(fileName, file);

        mailSender.send(message);
    }

    @Override
    @SneakyThrows
    public void sendAttachmentsMail(String to, String subject, String content, String filePath, String... cc) {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(new InternetAddress(from,fromName,"UTF-8"));
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        helper.setCc(cc);

        FileSystemResource file = new FileSystemResource(new File(filePath));
        String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
        helper.addAttachment(fileName, file);

        mailSender.send(message);
    }

    /**
     * 发送正文中有静态资源的邮件
     * @param to
     * @param subject
     * @param content
     * @param rscPath
     * @param rscId
     */
    @Override
    @SneakyThrows
    public void sendResourceMail(String to, String subject, String content, String rscPath, String rscId) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(new InternetAddress(from,fromName,"UTF-8"));
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        FileSystemResource res = new FileSystemResource(new File(rscPath));
        helper.addInline(rscId, res);

        mailSender.send(message);
    }

    @Override
    @SneakyThrows
    public void sendResourceMail(String to, String subject, String content, String rscPath, String rscId, String... cc) {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(new InternetAddress(from,fromName,"UTF-8"));
        helper.setTo(to);
        helper.setCc(cc);
        helper.setSubject(subject);
        helper.setText(content, true);

        FileSystemResource res = new FileSystemResource(new File(rscPath));
        helper.addInline(rscId, res);

        mailSender.send(message);
    }
}
