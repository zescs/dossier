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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.zescs.dossier.common.web.action.ApplicationServletContext;
import com.zescs.dossier.common.web.commons.CommonsUtils;
import com.zescs.dossier.common.web.date.DateUtils;
import com.zescs.dossier.common.web.poi.domain.ExcelHeader;
import com.zescs.dossier.common.web.poi.domain.FilePrintModel;
import com.zescs.dossier.config.R;

/**
 * 
 * @ClassName: ExcelUtils
 * @Description: TODO(基于反射的Excel工具类)
 * @author 郑建平
 * @date 2015年6月15日 上午3:10:59
 *
 */
@SuppressWarnings("unchecked")
public final class ExcelUtils {
	private static ExcelUtils instance = null;
	/**
	 * 当前锁
	 */
	private static Lock lock = new ReentrantLock();

	private ExcelUtils() {
	}

	public static ExcelUtils getInstance() {
		if (instance == null) {
			try {
				lock.lock();
				if (instance == null) {
					instance = new ExcelUtils();
				}
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}

	public <T> void exportObjectToExcelByTemplate(String templatePath,
			String outPath, Map<String, String> datas, List<T> data,
			Class<?> entityClass, Boolean isSerNum) {
		try {
			ExcelTemplate et = ExcelTemplate.getInstance();
			et.readTemplateByClassPath(templatePath);
			List<ExcelHeader> header = ExcelHeaderUtils
					.getExcelHeader(entityClass);
			Collections.sort(header);
			// 输出表头
			et.createHeader(header, isSerNum);
			// 输出值
			for (T t : data) {
				et.createNewRow();
				for (ExcelHeader eh : header) {
					et.setCellValue(eh, t);
				}
			}
			if (isSerNum) {
				et.insertSerNum(isSerNum);
			}
			if (datas != null) {
				et.replaceFinalDate(datas);
			}
			et.writeToFile(outPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public <T> void exportObjectToExcelByTemplate(InputStream in,
			String outPath, Map<String, String> datas, List<T> data,
			Class<?> entityClass, Boolean isSerNum) {
		try {
			ExcelTemplate et = ExcelTemplate.getInstance();
			et.readTemplateByPath(in);
			List<ExcelHeader> header = ExcelHeaderUtils
					.getExcelHeader(entityClass);
			Collections.sort(header);
			// 输出表头
			et.createHeader(header, isSerNum);
			// 输出值
			for (T t : data) {
				et.createNewRow();
				for (ExcelHeader eh : header) {
					et.createCell(BeanUtils.getProperty(t,
							eh.getExportPropertyName()));
				}
			}
			if (isSerNum) {
				et.insertSerNum(isSerNum);
			}
			if (datas != null) {
				et.replaceFinalDate(datas);
			}
			et.writeToFile(outPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public <T> void exportObjectToExcelByTemplate(String templatePath,
			String outPath, List<T> data, Class<?> entityClass) {
		exportObjectToExcelByTemplate(templatePath, outPath, null, data,
				entityClass, false);
	}

	public <T> void exportObjectToExcelByTemplate(String templatePath,
			String outPath, List<T> data, Class<?> entityClass, boolean isSerNum) {
		exportObjectToExcelByTemplate(templatePath, outPath, null, data,
				entityClass, isSerNum);
	}

	public <T> void exportObjectToExcelByTemplate(InputStream in,
			String outPath, List<T> data, Class<?> entityClass) {
		exportObjectToExcelByTemplate(in, outPath, null, data, entityClass,
				false);
	}

	public <T> FilePrintModel exportObjectToExcelByTemplate(InputStream in,
			Map<String, String> datas, List<T> data, Class<?> entityClass,
			Boolean isSerNum) {
		try {
			ExcelTemplate et = ExcelTemplate.getInstance();
			et.readTemplateByPath(in);
			List<ExcelHeader> header = ExcelHeaderUtils
					.getExcelHeader(entityClass);
			Collections.sort(header);
			// 输出表头
			et.createHeader(header, isSerNum);
			// 输出值
			for (T t : data) {
				et.createNewRow();
				for (ExcelHeader eh : header) {
					et.setCellValue(eh, t);
				}
			}
			if (isSerNum) {
				et.insertSerNum(isSerNum);
			}
			if (datas != null) {
				et.replaceFinalDate(datas);
			}
			return writeTempData(et);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private FilePrintModel writeTempData(ExcelTemplate et)
			throws FileNotFoundException {
		String filePath = ApplicationServletContext.getServletContext()
				.getRealPath(
						R.ConfigMap.Value.TEMP_PATH + "excel/"+System.currentTimeMillis()+"/"+"data.xlsx");
		File file = new File(filePath);
		if(file.exists()){
			file.delete();
		}
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		OutputStream output = new BufferedOutputStream(new FileOutputStream(
				file));
		et.wirteToStream(output);
		CommonsUtils.close(output);
		file = new File(filePath);
		return new FilePrintModel(new FileInputStream(file), file.length());
	}

	public <T> FilePrintModel exportObjectToExcelByTemplate(InputStream in,
			List<T> data, Class<?> entityClass, Boolean isSerNum) {
		return exportObjectToExcelByTemplate(in, null, data, entityClass,
				isSerNum);
	}

	public <T> void exportObjectToExcel(InputStream in, OutputStream out,
			Map<String, String> datas, List<T> data, Class<?> entityClass,
			Boolean isXssf) {
		Workbook wb = handleObj2Excel(data, entityClass, isXssf);
		try {
			wb.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @Title: handleObj2Excel
	 * @Description: TODO()
	 * @param datas
	 * @param entityClass
	 * @param isXssf
	 * @return
	 */
	private <T> Workbook handleObj2Excel(List<T> datas, Class<?> entityClass,
			boolean isXssf) {
		Workbook wb = null;
		try {
			if (isXssf) {
				wb = new XSSFWorkbook();
			} else {
				wb = new HSSFWorkbook();
			}
			Sheet sheet = wb.createSheet();
			Row r = sheet.createRow(0);
			List<ExcelHeader> headers = ExcelHeaderUtils
					.getExcelHeader(entityClass);
			Collections.sort(headers);
			// 写标题
			for (int i = 0; i < headers.size(); i++) {
				r.createCell(i).setCellValue(headers.get(i).getTitle());
			}
			// 写数据
			Object obj = null;
			for (int i = 0; i < datas.size(); i++) {
				r = sheet.createRow(i + 1);
				obj = datas.get(i);
				for (int j = 0; j < headers.size(); j++) {
					r.createCell(j).setCellValue(
							BeanUtils.getProperty(obj, headers.get(j)
									.getExportPropertyName()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wb;
	}

	/**
	 * 
	 * @Title: readExceToObjects
	 * @Description: TODO(根据指定的模板读取excel的数据到数据库中)
	 * @param filePath
	 *            文件路径
	 * @param entityClass
	 *            封装模型对象
	 * @return
	 */
	public <T> List<T> readExceToObjects(String filePath, Class<?> entityClass) {
		return readExceToObjects(filePath, entityClass, null, null);
	}

	/**
	 * 
	 * @Title: readExceToObjects
	 * @Description: TODO(导入excel数据到数据库中)
	 * @param filePath
	 *            文件的路径 基于classPath
	 * @param entityClass
	 *            数据封装模型
	 * @param readLine
	 *            开始读取的行
	 * @param tailLine
	 *            结束的行
	 * @return
	 */
	public <T> List<T> readExceToObjects(String filePath, Class<?> entityClass,
			Integer readLine, Integer tailLine) {
		Workbook work = null;
		try {
			work = WorkbookFactory.create(ExcelUtils.class
					.getResourceAsStream(filePath));
			List<T> result = handlerExcelToObjects(work, entityClass, readLine,
					tailLine);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (work != null) {
				try {
					work.close();
				} catch (IOException e) {
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @Title: readExceToObjects
	 * @Description: TODO(读取excel的数据到数据库中)
	 * @param input
	 *            excel文件所在的流
	 * @param entityClass
	 *            封装的模型对象
	 * @param readLine
	 *            开始的读取的行
	 * @param tailLine
	 *            多少行不是数据
	 * @return
	 */
	public <T> List<T> readExceToObjects(InputStream input,
			Class<?> entityClass, Integer readLine, Integer tailLine) {
		Workbook work = null;
		try {
			work = WorkbookFactory.create(input);
			List<T> result = handlerExcelToObjects(work, entityClass, readLine,
					tailLine);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (work != null) {
				try {
					work.close();
				} catch (IOException e) {
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @Title: readExceToObjects
	 * @Description: TODO(读取excel的数据到数据库中)
	 * @param input
	 *            excel文件所在的流
	 * @param entityClass
	 *            封装的模型对象
	 * @param readLine
	 *            开始的读取的行
	 * @return
	 */
	public <T> List<T> readExceToObjects(InputStream input,
			Class<?> entityClass, Integer readLine) {
		return readExceToObjects(input, entityClass, readLine, null);
	}

	/**
	 * 
	 * @Title: readExceToObjects
	 * @Description: TODO(读取excel的数据到数据库中)
	 * @param input
	 *            excel文件所在的流
	 * @param entityClass
	 *            封装的模型对象
	 * @return
	 */
	public <T> List<T> readExceToObjects(InputStream input, Class<?> entityClass) {
		return readExceToObjects(input, entityClass, null, null);
	}

	/**
	 * 
	 * @Title: handlerExcelToObjects
	 * @Description: TODO()
	 * @param work
	 * @param entityClass
	 * @param readLine
	 * @param tailLine
	 * @return
	 */
	private <T> List<T> handlerExcelToObjects(Workbook work,
			Class<?> entityClass, Integer readLine, Integer tailLine) {
		try {
			Sheet sheet = work.getSheetAt(0);
			// 数据校验
			if (readLine == null) {
				readLine = 0;
			}
			if (tailLine == null) {
				tailLine = 0;
			}
			// 不能小于0
			if (readLine < 0) {
				readLine = 0;
			}
			// 不能大于最后一行
			if (readLine >= sheet.getPhysicalNumberOfRows()) {
				readLine = 0;
			}

			if (tailLine < 0) {
				tailLine = 0;
			}

			if (tailLine > sheet.getPhysicalNumberOfRows()) {
				tailLine = 0;
			}
			List<T> result = new ArrayList<T>();
			// 校验开始的行是否大于了该工作薄最大的行
			if (readLine > sheet.getPhysicalNumberOfRows()) {
				readLine = 0;
			}
			Map<Integer, ExcelHeader> map = ExcelHeaderUtils.getHeaderMap(
					entityClass, sheet.getRow(readLine));
			int start = readLine + 1;
			int end = sheet.getPhysicalNumberOfRows() - tailLine;
			// 结束的行是否大雨了最后一行
			if (end > sheet.getPhysicalNumberOfRows()) {
				// 如果大于了的话默认为最后一行
				end = sheet.getPhysicalNumberOfRows();
			}
			if (end < 0) {
				end = 0;
			}
			for (int i = start; i < end; i++) {
				Row row = sheet.getRow(i);
				Object obj = entityClass.newInstance();
				if (row != null) {
					for (Cell cell : row) {
						if (cell != null) {
							try {
								ExcelHeader header = map.get(cell
										.getColumnIndex());
								if (header.getIsDateField()) {
									String date = getCellValue(cell);
									BeanUtils.setProperty(
											obj,
											header.getImportPropertyName(),
											DateUtils.parseDate(date,
													header.getPattern()));
								} else {
									BeanUtils.setProperty(obj,
											header.getImportPropertyName(),
											getCellValue(cell));
								}
							} catch (Exception e) {
								continue;
							}
						}
					}
					result.add((T) obj);
				}
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String getCellValue(Cell c) {
		String o = null;
		switch (c.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
			o = "";
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			o = String.valueOf(c.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA:
			o = String.valueOf(c.getCellFormula());
			break;
		case Cell.CELL_TYPE_NUMERIC:
			o = String.valueOf(c.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING:
			o = c.getStringCellValue();
			break;
		default:
			o = null;
			break;
		}
		return o;
	}
}
