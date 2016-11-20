package com.zescs.dossier.common.web.solr;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import org.apache.solr.common.SolrInputDocument;

public final class SolrUtils {

	public static PropertyDescriptor[] getPropertyDescriptors(Class<?> entityClass) {
		try {
			BeanInfo info = Introspector.getBeanInfo(entityClass);
			return info.getPropertyDescriptors();
		} catch (IntrospectionException e) {
		}
		return null;
	}

	public static Boolean remove(String query) {
		try {
			SolrContext.getClient().deleteByQuery(query);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static Boolean removeById(String id) {
		try {
			SolrContext.getClient().deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static Boolean rollback() {
		try {
			SolrContext.getClient().rollback();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static Boolean commit() {
		try {
			SolrContext.getClient().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static Boolean add(SolrInputDocument doc) {
		try {
			SolrContext.getClient().add(doc);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
