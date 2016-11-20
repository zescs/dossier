package com.zescs.dossier.common.aspect.util;

public enum OperationType {
	QUERY, UPDATE, DELETE, INSERT,IMPORT,EXPORT;
	
	public String getText(){
		switch (this) {
		case QUERY:
			return "查询";
		case UPDATE:
			return "修改";
		case DELETE:
			return "删除";
		case INSERT:
			return "添加";
		case IMPORT:
			return "导入";
		case EXPORT:
			return "导出";
		default:
			return "未知";
		}
	}
}
