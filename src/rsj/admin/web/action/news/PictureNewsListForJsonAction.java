package rsj.admin.web.action.news;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rsj.admin.web.action.BaseAction;
import rsj.admin.web.domain.news.News;
import rsj.admin.web.service.news.NewsService;

import com.lehecai.core.YesNoStatus;
import com.opensymphony.xwork2.Action;

public class PictureNewsListForJsonAction extends BaseAction {
	private static final long serialVersionUID = 2436161530465382824L;
	private Logger logger = LoggerFactory.getLogger(PictureNewsListForJsonAction.class);
	
	private NewsService newsService;
	
	private Integer newsSize;
	
	public String handle() {
		logger.info("进入json查询图片新闻列表");
		int rc = 0;
		String msg = "";
		List<News> newses = null;
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject json = new JSONObject();
		newses = newsService.pictureNewsListForJson(YesNoStatus.YES, YesNoStatus.YES, newsSize);
		if (newses != null && newses.size() > 0) {
			json.put("code", rc);
			json.put("msg", msg);
			JSONArray jsonArray = new JSONArray();
			for (News news : newses) {
				JSONObject j = new JSONObject();
				j.put("key", news.getId());
				j.put("name", news.getTitle());
				j.put("imagePath", news.getImagePath());
				jsonArray.add(j);
			}
			json.put("data", jsonArray.toString());
			writeRs(response, json);
				
		}
		logger.info("结束json查询图片新闻列表");
		return Action.NONE;
		
	}
	
	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public Integer getNewsSize() {
		return newsSize;
	}

	public void setNewsSize(Integer newsSize) {
		this.newsSize = newsSize;
	}

	
}
