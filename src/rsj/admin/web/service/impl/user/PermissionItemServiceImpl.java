package rsj.admin.web.service.impl.user;

import java.util.List;

import rsj.admin.web.dao.user.PermissionItemDao;
import rsj.admin.web.domain.user.Permission;
import rsj.admin.web.domain.user.PermissionItem;
import rsj.admin.web.service.user.PermissionItemService;

public class PermissionItemServiceImpl implements PermissionItemService {
	/* (non-Javadoc)
	 * @see com.lehecai.admin.web.service.impl.PermissionItemService#add()
	 */
	private PermissionItemDao permissionItemDao;
	
	public void manage(PermissionItem permissionItem){
		permissionItemDao.merge(permissionItem);
	}

	public PermissionItemDao getPermissionItemDao() {
		return permissionItemDao;
	}

	public void setPermissionItemDao(PermissionItemDao permissionItemDao) {
		this.permissionItemDao = permissionItemDao;
	}

	@Override
	public List<PermissionItem> list(PermissionItem permissionItem) {
		// TODO Auto-generated method stub
		return permissionItemDao.list(permissionItem);
	}
	@Override
	public List<PermissionItem> list(Permission permission) {
		// TODO Auto-generated method stub
		return permissionItemDao.list(permission);
	}

	@Override
	public PermissionItem get(Long ID) {
		// TODO Auto-generated method stub
		return permissionItemDao.get(ID);
	}

	@Override
	public void del(PermissionItem permissionItem) {
		// TODO Auto-generated method stub
		permissionItemDao.del(permissionItem);
	}
}
