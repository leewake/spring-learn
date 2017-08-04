package com.pangpang.controller;

import com.pangpang.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by leewake on 2017/8/3 0003.
 */

@RestController
@RequestMapping("/mail/send")
public class MailController {

    @Autowired
    private MailService mailService;

    @RequestMapping("/simple")
    public void simpleMail() {
        mailService.sendSimpleMail("leewake@163.com",
                "a simple mail", "Hello, this is a simple mail");
    }

    @RequestMapping("/html")
    public void htmlMail() {

        String content = "<html>\n" +
                "<body>\n" +
                "   <h2>hello world!</h3>\n" +
                "   <li> This is a Html mail!</li>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("leewake@163.com",
                "a simple mail", content);
    }

    @RequestMapping("/attachment")
    public void attachmentMail() {

        String content = "<html>\n" +
                "<body>\n" +
                "   <h2>hello world!</h3>\n" +
                "   <li> This is a Html mail!</li>\n" +
                "</body>\n" +
                "</html>";

        mailService.sendAttachmentMail("leewake@163.com",
                "a simple mail", content, "F:\\redis-log\\redis.log");
    }


    @RequestMapping("/inlineResource")
    public void inlineResourceMail() {

        String rscId = "pangpangxiong";

        String content = "<html>\n" +
                "<body>\n" +
                "   <h2>这是一封有图片的邮件:</h2>\n" +
                "   <img src=\'cid:" + rscId + "\'>" +
                "</body>\n" +
                "</html>";

        mailService.sendInlineResourceMail("leewake@163.com",
                "这是有图片的邮件", content, "C:\\Users\\Administrator\\Desktop\\考勤\\4月打卡.jpg", rscId);
    }

    @RequestMapping("/template")
    public void templateMail() {

        mailService.sendTemplateMail();

    }

}
