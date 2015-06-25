package rsj.admin.web.service.impl.news;

import java.util.Date;
import java.util.List;

import rsj.admin.web.bean.PageBean;
import rsj.admin.web.dao.news.NewsDao;
import rsj.admin.web.domain.news.News;
import rsj.admin.web.enums.NewsType;
import rsj.admin.web.service.news.NewsService;

import com.lehecai.core.YesNoStatus;

public class NewsServiceImpl implements NewsService {

	private NewsDao newsDao;
	
		public NewsDao getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}

	@Override
	public List<News> list(String title, NewsType newsType, String content,
			String username, String updateUsername, Date beginCreateTime,
			Date endCreateTime, Date beginUpdateTime, Date endUpdateTime,
			String imagePath, YesNoStatus isApply, YesNoStatus isImageNews, String memo,
			PageBean pageBean) {
		// TODO Auto-generated method stub
		return newsDao.list(title, newsType, content, username, updateUsername, beginCreateTime, endCreateTime, beginUpdateTime, endUpdateTime, imagePath, isApply, isImageNews, memo, pageBean);
	}
	
	@Override
	public List<News> listForJson(NewsType newsType, YesNoStatus isApply, Integer newsSize) {
		// TODO Auto-generated method stub
		return newsDao.listForJson(newsType, isApply, newsSize);
	}

	@Override
	public PageBean getPageBean(String title, NewsType newsType,
			String content, String username, String updateUsername,
			Date beginCreateTime, Date endCreateTime, Date beginUpdateTime,
			Date endUpdateTime, String imagePath, YesNoStatus isApply, YesNoStatus isImageNews,
			String memo, PageBean pageBean) {
		// TODO Auto-generated method stub
		return newsDao.getPageBean(title, newsType, content, username, updateUsername, beginCreateTime, endCreateTime, beginUpdateTime, endUpdateTime, imagePath, isApply, isImageNews, memo, pageBean);
	}

	@Override
	public News get(Long id) {
		// TODO Auto-generated method stub
		return newsDao.get(id);
	}

	@Override
	public void save(News news) {
		// TODO Auto-generated method stub
		newsDao.save(news);
	}

	@Override
	public void merge(News news) {
		// TODO Auto-generated method stub
		newsDao.merge(news);
	}

}
