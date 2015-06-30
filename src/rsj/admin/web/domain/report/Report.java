package rsj.admin.web.domain.report;

import java.io.Serializable;
import java.util.Date;

import com.lehecai.core.YesNoStatus;

public class Report implements Serializable {

	/**
	 * 举报投诉
	 */
	private static final long serialVersionUID = -6100412628585480536L;
	
	private Long reportId;
	private String peopleName;
	private String peopleTel;
	private String peoplePost;
	private String peopleEmail;
	private String peopleAddr;
	private String reportContent;
	private String reportTitle;
	private Date createTime;
	private Date updateTime;
	private YesNoStatus isRe;
	private String reName;
	private String reContent;
	private String reStyle;
	private String memo;
	public Long getReportId() {
		return reportId;
	}
	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}
	public String getPeopleName() {
		return peopleName;
	}
	public void setPeopleName(String peopleName) {
		this.peopleName = peopleName;
	}
	public String getPeopleTel() {
		return peopleTel;
	}
	public void setPeopleTel(String peopleTel) {
		this.peopleTel = peopleTel;
	}
	public String getPeoplePost() {
		return peoplePost;
	}
	public void setPeoplePost(String peoplePost) {
		this.peoplePost = peoplePost;
	}
	public String getPeopleEmail() {
		return peopleEmail;
	}
	public void setPeopleEmail(String peopleEmail) {
		this.peopleEmail = peopleEmail;
	}
	public String getPeopleAddr() {
		return peopleAddr;
	}
	public void setPeopleAddr(String peopleAddr) {
		this.peopleAddr = peopleAddr;
	}
	public String getReportContent() {
		return reportContent;
	}
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	public String getReportTitle() {
		return reportTitle;
	}
	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
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
	public YesNoStatus getIsRe() {
		return isRe;
	}
	public void setIsRe(YesNoStatus isRe) {
		this.isRe = isRe;
	}
	public String getReName() {
		return reName;
	}
	public void setReName(String reName) {
		this.reName = reName;
	}
	public String getReContent() {
		return reContent;
	}
	public void setReContent(String reContent) {
		this.reContent = reContent;
	}
	public String getReStyle() {
		return reStyle;
	}
	public void setReStyle(String reStyle) {
		this.reStyle = reStyle;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

}
