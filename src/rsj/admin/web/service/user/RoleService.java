package rsj.admin.web.service.user;

import java.util.List;

import rsj.admin.web.domain.user.Role;

public interface RoleService {

	void manage(Role role);
	/**
	 * 查询所有角色
	 */
	List<Role> list(Role role); 
	Role get(Long ID);
	void del(Role role);
}