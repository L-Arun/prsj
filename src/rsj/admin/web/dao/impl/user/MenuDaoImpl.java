package rsj.admin.web.dao.impl.user;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import rsj.admin.web.dao.user.MenuDao;
import rsj.admin.web.domain.user.Menu;

public class MenuDaoImpl extends HibernateDaoSupport implements MenuDao {

	@Override
	public void merge(Menu menu) {
		// TODO Auto-generated method stub
		getHibernateTemplate().merge(menu);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> list(Menu menu) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("from Menu u ");
		sb.append(" order by u.orderView desc,u.id");
		return getHibernateTemplate().find(sb.toString());
	}

	@Override
	public Menu get(Long ID) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(Menu.class, ID);
	}

	@Override
	public void del(Menu menu) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(menu);
	}

}
