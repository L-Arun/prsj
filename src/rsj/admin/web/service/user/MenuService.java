package rsj.admin.web.service.user;

import java.util.List;

import rsj.admin.web.domain.user.Menu;

public interface MenuService {

	void manage(Menu menu);
	List<Menu> list(Menu menu); 
	Menu get(Long ID);
	void del(Menu menu);
}