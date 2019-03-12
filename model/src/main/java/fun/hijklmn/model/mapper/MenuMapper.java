package fun.hijklmn.model.mapper;

import java.util.List;

import fun.hijklmn.model.pojo.Menu;

public interface MenuMapper extends CommonMapper<Menu> {

	List<Menu> listMenu(Menu menu);

}