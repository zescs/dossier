package com.zescs.dossier.common.web.poi;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.zescs.dossier.common.web.action.ApplicationServletContext;
import com.zescs.dossier.common.web.commons.CommonsUtils;
import com.zescs.dossier.common.web.poi.domain.FilePrintModel;
import com.zescs.dossier.common.web.poi.util.WordType;
import com.zescs.dossier.config.R;

public final class WordUtils {

	// 返回Docx中需要替换的特殊字符，没有重复项
	// 推荐传入正则表达式参数"\\$\\{[^{}]+\\}"
	public ArrayList<String> getReplaceElementsInWord(String filePath, String regex) {
		String[] p = filePath.split("\\.");
		if (p.length > 0) {// 判断文件有无扩展名
			// 比较文件扩展名
			if (p[p.length - 1].equalsIgnoreCase("doc")) {
				ArrayList<String> al = new ArrayList<>();
				File file = new File(filePath);
				HWPFDocument document = null;
				try {
					InputStream is = new FileInputStream(file);
					document = new HWPFDocument(is);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				Range range = document.getRange();
				String rangeText = range.text();
				CharSequence cs = rangeText.subSequence(0, rangeText.length());
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(cs);
				int startPosition = 0;
				while (matcher.find(startPosition)) {
					if (!al.contains(matcher.group())) {
						al.add(matcher.group());
					}
					startPosition = matcher.end();
				}
				return al;
			} else if (p[p.length - 1].equalsIgnoreCase("docx")) {
				ArrayList<String> al = new ArrayList<>();
				XWPFDocument document = null;
				try {
					document = new XWPFDocument(POIXMLDocument.openPackage(filePath));
				} catch (IOException e) {
					e.printStackTrace();
				}
				// 遍历段落
				Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
				while (itPara.hasNext()) {
					XWPFParagraph paragraph = (XWPFParagraph) itPara.next();
					String paragraphString = paragraph.getText();
					CharSequence cs = paragraphString.subSequence(0, paragraphString.length());
					Pattern pattern = Pattern.compile(regex);
					Matcher matcher = pattern.matcher(cs);
					int startPosition = 0;
					while (matcher.find(startPosition)) {
						if (!al.contains(matcher.group())) {
							al.add(matcher.group());
						}
						startPosition = matcher.end();
					}
				}
				// 遍历表
				Iterator<XWPFTable> itTable = document.getTablesIterator();
				while (itTable.hasNext()) {
					XWPFTable table = (XWPFTable) itTable.next();
					int rcount = table.getNumberOfRows();
					for (int i = 0; i < rcount; i++) {
						XWPFTableRow row = table.getRow(i);
						List<XWPFTableCell> cells = row.getTableCells();
						for (XWPFTableCell cell : cells) {
							String cellText = "";
							cellText = cell.getText();
							CharSequence cs = cellText.subSequence(0, cellText.length());
							Pattern pattern = Pattern.compile(regex);
							Matcher matcher = pattern.matcher(cs);
							int startPosition = 0;
							while (matcher.find(startPosition)) {
								if (!al.contains(matcher.group())) {
									al.add(matcher.group());
								}
								startPosition = matcher.end();
							}
						}
					}
				}
				return al;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	// 替换word中需要替换的特殊字符
	@SuppressWarnings("resource")
	public static boolean replaceAndGenerateWord(String srcPath, String destPath, Map<String, String> map) {
		String[] sp = srcPath.split("\\.");
		String[] dp = destPath.split("\\.");
		if ((sp.length > 0) && (dp.length > 0)) {// 判断文件有无扩展名
			// 比较文件扩展名
			if (sp[sp.length - 1].equalsIgnoreCase("docx")) {
				try {
					XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(srcPath));
					// 替换段落中的指定文字
					Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
					while (itPara.hasNext()) {
						XWPFParagraph paragraph = (XWPFParagraph) itPara.next();
						List<XWPFRun> runs = paragraph.getRuns();
						for (int i = 0; i < runs.size(); i++) {
							String oneparaString = runs.get(i).getText(runs.get(i).getTextPosition());
							for (Map.Entry<String, String> entry : map.entrySet()) {
								oneparaString = oneparaString.replace(entry.getKey(), entry.getValue());
							}
							runs.get(i).setText(oneparaString, 0);
						}
					}

					// 替换表格中的指定文字
					Iterator<XWPFTable> itTable = document.getTablesIterator();
					while (itTable.hasNext()) {
						XWPFTable table = (XWPFTable) itTable.next();
						int rcount = table.getNumberOfRows();
						for (int i = 0; i < rcount; i++) {
							XWPFTableRow row = table.getRow(i);
							List<XWPFTableCell> cells = row.getTableCells();
							for (XWPFTableCell cell : cells) {
								String cellTextString = cell.getText();
								for (Entry<String, String> e : map.entrySet()) {
									if (cellTextString.contains(e.getKey()))
										cellTextString = cellTextString.replace(e.getKey(), e.getValue());
								}
								cell.removeParagraph(0);
								cell.setText(cellTextString);
							}
						}
					}
					FileOutputStream outStream = null;
					outStream = new FileOutputStream(destPath);
					document.write(outStream);
					outStream.close();
					return true;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}

			} else
			// doc只能生成doc，如果生成docx会出错
			if ((sp[sp.length - 1].equalsIgnoreCase("doc")) && (dp[dp.length - 1].equalsIgnoreCase("doc"))) {
				HWPFDocument document = null;
				try {
					document = new HWPFDocument(new FileInputStream(srcPath));
					Range range = document.getRange();
					for (Map.Entry<String, String> entry : map.entrySet()) {
						range.replaceText(entry.getKey(), entry.getValue());
					}
					FileOutputStream outStream = null;
					outStream = new FileOutputStream(destPath);
					document.write(outStream);
					outStream.close();
					return true;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					return false;
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static FilePrintModel replaceAndGenerateWord(InputStream in, WordType type, Map<String, String> map)
			throws IOException {
		if (type.equals(WordType.DOC)) {
			HWPFDocument document =new HWPFDocument(in);
			Range range = document.getRange();
			for (Map.Entry<String, String> entry : map.entrySet()) {
				range.replaceText(entry.getKey(), entry.getValue());
			}
			return writeTempData(document);
		} else {
			XWPFDocument document = new XWPFDocument(in);
			// 替换段落中的指定文字
			Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
			while (itPara.hasNext()) {
				XWPFParagraph paragraph = (XWPFParagraph) itPara.next();
				List<XWPFRun> runs = paragraph.getRuns();
				for (int i = 0; i < runs.size(); i++) {
					String oneparaString = runs.get(i).getText(runs.get(i).getTextPosition());
					for (Map.Entry<String, String> entry : map.entrySet()) {
						oneparaString = oneparaString.replace(entry.getKey(), entry.getValue());
					}
					runs.get(i).setText(oneparaString, 0);
				}
			}

			// 替换表格中的指定文字
			Iterator<XWPFTable> itTable = document.getTablesIterator();
			while (itTable.hasNext()) {
				XWPFTable table = (XWPFTable) itTable.next();
				int rcount = table.getNumberOfRows();
				for (int i = 0; i < rcount; i++) {
					XWPFTableRow row = table.getRow(i);
					List<XWPFTableCell> cells = row.getTableCells();
					for (XWPFTableCell cell : cells) {
						String cellTextString = cell.getText();
						for (Entry<String, String> e : map.entrySet()) {
							if (cellTextString.contains(e.getKey()))
								cellTextString = cellTextString.replace(e.getKey(), e.getValue());
						}
						cell.removeParagraph(0);
						cell.setText(cellTextString);
					}
				}
			}
			return writeTempData(document);
		}
	}

	private static FilePrintModel writeTempData(XWPFDocument document) throws IOException {
		String filePath = ApplicationServletContext.getServletContext().getRealPath(R.ConfigMap.Value.TEMP_PATH + "word/archives/"+System.currentTimeMillis()+"/"+ "data.doc");
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
		}
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		OutputStream output = new BufferedOutputStream(new FileOutputStream(file));
		document.write(output);
		CommonsUtils.close(output,document);
		file = new File(filePath);
		return new FilePrintModel(new FileInputStream(file), file.length());
	}
	
	private static FilePrintModel writeTempData(HWPFDocument document) throws IOException {
		String filePath = ApplicationServletContext.getServletContext().getRealPath(R.ConfigMap.Value.TEMP_PATH + "word/archives/"+System.currentTimeMillis()+"/"+ "data.doc");
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
		}
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		OutputStream output = new BufferedOutputStream(new FileOutputStream(file));
		document.write(output);
		CommonsUtils.close(output);
		file = new File(filePath);
		return new FilePrintModel(new FileInputStream(file), file.length());
	}
	
	public static FilePrintModel printCover(InputStream in, Map<String, String> root) throws IOException {
		return replaceAndGenerateWord(in, WordType.DOC, root);
	}
}