package com.example;

import org.apache.logging.log4j.message.SimpleMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot08TestApplicationTests {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("测试邮件");
        message.setText("hello world");

        message.setTo("1115182178@qq.com");
        message.setFrom("1115182178@qq.com");
        mailSender.send(message);
    }

    @Test
    public void contextLoads2() throws MessagingException {
        //邮件设置2：一个复杂的邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setSubject("通知-明天来听课");
        helper.setText("<b style='color:red'>今天 7:30来开会</b>",true);

        //发送附件
        helper.addAttachment("1.jpg",new File("/Users/zhaochoudemao/Documents/图片/头像图片/下载.jpeg"));
        //helper.addAttachment("2.jpg",new File(""));

        helper.setTo("1115182178@qq.com");
        helper.setFrom("1115182178@qq.com");

        mailSender.send(mimeMessage);
    }

}
