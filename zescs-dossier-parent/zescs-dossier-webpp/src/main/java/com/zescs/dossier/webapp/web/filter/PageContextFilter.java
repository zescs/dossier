package com.zescs.dossier.webapp.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.commons.lang3.StringUtils;

import com.zescs.dossier.common.pagination.PaginContext;
import com.zescs.dossier.common.pagination.impl.DefaultPageableBean;
import com.zescs.dossier.config.R;

@WebFilter(urlPatterns={"/*"})
public class PageContextFilter implements Filter {
	private Integer pageSize;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		try {
			pageSize = R.ConfigMap.Value.DEFAULT_PAGESIZE;
		} catch (NumberFormatException e) {
			pageSize =R.App.DEFAULT_PAGE_SIZE;
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Integer pageIndex = 1;
		try {
			if (!StringUtils.isEmpty(request.getParameter(R.ConfigMap.Key.PAGEINDEX))) {
				pageIndex = Integer.parseInt(request.getParameter(R.ConfigMap.Key.PAGEINDEX));
				pageIndex += 1;
			}
			if (!StringUtils.isEmpty(request.getParameter(R.ConfigMap.Key.PAGESIZE))) {
				pageSize = Integer.parseInt(request.getParameter(R.ConfigMap.Key.PAGESIZE));
			}
			PaginContext.setPageable(new DefaultPageableBean(pageIndex, pageSize));
			chain.doFilter(request, response);
		} catch (Exception e) {
			
		} finally {
			PaginContext.remove();
		}
	}

	@Override
	public void destroy() {
		PaginContext.remove();
	}

}
