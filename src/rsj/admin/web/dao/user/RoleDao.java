package rsj.admin.web.dao.user;

import java.util.List;

import rsj.admin.web.domain.user.Role;

public interface RoleDao {
	void merge(Role role);
	/**
	 * 查询所有角色
	 */
	List<Role> list(Role role);
	Role get(Long ID);
	void del(Role role);
}