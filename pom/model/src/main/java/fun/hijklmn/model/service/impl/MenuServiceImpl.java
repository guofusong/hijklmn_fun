package fun.hijklmn.model.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import fun.hijklmn.model.mapper.MenuMapper;
import fun.hijklmn.model.pojo.Menu;
import fun.hijklmn.model.service.IMenuService;

@Service("menuService")
public class MenuServiceImpl implements IMenuService {

	@Resource
	private MenuMapper menuMapper;

	@Override
	public List<Menu> listMenu(Menu menu) {
		return menuMapper.listMenu(menu);
	}

}
