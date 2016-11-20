package com.zescs.dossier.model.permissions.domain;

public enum FunctionType {
	FUN, ARCHIVES;
	
	public String getText(){
		switch (this) {
		case FUN:
			return "功能菜单";
		default:
			return "档案类型菜单";
		}
	}
}
