package rsj.admin.web.action.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rsj.admin.web.action.BaseAction;
import rsj.admin.web.domain.user.Permission;
import rsj.admin.web.domain.user.PermissionItem;
import rsj.admin.web.service.user.PermissionItemService;
import rsj.admin.web.service.user.PermissionService;

public class PermissionItemAction extends BaseAction {
	private static final long serialVersionUID = 2436161530465382824L;
	private Logger logger = LoggerFactory.getLogger(PermissionAction.class);
	
	private PermissionItemService permissionItemService;
	private PermissionService permissionService;
	
	private PermissionItem permissionItem;
	private Permission permission;
	
	private List<PermissionItem> permissionItems;
	
	public String handle() {
		logger.info("进入查询子权限列表");
		if (permissionItem != null && permissionItem.getPermissionID() != null) {
			permission = permissionService.get(permissionItem.getPermissionID());
		}
		permissionItems = permissionItemService.list(permissionItem);
		return "list";
	}
	
	public String manage() {
		logger.info("进入更新子权限");
		if (permissionItem != null) {
			if (permissionItem.getName() == null || "".equals(permissionItem.getName())) {
				logger.error("权限名称为空");
				super.setErrorMessage("权限名称不能为空");
				return "failure";
			}
			if (permissionItem.getMethodName() == null || "".equals(permissionItem.getMethodName())) {
				logger.error("权限action名称为空");
				super.setErrorMessage("权限action名称不能为空");
				return "failure";
			}
			permissionItemService.manage(permissionItem);
		} else {
			logger.error("添加权限错误，提交表单不能为空");
			super.setErrorMessage("添加权限错误，提交表单不能为空");
			return "failure";
		}
		super.setForwardUrl("/user/permissionItem.do?permissionItem.permissionID="+permissionItem.getPermissionID());
		logger.info("更新子权限结束");
		return "success";
	}
	
	public String input() {
		logger.info("进入输入子权限信息");
		if (permissionItem != null) {
			if (permissionItem.getId() != null) {
				permissionItem = permissionItemService.get(permissionItem.getId());
			} else {
				permissionItem.setValid(true);
				permissionItem.setOrderView(0);
			}
		}
		return "inputForm";
	}
	
	public String view() {
		logger.info("进入查看子权限详情");
		if (permissionItem != null && permissionItem.getId() != null) {
			permissionItem = permissionItemService.get(permissionItem.getId());
		} else {
			logger.error("查看子权限详情，编码为空");
			super.setErrorMessage("查看子权限详情，编码不能为空");
			return "failure";
		}
		logger.info("查看子权限详情结束");
		return "view";
	}
	
	public String del() {
		logger.info("进入删除子权限");
		if (permissionItem != null && permissionItem.getId() != null) {
			permissionItem = permissionItemService.get(permissionItem.getId());
			permissionItemService.del(permissionItem);
		} else {
			logger.error("删除子权限，编码为空");
			super.setErrorMessage("删除子权限，编码不能为空");
			return "failure";
		}
		super.setForwardUrl("/user/permissionItem.do");
		logger.info("删除子权限结束");
		return "forward";
	}
	
	
	public PermissionItemService getPermissionItemService() {
		return permissionItemService;
	}

	public void setPermissionItemService(PermissionItemService permissionItemService) {
		this.permissionItemService = permissionItemService;
	}

	public PermissionItem getPermissionItem() {
		return permissionItem;
	}

	public void setPermissionItem(PermissionItem permissionItem) {
		this.permissionItem = permissionItem;
	}

	public List<PermissionItem> getPermissionItems() {
		return permissionItems;
	}

	public void setPermissionItems(List<PermissionItem> permissionItems) {
		this.permissionItems = permissionItems;
	}

	public PermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}
}
