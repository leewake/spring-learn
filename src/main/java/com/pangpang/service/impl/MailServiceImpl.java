package com.pangpang.service.impl;

import com.pangpang.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

/**
 * Created by leewake on 2017/8/3 0003.
 */

@Service
public class MailServiceImpl implements MailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${addr.from.mail}")
    private String from;

    @Override
    public void sendTemplateMail() {

        //构造模板引擎
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/");
        resolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(resolver);

        Context context = new Context();
        context.setVariable("id", 006);

        String emailContent = templateEngine.process("emailTemplate", context);

        sendHtmlMail("leewake@163.com", "这是一封模板邮件", emailContent);

    }


    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setSentDate(new Date());
        simpleMailMessage.setText(content);

        try {
            javaMailSender.send(simpleMailMessage);
            logger.info("已发送简单邮件");
        } catch (Exception e) {
            logger.error("简单邮件发送失败", e);
        }

    }

    @Override
    public void sendHtmlMail(String to, String subject, String content) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            javaMailSender.send(mimeMessage);
            logger.info("发送html邮件成功！");
        } catch (MessagingException e) {
            logger.error("发送html邮件时发生异常", e);
        }

    }

    @Override
    public void sendAttachmentMail(String to, String subject, String content, String filePath) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            helper.addAttachment(fileName, file);

            javaMailSender.send(mimeMessage);
            logger.info("带附件的邮件发送成功！");
        } catch (MessagingException e) {
            logger.error("发送带附件的邮件时发生异常", e);
        }

    }

    @Override
    public void sendInlineResourceMail(String to, String subject, String content,
                                       String rscPath, String rscId) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            FileSystemResource resource = new FileSystemResource(new File(rscPath));

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            helper.addInline(rscId, resource);

            javaMailSender.send(mimeMessage);
            logger.info("嵌入静态资源的邮件已经发送！");
        } catch (MessagingException e) {
            logger.error("发送嵌入静态资源的邮件时发生异常", e);
        }
    }
}
