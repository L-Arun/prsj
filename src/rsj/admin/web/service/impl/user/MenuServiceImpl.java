package rsj.admin.web.service.impl.user;

import java.util.List;

import rsj.admin.web.dao.user.MenuDao;
import rsj.admin.web.domain.user.Menu;
import rsj.admin.web.service.user.MenuService;

public class MenuServiceImpl implements MenuService {
	/* (non-Javadoc)
	 * @see com.lehecai.admin.web.service.impl.MenuService#add()
	 */
	private MenuDao menuDao;
	
	public void manage(Menu menu){
		menuDao.merge(menu);
	}

	public MenuDao getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	@Override
	public List<Menu> list(Menu menu) {
		// TODO Auto-generated method stub
		return menuDao.list(menu);
	}

	@Override
	public Menu get(Long ID) {
		// TODO Auto-generated method stub
		return menuDao.get(ID);
	}

	@Override
	public void del(Menu menu) {
		// TODO Auto-generated method stub
		menuDao.del(menu);
	}
}
