package rsj.admin.web.service.impl.news;

import java.util.List;

import rsj.admin.web.dao.news.NewsMailDao;
import rsj.admin.web.domain.news.NewsMail;
import rsj.admin.web.service.news.NewsMailService;

import com.lehecai.core.YesNoStatus;

public class NewsMailServiceImpl implements NewsMailService {

	private NewsMailDao newsMailDao;

	@Override
	public List<NewsMail> list(Long newsMailId, String newsMailName,
			String newsMailAddr, YesNoStatus isDefaultSend) {
		// TODO Auto-generated method stub
		return newsMailDao.list(newsMailId, newsMailName, newsMailAddr, isDefaultSend);
	}

	@Override
	public NewsMail get(Long newsMailId) {
		// TODO Auto-generated method stub
		return newsMailDao.get(newsMailId);
	}

	@Override
	public void save(NewsMail newsMail) {
		// TODO Auto-generated method stub
		newsMailDao.save(newsMail);
	}

	@Override
	public void merge(NewsMail newsMail) {
		// TODO Auto-generated method stub
		newsMailDao.merge(newsMail);
	}

	@Override
	public void del(NewsMail newsMail) {
		// TODO Auto-generated method stub
		newsMailDao.del(newsMail);
	}

	public NewsMailDao getNewsMailDao() {
		return newsMailDao;
	}

	public void setNewsMailDao(NewsMailDao newsMailDao) {
		this.newsMailDao = newsMailDao;
	}
	
	

}
