package rsj.admin.web.service.impl.report;

import java.util.Date;
import java.util.List;

import com.lehecai.core.YesNoStatus;

import rsj.admin.web.bean.PageBean;
import rsj.admin.web.dao.report.ReportDao;
import rsj.admin.web.domain.report.Report;
import rsj.admin.web.service.report.ReportService;

public class ReportServiceImpl implements ReportService {

	private ReportDao reportDao;
	@Override
	public Report get(Long id) {
		// TODO Auto-generated method stub
		return reportDao.get(id);
	}

	@Override
	public PageBean getPageBean(Long reportId, String peopleName,
			String peopleTel, String peoplePost, String peopleEmail,
			String peopleAddr, String reportContent, String reportTitle,
			Date beginCreateTime, Date endCreateTime, Date beginUpdateTime,
			Date endUpdateTime, YesNoStatus isRe, String reName,
			String reStyle, String memo, PageBean pageBean) {
		// TODO Auto-generated method stub
		return reportDao.getPageBean(reportId, peopleName, peopleTel, peoplePost, peopleEmail, peopleAddr, reportContent, reportTitle, beginCreateTime, endCreateTime, beginUpdateTime, endUpdateTime, isRe, reName, reStyle, memo, pageBean);
	}

	@Override
	public List<Report> list(Long reportId, String peopleName,
			String peopleTel, String peoplePost, String peopleEmail,
			String peopleAddr, String reportContent, String reportTitle,
			Date beginCreateTime, Date endCreateTime, Date beginUpdateTime,
			Date endUpdateTime, YesNoStatus isRe, String reName,
			String reStyle, String memo, PageBean pageBean) {
		// TODO Auto-generated method stub
		return reportDao.list(reportId, peopleName, peopleTel, peoplePost, peopleEmail, peopleAddr, reportContent, reportTitle, beginCreateTime, endCreateTime, beginUpdateTime, endUpdateTime, isRe, reName, reStyle, memo, pageBean);
	}

	@Override
	public void merge(Report report) {
		// TODO Auto-generated method stub
		reportDao.merge(report);
	}

	@Override
	public void save(Report report) {
		// TODO Auto-generated method stub
		reportDao.save(report);
	}

	public ReportDao getReportDao() {
		return reportDao;
	}

	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}

}
