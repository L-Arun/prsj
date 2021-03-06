package rsj.admin.web.domain.user;

import java.io.Serializable;

public class PermissionItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6100412628585480536L;
	private Long id;
	private String name;
	private Long permissionID;
	private Integer orderView;
	private String methodName;
	private boolean valid;
	private String memo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long iD) {
		id = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Integer getOrderView() {
		if(orderView == null){
			orderView = 0;
		}
		return orderView;
	}
	public void setOrderView(Integer orderView) {
		this.orderView = orderView;
	}
	public Long getPermissionID() {
		return permissionID;
	}
	public void setPermissionID(Long permissionID) {
		this.permissionID = permissionID;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
}
