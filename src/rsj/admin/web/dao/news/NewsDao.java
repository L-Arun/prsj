package rsj.admin.web.dao.news;

import java.util.Date;
import java.util.List;

import com.lehecai.core.YesNoStatus;

import rsj.admin.web.bean.PageBean;
import rsj.admin.web.domain.news.News;
import rsj.admin.web.enums.NewsType;

public interface NewsDao {
	
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
	 * 多条件查询分页信息
	 * @param number 文号
	 * @param title 标题
	 * @param department 部门
	 * @param beginCreateTime 开始创建时间
	 * @param endCreateTime 结束创建时间
	 * @param beginUpdateTime 开始更新时间
	 * @param endUpdateTime 结束更新时间
	 * @param archivesType	档案类型
	 * @param path 保存路径
	 * @param isDigital 是否有电子版
	 * @param memo 备注
	 * @param pageBean
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
	 * 添加
	 * @param news
	 * @return
	 */
	void save(News news);
	/**
	 * 修改
	 * @param news
	 * @return
	 */
	void merge(News news);
	/**
	 * 
	 * @param isApply
	 * @param isImageNews
	 * @param newsSize
	 * @return
	 */
	List<News> pictureNewsListForJson(YesNoStatus isApply,
			YesNoStatus isImageNews, Integer newsSize);
	/**
	 * 
	 * @param newsType
	 * @param isApply
	 * @param newsSize
	 * @param pageBean
	 * @return
	 */
	PageBean getListForJsonPageBean(NewsType newsType, YesNoStatus isApply,
			PageBean pageBean);
	/**
	 * 
	 * @param newsType
	 * @param isApply
	 * @param newsSize
	 * @param pageBean
	 * @return
	 */
	List<News> listForJson(NewsType newsType, YesNoStatus isApply,
			PageBean pageBean);
}
