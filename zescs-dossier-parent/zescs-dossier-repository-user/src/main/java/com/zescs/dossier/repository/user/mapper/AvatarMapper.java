package com.zescs.dossier.repository.user.mapper;

import com.zescs.dossier.model.user.bean.Avatar;

/**
 * 
 * @ClassName: AvatarMapper
 * @Description: TODO()
 * @author zescs.com 郑建平
 * @date 2016年11月1日 下午9:39:35
 *
 */
public interface AvatarMapper {

	int insert(Avatar record);

	Avatar findById(Integer avatarId);

	int update(Avatar record);
}