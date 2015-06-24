package rsj.admin.web.dao.impl.archives;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import rsj.admin.web.bean.PageBean;
import rsj.admin.web.dao.archives.ArchivesDao;
import rsj.admin.web.domain.archives.Archives;
import rsj.admin.web.enums.ArchivesType;

import com.lehecai.core.YesNoStatus;

public class ArchivesDaoImpl extends HibernateDaoSupport implements ArchivesDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Archives> list(final String number, final String title, final String department,
			final Date beginCreateTime, final Date endCreateTime, final Date beginUpdateTime,
			final Date endUpdateTime, final ArchivesType archivesType, final String path,
			final YesNoStatus isDigital, final String memo, final PageBean pageBean) {
		// TODO Auto-generated method stub
		System.out.println("" + beginCreateTime);
		return (List<Archives>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						StringBuffer hql = new StringBuffer("from Archives u where 1 = 1");

						if(number != null && !"".equals(number)){
							hql.append(" and u.number like :number");
						}
						if(title != null && !"".equals(title)){
							hql.append(" and u.title like :title");
						}
						if(department != null && !"".equals(department)){
							hql.append(" and u.department like :department");
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
						if(archivesType != null && archivesType.getValue() != ArchivesType.ALL.getValue()){
							hql.append(" and u.archivesType = :archivesType");
						}
						if(path != null && !"".equals(path)){
							hql.append(" and u.path like :path");
						}
						if(isDigital != null && isDigital.getValue() != YesNoStatus.ALL.getValue()){
							hql.append(" and u.isDigital = :isDigital");
						}
						if(memo != null && !"".equals(memo)){
							hql.append(" and u.memo like :memo");
						}
						hql.append(" order by u.createTime desc");
						Query query = session.createQuery(hql.toString());
								
						if(number != null && !"".equals(number)){
							query.setParameter("number", "%" + number + "%");
						}
						if(title != null && !"".equals(title)){
							query.setParameter("title", "%" + title + "%");
						}
						if(department != null && !"".equals(department)){
							query.setParameter("department", "%" + department + "%");
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
						if(archivesType != null && archivesType.getValue() != ArchivesType.ALL.getValue()){
							query.setParameter("archivesType", archivesType);
						}
						if(path != null && !"".equals(path)){
							query.setParameter("path", "%" + path + "%");
						}
						if(isDigital != null && isDigital.getValue() != YesNoStatus.ALL.getValue()){
							query.setParameter("isDigital", isDigital);
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PageBean getPageBean(final String number, final String title, final String department,
			final Date beginCreateTime, final Date endCreateTime, final Date beginUpdateTime,
			final Date endUpdateTime, final ArchivesType archivesType, final String path,
			final YesNoStatus isDigital, final String memo, final PageBean pageBean) {
		// TODO Auto-generated method stub
		return (PageBean) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						StringBuffer hql = new StringBuffer("select count(u) from Archives u where 1 = 1");

						if(number != null && !"".equals(number)){
							hql.append(" and u.number like :number");
						}
						if(title != null && !"".equals(title)){
							hql.append(" and u.title like :title");
						}
						if(department != null && !"".equals(department)){
							hql.append(" and u.department like :department");
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
						if(archivesType != null && archivesType.getValue() != ArchivesType.ALL.getValue()){
							hql.append(" and u.archivesType = :archivesType");
						}
						if(path != null && !"".equals(path)){
							hql.append(" and u.path like :path");
						}
						if(isDigital != null && isDigital.getValue() != YesNoStatus.ALL.getValue()){
							hql.append(" and u.isDigital = :isDigital");
						}
						if(memo != null && !"".equals(memo)){
							hql.append(" and u.memo like :memo");
						}
						hql.append(" order by u.createTime desc");
						Query query = session.createQuery(hql.toString());
								
						if(number != null && !"".equals(number)){
							query.setParameter("number", "%" + number + "%");
						}
						if(title != null && !"".equals(title)){
							query.setParameter("title", "%" + title + "%");
						}
						if(department != null && !"".equals(department)){
							query.setParameter("department", "%" + department + "%");
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
						if(archivesType != null && archivesType.getValue() != ArchivesType.ALL.getValue()){
							query.setParameter("archivesType", archivesType);
						}
						if(path != null && !"".equals(path)){
							query.setParameter("path", "%" + path + "%");
						}
						if(isDigital != null && isDigital.getValue() != YesNoStatus.ALL.getValue()){
							query.setParameter("isDigital", isDigital);
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

	@Override
	public Archives get(Long id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(Archives.class, id);
	}

	@Override
	public void save(Archives archives) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(archives);
	}

	@Override
	public void merge(Archives archives) {
		// TODO Auto-generated method stub
		getHibernateTemplate().merge(archives);
		
	}

	

}
