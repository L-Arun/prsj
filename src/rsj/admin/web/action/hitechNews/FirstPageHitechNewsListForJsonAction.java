package rsj.admin.web.action.hitechNews;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rsj.admin.web.action.BaseAction;
import rsj.admin.web.domain.hitechNews.HitechNews;
import rsj.admin.web.enums.HitechNewsType;
import rsj.admin.web.service.hitechNews.HitechNewsService;

import com.lehecai.core.YesNoStatus;
import com.opensymphony.xwork2.Action;

public class FirstPageHitechNewsListForJsonAction extends BaseAction {
	private static final long serialVersionUID = 2436161530465382824L;
	private Logger logger = LoggerFactory.getLogger(FirstPageHitechNewsListForJsonAction.class);
	
	private HitechNewsService hitechNewsService;
	
	private Integer listTypeValue;
	private Integer newsSize;
	
	public String handle() {
		logger.info("进入json查询新闻列表");
		int rc = 0;
		String msg = "";
		List<HitechNews> hitechNewses = null;
		HitechNewsType hitechNewsType = null;
		if (listTypeValue != null && listTypeValue != HitechNewsType.ALL.getValue()) {
			hitechNewsType = HitechNewsType.getItem(listTypeValue);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject json = new JSONObject();
		hitechNewses = hitechNewsService.listForJson(hitechNewsType, YesNoStatus.YES, newsSize);
		if (hitechNewses != null && hitechNewses.size() > 0) {
			json.put("code", rc);
			json.put("msg", msg);
			JSONArray jsonArray = new JSONArray();
			for (HitechNews hitechNews : hitechNewses) {
				JSONObject j = new JSONObject();
				j.put("key", hitechNews.getNewsId());
				j.put("name", hitechNews.getTitle());
				jsonArray.add(j);
			}
			json.put("data", jsonArray.toString());
			writeRs(response, json);
		}
		logger.info("结束json查询新闻列表");
		return Action.NONE;
		
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

	public HitechNewsService getHitechNewsService() {
		return hitechNewsService;
	}

	public void setHitechNewsService(HitechNewsService hitechNewsService) {
		this.hitechNewsService = hitechNewsService;
	}

	
}
