package rsj.admin.web.action.news;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rsj.admin.web.action.BaseAction;
import rsj.admin.web.domain.news.News;
import rsj.admin.web.service.news.NewsService;
import rsj.admin.web.utils.DateUtil;

import com.opensymphony.xwork2.Action;

public class NewsForJsonAction extends BaseAction {
	private static final long serialVersionUID = 2436161530465382824L;
	private Logger logger = LoggerFactory.getLogger(NewsForJsonAction.class);
	
	private NewsService newsService;
	
	private Long newsId;
	
	public String handle() {
		logger.info("进入json查询新闻");
		int rc = 0;
		String msg = "";
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject json = new JSONObject();
		if (newsId == null || newsId == 0) {
			rc = 1;
			msg = "新闻查询出错";
			json.put("code", rc);
			json.put("msg", msg);
			writeRs(response, json);
		} else {
			News news = newsService.get(newsId);
			if (news == null) {
				rc = 1;
				msg = "新闻查询出错";
				json.put("code", rc);
				json.put("msg", msg);
				writeRs(response, json);
			} else {
				news.setViewTimes(news.getViewTimes() + 1);
				json.put("code", rc);
				json.put("msg", msg);
				JSONArray jsonArray = new JSONArray();
				JSONObject j = new JSONObject();
				j.put("key", news.getNewsId());
				j.put("title", news.getTitle());
				j.put("username", news.getUsername());
				j.put("viewTimes", news.getViewTimes());
				j.put("content", news.getContent());
				j.put("updateTime", DateUtil.formatDate(news.getUpdateTime(), DateUtil.DATETIME));
				j.put("typeValue", news.getNewsType().getValue());
				j.put("typeName", news.getNewsType().getName());
				jsonArray.add(j);
				json.put("data", jsonArray.toString());
				writeRs(response, json);
				newsService.merge(news);
			}
			
		}
		logger.info("结束json查询热点新闻列表");
		return Action.NONE;
		
	}
	
	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	
}
