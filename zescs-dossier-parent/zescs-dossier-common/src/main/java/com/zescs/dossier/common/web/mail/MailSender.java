package com.zescs.dossier.common.web.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zescs.dossier.common.web.exception.MailException;

/**
 * 
 * @ClassName: MailSender
 * @Description: TODO(邮件发送器)
 * @author 郑建平
 * @date 2015年1月5日 下午7:00:02
 *
 */
public final class MailSender {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MailSender.class);

	/**
	 * 
	 * @Title: send
	 * @Description: TODO(发送HTML)
	 * @param address
	 * @param subject
	 * @param content
	 * @return
	 */
	public static Boolean send(MailAddress address, String subject,
			String content) {
		return send(address, subject, content, null);
	}

	/**
	 * 
	 * @Title: send
	 * @Description: TODO(发送附件)
	 * @param address
	 * @param subject
	 * @param content
	 * @param attach
	 * @return
	 */
	public static Boolean send(MailAddress address, String subject,
			String content, AttachBean attach) {
		try {
			MailHandler handler = new MailHandler();
			// 设置收件人
			handler.setTo(address);
			// 设置标题
			handler.setSubject(subject);
			// 设置内容
			handler.setHtmlContent(content);
			// 设置附件
			if (attach != null) {
				handler.addAttachFile(attach);
			}
			// 发送
			handler.send();
			return true;
		} catch (MailException e) {
			LOGGER.error(e.getMessage());
		}
		return false;
	}
}
