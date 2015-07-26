package rsj.admin.web.service.impl.hitechNews;

import java.util.Date;
import java.util.List;

import com.lehecai.core.YesNoStatus;

import rsj.admin.web.bean.PageBean;
import rsj.admin.web.dao.hitechNews.HitechNewsDao;
import rsj.admin.web.domain.hitechNews.HitechNews;
import rsj.admin.web.enums.HitechNewsType;
import rsj.admin.web.service.hitechNews.HitechNewsService;

public class HitechNewsServiceImpl implements HitechNewsService {

	private HitechNewsDao hitechNewsDao;

	@Override
	public List<HitechNews> list(Long newsId, String title,
			HitechNewsType hitechNewsType, String content, String username,
			String updateUsername, Date beginCreateTime, Date endCreateTime,
			Date beginUpdateTime, Date endUpdateTime, String imagePath,
			YesNoStatus isApply, YesNoStatus isImageNews, String memo,
			PageBean pageBean) {
		// TODO Auto-generated method stub
		return hitechNewsDao.list(newsId, title, hitechNewsType, content, username, updateUsername, beginCreateTime, endCreateTime, beginUpdateTime, endUpdateTime, imagePath, isApply, isImageNews, memo, pageBean);
	}

	@Override
	public PageBean getPageBean(Long newsId, String title,
			HitechNewsType hitechNewsType, String content, String username,
			String updateUsername, Date beginCreateTime, Date endCreateTime,
			Date beginUpdateTime, Date endUpdateTime, String imagePath,
			YesNoStatus isApply, YesNoStatus isImageNews, String memo,
			PageBean pageBean) {
		// TODO Auto-generated method stub
		return hitechNewsDao.getPageBean(newsId, title, hitechNewsType, content, username, updateUsername, beginCreateTime, endCreateTime, beginUpdateTime, endUpdateTime, imagePath, isApply, isImageNews, memo, pageBean);
	}

	@Override
	public HitechNews get(Long newsId) {
		// TODO Auto-generated method stub
		return hitechNewsDao.get(newsId);
	}

	@Override
	public void save(HitechNews hitechNews) {
		// TODO Auto-generated method stub
		hitechNewsDao.save(hitechNews);
	}

	@Override
	public void merge(HitechNews hitechNews) {
		// TODO Auto-generated method stub
		hitechNewsDao.merge(hitechNews);
	}

	public HitechNewsDao getHitechNewsDao() {
		return hitechNewsDao;
	}

	public void setHitechNewsDao(HitechNewsDao hitechNewsDao) {
		this.hitechNewsDao = hitechNewsDao;
	}
	
}
