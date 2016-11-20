package com.zescs.dossier.webapp.web.converter;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;

public class AppPropertyEditorRegistrar implements PropertyEditorRegistrar {

	@Override
	public void registerCustomEditors(PropertyEditorRegistry registry) {
		// 2.去除参数两边的空格;
		registry.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}
}
