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
import rsj.admin.web.enums.NewsType;
import rsj.admin.web.service.news.NewsService;

import com.opensymphony.xwork2.Action;

public class NewsListForJsonAction extends BaseAction {
	private static final long serialVersionUID = 2436161530465382824L;
	private Logger logger = LoggerFactory.getLogger(NewsListForJsonAction.class);
	
	private NewsService newsService;
	
	private Integer listTypeValue;
	private Integer pageNumber;
	
	public String handle() {
		logger.info("进入json查询新闻列表");
		int rc = 0;
		String msg = "";
		List<News> newses = null;
		if (listTypeValue != null && listTypeValue != NewsType.ALL.getValue()) {
			HttpServletResponse response = ServletActionContext.getResponse();
			JSONObject json = new JSONObject();
			NewsType newsType = NewsType.getItem(listTypeValue);
			newses = newsService.listForJson(null, newsType, null, null, null, null, null, null, null, null, null, null, null);
			if (newses != null && newses.size() > 0) {
				json.put("code", rc);
				json.put("msg", msg);
				JSONArray jsonArray = new JSONArray();
				for (News news : newses) {
					JSONObject j = new JSONObject();
					j.put("key", news.getId());
					j.put("name", news.getTitle());
					jsonArray.add(j);
				}
				json.put("data", jsonArray.toString());
				writeRs(response, json);
				
			}
		}
		logger.info("结束json查询新闻列表");
		return Action.NONE;
		
	}
	
	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getListTypeValue() {
		return listTypeValue;
	}

	public void setListTypeValue(Integer listTypeValue) {
		this.listTypeValue = listTypeValue;
	}

	
}
