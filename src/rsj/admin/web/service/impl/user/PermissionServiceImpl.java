package rsj.admin.web.service.impl.user;

import java.util.List;

import rsj.admin.web.dao.user.PermissionDao;
import rsj.admin.web.domain.user.Permission;
import rsj.admin.web.service.user.PermissionService;

public class PermissionServiceImpl implements PermissionService {
	/* (non-Javadoc)
	 * @see com.lehecai.admin.web.service.impl.PermissionService#add()
	 */
	private PermissionDao permissionDao;
	
	public void manage(Permission permission){
		permissionDao.merge(permission);
	}

	public PermissionDao getPermissionDao() {
		return permissionDao;
	}

	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}

	@Override
	public List<Permission> list(Permission permission) {
		// TODO Auto-generated method stub
		return permissionDao.list(permission);
	}

	@Override
	public Permission get(Long ID) {
		// TODO Auto-generated method stub
		return permissionDao.get(ID);
	}

	@Override
	public void del(Permission permission) {
		// TODO Auto-generated method stub
		permissionDao.del(permission);
	}
}
