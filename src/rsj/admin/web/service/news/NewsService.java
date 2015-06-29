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
	List<News> list(Long newsId, String title, NewsType newsType, String content, String username, String updateUsername, Date beginCreateTime, 
			Date endCreateTime, Date beginUpdateTime, Date endUpdateTime, String imagePath, YesNoStatus isApply, YesNoStatus isImageNews, String memo, PageBean pageBean);
	/**
	 * 
	 * @param 
	 * @return
	 */
	List<News> listForJson(NewsType newsType, YesNoStatus isApply, Integer newsSize);
	/**
	 * 
	 * @param newsType
	 * @param isApply
	 * @param newsSize
	 * @param pageBean
	 * @return
	 */
	List<News> listForJson(NewsType newsType, YesNoStatus isApply, PageBean pageBean);
	/**
	 * 
	 * @param newsType
	 * @param isApply
	 * @param newsSize
	 * @param pageBean
	 * @return
	 */
	PageBean getListForJsonPageBean(NewsType newsType, YesNoStatus isApply, PageBean pageBean);

	/**
	 * 
	 * @param 
	 * @return
	 */
	PageBean getPageBean(Long newsId, String title, NewsType newsType, String content, String username, String updateUsername, Date beginCreateTime, 
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
	/**
	 * 
	 * 
	 * @param yisApplyes
	 * @param isImageNews
	 * @param newsSize
	 * @return
	 */
	List<News> pictureNewsListForJson(YesNoStatus isApply, YesNoStatus isImageNews,
			Integer newsSize);
	/**
	 * 
	 * @param yes
	 * @param newsSize
	 * @param oRDER_BY_VIEW_TIMES
	 * @return
	 */
	List<News> hotNewsListForJson(YesNoStatus isApply, Integer newsSize,
			String order);
}
