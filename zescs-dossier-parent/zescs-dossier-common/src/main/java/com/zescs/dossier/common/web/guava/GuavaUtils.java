package com.zescs.dossier.common.web.guava;

import java.util.List;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public final class GuavaUtils {
	public static List<String> Splitter(String source, String separator) {
		try {
			if (separator == null) {
				separator = ",";
			}
			List<String> list = Lists.newArrayList(Splitter.on(separator)
					.trimResults().omitEmptyStrings().split(source));
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<String> Splitter(String source) {
		try {
			return Splitter(source, null);
		} catch (Exception e) {
			return null;
		}
	}
}
