package com.zescs.dossier.common.web.util;

import java.math.BigDecimal;

public final class DataTypeUtils {
	public static double getDouble(int scale, Double source) {
		BigDecimal temp = new BigDecimal(source);
		return temp.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
