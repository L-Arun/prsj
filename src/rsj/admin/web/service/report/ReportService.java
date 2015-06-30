package rsj.admin.web.service.report;

import java.util.Date;
import java.util.List;

import rsj.admin.web.bean.PageBean;
import rsj.admin.web.domain.report.Report;

import com.lehecai.core.YesNoStatus;

public interface ReportService {
	
	/**
	 * 
	 * @param 
	 * @return
	 */
	List<Report> list(Long reportId, String peopleName, String peopleTel, String peoplePost, String peopleEmail, String peopleAddr, String reportContent, 
			String reportTitle, Date beginCreateTime, Date endCreateTime,Date beginUpdateTime, Date endUpdateTime, YesNoStatus isRe, String reName, String reStyle,
			String memo, PageBean pageBean);
	/**
	 * 多条件查询分页信息
	 * @param number 文号
	 * @param title 标题
	 * @param department 部门
	 * @param beginCreateTime 开始创建时间
	 * @param endCreateTime 结束创建时间
	 * @param beginUpdateTime 开始更新时间
	 * @param endUpdateTime 结束更新时间
	 * @param archivesType	档案类型
	 * @param path 保存路径
	 * @param isDigital 是否有电子版
	 * @param memo 备注
	 * @param pageBean
	 * @return
	 */
	PageBean getPageBean(Long reportId, String peopleName, String peopleTel, String peoplePost, String peopleEmail, String peopleAddr, String reportContent, 
			String reportTitle, Date beginCreateTime, Date endCreateTime,Date beginUpdateTime, Date endUpdateTime, YesNoStatus isRe, String reName, String reStyle,
			String memo, PageBean pageBean);
	/**
	 * 
	 * @param id 
	 * @return
	 */
	Report get(Long id);
	/**
	 * 添加
	 * @param news
	 * @return
	 */
	void save(Report report);
	/**
	 * 修改
	 * @param news
	 * @return
	 */
	void merge(Report report);
}
