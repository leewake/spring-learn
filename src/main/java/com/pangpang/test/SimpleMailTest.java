package com.pangpang.test;

import com.pangpang.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Created by leewake on 2017/8/3 0003.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleMailTest {

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

   /* @Test
    public void testSimpleMail() throws Exception {
        mailService.sendSimpleMail("leewake@163.com","test simple mail"," hello this is simple mail");
    }*/

    @Test
    public void sendTemplateMail() {
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("emailTemplate", context);
        mailService.sendHtmlMail("leewake@163.com", "这是一封模板邮件", emailContent);
    }

}
