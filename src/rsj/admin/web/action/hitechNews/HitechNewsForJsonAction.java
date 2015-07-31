package rsj.admin.web.action.hitechNews;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rsj.admin.web.action.BaseAction;
import rsj.admin.web.domain.hitechNews.HitechNews;
import rsj.admin.web.service.hitechNews.HitechNewsService;
import rsj.admin.web.utils.DateUtil;

import com.lehecai.core.YesNoStatus;
import com.opensymphony.xwork2.Action;

public class HitechNewsForJsonAction extends BaseAction {
	private static final long serialVersionUID = 2436161530465382824L;
	private Logger logger = LoggerFactory.getLogger(HitechNewsForJsonAction.class);
	
	private HitechNewsService hitechNewsService;
	
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
			HitechNews hitechNews = hitechNewsService.get(newsId);
			if (hitechNews == null || hitechNews.getIsApply().getValue() == YesNoStatus.NO.getValue()) {
				rc = 1;
				msg = "新闻查询出错";
				json.put("code", rc);
				json.put("msg", msg);
				writeRs(response, json);
			} else {
				hitechNews.setViewTimes(hitechNews.getViewTimes() + 1);
				json.put("code", rc);
				json.put("msg", msg);
				JSONArray jsonArray = new JSONArray();
				JSONObject j = new JSONObject();
				j.put("key", hitechNews.getNewsId());
				j.put("title", hitechNews.getTitle());
				j.put("username", hitechNews.getUsername());
				j.put("viewTimes", hitechNews.getViewTimes());
				j.put("content", hitechNews.getContent());
				j.put("updateTime", DateUtil.formatDate(hitechNews.getCreateTime(), DateUtil.DATETIME));
				j.put("typeValue", hitechNews.getHitechNewsType().getValue());
				j.put("typeName", hitechNews.getHitechNewsType().getName());
				jsonArray.add(j);
				json.put("data", jsonArray.toString());
				writeRs(response, json);
				hitechNewsService.merge(hitechNews);
			}
			
		}
		logger.info("结束json查询热点新闻列表");
		return Action.NONE;
		
	}
	
	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	public HitechNewsService getHitechNewsService() {
		return hitechNewsService;
	}

	public void setHitechNewsService(HitechNewsService hitechNewsService) {
		this.hitechNewsService = hitechNewsService;
	}

	
}
