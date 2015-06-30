package rsj.admin.web.action.report;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rsj.admin.web.action.BaseAction;
import rsj.admin.web.domain.report.Report;
import rsj.admin.web.service.report.ReportService;

import com.lehecai.core.YesNoStatus;
import com.opensymphony.xwork2.Action;

public class ReportSubmitForJsonAction extends BaseAction {
	private static final long serialVersionUID = 2436161530465382824L;
	private Logger logger = LoggerFactory.getLogger(ReportSubmitForJsonAction.class);
	
	private ReportService reportService;
	
	private String peopleName;
	private String telno;
	private String post;
	private String email;
	private String addr;
	private String stitle;
	private String content;
	
	public String handle() {
		logger.info("进入json举报投诉添加");
		int rc = 0;
		String msg = "提交成功";
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject json = new JSONObject();
		Report report = new Report();
		report.setPeopleName(peopleName);
		report.setPeopleAddr(addr);
		report.setPeopleEmail(email);
		report.setPeopleTel(telno);
		report.setPeoplePost(post);
		report.setReportContent(content);
		report.setReportTitle(stitle);
		report.setIsRe(YesNoStatus.NO);
		report.setCreateTime(new Date());
		report.setUpdateTime(new Date());
		reportService.save(report);
		json.put("code", rc);
		json.put("msg", msg);
		writeRs(response, json);
		logger.info("结束json举报投诉添加");
		return Action.NONE;
		
	}
	
	public ReportService getReportService() {
		return reportService;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public String getPeopleName() {
		return peopleName;
	}

	public void setPeopleName(String peopleName) {
		this.peopleName = peopleName;
	}

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getStitle() {
		return stitle;
	}

	public void setStitle(String stitle) {
		this.stitle = stitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
}
