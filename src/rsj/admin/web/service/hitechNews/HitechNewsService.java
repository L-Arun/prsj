package rsj.admin.web.service.hitechNews;

import java.util.Date;
import java.util.List;

import rsj.admin.web.bean.PageBean;
import rsj.admin.web.domain.hitechNews.HitechNews;
import rsj.admin.web.enums.HitechNewsType;

import com.lehecai.core.YesNoStatus;

public interface HitechNewsService {

	/**
	 * 
	 * @param 
	 * @return
	 */
	List<HitechNews> list(Long newsId, String title, HitechNewsType hitechNewsType, String content, String username, String updateUsername, Date beginCreateTime, 
			Date endCreateTime, Date beginUpdateTime, Date endUpdateTime, String imagePath, YesNoStatus isApply, YesNoStatus isImageNews, String memo, PageBean pageBean);

	/**
	 * 
	 * @param 
	 * @return
	 */
	PageBean getPageBean(Long newsId, String title, HitechNewsType hitechNewsType, String content, String username, String updateUsername, Date beginCreateTime, 
			Date endCreateTime, Date beginUpdateTime, Date endUpdateTime, String imagePath, YesNoStatus isApply, YesNoStatus isImageNews, String memo, PageBean pageBean);
	/**
	 * 
	 * @param id 
	 * @return
	 */
	HitechNews get(Long newsId);
	/**
	 * 
	 * @param news
	 * @return
	 */
	void save(HitechNews hitechNews);
	/**
	 * 
	 * @param news
	 * @return
	 */
	void merge(HitechNews hitechNews);
	/**
	 * 
	 * @param 
	 * @return
	 */
	List<HitechNews> listForJson(HitechNewsType hitechNewsType, YesNoStatus isApply, Integer newsSize);
	/**
	 * 
	 * @param hitechNewsType
	 * @param isApply
	 * @param newsSize
	 * @param pageBean
	 * @return
	 */
	List<HitechNews> listForJson(HitechNewsType hitechNewsType, YesNoStatus isApply, PageBean pageBean);
	/**
	 * 
	 * @param hitechNewsType
	 * @param isApply
	 * @param newsSize
	 * @param pageBean
	 * @return
	 */
	PageBean getListForJsonPageBean(HitechNewsType hitechNewsType, YesNoStatus isApply, PageBean pageBean);
	/**
	 * 
	 * 
	 * @param isApplyes
	 * @param isImageNews
	 * @param newsSize
	 * @return
	 */
	List<HitechNews> pictureNewsListForJson(YesNoStatus isApply, YesNoStatus isImageNews,
			Integer newsSize);
}
