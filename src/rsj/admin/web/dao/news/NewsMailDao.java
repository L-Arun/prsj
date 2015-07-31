package rsj.admin.web.dao.news;

import java.util.List;

import rsj.admin.web.domain.news.NewsMail;

import com.lehecai.core.YesNoStatus;

public interface NewsMailDao {
	
	/**
	 * 
	 * @param 
	 * @return
	 */
	List<NewsMail> list(Long newsMailId, String newsMailName, String newsMailAddr, YesNoStatus isDefaultSend);
	/**
	 * 
	 * @param id 
	 * @return
	 */
	NewsMail get(Long newsMailId);
	/**
	 * 添加
	 * @param news
	 * @return
	 */
	void save(NewsMail newsMail);
	/**
	 * 修改
	 * @param news
	 * @return
	 */
	void merge(NewsMail newsMail);
	/**
	 * 删除
	 */
	void del(NewsMail newsMail);
	
}
