package rsj.admin.web.domain.archives;

import java.io.Serializable;
import java.util.Date;

import rsj.admin.web.enums.ArchivesType;

import com.lehecai.core.YesNoStatus;

public class Archives implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6100412628585480536L;
	private Long id;
	private String number;
	private String title;
	private String department;
	private Date createTime;
	private Date updateTime;
	private ArchivesType archivesType;
	private String path;
	private YesNoStatus isDigital;
	private String memo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public YesNoStatus getIsDigital() {
		return isDigital;
	}
	public void setIsDigital(YesNoStatus isDigital) {
		this.isDigital = isDigital;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public ArchivesType getArchivesType() {
		return archivesType;
	}
	public void setArchivesType(ArchivesType archivesType) {
		this.archivesType = archivesType;
	}

}
