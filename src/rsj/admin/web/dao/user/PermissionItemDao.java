package rsj.admin.web.dao.user;

import java.util.List;

import rsj.admin.web.domain.user.Permission;
import rsj.admin.web.domain.user.PermissionItem;

public interface PermissionItemDao {
	void merge(PermissionItem permissionItem);
	List<PermissionItem> list(PermissionItem permissionItem);
	PermissionItem get(Long ID);
	void del(PermissionItem permissionItem);
	List<PermissionItem> list(Permission permission);
}