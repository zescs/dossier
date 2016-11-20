package com.zescs.dossier.core.permissions.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.zescs.dossier.common.service.impl.BaseServiceBean;
import com.zescs.dossier.common.web.util.StringUtils;
import com.zescs.dossier.config.R;
import com.zescs.dossier.core.permissions.service.MenuService;
import com.zescs.dossier.model.permissions.bean.Menu;
import com.zescs.dossier.repository.permissions.mapper.MenuMapper;

@Service("menuService")
public class MenuServiceBean extends BaseServiceBean<Menu> implements MenuService {
	private static final Logger LOG = LoggerFactory.getLogger(MenuServiceBean.class);
	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private StringRedisTemplate redisTemplate;

	@Override
	public Boolean add(Menu record) {
		return null;
	}

	@Override
	public Boolean update(Menu record) {
		return null;
	}

	@Override
	public Menu findById(Integer id) {
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Menu> loadUserMenus(Integer userId, Integer menuId) {
		// 此处需要加上缓存
		// id 策略 用户id+menuId
		String key = String.valueOf(userId);
		if (menuId != null) {
			key = userId + "-" + menuId;
		}
		BoundHashOperations<String, String, String> bs = redisTemplate.boundHashOps(R.Cache.Key.DOSSIER_PERMISSIONS_MENU);
		List<Menu> menus = null;
		try {
			//获取该key存在的值
			String menus_str = bs.get(key);
			if (StringUtils.isNotEmpty(menus_str)) {
				menus = JSON.parseArray(menus_str, Menu.class);
				LOG.info("从缓存中获取数据成功");
			}
		} catch (Exception e) {
			LOG.error("redis获取数据转换失败");
		}
		if (menus == null || menus.isEmpty()) {
			if (menuId == null) {
				// 获取一级菜单
				menus = menuMapper.queryListParentIsNuLLByUserId(userId);
			} else {
				// 获取二级菜单
				Map<String, Integer> map = Maps.newHashMap();
				map.put("userId", userId);
				map.put("menuId", menuId);
				// 检测层级
				menus = menuMapper.queryListByParentIdBAndUserId(map);
			}
			bs.put(key, JSON.toJSONString(menus));
			LOG.info("数据写入缓存中");
		}
		return menus;
	}

}
