package rsj.admin.web.service.news;

import java.util.Date;
import java.util.List;

import rsj.admin.web.bean.PageBean;
import rsj.admin.web.domain.news.News;
import rsj.admin.web.enums.NewsType;

import com.lehecai.core.YesNoStatus;

public interface NewsService {

	/**
	 * 
	 * @param 
	 * @return
	 */
	List<News> list(String title, NewsType newsType, String content, String username, String updateUsername, Date beginCreateTime, 
			Date endCreateTime, Date beginUpdateTime, Date endUpdateTime, String imagePath, YesNoStatus isApply, YesNoStatus isImageNews, String memo, PageBean pageBean);
	/**
	 * 
	 * @param 
	 * @return
	 */
	List<News> listForJson(String title, NewsType newsType, String content, String username, String updateUsername, Date beginCreateTime, 
			Date endCreateTime, Date beginUpdateTime, Date endUpdateTime, String imagePath, YesNoStatus isApply, YesNoStatus isImageNews, String memo);
	/**
	 * 
	 * @param 
	 * @return
	 */
	PageBean getPageBean(String title, NewsType newsType, String content, String username, String updateUsername, Date beginCreateTime, 
			Date endCreateTime, Date beginUpdateTime, Date endUpdateTime, String imagePath, YesNoStatus isApply, YesNoStatus isImageNews, String memo, PageBean pageBean);
	/**
	 * 
	 * @param id 
	 * @return
	 */
	News get(Long id);
	/**
	 * 
	 * @param news
	 * @return
	 */
	void save(News news);
	/**
	 * 
	 * @param news
	 * @return
	 */
	void merge(News news);
}
