package rsj.admin.web.action.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rsj.admin.web.action.BaseAction;
import rsj.admin.web.bean.UserSessionBean;
import rsj.admin.web.constant.Global;
import rsj.admin.web.domain.user.Menu;
import rsj.admin.web.domain.user.Permission;
import rsj.admin.web.domain.user.Role;
import rsj.admin.web.domain.user.User;
import rsj.admin.web.service.user.MenuService;
import rsj.admin.web.service.user.PermissionItemService;
import rsj.admin.web.service.user.PermissionService;
import rsj.admin.web.service.user.RoleService;
import rsj.admin.web.service.user.UserService;
import rsj.admin.web.utils.CaptchaServiceSingleton;
import rsj.admin.web.utils.StringUtil;


import com.lehecai.core.util.CoreHttpUtils;
import com.octo.captcha.service.CaptchaServiceException;

public class LoginAction extends BaseAction {
	private static final long serialVersionUID = -8830679912602886965L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private UserService userService;
	private RoleService roleService;
	private PermissionService permissionService;
	private PermissionItemService permissionItemService;
	private MenuService menuService;
	
	private User user;
	
	private String userName;
	private String password;
	private String verifyCode;

	@SuppressWarnings("unchecked")
	public String handle() {
		logger.info("进入验证登录");
		if (userName == null || "".equals(userName)) {
			logger.error("用户名为空");
			super.setErrorMessage("用户名不能为空");
			return "index";
		}
		if (password == null || "".equals(password)) {
			logger.error("密码为空");
			super.setErrorMessage("密码不能为空");
			return "index";
		}
		if (verifyCode == null || "".equals(verifyCode)) {
			logger.error("验证码为空");
			super.setErrorMessage("验证码不能为空");
			return "index";
		}
		Boolean isVerifyCodeRight = Boolean.FALSE;
		HttpServletRequest request = ServletActionContext.getRequest();
		String captchaId = null;
		logger.info("request.getSession(false): {}",request.getSession(false));
		if (request.getSession(false) == null) {
			captchaId = request.getSession(true).getId();
		} else {
			captchaId = request.getSession(false).getId();
		}
		try {
			isVerifyCodeRight = CaptchaServiceSingleton.getInstance()
					.validateResponseForID(captchaId, verifyCode);
		} catch (CaptchaServiceException e) {
			logger.error("验证码超时");
			super.setErrorMessage("验证码超时,请重新登录");
			return "index";
		}
		if (!isVerifyCodeRight) {
			logger.error("验证码错误");
			super.setErrorMessage("验证码错误");
			return "index";
		}
		User user = userService.login(userName, password);
		if (user == null) {
			logger.error("用户名或密码错误");
			super.setErrorMessage("用户名或密码错误");
			return "index";
		}
		
		//ip限定判断
		Role role = roleService.get(user.getRoleID());
		if (role.isRestriction()) {//限定ip时，进行有效ip段验证
			String remoteIp = getRemoteIp(ServletActionContext.getRequest());
			if (!matchingIp(role.getRestrictionIp(), remoteIp)) {
				logger.error("IP地址无效");
				super.setErrorMessage("您的IP地址无效");
				return "index";
			}
			
		}
		
		if (user.getLoginTime() != null) {
			user.setLastLoginTime(user.getLoginTime());
		}
		user.setLoginTime(new Date());
		userService.manage(user);
		//创建userSessionBean
		UserSessionBean userSessionBean = new UserSessionBean();
		//setUser
		userSessionBean.setUser(user);
		
		//serRole
		userSessionBean.setRole(role);
		
		List<Permission> permList = new ArrayList<Permission>();
		List<Menu> menuList = new ArrayList<Menu>();
		//拆分权限字段
		String permissions = role.getPermission();
		if (permissions == null || "".equals(permissions)) {
			logger.error("您所拥有的角色没有任何权限");
			super.setErrorMessage("您所拥有的角色没有任何权限，请联系管理员");
			return "index";
		}
		String[] rootNode = StringUtil.split(permissions, '@');
		Set<Long> tmpMenuId = new HashSet<Long>();
		for (String permStr: rootNode) {
			String[] permNode = StringUtil.split(permStr, '#');
			//添加主权限
			Permission tmpPermission = permissionService.get(Long.valueOf(permNode[0]));
			if (permNode.length > 1) {
				List<String> list = new ArrayList<String>();
				String[] permItemNode = StringUtil.split(permNode[1], ',');
				for(String permItemStr : permItemNode) {
					list.add(permItemStr);
				}
				tmpPermission.setPermissionItemStr(list);
			}
			permList.add(tmpPermission);
			tmpMenuId.add(tmpPermission.getMenuID());
		}
		//添加菜单
		for (Long menuId : tmpMenuId) {
			Menu menu = menuService.get(menuId);
			menuList.add(menu);
		}
		userSessionBean.setPermissions(permList);
		userSessionBean.setMenus(menuList);
		super.getSession().put(Global.USER_SESSION, userSessionBean);
		super.setForwardUrl("/main.do");
		logger.info("验证登录结束");
		return "forward";
	}
	
	//ip段匹配
	private boolean matchingIp(String restrictionIp, String remoteIp) {
		//有效ip段或请求ip不存在
		if (restrictionIp == null || remoteIp == null) {
			return false;
		}
		
		String[] restrictionIpArray = restrictionIp.trim().split(",");
		String[] remoteIpItem = remoteIp.trim().split("\\.");
		if (restrictionIpArray.length > 0) {
			for (String restrictionIpSegment : restrictionIpArray) {
				String[] restrictionIpItem = restrictionIpSegment.trim().split("\\.");
				if (restrictionIpItem.length == 4 && remoteIpItem.length == 4) {
					boolean flag = true;
					for (int i = 0; i < 4; i++) {
						if (!restrictionIpItem[i].equals("*") && !restrictionIpItem[i].equals(remoteIpItem[i])) {
							flag = false;
							break;
						}
					}
					if (flag) {
						return true;
					}
				}
			}
		}
		
		
		return false;
	}

	//获取访问者IP
	private String getRemoteIp(HttpServletRequest request) {
		String[] clientIPArray = CoreHttpUtils.getClientIPArray(request);
		if (clientIPArray != null && clientIPArray.length > 0) {
			return clientIPArray[0];
		}
		return null;
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
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
}
