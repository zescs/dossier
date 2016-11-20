package com.zescs.dossier.common.web.poi;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.zescs.dossier.common.web.poi.annotation.ExcelField;
import com.zescs.dossier.common.web.poi.domain.ExcelHeader;

/**
 * 
 * @ClassName: ExcelHeaderUtils
 * @Description: TODO()
 * @author 郑建平
 * @date 2015年6月16日 上午9:09:14
 *
 */
public class ExcelHeaderUtils {
	/**
	 * 
	 * @Title: getHeaderMap
	 * @Description: TODO()
	 * @param entityClass
	 * @param row
	 * @return
	 */
	public static Map<Integer, ExcelHeader> getHeaderMap(Class<?> entityClass, Row row) {
		Map<Integer, ExcelHeader> map = new HashMap<Integer, ExcelHeader>();
		List<ExcelHeader> list = getExcelHeader(entityClass);
		for (Cell cell : row) {
			String value = cell.getStringCellValue().trim();
			for (ExcelHeader eh : list) {
				if (eh.getTitle().equals(value)) {
					map.put(cell.getColumnIndex(), eh);
				}
			}
		}
		return map;
	}

	/**
	 * 
	 * @Title: getExcelHeader
	 * @Description: TODO(获取导入导出属性的名称)
	 * @param entityClass
	 * @return
	 */
	public static List<ExcelHeader> getExcelHeader(Class<?> entityClass) {
		List<ExcelHeader> header = new ArrayList<ExcelHeader>();
		try {
			// 获取注解生成表头
			BeanInfo info = Introspector.getBeanInfo(entityClass);
			PropertyDescriptor[] pds = info.getPropertyDescriptors();
			for (PropertyDescriptor pd : pds) {
				Method method = pd.getReadMethod();
				if (method.isAnnotationPresent(ExcelField.class)) {
					ExcelField er = method.getAnnotation(ExcelField.class);
					// 导出属性
					String exportPropertyName = er.exportPropertyName();
					if (StringUtils.isEmpty(exportPropertyName)) {
						exportPropertyName = pd.getName();
					}
					// 导入属性
					String importPropertyName = er.importPropertyName();
					if (StringUtils.isEmpty(importPropertyName)) {
						importPropertyName = pd.getName();
					}
					header.add(new ExcelHeader(er.title(), er.displayOrder(), exportPropertyName, importPropertyName,
							er.isDateField(), er.pattern()));
				}
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return header;
	}
}
