package rsj.admin.web.action.news;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rsj.admin.web.action.BaseAction;
import rsj.admin.web.bean.PageBean;
import rsj.admin.web.domain.news.News;
import rsj.admin.web.enums.NewsType;
import rsj.admin.web.service.news.NewsService;
import rsj.admin.web.utils.DateUtil;
import rsj.admin.web.utils.PageUtil;

import com.lehecai.core.YesNoStatus;
import com.opensymphony.xwork2.Action;

public class NewsListForJsonAction extends BaseAction {
	private static final long serialVersionUID = 2436161530465382824L;
	private Logger logger = LoggerFactory.getLogger(NewsListForJsonAction.class);
	
	private NewsService newsService;
	
	private Integer listTypeValue;
	private Integer pageNumber;
	private Integer newsSize;
	
	public String handle() {
		logger.info("进入json查询新闻列表");
		int rc = 0;
		String msg = "";
		List<News> newses = null;
		NewsType newsType = null;
		if (listTypeValue != null && listTypeValue != NewsType.ALL.getValue()) {
			newsType = NewsType.getItem(listTypeValue);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject json = new JSONObject();
		newses = newsService.listForJson(newsType, YesNoStatus.YES, newsSize);
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(newsSize);
		pageBean.setPage(pageNumber);
		pageBean = newsService.getListForJsonPageBean(newsType, YesNoStatus.YES, pageBean);
		if (newses != null && newses.size() > 0) {
			json.put("code", rc);
			json.put("msg", msg);
			JSONArray jsonArray = new JSONArray();
			for (News news : newses) {
				JSONObject j = new JSONObject();
				j.put("key", news.getNewsId());
				j.put("name", news.getTitle());
				j.put("updateTime", DateUtil.formatDate(news.getUpdateTime(), DateUtil.DATETIME));
				jsonArray.add(j);
			}
			json.put("typeValue", newsType.getValue());
			json.put("typeName", newsType.getName());
			json.put("pageBean", PageUtil.getSimplePageString(pageBean));
			json.put("data", jsonArray.toString());
			writeRs(response, json);
			
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

	public Integer getNewsSize() {
		return newsSize;
	}

	public void setNewsSize(Integer newsSize) {
		this.newsSize = newsSize;
	}

	
}
