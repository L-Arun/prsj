package rsj.admin.web.dao.impl.report;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import rsj.admin.web.bean.PageBean;
import rsj.admin.web.dao.report.ReportDao;
import rsj.admin.web.domain.report.Report;

import com.lehecai.core.YesNoStatus;

public class NewsDaoImpl extends HibernateDaoSupport implements ReportDao {

	@Override
	public Report get(Long id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(Report.class, id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PageBean getPageBean(final Long reportId, final String peopleName,
			final String peopleTel, final String peoplePost, final String peopleEmail,
			final String peopleAddr, final String reportContent, final String reportTitle,
			final Date beginCreateTime, final Date endCreateTime, final Date beginUpdateTime,
			final Date endUpdateTime, final YesNoStatus isRe, final String reName,
			final String reStyle, final String memo, final PageBean pageBean) {
		// TODO Auto-generated method stub
		return (PageBean) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						StringBuffer hql = new StringBuffer("select count(u) from News u where 1 = 1");

						if(reportId != null && reportId != 0){
							hql.append(" and u.reportId = :reportId");
						}
						if(peopleName != null && !"".equals(peopleName)){
							hql.append(" and u.peopleName like :peopleName");
						}
						if(peopleTel != null && !"".equals(peopleTel)){
							hql.append(" and u.peopleTel like :peopleTel");
						}
						if(peoplePost != null && !"".equals(peoplePost)){
							hql.append(" and u.peoplePost like :peoplePost");
						}
						if(peopleEmail != null && !"".equals(peopleEmail)){
							hql.append(" and u.peopleEmail like :peopleEmail");
						}
						if(peopleAddr != null && !"".equals(peopleAddr)){
							hql.append(" and u.peopleAddr like :peopleAddr");
						}
						if(reportContent != null && !"".equals(reportContent)){
							hql.append(" and u.reportContent like :reportContent");
						}
						if(reportTitle != null && !"".equals(reportTitle)){
							hql.append(" and u.reportTitle like :reportTitle");
						}
						if(beginCreateTime != null){
							hql.append(" and u.createTime >= :beginCreateTime");
						}
						if(endCreateTime != null){
							hql.append(" and u.createTime <= :endCreateTime");
						}
						if(beginUpdateTime != null){
							hql.append(" and u.updateTime >= :beginUpdateTime");
						}
						if(endUpdateTime != null){
							hql.append(" and u.updateTime <= :endUpdateTime");
						}
						if(isRe != null && isRe.getValue() != YesNoStatus.ALL.getValue()){
							hql.append(" and u.isRe = :isRe");
						}
						if(reName != null && !"".equals(reName)){
							hql.append(" and u.reName like :reName");
						}
						if(reStyle != null && !"".equals(reStyle)){
							hql.append(" and u.reStyle like :reStyle");
						}
						if(memo != null && !"".equals(memo)){
							hql.append(" and u.memo like :memo");
						}
						hql.append(" order by u.updateTime desc");
						Query query = session.createQuery(hql.toString());
								
						if(reportId != null && reportId != 0){
							query.setParameter("reportId", reportId);
						}
						if(peopleName != null && !"".equals(peopleName)){
							query.setParameter("peopleName", "%" + peopleName + "%");
						}
						if(peopleTel != null && !"".equals(peopleTel)){
							query.setParameter("peopleTel", "%" + peopleTel + "%");
						}
						if(peoplePost != null && !"".equals(peoplePost)){
							query.setParameter("peoplePost", "%" + peoplePost + "%");
						}
						if(peopleEmail != null && !"".equals(peopleEmail)){
							query.setParameter("peopleEmail", "%" + peopleEmail + "%");
						}
						if(peopleAddr != null && !"".equals(peopleAddr)){
							query.setParameter("peopleAddr", "%" + peopleAddr + "%");
						}
						if(reportContent != null && !"".equals(reportContent)){
							query.setParameter("reportContent", "%" + reportContent + "%");
						}
						if(reportTitle != null && !"".equals(reportTitle)){
							query.setParameter("reportTitle", "%" + reportTitle + "%");
						}
						if(beginCreateTime != null){
							query.setParameter("beginCreateTime", beginCreateTime);
						}
						if(endCreateTime != null){
							query.setParameter("endCreateTime", endCreateTime);
						}
						if(beginUpdateTime != null){
							query.setParameter("beginUpdateTime", beginUpdateTime);
						}
						if(endUpdateTime != null){
							query.setParameter("endUpdateTime", endUpdateTime);
						}
						if(isRe != null && isRe.getValue() != YesNoStatus.ALL.getValue()){
							query.setParameter("isRe", isRe);
						}
						
						if(reName != null && !"".equals(reName)){
							query.setParameter("reName", "%" + reName + "%");
						}
						if(reStyle != null && !"".equals(reStyle)){
							query.setParameter("reStyle", "%" + reStyle + "%");
						}
						if(memo != null && !"".equals(memo)){
							query.setParameter("memo", "%" + memo + "%");
						}
						if(pageBean.isPageFlag()){
							int totalCount = ((Long)query.iterate().next()).intValue();
							pageBean.setCount(totalCount);
							int pageCount = 0;//页数
							if(pageBean.getPageSize() != 0) {
					            pageCount = totalCount / pageBean.getPageSize();
					            if(totalCount % pageBean.getPageSize() != 0) {
					                pageCount ++;
					            }
					        }
							pageBean.setPageCount(pageCount);
						}
						return pageBean;
					}
				});
	}

	@SuppressWarnings({ "unchecked", "rawtypes"})
	@Override
	public List<Report> list(final Long reportId, final String peopleName,
			final String peopleTel, final String peoplePost, final String peopleEmail,
			final String peopleAddr, final String reportContent, final String reportTitle,
			final Date beginCreateTime, final Date endCreateTime, final Date beginUpdateTime,
			final Date endUpdateTime, final YesNoStatus isRe, final String reName,
			final String reStyle, final String memo, final PageBean pageBean) {
		// TODO Auto-generated method stub
		return (List<Report>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						StringBuffer hql = new StringBuffer("from Report u where 1 = 1");

						if(reportId != null && reportId != 0){
							hql.append(" and u.reportId = :reportId");
						}
						if(peopleName != null && !"".equals(peopleName)){
							hql.append(" and u.peopleName like :peopleName");
						}
						if(peopleTel != null && !"".equals(peopleTel)){
							hql.append(" and u.peopleTel like :peopleTel");
						}
						if(peoplePost != null && !"".equals(peoplePost)){
							hql.append(" and u.peoplePost like :peoplePost");
						}
						if(peopleEmail != null && !"".equals(peopleEmail)){
							hql.append(" and u.peopleEmail like :peopleEmail");
						}
						if(peopleAddr != null && !"".equals(peopleAddr)){
							hql.append(" and u.peopleAddr like :peopleAddr");
						}
						if(reportContent != null && !"".equals(reportContent)){
							hql.append(" and u.reportContent like :reportContent");
						}
						if(reportTitle != null && !"".equals(reportTitle)){
							hql.append(" and u.reportTitle like :reportTitle");
						}
						if(beginCreateTime != null){
							hql.append(" and u.createTime >= :beginCreateTime");
						}
						if(endCreateTime != null){
							hql.append(" and u.createTime <= :endCreateTime");
						}
						if(beginUpdateTime != null){
							hql.append(" and u.updateTime >= :beginUpdateTime");
						}
						if(endUpdateTime != null){
							hql.append(" and u.updateTime <= :endUpdateTime");
						}
						if(isRe != null && isRe.getValue() != YesNoStatus.ALL.getValue()){
							hql.append(" and u.isRe = :isRe");
						}
						if(reName != null && !"".equals(reName)){
							hql.append(" and u.reName like :reName");
						}
						if(reStyle != null && !"".equals(reStyle)){
							hql.append(" and u.reStyle like :reStyle");
						}
						if(memo != null && !"".equals(memo)){
							hql.append(" and u.memo like :memo");
						}
						hql.append(" order by u.updateTime desc");
						Query query = session.createQuery(hql.toString());
						
						if(reportId != null && reportId != 0){
							query.setParameter("reportId", reportId);
						}
						if(peopleName != null && !"".equals(peopleName)){
							query.setParameter("peopleName", "%" + peopleName + "%");
						}
						if(peopleTel != null && !"".equals(peopleTel)){
							query.setParameter("peopleTel", "%" + peopleTel + "%");
						}
						if(peoplePost != null && !"".equals(peoplePost)){
							query.setParameter("peoplePost", "%" + peoplePost + "%");
						}
						if(peopleEmail != null && !"".equals(peopleEmail)){
							query.setParameter("peopleEmail", "%" + peopleEmail + "%");
						}
						if(peopleAddr != null && !"".equals(peopleAddr)){
							query.setParameter("peopleAddr", "%" + peopleAddr + "%");
						}
						if(reportContent != null && !"".equals(reportContent)){
							query.setParameter("reportContent", "%" + reportContent + "%");
						}
						if(reportTitle != null && !"".equals(reportTitle)){
							query.setParameter("reportTitle", "%" + reportTitle + "%");
						}
						if(beginCreateTime != null){
							query.setParameter("beginCreateTime", beginCreateTime);
						}
						if(endCreateTime != null){
							query.setParameter("endCreateTime", endCreateTime);
						}
						if(beginUpdateTime != null){
							query.setParameter("beginUpdateTime", beginUpdateTime);
						}
						if(endUpdateTime != null){
							query.setParameter("endUpdateTime", endUpdateTime);
						}
						if(isRe != null && isRe.getValue() != YesNoStatus.ALL.getValue()){
							query.setParameter("isRe", isRe);
						}
						
						if(reName != null && !"".equals(reName)){
							query.setParameter("reName", "%" + reName + "%");
						}
						if(reStyle != null && !"".equals(reStyle)){
							query.setParameter("reStyle", "%" + reStyle + "%");
						}
						if(memo != null && !"".equals(memo)){
							query.setParameter("memo", "%" + memo + "%");
						}
						if(pageBean.isPageFlag()){
							if(pageBean.getPageSize() != 0){
								query.setFirstResult((pageBean.getPage() - 1) * pageBean.getPageSize());
								query.setMaxResults(pageBean.getPageSize());
							}
						}
						return query.list();
					}
				});
	}

	@Override
	public void merge(Report report) {
		// TODO Auto-generated method stub
		getHibernateTemplate().merge(report);
	}

	@Override
	public void save(Report report) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(report);
	}

}


