package rsj.admin.web.dao.hitechNews;

import java.util.Date;
import java.util.List;

import rsj.admin.web.bean.PageBean;
import rsj.admin.web.domain.hitechNews.HitechNews;
import rsj.admin.web.enums.HitechNewsType;

import com.lehecai.core.YesNoStatus;

public interface HitechNewsDao {
	
	/**
	 * 
	 * @param 
	 * @return
	 */
	List<HitechNews> list(Long newsId, String title, HitechNewsType hitechNewsType, String content, String username, String updateUsername, Date beginCreateTime, 
			Date endCreateTime, Date beginUpdateTime, Date endUpdateTime, String imagePath, YesNoStatus isApply, YesNoStatus isImageNews, String memo, PageBean pageBean);
	
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
	PageBean getPageBean(Long newsId, String title, HitechNewsType hitechNewsType, String content, String username, String updateUsername, Date beginCreateTime, 
			Date endCreateTime, Date beginUpdateTime, Date endUpdateTime, String imagePath, YesNoStatus isApply, YesNoStatus isImageNews, String memo, PageBean pageBean);
	/**
	 * 
	 * @param id 
	 * @return
	 */
	HitechNews get(Long newsId);
	/**
	 * 添加
	 * @param news
	 * @return
	 */
	void save(HitechNews hitechNews);
	/**
	 * 修改
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
	 * @param pageBean
	 * @return
	 */
	PageBean getListForJsonPageBean(HitechNewsType hitechNewsType, YesNoStatus isApply,	PageBean pageBean);
	/**
	 * 
	 * @param hitechNewsType
	 * @param isApply
	 * @param pageBean
	 * @return
	 */
	List<HitechNews> listForJson(HitechNewsType hitechNewsType, YesNoStatus isApply, PageBean pageBean);
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
