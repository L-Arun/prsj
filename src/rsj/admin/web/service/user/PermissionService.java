package rsj.admin.web.service.user;

import java.util.List;

import rsj.admin.web.domain.user.Permission;

public interface PermissionService {

	void manage(Permission permission);
	List<Permission> list(Permission permission); 
	Permission get(Long ID);
	void del(Permission permission);
}