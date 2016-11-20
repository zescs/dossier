package com.zescs.dossier.common.web.poi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.zescs.dossier.common.web.date.DateUtils;
import com.zescs.dossier.common.web.poi.domain.ExcelHeader;
import com.zescs.dossier.common.web.util.StringUtils;

public final class ExcelTemplate {
	/**
	 * 填充数据的行
	 */
	private static final String DATA_LINE = "#datas";
	// 默认样式
	private static final String DEFAULT_STYLS = "defaultStyles";
	// 标题样式
	private static final String TITLE_STYLE = "titleStyles";
	private static final String STYLES = "styles";
	private static final String SERNUM = "sernum";
	/**
	 * 工作簿
	 */
	private Workbook work = null;
	private static ExcelTemplate instance;
	// 初始化的行的下标
	private Integer initCellIndex;
	// 初始化的列的下标
	private Integer initRowIndex;
	// 当前列的下标
	private Integer currentCellIndex;
	// 当前行的下标
	private Integer currentRowIndex;
	// 当前工作簿
	private Sheet sheet;
	// 当前行
	private Row currentRow;
	// 最后一行
	private Integer lastRowIndex;
	// 默认样式
	private CellStyle defaultCellStyle;
	// 标题列默认样式
	private CellStyle titleCellStyle;
	// 标题行高
	private float titleLineHeight;
	// 行高
	private float lineHeight;
	// 序号的列
	private Integer serCellIndex;
	// 存储某一列的样式
	private Map<Integer, CellStyle> styles = new HashMap<Integer, CellStyle>();
	/**
	 * 当前锁
	 */
	private static Lock lock = new ReentrantLock();

	public float getTitleLineHeight() {
		return titleLineHeight;
	}

	public void setTitleLineHeight(float titleLineHeight) {
		this.titleLineHeight = titleLineHeight;
	}

	private ExcelTemplate() {
		if (instance != null) {
			throw new RuntimeException("对象已经创建不能重复创建!!!!");
		}
	}

	public static ExcelTemplate getInstance() {
		if (instance == null) {
			try {
				lock.lock();
				if (instance == null) {
					instance = new ExcelTemplate();
				}
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}

	// 读取相应的模板文件
	public void readTemplateByClassPath(String path) {
		try {
			work = WorkbookFactory.create(ExcelTemplate.class.getResourceAsStream(path));
			initTemplate();
		} catch (InvalidFormatException e) {
			throw new RuntimeException("模板文件格式错误");
		} catch (IOException e) {
			throw new RuntimeException("模板文件不存在");
		}
	}

	/**
	 * 根据输入流来读可以使用网络路径
	 * 
	 * @param inputStream
	 */
	public void readTemplateByPath(InputStream inputStream) {
		try {
			work = WorkbookFactory.create(inputStream);
			initTemplate();
		} catch (InvalidFormatException e) {
			throw new RuntimeException("模板文件格式错误");
		} catch (IOException e) {
			throw new RuntimeException("模板文件不存在");
		}
	}

	/**
	 * 写到文件中
	 * 
	 * @param filepath
	 */
	public void writeToFile(String filepath) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filepath);
			work.write(fos);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("写入的文件不存在");
		} catch (IOException e) {
			throw new RuntimeException("写入数据失败:" + e.getMessage());
		} finally {
			try {
				if (fos != null)
					fos.close();
				work.close();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * 将文件写到某个输出流中
	 * 
	 * @param os
	 */
	public void wirteToStream(OutputStream os) {
		try {
			work.write(os);
		} catch (IOException e) {
			throw new RuntimeException("写入流失败:" + e.getMessage());
		}
	}

	private void initTemplate() {
		sheet = work.getSheetAt(0);
		initConfigData();
		lastRowIndex = sheet.getLastRowNum();
		currentRow = sheet.createRow(currentRowIndex);

	}

	private void initConfigData() {
		boolean findData = false;
		boolean hasSer = false;
		for (Row row : sheet) {
			if (!findData) {
				for (Cell cell : row) {
					if (cell.getCellType() != Cell.CELL_TYPE_STRING) {
						continue;
					}
					String cellValue = cell.getStringCellValue();
					if (!hasSer) {
						if (cellValue != null && cellValue.trim().length() != 0 && !cellValue.equals("NULL")
								&& cellValue.equals(SERNUM)) {
							serCellIndex = cell.getColumnIndex();
							hasSer = true;
						}
					}
					if (cellValue != null && cellValue.trim().length() != 0 && !cellValue.equals("NULL")
							&& cellValue.equals(DATA_LINE)) {
						initCellIndex = cell.getColumnIndex();
						initRowIndex = row.getRowNum();
						currentCellIndex = initCellIndex;
						currentRowIndex = initRowIndex;
						defaultCellStyle = cell.getCellStyle();
						lineHeight = row.getHeightInPoints();
						findData = true;
						initStyles();
						break;
					}
				}
			}
		}
		if (!hasSer) {
			initSerNum();
		}
	}

	private void initSerNum() {
		boolean findSerNum = false;
		for (Row row : sheet) {
			if (!findSerNum) {
				for (Cell cell : row) {
					if (cell.getCellType() != CellType.NUMERIC.getCode()) {
						continue;
					}
					String cellValue = cell.getStringCellValue();
					if (cellValue != null && cellValue.trim().length() != 0 && !cellValue.equals("NULL")
							&& cellValue.equals(SERNUM)) {
						serCellIndex = cell.getColumnIndex();
						findSerNum = true;
					}
				}
			}
		}
	}

	private void initStyles() {
		boolean findData = false;
		styles = new HashMap<Integer, CellStyle>();
		for (Row row : sheet) {
			if (!findData) {
				for (Cell cell : row) {
					if (cell.getCellType() != Cell.CELL_TYPE_STRING) {
						continue;
					}
					String cellValue = cell.getStringCellValue();
					if (cellValue != null && cellValue.trim().length() != 0 && !cellValue.equals("NULL")
							&& cellValue.equals(DEFAULT_STYLS)) {
						defaultCellStyle = cell.getCellStyle();
					}
					if (cellValue != null && cellValue.trim().length() != 0 && !cellValue.equals("NULL")
							&& cellValue.equals(STYLES)) {
						styles.put(cell.getColumnIndex(), cell.getCellStyle());
					}
				}
			}
		}

		for (Row row : sheet) {
			if (!findData) {
				for (Cell cell : row) {
					if (cell.getCellType() != Cell.CELL_TYPE_STRING) {
						continue;
					}
					String cellValue = cell.getStringCellValue();
					if (cellValue != null && cellValue.trim().length() != 0 && !cellValue.equals("NULL")
							&& cellValue.equals(TITLE_STYLE)) {
						titleCellStyle = cell.getCellStyle();
						titleLineHeight = row.getHeightInPoints();
					}
				}
			}
		}
	}

	// 创建列
	public void createCell(String value) {
		Cell cell = currentRow.createCell(currentCellIndex);
		cell.setCellValue(value);
		setCellStyle(cell);
		currentCellIndex++;
	}

	/**
	 * 
	 * @Title: createTitleCell
	 * @Description: TODO(创建标题列)
	 * @param value
	 */
	public void createTitleCell(String value) {
		Cell cell = currentRow.createCell(currentCellIndex);
		cell.setCellValue(value);
		cell.setCellStyle(titleCellStyle);
		currentCellIndex++;
	}

	public void createCell(String value, int index) {
		Cell cell = currentRow.createCell(index);
		cell.setCellValue(value);
		setCellStyle(cell);
	}

	public void createCell(int value) {
		Cell cell = currentRow.createCell(currentCellIndex);
		cell.setCellValue((int) value);
		setCellStyle(cell);
		currentCellIndex++;
	}

	public void createCell(Date value) {
		Cell cell = currentRow.createCell(currentCellIndex);
		cell.setCellValue((Date) value);
		setCellStyle(cell);
		currentCellIndex++;
	}

	public void createCell(double value) {
		Cell cell = currentRow.createCell(currentCellIndex);
		cell.setCellValue((double) value);
		setCellStyle(cell);
		currentCellIndex++;
	}

	public void createCell(Boolean value) {
		Cell cell = currentRow.createCell(currentCellIndex);
		cell.setCellValue((Boolean) value);
		setCellStyle(cell);
		currentCellIndex++;
	}

	// 创建行
	public void createNewRow() {
		// 存在最后一行
		if (lastRowIndex > currentRowIndex && currentRowIndex != initRowIndex) {
			sheet.shiftRows(currentRowIndex, lastRowIndex - 1, 1, true, true);
			lastRowIndex++;
		}
		currentRow = sheet.createRow(currentRowIndex);
		currentRow.setHeightInPoints(lineHeight);
		currentRowIndex++;
		currentCellIndex = initCellIndex;
		lastRowIndex++;
	}

	// 创建行
	public void createNewRow(float lineHeight) {
		// 存在最后一行
		if (lastRowIndex > currentRowIndex && currentRowIndex != initRowIndex) {
			sheet.shiftRows(currentRowIndex, lastRowIndex - 1, 1, true, true);
			lastRowIndex++;
		}
		currentRow = sheet.createRow(currentRowIndex);
		currentRow.setHeightInPoints(lineHeight);
		currentRowIndex++;
		currentCellIndex = initCellIndex;
		lastRowIndex++;
	}

	public void replaceFinalDate(Map<String, String> map) {
		for (Row row : sheet) {
			for (Cell cell : row) {
				if (cell.getCellType() != Cell.CELL_TYPE_STRING) {
					continue;
				}
				String cellValue = cell.getStringCellValue();
				if (cellValue.startsWith("#")) {
					String key = cellValue.substring(1);
					if (map.containsKey(key)) {
						cell.setCellValue(map.get(key));
					}
				}
			}
		}
	}

	private void setCellStyle(Cell cell) {
		// 设置为文本格式
		cell.setCellType(Cell.CELL_TYPE_STRING);
		if (styles.containsKey(currentCellIndex)) {
			cell.setCellStyle(styles.get(currentCellIndex));
		} else {
			cell.setCellStyle(defaultCellStyle);
		}
	}

	public void insertSerNum() {
		insertSerNum(false);
	}

	public void insertSerNum(Boolean isSerNum) {
		int beginRowIndex = initRowIndex;
		if (isSerNum) {
			beginRowIndex += 1;
		}
		int index = 1;
		for (int i = beginRowIndex; i < currentRowIndex; i++) {
			Row row = sheet.getRow(i);
			if (row != null) {
				Cell cell = row.createCell(serCellIndex);
				setCellStyle(cell);
				if (cell != null) {
					cell.setCellValue(index);
					index++;
				}
			}
		}
	}

	public void createHeader(List<ExcelHeader> header) {
		createHeader(header, false);
	}

	public <T> void setCellValue(ExcelHeader eh, T entity) {
		String result = "";
		try {
			result = BeanUtils.getProperty(entity, eh.getExportPropertyName());
			if (eh.getIsDateField()) {
				// 格式化日期
				result = DateUtils.format(result, eh.getPattern());
			}
			createCell(result);
		} catch (Exception e) {
			createCell("");
		}
	}

	public void setCellValue(String value) {
		try {
			createCell(value);
		} catch (Exception e) {
			createCell("");
		}
	}

	/**
	 * 
	 * @Title: createHeader
	 * @Description: TODO(创建标题行)
	 * @param header
	 * @param isSerNum
	 */
	public void createHeader(List<ExcelHeader> header, Boolean isSerNum) {
		createNewRow(titleLineHeight);
		if (isSerNum) {
			if (serCellIndex == null) {
				serCellIndex = 0;
			}
			createCell("序号", serCellIndex);
		}
		for (ExcelHeader eh : header) {
			createTitleCell(eh.getTitle());
		}
	}

	public Boolean createArchivesHeader(List<ExcelHeader> header, Boolean createSerial) {
		createNewRow(titleLineHeight);
		if (createSerial) {
			if (serCellIndex == null) {
				serCellIndex = 0;
			}
			createCell("序号", serCellIndex);
		}
		for (ExcelHeader eh : header) {
			createTitleCell(eh.getTitle());
		}
		return true;
	}

	/**
	 * 设置页眉
	 * 
	 * @param center
	 * @param left
	 * @param right
	 * @return
	 */
	public Boolean setHeader(String center, String left, String right) {
		Header header = sheet.getHeader();
		if (StringUtils.isNotEmpty(center)) {
			header.setCenter(center);
		}
		if (StringUtils.isNotEmpty(left)) {
			header.setCenter(left);
		}
		if (StringUtils.isNotEmpty(right)) {
			header.setCenter(right);
		}
		HSSFHeader.fontSize((short) 16);
		return true;
	}

}
