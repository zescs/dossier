package com.zescs.dossier.common.web.mail;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * 
 * @ClassName: MailAddress
 * @Description: TODO()
 * @author 郑建平
 * @date 2015年1月5日 下午10:33:40
 *
 */
public class MailAddress {
	private List<Address> address = new ArrayList<Address>();

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public MailAddress() {
	}

	public MailAddress(String address) {
		try {
			this.address.add(new InternetAddress(address));
		} catch (AddressException e) {
		}

	}

	public void add(String address) {
		try {
			this.address.add(new InternetAddress(address));
		} catch (AddressException e) {
		}
	}
}
