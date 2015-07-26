package rsj.admin.web.action.hitechNews;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rsj.admin.web.action.BaseAction;
import rsj.admin.web.bean.PageBean;
import rsj.admin.web.bean.UserSessionBean;
import rsj.admin.web.constant.Global;
import rsj.admin.web.domain.hitechNews.HitechNews;
import rsj.admin.web.enums.HitechNewsType;
import rsj.admin.web.service.hitechNews.HitechNewsService;
import rsj.admin.web.utils.PageUtil;

import com.lehecai.core.YesNoStatus;
import com.opensymphony.xwork2.Action;

public class HitechNewsAction extends BaseAction {
	private static final long serialVersionUID = 2436161530465382824L;
	private Logger logger = LoggerFactory.getLogger(HitechNewsAction.class);
	
	private HitechNewsService hitechNewsService;
	
	private HitechNews hitechNews;
	
	private List<HitechNews> hitechNewses;
	
	private Long newsId;
	private String title;
	private String username;
	private String content;
	private Date beginCreateTime;
	private Date endCreateTime;
	private Date beginUpdateTime;
	private Date endUpdateTime;
	private Integer hitechNewsTypeValue;
	private Integer isApplyValue;
	private Integer isImageNewsValue;
	private String imagePath;
	private String memo;
	
	public String handle() {
		logger.info("进入默认查询新闻列表");
		if (beginCreateTime == null) {
			beginCreateTime = getDefaultQueryBeginDate();
		}
		return query();
	}
	
	public String query() {
		logger.info("进入查询新闻列表");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		YesNoStatus isApply = null;
		HitechNewsType hitechNewsType = null;
		if(isApplyValue != null && isApplyValue != YesNoStatus.ALL.getValue()){
			isApply = YesNoStatus.getItem(isApplyValue);
		}
		YesNoStatus isImageNews = null;
		if(isImageNewsValue != null && isImageNewsValue != YesNoStatus.ALL.getValue()){
			isImageNews = YesNoStatus.getItem(isImageNewsValue);
		}
		if(hitechNewsTypeValue != null && hitechNewsTypeValue != HitechNewsType.ALL.getValue()){
			hitechNewsType = HitechNewsType.getItem(hitechNewsTypeValue);
		}
		hitechNewses = hitechNewsService.list(newsId, title, hitechNewsType, content, username, null, beginCreateTime, endCreateTime, beginUpdateTime, endUpdateTime, null, isApply, isImageNews, null, super.getPageBean());
		PageBean pageBean = hitechNewsService.getPageBean(newsId, title, hitechNewsType, content, username, null, beginCreateTime, endCreateTime, beginUpdateTime, endUpdateTime, null, isApply, isImageNews, null, super.getPageBean());
		super.setPageString(PageUtil.getPageString(request, pageBean));
		logger.info("结束查询新闻列表");
		
		return "list";
	}
	
	public String view(){
		logger.info("进入查询新闻信息");
		if(hitechNews != null && hitechNews.getNewsId() != null){
			hitechNews = hitechNewsService.get(hitechNews.getNewsId());
		}else{
			return "failure";
		}
		logger.info("结束查询新闻信息");
		return "view";
	}
	public String jumpInit() {
		return "init";
	}
	
	public String manage(){
		logger.info("进入修改新闻信息");		
		if (hitechNews == null) {
			return "failure";
		}
		UserSessionBean userSessionBean = (UserSessionBean) super.getSession().get(Global.USER_SESSION);
		String tmpUsername = userSessionBean.getUser().getName();
		
		if (hitechNewsTypeValue != null && hitechNewsTypeValue != 0) {
			hitechNews.setHitechNewsType(HitechNewsType.getItem(hitechNewsTypeValue));
		}
		if (isImageNewsValue != null && isImageNewsValue != YesNoStatus.ALL.getValue()) {
			hitechNews.setIsImageNews(YesNoStatus.getItem(isImageNewsValue));
			if (hitechNews.getIsImageNews().getValue() == YesNoStatus.YES.getValue()) {
				if(hitechNews.getImagePath() == null || "".equals(hitechNews.getImagePath())){
					hitechNews.setIsImageNews(YesNoStatus.NO);
				}
			}
		}
		hitechNews.setIsApply(YesNoStatus.NO);
		if(hitechNews.getNewsId() == null || hitechNews.getNewsId() == 0) {
			hitechNews.setUsername(tmpUsername);
			hitechNews.setCreateTime(new Date());
			hitechNews.setUpdateUsername(tmpUsername);
			hitechNews.setViewTimes(Long.valueOf(0));
			hitechNews.setUpdateTime(new Date());
			hitechNewsService.save(hitechNews);
		} else{
			hitechNews.setUpdateUsername(tmpUsername);
			hitechNews.setUpdateTime(new Date());
			hitechNewsService.merge(hitechNews);
		}
		logger.info("结束修改新闻信息");
		return "success";
	}
	public String input(){
		logger.info("进入输入新闻信息");
		if (hitechNews != null && hitechNews.getNewsId() != null) {
			hitechNews = hitechNewsService.get(hitechNews.getNewsId());
			isApplyValue = hitechNews.getIsApply().getValue();
			hitechNewsTypeValue = hitechNews.getHitechNewsType().getValue();
		}
		logger.info("进入输入新闻信息");
		return "inputForm";
	}
	public String apply() {
		logger.info("进入新闻审核");
		int rc = 0;
		String msg = "";
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject json = new JSONObject();
		
		if (hitechNews != null && hitechNews.getNewsId() != null && hitechNews.getNewsId() != 0) {
			hitechNews = hitechNewsService.get(hitechNews.getNewsId());
			if (hitechNews != null) {
				hitechNews.setIsApply(YesNoStatus.YES);
				hitechNewsService.merge(hitechNews);
				rc = 0;
			} else {
				rc = 1;
				msg = "审核程序错误";
			}
		} else {
			rc = 1;
			msg = "审核程序错误";
		}
		json.put("code", rc);
		json.put("msg", msg);
		writeRs(response, json);
		logger.info("结束新闻审核");
		return Action.NONE;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getBeginCreateTime() {
		return beginCreateTime;
	}

	public void setBeginCreateTime(Date beginCreateTime) {
		this.beginCreateTime = beginCreateTime;
	}

	public Date getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(Date endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	public Date getBeginUpdateTime() {
		return beginUpdateTime;
	}

	public void setBeginUpdateTime(Date beginUpdateTime) {
		this.beginUpdateTime = beginUpdateTime;
	}

	public Date getEndUpdateTime() {
		return endUpdateTime;
	}

	public void setEndUpdateTime(Date endUpdateTime) {
		this.endUpdateTime = endUpdateTime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<YesNoStatus> getYesNoStatuses(){
		return YesNoStatus.getItems();
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Integer getIsApplyValue() {
		return isApplyValue;
	}

	public void setIsApplyValue(Integer isApplyValue) {
		this.isApplyValue = isApplyValue;
	}

	public Integer getIsImageNewsValue() {
		return isImageNewsValue;
	}

	public void setIsImageNewsValue(Integer isImageNewsValue) {
		this.isImageNewsValue = isImageNewsValue;
	}

	public List<HitechNewsType> getNewsTypesQuery() {
		return HitechNewsType.getItemsForQuery();
	}
	
	public List<YesNoStatus> getYesNoStatusQuery() {
		return YesNoStatus.getItemsForQuery();
	}
	
	public List<HitechNewsType> getNewsTypes() {
		return HitechNewsType.getItems();
	}
	
	public List<YesNoStatus> getYesNoStatus() {
		return YesNoStatus.getItems();
	}

	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public HitechNewsService getHitechNewsService() {
		return hitechNewsService;
	}

	public void setHitechNewsService(HitechNewsService hitechNewsService) {
		this.hitechNewsService = hitechNewsService;
	}

	public HitechNews getHitechNews() {
		return hitechNews;
	}

	public void setHitechNews(HitechNews hitechNews) {
		this.hitechNews = hitechNews;
	}

	public List<HitechNews> getHitechNewses() {
		return hitechNewses;
	}

	public void setHitechNewses(List<HitechNews> hitechNewses) {
		this.hitechNewses = hitechNewses;
	}

	public Integer getHitechNewsTypeValue() {
		return hitechNewsTypeValue;
	}

	public void setHitechNewsTypeValue(Integer hitechNewsTypeValue) {
		this.hitechNewsTypeValue = hitechNewsTypeValue;
	}
	
}
