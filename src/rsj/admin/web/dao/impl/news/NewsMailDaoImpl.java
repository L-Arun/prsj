package rsj.admin.web.dao.impl.news;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import rsj.admin.web.dao.news.NewsMailDao;
import rsj.admin.web.domain.news.NewsMail;

import com.lehecai.core.YesNoStatus;

public class NewsMailDaoImpl extends HibernateDaoSupport implements NewsMailDao {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<NewsMail> list(final Long newsMailId, final String newsMailName,
			final String newsMailAddr, final YesNoStatus isDefaultSend) {
		// TODO Auto-generated method stub
		return (List<NewsMail>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						StringBuffer hql = new StringBuffer("from NewsMail u where 1 = 1");

						if(newsMailId != null && newsMailId != 0){
							hql.append(" and u.newsMailId = :newsMailId");
						}
						if(newsMailName != null && !"".equals(newsMailName)){
							hql.append(" and u.newsMailName like :newsMailName");
						}
						if(newsMailAddr != null && !"".equals(newsMailAddr)){
							hql.append(" and u.newsMailAddr like :newsMailAddr");
						}
						if(isDefaultSend != null && isDefaultSend.getValue() != YesNoStatus.ALL.getValue()){
							hql.append(" and u.isDefaultSend = :isDefaultSend");
						}
						hql.append(" order by u.newsMailId desc");
						Query query = session.createQuery(hql.toString());
						
						if(newsMailId != null && newsMailId != 0){
							query.setParameter("newsMailId", newsMailId);
						}
						if(newsMailName != null && !"".equals(newsMailName)){
							query.setParameter("newsMailName", "%" + newsMailName + "%");
						}
						if(newsMailAddr != null && !"".equals(newsMailAddr)){
							query.setParameter("newsMailAddr", "%" + newsMailAddr + "%");
						}
						if(isDefaultSend != null && isDefaultSend.getValue() != YesNoStatus.ALL.getValue()){
							query.setParameter("isDefaultSend", isDefaultSend);
						}
						return query.list();
					}
				});
	}

	@Override
	public void save(NewsMail newsMail) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(newsMail);
	}

	@Override
	public void merge(NewsMail newsMail) {
		// TODO Auto-generated method stub
		getHibernateTemplate().merge(newsMail);
	}

	@Override
	public void del(NewsMail newsMail) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(newsMail);
	}

	@Override
	public NewsMail get(Long newsMailId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(NewsMail.class, newsMailId);
	}

}


