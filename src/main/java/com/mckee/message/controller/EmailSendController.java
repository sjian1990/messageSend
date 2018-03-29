package com.mckee.message.controller;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailSendController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmailSendController.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String sender;
	
	
	@RequestMapping(value="/sentHtmlEmail")
	public void sentHtmlEmail(HttpServletRequest request) {
		MimeMessage message = null;
		try {
			message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message,true);
			helper.setFrom(sender);
			helper.setTo("505444609@qq.com");
			helper.setSubject("主题：HTML邮件");
			
			StringBuffer sb = new StringBuffer();
			sb.append("<h1>大标题-h1</h1>").append("<p style='color:#F00'>红色字</p>")
				.append("<p style='text-align:right'>右对齐</p>");
			helper.setText(sb.toString(), true);
			
			mailSender.send(message);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("推送报错！",e);
		}
		
	}
	
	
	
}
