package com.zescs.dossier.webapp.web.controller;

import java.io.ByteArrayOutputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.code.kaptcha.Producer;
import com.zescs.dossier.common.web.json.Message;
import com.zescs.dossier.config.R;
import com.zescs.dossier.webapp.web.interceptor.login.annotation.LoginPass;

@Controller
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SessionAttributes({ R.ProJectConfig.SECURITY_CODE, R.ProJectConfig.FILE_PROGRESS })
public class CommonController extends BaseController {
	@Resource
	private Producer producer;

	@RequestMapping(value = "/securityCode", method = RequestMethod.GET)
	@LoginPass
	public ResponseEntity<byte[]> securityCode(Model model) throws Exception {
		String securityCode = producer.createText();
		model.addAttribute(R.ProJectConfig.SECURITY_CODE, securityCode.toLowerCase());
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(producer.createImage(securityCode), "jpeg", os);
		HttpHeaders hh = new HttpHeaders();
		hh.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(os.toByteArray(), hh, HttpStatus.OK);
	}

	@RequestMapping(value = "/createFormCode", method = RequestMethod.POST)
	@ResponseBody
	public Message createFormCode() {
		return Message.newInstance(getFlag(), getMessage(), getFormCode());
	}
}
