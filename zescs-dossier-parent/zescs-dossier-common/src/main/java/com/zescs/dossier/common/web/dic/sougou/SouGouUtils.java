package com.zescs.dossier.common.web.dic.sougou;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zescs.dossier.common.web.commons.CommonsUtils;
import com.zescs.dossier.common.web.util.PinyinUtil;

public final class SouGouUtils {
	/**
	 * 单个文件目录
	 */
	private static final String FILEPATH = "d:/dic/sougou/";
	/**
	 * 合并文件目录
	 */
	private static final String FILEPATH_MERGE = "d:/dic/merge";

	/**
	 * 转换为词库文件
	 * 
	 * @param file
	 * @return
	 */
	public static Boolean changeToDic(File file) {
		BufferedWriter writer = null;
		try {
			File destFile = new File(FILEPATH + "/words-"
					+ PinyinUtil.strFirst2Pinyin(file.getName()) + ".dic");
			if (!destFile.getParentFile().exists()) {
				destFile.getParentFile().mkdirs();
			}
			writer = new BufferedWriter(new FileWriter(destFile));
			List<WordLibrary> words = getWord(file);
			for (WordLibrary word : words) {
				writer.write(word.getWord());
				writer.write("\r\n");
				writer.flush();
			}
			writer.close();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * 批量转换
	 * 
	 * @param file
	 * @return
	 */
	public static Boolean bathChange(File file) {
		try {
			File[] files = file.listFiles();
			for (File f : files) {
				SouGouUtils.changeToDic(f);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @return
	 */
	public static Boolean merge() {
		BufferedWriter writer = null;
		BufferedReader reader = null;
		try {
			File destFile = new File(FILEPATH_MERGE + "/words-sougou-all.dic");
			if (!destFile.getParentFile().exists()) {
				destFile.getParentFile().mkdirs();
			}
			writer = new BufferedWriter(new FileWriter(destFile));
			File file = new File(FILEPATH);
			File[] files = file.listFiles();
			for (File f : files) {
				reader = new BufferedReader(new FileReader(f));
				String temp = null;
				while ((temp = reader.readLine()) != null) {
					writer.write(temp);
					writer.write("\r\n");
					writer.flush();
				}
				// 将当前打开的关闭
				CommonsUtils.close(reader);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			CommonsUtils.close(reader, writer);
		}
	}

	/**
	 * 获取转换后的词汇对象
	 * 
	 * @param file
	 * @throws Exception
	 */
	private static List<WordLibrary> getWord(File file) throws Exception {
		FileInputStream input = new FileInputStream(file);
		byte[] str = new byte[128];
		Map<Integer, String> pyDic = new HashMap<Integer, String>();
		List<WordLibrary> pyAndWord = new ArrayList<WordLibrary>();
		byte[] num;
		int hzPosition = 0;
		input.read(str, 0, 128);

		if (str[4] == 0x44) {
			hzPosition = 0x2628;
		}
		if (str[4] == 0x45) {
			hzPosition = 0x26C4;
		}
		/******************** 字库名称开始 **************************/
		input.getChannel().position(0x130);
		int lenc = input.read(str, 0, 128);
		System.out.println("字库名称:" + toString(str, lenc));

		/******************** 字库名称结束 *************************/

		/******************** 字库类别开始 **************************/
		input.getChannel().position(0x338);
		lenc = input.read(str, 0, 128);
		System.out.println("字库类别:" + toString(str, lenc));

		/******************** 字库类别结束 *************************/

		/******************** 字库信息开始 **************************/
		input.getChannel().position(0x540);
		lenc = input.read(str, 0, 128);
		System.out.println("字库信息:" + toString(str, lenc));

		/******************** 字库信息结束 *************************/

		/******************** 字库示例开始 **************************/
		input.getChannel().position(0xd40);
		lenc = input.read(str, 0, 128);
		System.out.println("字库示例:" + toString(str, lenc));

		/******************** 字库示例结束 *************************/

		input.getChannel().position(0x1540);
		str = new byte[4];
		input.read(str, 0, 4);

		while (true) {
			num = new byte[4];
			input.read(num, 0, 4);
			int mark = num[0] + num[1] * 256;
			str = new byte[128];
			if (num[2] > 0) {
				input.read(str, 0, (num[2]));
				String py = new String(str, 0, num[2]);
				py = py.replaceAll("\0", "");
				pyDic.put(mark, py);
				if (py == "zuo") // 最后一个拼音
				{
					break;
				}
			} else {
				break;
			}
		}
		input.getChannel().position(hzPosition);
		int i = 0, count = 0, samePYcount = 0;
		while (true) {
			num = new byte[4];
			input.read(num, 0, 4);
			samePYcount = num[0] + num[1] * 256;
			count = num[2] + num[3] * 256;
			// 接下来读拼音
			str = new byte[256];
			for (i = 0; i < count; i++) {
				str[i] = (byte) input.read();
			}
			String wordPY = "";
			for (i = 0; i < count / 2; i++) {
				int key = str[i * 2] + str[i * 2 + 1] * 256;
				wordPY += pyDic.get(key) + " ";
			}
			// 接下来读词语
			for (int s = 0; s < samePYcount; s++) // 同音词，使用前面相同的拼音
			{
				num = new byte[2];
				input.read(num, 0, 2);
				int hzBytecount = num[0] + num[1] * 256;
				str = new byte[hzBytecount];
				int len = input.read(str, 0, hzBytecount);
				String word = toString(str, len);
				pyAndWord.add(new WordLibrary(word, wordPY));
				byte[] temp = new byte[12];
				for (i = 0; i < 12; i++) {
					temp[i] = (byte) input.read();
				}
			}
			if (file.length() == input.getChannel().position()) // 判断文件结束
			{
				input.close();
				break;
			}
		}
		return pyAndWord;
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	public static String toString(byte[] data, int len) {
		StringBuffer strb = new StringBuffer();
		for (int ix = 0; ix < len; ix += 2) {
			if (data[ix + 1] <= 0 && data[ix] <= 0) {
				continue;
			}
			int d = data[ix] + data[ix + 1] * 256;
			if (data[ix] <= 0) {
				d = d + 256;
			}
			if (d != 0) {
				strb.append((char) d);
			}
		}
		return strb.toString();
	}

	public static void main(String[] args) {
		bathChange(new File("D:\\dic\\dicsougou"));
		merge();
	}
}
