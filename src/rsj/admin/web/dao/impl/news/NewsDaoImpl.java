package rsj.admin.web.dao.impl.news;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import rsj.admin.web.bean.PageBean;
import rsj.admin.web.dao.news.NewsDao;
import rsj.admin.web.domain.news.News;
import rsj.admin.web.enums.NewsType;

import com.lehecai.core.YesNoStatus;

public class NewsDaoImpl extends HibernateDaoSupport implements NewsDao {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<News> list(final String title,final NewsType newsType, final String content, final String username, 
			final String updateUsername, final Date beginCreateTime, final Date endCreateTime, final Date beginUpdateTime,
			final Date endUpdateTime, final String imagePath, final YesNoStatus isApply, final YesNoStatus isImageNews, final String memo, final PageBean pageBean) {
		return (List<News>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						StringBuffer hql = new StringBuffer("from News u where 1 = 1");

						if(title != null && !"".equals(title)){
							hql.append(" and u.title like :title");
						}
						if(newsType != null && newsType.getValue() != NewsType.ALL.getValue()){
							hql.append(" and u.newsType = :newsType");
						}
						if(content != null && !"".equals(content)){
							hql.append(" and u.content like :content");
						}
						if(username != null && !"".equals(username)){
							hql.append(" and u.username like :username");
						}
						if(updateUsername != null && !"".equals(updateUsername)){
							hql.append(" and u.updateUsername like :updateUsername");
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
						if(imagePath != null && !"".equals(imagePath)){
							hql.append(" and u.imagePath like :imagePath");
						}
						if(isApply != null && isApply.getValue() != YesNoStatus.ALL.getValue()){
							hql.append(" and u.isApply = :isApply");
						}
						if(isImageNews != null && isImageNews.getValue() != YesNoStatus.ALL.getValue()){
							hql.append(" and u.isImageNews = :isImageNews");
						}
						if(memo != null && !"".equals(memo)){
							hql.append(" and u.memo like :memo");
						}
						hql.append(" order by u.createTime desc");
						Query query = session.createQuery(hql.toString());
								
						if(title != null && !"".equals(title)){
							query.setParameter("title", "%" + title + "%");
						}
						if(newsType != null && newsType.getValue() != NewsType.ALL.getValue()){
							query.setParameter("newsType", newsType);
						}
						if(content != null && !"".equals(content)){
							query.setParameter("content", "%" + content + "%");
						}
						if(username != null && !"".equals(username)){
							query.setParameter("username", "%" + username + "%");
						}
						if(updateUsername != null && !"".equals(updateUsername)){
							query.setParameter("updateUsername", "%" + updateUsername + "%");
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
						if(imagePath != null && !"".equals(imagePath)){
							query.setParameter("imagePath", "%" + imagePath + "%");
						}
						if(isApply != null && isApply.getValue() != YesNoStatus.ALL.getValue()){
							query.setParameter("isApply", isApply);
						}
						if(isImageNews != null && isImageNews.getValue() != YesNoStatus.ALL.getValue()){
							query.setParameter("isImageNews", isImageNews);
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
	public List<News> listForJson(final NewsType newsType, final YesNoStatus isApply, final Integer newsSize) {
		return (List<News>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						StringBuffer hql = new StringBuffer("from News u where 1 = 1");

						if(newsType != null && newsType.getValue() != NewsType.ALL.getValue()){
							hql.append(" and u.newsType = :newsType");
						}
						if(isApply != null && isApply.getValue() != YesNoStatus.ALL.getValue()){
							hql.append(" and u.isApply = :isApply");
						}
						hql.append(" order by u.createTime desc");
						Query query = session.createQuery(hql.toString());
								
						if(newsType != null && newsType.getValue() != NewsType.ALL.getValue()){
							query.setParameter("newsType", newsType);
						}
						if(isApply != null && isApply.getValue() != YesNoStatus.ALL.getValue()){
							query.setParameter("isApply", isApply);
						}
						if(newsSize != null && newsSize != 0) {
							query.setMaxResults(newsSize);
						}
						return query.list();
					}
				});
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PageBean getPageBean(final String title, final NewsType newsType,
			final String content, final String username, final String updateUsername, final Date beginCreateTime, 
			final Date endCreateTime, final Date beginUpdateTime, final Date endUpdateTime, final String imagePath,
			final YesNoStatus isApply,final YesNoStatus isImageNews, final String memo, final PageBean pageBean) {
		// TODO Auto-generated method stub
		return (PageBean) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						StringBuffer hql = new StringBuffer("select count(u) from News u where 1 = 1");

						if(title != null && !"".equals(title)){
							hql.append(" and u.title like :title");
						}
						if(newsType != null && newsType.getValue() != NewsType.ALL.getValue()){
							hql.append(" and u.newsType = :newsType");
						}
						if(content != null && !"".equals(content)){
							hql.append(" and u.content like :content");
						}
						if(username != null && !"".equals(username)){
							hql.append(" and u.username like :username");
						}
						if(updateUsername != null && !"".equals(updateUsername)){
							hql.append(" and u.updateUsername like :updateUsername");
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
						if(imagePath != null && !"".equals(imagePath)){
							hql.append(" and u.imagePath like :imagePath");
						}
						if(isApply != null && isApply.getValue() != YesNoStatus.ALL.getValue()){
							hql.append(" and u.isApply = :isApply");
						}
						if(isImageNews != null && isImageNews.getValue() != YesNoStatus.ALL.getValue()){
							hql.append(" and u.isImageNews = :isImageNews");
						}
						if(memo != null && !"".equals(memo)){
							hql.append(" and u.memo like :memo");
						}
						hql.append(" order by u.createTime desc");
						Query query = session.createQuery(hql.toString());
								
						if(title != null && !"".equals(title)){
							query.setParameter("title", "%" + title + "%");
						}
						if(newsType != null && newsType.getValue() != NewsType.ALL.getValue()){
							query.setParameter("newsType", newsType);
						}
						if(content != null && !"".equals(content)){
							query.setParameter("content", "%" + content + "%");
						}
						if(username != null && !"".equals(username)){
							query.setParameter("username", "%" + username + "%");
						}
						if(updateUsername != null && !"".equals(updateUsername)){
							query.setParameter("updateUsername", "%" + updateUsername + "%");
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
						if(imagePath != null && !"".equals(imagePath)){
							query.setParameter("imagePath", "%" + imagePath + "%");
						}
						if(isApply != null && isApply.getValue() != YesNoStatus.ALL.getValue()){
							query.setParameter("isApply", isApply);
						}
						if(isImageNews != null && isImageNews.getValue() != YesNoStatus.ALL.getValue()){
							query.setParameter("isImageNews", isImageNews);
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
	public News get(Long id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(News.class, id);
	}

	@Override
	public void save(News news) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(news);
	}

	@Override
	public void merge(News news) {
		// TODO Auto-generated method stub
		getHibernateTemplate().merge(news);		
	}

}


