package com.pangpang.service;

/**
 * Created by leewake on 2017/8/3 0003.
 */
public interface MailService {

    void sendTemplateMail();

    void sendSimpleMail(String to, String subject, String content);

    void sendHtmlMail(String to, String subject, String content);

    void sendAttachmentMail(String to, String subject, String content, String filePath);

    void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);

}
