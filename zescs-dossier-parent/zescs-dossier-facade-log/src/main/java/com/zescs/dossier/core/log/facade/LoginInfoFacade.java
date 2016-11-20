package com.zescs.dossier.core.log.facade;

import com.zescs.dossier.model.log.bean.LoginInfo;

/**
 * 
 * @ClassName: LoginInfoFacade
 * @Description: TODO()
 * @author zescs.com 郑建平
 * @date 2016年11月3日 下午8:27:26
 *
 */
public interface LoginInfoFacade {
	/**
	 * 添加登录信息
	 * @param entity
	 * @return
	 */
	Boolean add(LoginInfo entity);
}
