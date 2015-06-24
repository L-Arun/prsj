package rsj.admin.web.action.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rsj.admin.web.action.BaseAction;
import rsj.admin.web.bean.TreeViewBean;
import rsj.admin.web.bean.UserSessionBean;
import rsj.admin.web.constant.Global;
import rsj.admin.web.domain.user.Menu;
import rsj.admin.web.domain.user.Permission;
import rsj.admin.web.domain.user.PermissionItem;
import rsj.admin.web.domain.user.Role;
import rsj.admin.web.service.user.MenuService;
import rsj.admin.web.service.user.PermissionItemService;
import rsj.admin.web.service.user.PermissionService;
import rsj.admin.web.service.user.RoleService;
import rsj.admin.web.utils.StringUtil;

public class RoleAction extends BaseAction {
	private static final long serialVersionUID = 2436161530465382824L;
	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	private RoleService roleService;
	private MenuService menuService;
	private PermissionService permissionService;
	private PermissionItemService permissionItemService;
	private Role role;
	
	private String func;//用于区分输入的操作
	
	private List<Long> permissions;
	private List<Long> permissionsItem;
	
	private List<Role> roles;
	
	public String handle(){
		logger.info("进入查询角色");
		roles = roleService.list(role);
		return "list";
	}
	
	public String manage() {
		logger.info("进入更新角色信息");
		List<Permission> permList = new ArrayList<Permission>();
		List<PermissionItem> permItemList = new ArrayList<PermissionItem>();
		if (role != null) {
			if (role.getName() == null || "".equals(role.getName())) {
				logger.error("角色名称为空");
				super.setErrorMessage("角色名称不能为空");
				return "failure";
			}
			StringBuffer sb = new StringBuffer("");
			if (permissions != null && permissions.size() != 0) {
				for(Long permissionID:permissions) {
					Permission perm = permissionService.get(permissionID);
					List<String> list = new ArrayList<String>();
					sb.append(permissionID).append("#");
					boolean flag = false;
					if (permissionsItem != null && permissionsItem.size() != 0) {
						PermissionItem pit = new PermissionItem();
						pit.setPermissionID(permissionID);
						List<PermissionItem> pits = permissionItemService.list(pit);
						if (pits != null && pits.size() != 0) {					
							for(Long permissionItemID:permissionsItem) {
								for(PermissionItem p:pits) {
									if (p.getId().toString().equals(permissionItemID.toString())) {
										flag = true;
										list.add(permissionItemID.toString());
										PermissionItem permItem = permissionItemService.get(permissionItemID);
										permItemList.add(permItem);
										sb.append(permissionItemID).append(",");
									}
								}
							}
							if (flag) {
								if (sb.lastIndexOf(",") != -1) {						
									sb.deleteCharAt(sb.lastIndexOf(","));
								}
							}
						}
					}
					perm.setPermissionItemStr(list);
					permList.add(perm);
					if (!flag) {
						if (sb.lastIndexOf("#") != -1) {						
							sb.deleteCharAt(sb.lastIndexOf("#"));
						}
					}
					sb.append("@");
				}
				if (sb.lastIndexOf("@") != -1) {				
					sb.deleteCharAt(sb.lastIndexOf("@"));
				}
			}

			role.setPermission(sb.toString());
			roleService.manage(role);
			//如果admin修改，其他角色的role，会导致admin的权限错误,如果修改的是自己的权限就更新session，如果修改的为其他角色则不更新
			UserSessionBean userSessionBean = (UserSessionBean)super.getSession().get(Global.USER_SESSION);
			if (userSessionBean != null && role.getId() != null && userSessionBean.getRole().getId().longValue() == role.getId().longValue()) {
				userSessionBean.setRole(role);
				userSessionBean.setPermissions(permList);
				userSessionBean.setPermissionItems(permItemList);
			}
		} else {
			logger.error("添加角色错误，提交表单为空");
			super.setErrorMessage("添加角色错误，提交表单不能为空");
			return "failure";
		}
		super.setForwardUrl("/user/role.do");
		logger.info("更新角色信息结束");
		return "success";
	}
	
	/**
	 * 转向添加/修改角色
	 */
	public String input() {
		logger.info("进入输入角色信息");
		if (role != null) {
			if (role.getId() != null) {
				if ("copy".equals(func)) {//复制
					role.setValid(true);//设置有效
					role.setRestriction(true);//设置限制IP
				} else {//修改
					role = roleService.get(role.getId());
				}
			}
		} else {
			role = new Role();
			role.setRestriction(false);
			role.setValid(true);
		}
		return "inputForm";
	}
	
	public String view() {
		logger.info("进入查看角色详情");
		if (role != null && role.getId() != null) {
			role = roleService.get(role.getId());
		} else {
			logger.error("查看角色详情，编码为空");
			super.setErrorMessage("查看角色详情，编码不能为空");
			return "failure";
		}
		logger.info("查看角色详情结束");
		return "view";
	}
	
	public String del() {
		logger.info("进入删除角色");
		if (role != null && role.getId() != null) {
			role = roleService.get(role.getId());
			roleService.del(role);
		} else {
			logger.error("删除角色， 编码为空");
			super.setErrorMessage("删除角色，编码不能为空");
			return "failure";
		}
		super.setForwardUrl("/user/role.do");
		logger.info("删除角色结束");
		return "forward";
	}
	
	public void findPermissions() {
		logger.info("进入查询权限");
		HttpServletResponse response = ServletActionContext.getResponse();
		String permissionStr = null;
		String[] bothStr = null;
		String[] perms = null;
		String[] permsItem = null;
		int itemLength = 0;//permissionItem数组的长度
		if (role != null && role.getId() != null) {
			permissionStr = roleService.get(role.getId()).getPermission();
		}
		if (permissionStr != null) {//修改角色
			bothStr = StringUtil.split(permissionStr, '@');
			if (bothStr != null) {
				perms = new String[bothStr.length];
				for(int j = 0;j < bothStr.length;j ++) {
					String[] tmpStr = StringUtil.split(bothStr[j], '#');
					perms[j] = tmpStr[0];
					if (tmpStr.length > 1) {				
						String[] tmpItemStr = StringUtil.split(tmpStr[1], ',');
						itemLength += tmpItemStr.length;//计算permissionItem数组的长度
					}
				}
				permsItem = new String[itemLength];
				int itemIndex = 0;//permsItem的索引值
				for(int k = 0;k < bothStr.length;k ++) {//将子权限存入permissionItem数组
					String[] tmpStr = StringUtil.split(bothStr[k], '#');
					if (tmpStr.length > 1) {
						String[] tmpItemStr = StringUtil.split(tmpStr[1], ',');
						for(int m = 0;m < tmpItemStr.length;m ++) {
							permsItem[m + itemIndex] = tmpItemStr[m];
						}
						itemIndex += tmpItemStr.length;
					}
				}
			}
		}
		List<TreeViewBean> list = new ArrayList<TreeViewBean>();
		for(Menu menu: menuService.list(null)) {
			TreeViewBean treeViewBean = new TreeViewBean();
			treeViewBean.setId(menu.getId().toString());
			treeViewBean.setText(menu.getName());
			treeViewBean.setHasChildren(false);
			treeViewBean.setClasses("");
			
			List<TreeViewBean> list2 = new ArrayList<TreeViewBean>();
			Permission permissionID = new Permission();
			permissionID.setMenuID(menu.getId());
			for(Permission permission: permissionService.list(permissionID)) {
				TreeViewBean treeViewBean2 = new TreeViewBean();
				treeViewBean2.setId(permission.getId().toString());
				if (perms != null) {
					boolean permsFlag = false;
					for(String permID:perms) {
						if (permission.getId().toString().equals(permID)) {
							treeViewBean2.setText("<input type='checkbox' name='permissions' value='"+ permission.getId().toString() +"' checked='checked' class='perms'>&nbsp;" + permission.getName());
							permsFlag = true;
							break;
						}
					}
					if (!permsFlag) {
						treeViewBean2.setText("<input type='checkbox' name='permissions' value='"+ permission.getId().toString() +"' class='perms'>&nbsp;" + permission.getName());
					}
				} else {				
					treeViewBean2.setText("<input type='checkbox' name='permissions' value='"+ permission.getId().toString() +"' class='perms'>&nbsp;" + permission.getName());
				}
				treeViewBean2.setHasChildren(false);
				treeViewBean2.setClasses("");
				
				List<TreeViewBean> list3 = new ArrayList<TreeViewBean>();
				PermissionItem permissionItemID = new PermissionItem();
				permissionItemID.setPermissionID(permission.getId());
				for(PermissionItem permissionItem: permissionItemService.list(permissionItemID)) {
					TreeViewBean treeViewBean3 = new TreeViewBean();
					treeViewBean3.setId(permissionItem.getId().toString());
					if (permsItem != null) {
						boolean permsItemFlag = false;
						for(String permItemID:permsItem) {
							if (permissionItem.getId().toString().equals(permItemID)) {
								treeViewBean3.setText("<input type='checkbox' name='permissionsItem' value='"+ permissionItem.getId().toString() +"' checked='checked' class='perms'>&nbsp;" + permissionItem.getName());
								permsItemFlag = true;
								break;
							}
						}
						if (!permsItemFlag) {
							treeViewBean3.setText("<input type='checkbox' name='permissionsItem' value='"+ permissionItem.getId().toString() +"' class='perms'>&nbsp;" + permissionItem.getName());
						}
					} else {						
						treeViewBean3.setText("<input type='checkbox' name='permissionsItem' value='"+ permissionItem.getId().toString() +"' class='perms'>&nbsp;" + permissionItem.getName());
					}
					treeViewBean3.setHasChildren(false);
					treeViewBean3.setClasses("");
					list3.add(treeViewBean3);
				}
				if (list3.size() == 0) {
					list3 = null;
				}
				treeViewBean2.setChildren(list3);
				list2.add(treeViewBean2);
			}
			treeViewBean.setChildren(list2);
			list.add(treeViewBean);
		}
		JSONArray ja = JSONArray.fromObject(list);
		System.out.println(ja.toString());
		PrintWriter out = null;
		response.setContentType("text/json; charset=utf-8");
		try {
			out = response.getWriter();		
			out.println(ja.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public RoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public PermissionService getPermissionService() {
		return permissionService;
	}
	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}
	public PermissionItemService getPermissionItemService() {
		return permissionItemService;
	}
	public void setPermissionItemService(PermissionItemService permissionItemService) {
		this.permissionItemService = permissionItemService;
	}
	public MenuService getMenuService() {
		return menuService;
	}
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	public String getFunc() {
		return func;
	}
	public void setFunc(String func) {
		this.func = func;
	}

	public List<Long> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Long> permissions) {
		this.permissions = permissions;
	}
	public List<Long> getPermissionsItem() {
		return permissionsItem;
	}
	public void setPermissionsItem(List<Long> permissionsItem) {
		this.permissionsItem = permissionsItem;
	}
}
