package com.zescs.dossier.common.web.date;

import com.zescs.dossier.config.R;


/**
 * 
 * @ClassName: DatePattern
 * @Description: TODO(部分日期格式化字符串)
 * @author sjth:郑建平
 * @date 2014年5月9日 上午9:53:02
 * 
 * @mappyby t_DatePattern
 */
public interface DatePattern {
	/**
	 * 格式化到天
	 */
	String FORMAT_DAY = "yyyy-MM-dd";
	/**
	 * 格式化24小时格式
	 */
	String FORMAT_DEFAULT = "yyyy-MM-dd";
	/**
	 * 
	 */
	String FORMAT_YY = "yyyy";
	/**
	 * 
	 */
	String FORMAT_MONTH = "yyyy-MM";
	/**
	 * 文件格式
	 */
	String FILE_FORMAT = R.SystemConfig.FILE_SEP + "yyyy"
			+ R.SystemConfig.FILE_SEP + "MM" + R.SystemConfig.FILE_SEP + "dd";
	/**
	 * 一天的毫秒数
	 */
	long DD = 1000 * 24 * 60 * 60;
	/**
	 * 一小时的毫秒数
	 */
	long HH = 1000 * 60 * 60;
	/**
	 * 一分钟的毫秒数
	 */
	long MM = 1000 * 60;
	/**
	 * 一秒
	 */
	long SS = 1000;
}
