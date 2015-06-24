package rsj.admin.web.bean;

import java.io.Serializable;
import java.util.List;

import rsj.admin.web.domain.user.Menu;
import rsj.admin.web.domain.user.Permission;
import rsj.admin.web.domain.user.PermissionItem;
import rsj.admin.web.domain.user.Role;
import rsj.admin.web.domain.user.User;



public class UserSessionBean implements Serializable {

	/**
	 * @author arun
	 */
	private static final long serialVersionUID = -3764402465865930790L;
	private User user;
	private Role role;
	private List<Menu> menus;
	private List<Permission> permissions;
	private List<PermissionItem> permissionItems;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	public List<PermissionItem> getPermissionItems() {
		return permissionItems;
	}
	public void setPermissionItems(List<PermissionItem> permissionItems) {
		this.permissionItems = permissionItems;
	}
}
