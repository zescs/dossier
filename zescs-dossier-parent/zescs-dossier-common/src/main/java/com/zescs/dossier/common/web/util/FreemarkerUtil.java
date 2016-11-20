package com.zescs.dossier.common.web.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Path;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.io.output.FileWriterWithEncoding;

import com.zescs.dossier.config.R;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

/**
 * 
 * @ClassName: FreemarkerUtil
 * @Description: TODO()
 * @author 郑建平
 * @date 2015年1月5日 下午3:35:57
 *
 */
public final class FreemarkerUtil {
	private static FreemarkerUtil util;
	private static Configuration cfg;
	private static Version version = Configuration.VERSION_2_3_23;
	private static final String ENCODING = "UTF-8";
	/**
	 * 当前锁
	 */
	private static Lock lock = new ReentrantLock();

	private FreemarkerUtil() {
	}

	public static FreemarkerUtil getInstance(String pname) {
		if (util == null) {
			try {
				lock.lock();
				if (util == null) {
					cfg = new Configuration(version);
					cfg.setClassForTemplateLoading(FreemarkerUtil.class, pname);
					cfg.setDefaultEncoding(ENCODING);
					util = new FreemarkerUtil();
				}
			} finally {
				lock.unlock();
			}
		}
		return util;
	}

	public static FreemarkerUtil getInstance() {
		if (util == null) {
			try {
				lock.lock();
				if (util == null) {
					cfg = new Configuration(version);
					cfg.setClassForTemplateLoading(FreemarkerUtil.class,
							R.SystemConfig.TEMPLATE);
					cfg.setDefaultEncoding(ENCODING);
					util = new FreemarkerUtil();
				}
			} finally {
				lock.unlock();
			}
		}
		return util;
	}

	private Template getTemplate(String fname) {
		try {
			return cfg.getTemplate(fname);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过标准输出流输出模板的结果
	 * 
	 * @param root
	 *            数据对象
	 * @param fname
	 *            模板文件
	 */
	public void sprint(Object root, String fname) {
		try {
			Template template = getTemplate(fname);
			template.process(root, new PrintWriter(System.out));
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 基于文件的输出
	 * 
	 * @param root
	 * @param fname
	 * @param outpath
	 */
	public Boolean out(Object root, String fname, String outpath) {
		try {
			Template template = getTemplate(fname);
			template.process(root,
					new FileWriterWithEncoding(outpath, ENCODING));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean out(Object root, String fname, Path path) {
		try {
			Template template = getTemplate(fname);
			template.process(root, new FileWriterWithEncoding(path
					.toAbsolutePath().toString(), ENCODING));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 基于文件的输出
	 * 
	 * @param root
	 * @param fname
	 * @param outpath
	 */
	public Boolean out(String fname, String outpath) {
		try {
			Template template = getTemplate(fname);
			template.process(null,
					new FileWriterWithEncoding(outpath, ENCODING));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 基于文件的输出
	 * 
	 * @param root
	 * @param fname
	 * @param outpath
	 */
	public Boolean out(String fname,Path path) {
		try {
			Template template = getTemplate(fname);
			template.process(null,
					new FileWriterWithEncoding(path.toAbsolutePath().toString(), ENCODING));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @param root
	 *            数据对象
	 * @param fname
	 *            模板文件
	 */
	public String getValueAsString(Object root, String fname) {
		try {
			StringWriter sw = new StringWriter();
			Template template = getTemplate(fname);
			template.process(root, new PrintWriter(sw));
			return sw.toString();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
