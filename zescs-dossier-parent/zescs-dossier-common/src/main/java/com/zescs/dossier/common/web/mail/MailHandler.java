package com.zescs.dossier.common.web.mail;

import java.io.File;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.configuration.Configuration;

import com.zescs.dossier.common.web.commons.ConfigurationUtils;
import com.zescs.dossier.common.web.exception.MailException;
import com.zescs.dossier.config.R;

/**
 * 
 * @ClassName: MailHandler
 * @Description: TODO(发送信息)
 * @author 郑建平
 * @date 2015年1月5日 下午5:07:22
 *
 */
public class MailHandler {
	/**
	 * 消息对象
	 */
	private MimeMessage message = null;
	/**
	 * 邮件体
	 */
	private MimeMultipart part = null;
	/**
	 * Session
	 */
	private Session session = null;
	private static Configuration config = null;
	static {
		config = ConfigurationUtils.getInstance().getConfig(
				R.MailConfig.CONFIG_FILE);
		if (config == null) {
			throw new ExceptionInInitializerError("屬性配置文件不存在");
		}
	}

	public MailHandler() {
		// 创建Session对象
		this.setSession();
		// 创建邮件消息对象
		this.setMessage();
	}

	public MimeMessage getMessage() {
		return message;
	}

	public void setMessage() {
		this.message = new MimeMessage(this.getSession());
	}

	public MimeMultipart getPart() {
		return part;
	}

	/**
	 * 
	 * @Title: setPart
	 * @Description: TODO(设置体)
	 */
	protected void setPart() {
		if (this.getPart() == null) {
			this.part = new MimeMultipart();
		}
	}

	/**
	 * 
	 * @Title: getSession
	 * @Description: TODO(获取session)
	 * @return
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * 
	 * @Title: setSession
	 * @Description: TODO(初始化Session对象)
	 * @param session
	 */
	protected void setSession() {
		Properties properties = new Properties();
		Properties props = new Properties();
		props.put("mail.smtp.host", config.getString(R.MailConfig.HOST));
		props.put("mail.smtp.port", config.getString(R.MailConfig.PORT));
		props.put("mail.smtp.auth", "true");
		this.session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(config
						.getString(R.MailConfig.USERNAME), config
						.getString(R.MailConfig.PASSWORD));
			}
		});
	}

	// 设置标题
	public void setSubject(String subject) throws MailException {
		try {
			this.getMessage().setSubject(subject);
		} catch (MessagingException e) {
			throw new MailException(e);
		}
	}

	// 添加html文本
	public void setHtmlContent(String content) throws MailException {
		try {
			setPart();
			BodyPart bodyPart = new MimeBodyPart();
			// 设置HTML内容
			bodyPart.setContent(content, R.MailConfig.HTML_TYPE);
			this.getPart().addBodyPart(bodyPart);
		} catch (MessagingException e) {
			throw new MailException(e);
		}
	}

	/**
	 * 
	 * @Title: addAttachFile
	 * @Description: TODO(添加附件)
	 * @param attach
	 * @throws MailException
	 */
	public void addAttachFile(AttachBean attach) throws MailException {
		this.setPart();
		try {
			Map<String, File> map = attach.getMap();
			for (String key : map.keySet()) {
				MimeBodyPart filePart = new MimeBodyPart();
				filePart.attachFile(map.get(key));
				filePart.setFileName(MimeUtility.encodeText(key));
				this.getPart().addBodyPart(filePart);
			}
		} catch (Exception e) {
			throw new MailException(e);
		}
	}

	/**
	 * 
	 * @Title: setTo
	 * @Description: TODO(设置收件人)
	 * @param recAddress
	 * @throws MailException
	 */
	public void setTo(MailAddress address) throws MailException {
		try {
			Address[] recAddress = new Address[address.getAddress().size()];
			recAddress = address.getAddress().toArray(recAddress);
			this.getMessage().setRecipients(Message.RecipientType.TO,
					recAddress);
			// this.getMessage().setRecipient(Message.RecipientType.TO, new
			// InternetAddress(recAddress));
		} catch (MessagingException e) {
			throw new MailException(e);
		}
	}

	/**
	 * 
	 * @Title: send
	 * @Description: TODO(发送邮件)
	 * @throws MailException
	 */
	public void send() throws MailException {
		try {
			// 设置发件人
			this.setFrom();
			if (this.getPart() != null) {
				this.getMessage().setContent(this.getPart());
			}
			this.getMessage().saveChanges();
			// 获取发送器
			Transport transport = getSession().getTransport(R.MailConfig.SMTP);
			// 连接到服务器
			transport.connect(config.getString(R.MailConfig.HOST), Integer
					.parseInt(String.valueOf(config
							.getString(R.MailConfig.PORT))), config
					.getString(R.MailConfig.USERNAME), config
					.getString(R.MailConfig.PASSWORD));
			// 发送邮件
			transport.sendMessage(this.getMessage(), this.getMessage()
					.getAllRecipients());
			// 关闭发送器
			transport.close();
		} catch (Exception e) {
			throw new MailException(e);
		}
	}

	/**
	 * 
	 * @Title: setFrom
	 * @Description: TODO(发件人)
	 * @param address
	 * @throws MailException
	 */
	public void setFrom(String address) throws MailException {
		try {
			if (address == null) {
				this.getMessage().setFrom(
						config.getString(R.MailConfig.USERNAME));
			} else {
				this.getMessage().setFrom(address);
			}
		} catch (MessagingException e) {
			throw new MailException(e);
		}
	}

	public void setFrom() throws MailException {
		setFrom(null);
	}
}
