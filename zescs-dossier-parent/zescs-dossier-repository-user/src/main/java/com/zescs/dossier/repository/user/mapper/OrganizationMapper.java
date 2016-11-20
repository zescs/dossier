package com.zescs.dossier.repository.user.mapper;

import com.zescs.dossier.model.user.bean.Organization;
/**
 * 
* @ClassName: OrganizationMapper 
* @Description: TODO() 
* @author zescs.com 郑建平 
* @date 2016年11月1日 下午9:39:25 
*
 */
public interface OrganizationMapper {

	int insert(Organization record);

	Organization findById(Integer organizationId);

	int update(Organization record);
}