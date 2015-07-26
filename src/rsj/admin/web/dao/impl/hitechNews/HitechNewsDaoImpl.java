package rsj.admin.web.dao.impl.hitechNews;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import rsj.admin.web.bean.PageBean;
import rsj.admin.web.dao.hitechNews.HitechNewsDao;
import rsj.admin.web.domain.hitechNews.HitechNews;
import rsj.admin.web.enums.HitechNewsType;
import rsj.admin.web.enums.NewsType;

import com.lehecai.core.YesNoStatus;

public class HitechNewsDaoImpl extends HibernateDaoSupport implements HitechNewsDao {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<HitechNews> list(final Long newsId, final String title,final HitechNewsType hitechNewsType, final String content, final String username, 
			final String updateUsername, final Date beginCreateTime, final Date endCreateTime, final Date beginUpdateTime,
			final Date endUpdateTime, final String imagePath, final YesNoStatus isApply, final YesNoStatus isImageNews, final String memo, final PageBean pageBean) {
		return (List<HitechNews>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						StringBuffer hql = new StringBuffer("from News u where 1 = 1");

						if(newsId != null && newsId != 0){
							hql.append(" and u.newsId = :newsId");
						}
						if(title != null && !"".equals(title)){
							hql.append(" and u.title like :title");
						}
						if(hitechNewsType != null && hitechNewsType.getValue() != NewsType.ALL.getValue()){
							hql.append(" and u.hitechNewsType = :hitechNewsType");
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
						hql.append(" order by u.updateTime desc");
						Query query = session.createQuery(hql.toString());
						
						if(newsId != null && newsId != 0){
							query.setParameter("newsId", newsId);
						}
						if(title != null && !"".equals(title)){
							query.setParameter("title", "%" + title + "%");
						}
						if(hitechNewsType != null && hitechNewsType.getValue() != NewsType.ALL.getValue()){
							query.setParameter("hitechNewsType", hitechNewsType);
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
	public PageBean getPageBean(final Long newsId, final String title, final HitechNewsType hitechNewsType,
			final String content, final String username, final String updateUsername, final Date beginCreateTime, 
			final Date endCreateTime, final Date beginUpdateTime, final Date endUpdateTime, final String imagePath,
			final YesNoStatus isApply,final YesNoStatus isImageNews, final String memo, final PageBean pageBean) {
		// TODO Auto-generated method stub
		return (PageBean) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						StringBuffer hql = new StringBuffer("select count(u) from News u where 1 = 1");

						if(newsId != null && newsId != 0){
							hql.append(" and u.newsId = :newsId");
						}
						if(title != null && !"".equals(title)){
							hql.append(" and u.title like :title");
						}
						if(hitechNewsType != null && hitechNewsType.getValue() != NewsType.ALL.getValue()){
							hql.append(" and u.hitechNewsType = :hitechNewsType");
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
						hql.append(" order by u.updateTime desc");
						Query query = session.createQuery(hql.toString());
								
						if(newsId != null && newsId != 0){
							query.setParameter("newsId", newsId);
						}
						if(title != null && !"".equals(title)){
							query.setParameter("title", "%" + title + "%");
						}
						if(hitechNewsType != null && hitechNewsType.getValue() != NewsType.ALL.getValue()){
							query.setParameter("hitechNewsType", hitechNewsType);
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
	public HitechNews get(Long newsId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(HitechNews.class, newsId);
	}

	@Override
	public void save(HitechNews hitechNews) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hitechNews);
	}

	@Override
	public void merge(HitechNews hitechNews){
		// TODO Auto-generated method stub
		getHibernateTemplate().merge(hitechNews);		
	}

}


