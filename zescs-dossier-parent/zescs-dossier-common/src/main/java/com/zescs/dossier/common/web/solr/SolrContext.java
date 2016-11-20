package com.zescs.dossier.common.web.solr;

import org.apache.solr.client.solrj.impl.HttpSolrClient;

public class SolrContext {
	private static HttpSolrClient client=null;

	public static HttpSolrClient getClient() {
		return client;
	}

	public static void initClient(String baseURL) {
		if (client == null) {
			client = new HttpSolrClient(baseURL);
			client.setAllowCompression(true);
			client.setSoTimeout(3000); // socket read timeout  
			client.setConnectionTimeout(1000);  
			client.setDefaultMaxConnectionsPerHost(1000);  
			client.setMaxTotalConnections(10);  
			client.setFollowRedirects(false); // defaults to false  
		}
	}
}
