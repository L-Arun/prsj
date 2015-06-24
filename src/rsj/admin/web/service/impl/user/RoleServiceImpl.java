package rsj.admin.web.service.impl.user;

import java.util.List;

import rsj.admin.web.dao.user.RoleDao;
import rsj.admin.web.domain.user.Role;
import rsj.admin.web.service.user.RoleService;

public class RoleServiceImpl implements RoleService {
	
	private RoleDao roleDao;
	
	public void manage(Role role){
		roleDao.merge(role);
	}

	/**
	 * 查询所有角色
	 */
	@Override
	public List<Role> list(Role role) {
		return roleDao.list(role);
	}

	@Override
	public Role get(Long ID) {
		return roleDao.get(ID);
	}

	@Override
	public void del(Role role) {
		roleDao.del(role);
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

}
