package com.zescs.dossier.common.web.poi.domain;

/**
 * 
 * @ClassName: ExcelHeader
 * @Description: TODO(存储头信息)
 * @author 郑建平
 * @date 2015年6月16日 上午9:02:03
 *
 */
public class ExcelHeader implements Comparable<ExcelHeader> {
	/**
	 * excel的标题名称
	 */
	private String title;
	/**
	 * 每一个标题的顺序
	 */
	private int order;
	/**
	 * 导出属性
	 */
	private String exportPropertyName;
	/**
	 * 导入属性名称
	 */
	private String importPropertyName;
	/**
	 * 是否是日期字段
	 */
	private Boolean isDateField;
	/**
	 * 格式化字符串
	 */
	private String pattern;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getExportPropertyName() {
		return exportPropertyName;
	}

	public void setExportPropertyName(String exportPropertyName) {
		this.exportPropertyName = exportPropertyName;
	}

	public String getImportPropertyName() {
		return importPropertyName;
	}

	public void setImportPropertyName(String importPropertyName) {
		this.importPropertyName = importPropertyName;
	}

	public Boolean getIsDateField() {
		return isDateField;
	}

	public void setIsDateField(Boolean isDateField) {
		this.isDateField = isDateField;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public int compareTo(ExcelHeader o) {
		return order > o.order ? 1 : (order < o.order ? -1 : 0);
	}

	public ExcelHeader(String title, int order) {
		this.title = title;
		this.order = order;
	}

	public ExcelHeader(String title, int order, String exportPropertyName, String importPropertyName) {
		this.title = title;
		this.order = order;
		this.exportPropertyName = exportPropertyName;
		this.importPropertyName = importPropertyName;
	}

	public ExcelHeader(String title, int order, String exportPropertyName, String importPropertyName,
			Boolean isDateField, String pattern) {
		this(title, order, exportPropertyName, importPropertyName);
		this.isDateField = isDateField;
		this.pattern = pattern;
	}
	
	
}
