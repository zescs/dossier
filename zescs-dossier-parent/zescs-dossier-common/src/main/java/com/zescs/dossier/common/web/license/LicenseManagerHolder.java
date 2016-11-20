package com.zescs.dossier.common.web.license;

import de.schlichtherle.license.LicenseManager;
import de.schlichtherle.license.LicenseParam;

/**
 * LicenseManager容器类
 * 
 * @author melina
 */
public class LicenseManagerHolder {

	private static LicenseManager licenseManager;

	public static synchronized LicenseManager getLicenseManager(
			LicenseParam licenseParams) {
		if (licenseManager == null) {
			licenseManager = new LicenseManager(licenseParams);
		}
		return licenseManager;
	}
}