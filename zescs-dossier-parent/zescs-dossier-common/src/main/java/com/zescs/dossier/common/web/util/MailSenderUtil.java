package com.zescs.dossier.common.web.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.zescs.dossier.config.R;

/**
 * 
 * @ClassName: MailSenderUtil
 * @Description: TODO()
 * @author
 * @date 2014年12月8日 下午8:23:43
 *
 */
public final class MailSenderUtil {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MailSenderUtil.class);
	private static JavaMailSenderImpl mailsender;
	private static MailSenderUtil mailSenderUtil;

	private MailSenderUtil() {

	}

	/**
	 * 
	 * @return
	 */
	public static MailSenderUtil getInstance() {
		try {
			if (mailSenderUtil == null) {
				synchronized (MailSenderUtil.class) {
					if (mailSenderUtil == null) {
						mailsender = new JavaMailSenderImpl();
						mailSenderUtil = new MailSenderUtil();
						mailsender.setHost(R.MailConfig.MAIL_HOST);
						mailsender.setPort(R.MailConfig.MAIL_PORT);
						mailsender.setUsername(R.MailConfig.MAIL_USERNAME);
						mailsender.setPassword(R.MailConfig.MAIL_PASSWORD);
						Properties props = new Properties();
						props.put("mail.smtp.host", R.MailConfig.MAIL_HOST);
						props.put("mail.smtp.port", R.MailConfig.MAIL_PORT);
						props.put("mail.smtp.auth", "true");
						mailsender.setJavaMailProperties(props);
					}
				}
			}
			return mailSenderUtil;
		} catch (Exception e) {
			LOGGER.error("初始化失败::" + e.getMessage());
			return null;
		}
	}

	public boolean sendText(String receive, String subject, String content) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(receive);
			message.setSubject(subject);
			message.setSentDate(new Date());
			message.setText(content);
			message.setFrom(R.MailConfig.MAIL_USERNAME);
			mailsender.send(message);
			return true;
		} catch (MailException e) {
			LOGGER.error("发送失败::" + e.getMessage());
			return false;
		}
	}

	public boolean sendHtml(String receive, String subject, String content) {
		try {
			// 创建message对象
			MimeMessage mailMessage = mailsender.createMimeMessage();
			//
			MimeMessageHelper mh = new MimeMessageHelper(mailMessage, true,
					"utf8");
			// 发送到
			mh.setTo(receive);
			mh.setSubject(subject);
			mh.setFrom(R.MailConfig.MAIL_USERNAME);
			mh.setText(content, true);
			mh.setSentDate(new Date());
			mailsender.send(mailMessage);
			return true;
		} catch (Exception e) {
			LOGGER.error("发送失败::" + e.getMessage());
			return false;
		}
	}
}
