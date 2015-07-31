package rsj.admin.web.action.hitechNews;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rsj.admin.web.action.BaseAction;
import rsj.admin.web.bean.PageBean;
import rsj.admin.web.domain.hitechNews.HitechNews;
import rsj.admin.web.enums.HitechNewsType;
import rsj.admin.web.service.hitechNews.HitechNewsService;
import rsj.admin.web.utils.DateUtil;
import rsj.admin.web.utils.PageUtil;

import com.lehecai.core.YesNoStatus;
import com.opensymphony.xwork2.Action;

public class HitechNewsListForJsonAction extends BaseAction {
	private static final long serialVersionUID = 2436161530465382824L;
	private Logger logger = LoggerFactory.getLogger(HitechNewsListForJsonAction.class);
	
	private HitechNewsService hitechNewsService;
	
	private Integer listTypeValue;
	private Integer pageNumber;
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
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(newsSize);
		pageBean.setPage(pageNumber);
		hitechNewses = hitechNewsService.listForJson(hitechNewsType, YesNoStatus.YES, pageBean);
		pageBean = hitechNewsService.getListForJsonPageBean(hitechNewsType, YesNoStatus.YES, pageBean);
		if (hitechNewses != null && hitechNewses.size() > 0) {
			json.put("code", rc);
			json.put("msg", msg);
			JSONArray jsonArray = new JSONArray();
			for (HitechNews hitechNews : hitechNewses) {
				JSONObject j = new JSONObject();
				j.put("key", hitechNews.getNewsId());
				j.put("name", hitechNews.getTitle());
				j.put("updateTime", DateUtil.formatDate(hitechNews.getCreateTime(), DateUtil.DATETIME));
				jsonArray.add(j);
			}
			json.put("typeValue", hitechNewsType.getValue());
			json.put("typeName", hitechNewsType.getName());
			json.put("pageBean", PageUtil.getSimplePageString(pageBean));
			json.put("data", jsonArray.toString());
			writeRs(response, json);
			
		}
		logger.info("结束json查询新闻列表");
		return Action.NONE;
		
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

	public HitechNewsService getHitechNewsService() {
		return hitechNewsService;
	}

	public void setHitechNewsService(HitechNewsService hitechNewsService) {
		this.hitechNewsService = hitechNewsService;
	}

	
}
