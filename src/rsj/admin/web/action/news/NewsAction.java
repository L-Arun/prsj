package rsj.admin.web.action.news;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rsj.admin.web.action.BaseAction;
import rsj.admin.web.bean.PageBean;
import rsj.admin.web.bean.UserSessionBean;
import rsj.admin.web.constant.Global;
import rsj.admin.web.domain.news.News;
import rsj.admin.web.enums.ArchivesType;
import rsj.admin.web.enums.NewsType;
import rsj.admin.web.service.news.NewsService;
import rsj.admin.web.utils.PageUtil;

import com.lehecai.core.YesNoStatus;

public class NewsAction extends BaseAction {
	private static final long serialVersionUID = 2436161530465382824L;
	private Logger logger = LoggerFactory.getLogger(NewsAction.class);
	
	private NewsService newsService;
	
	private News news;
	
	private List<News> newses;
	
	private String title;
	private String content;
	private Date beginCreateTime;
	private Date endCreateTime;
	private Date beginUpdateTime;
	private Date endUpdateTime;
	private Integer newsTypeValue;
	private Integer isApplyValue;
	private Integer isImageNewsValue;
	private String imagePath;
	private String memo;
	
	public String handle() {
		logger.info("进入查询新闻列表");
		
		if (beginCreateTime == null) {
			beginCreateTime = getDefaultQueryBeginDate();
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		
		YesNoStatus isApply = null;
		NewsType newsType = null;
		if(isApplyValue != null && isApplyValue != YesNoStatus.ALL.getValue()){
			isApply = YesNoStatus.getItem(isApplyValue);
		}
		YesNoStatus isImageNews = null;
		if(isImageNewsValue != null && isImageNewsValue != YesNoStatus.ALL.getValue()){
			isImageNews = YesNoStatus.getItem(isImageNewsValue);
		}
		if(newsTypeValue != null && newsTypeValue != NewsType.ALL.getValue()){
			newsType = NewsType.getItem(newsTypeValue);
		}
		newses = newsService.list(title, newsType, content, null, null, beginCreateTime, endCreateTime, beginUpdateTime, endUpdateTime, null, isApply, isImageNews, null, super.getPageBean());
		PageBean pageBean = newsService.getPageBean(title, newsType, content, null, null, beginCreateTime, endCreateTime, beginUpdateTime, endUpdateTime, null, isApply, isImageNews, null, super.getPageBean());
		super.setPageString(PageUtil.getPageString(request, pageBean));
		logger.info("结束查询新闻列表");
		
		return "list";
	}
	
	public String query() {
		logger.info("进入查询新闻列表");
		
		if (beginCreateTime == null) {
			beginCreateTime = getDefaultQueryBeginDate();
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		YesNoStatus isApply = null;
		NewsType newsType = null;
		if(isApplyValue != null && isApplyValue != YesNoStatus.ALL.getValue()){
			isApply = YesNoStatus.getItem(isApplyValue);
		}
		YesNoStatus isImageNews = null;
		if(isImageNewsValue != null && isImageNewsValue != YesNoStatus.ALL.getValue()){
			isImageNews = YesNoStatus.getItem(isImageNewsValue);
		}
		if(newsTypeValue != null && newsTypeValue != NewsType.ALL.getValue()){
			newsType = NewsType.getItem(newsTypeValue);
		}
		newses = newsService.list(title, newsType, content, null, null, beginCreateTime, endCreateTime, beginUpdateTime, endUpdateTime, null, isApply, isImageNews, null, super.getPageBean());
		PageBean pageBean = newsService.getPageBean(title, newsType, content, null, null, beginCreateTime, endCreateTime, beginUpdateTime, endUpdateTime, null, isApply, isImageNews, null, super.getPageBean());
		super.setPageString(PageUtil.getPageString(request, pageBean));
		logger.info("结束查询新闻列表");
		
		return "list";
	}
	
	public String view(){
		logger.info("进入查询新闻信息");
		if(news != null && news.getId() != null){
			news = newsService.get(news.getId());
		}else{
			return "failure";
		}
		logger.info("结束查询新闻信息");
		return "view";
	}
	public String manage(){
		logger.info("进入修改新闻信息");		
		if (news == null) {
			return "failure";
		}
		UserSessionBean userSessionBean = (UserSessionBean) super.getSession().get(Global.USER_SESSION);
		String username = userSessionBean.getUser().getUserName();
		
		if (newsTypeValue != null && newsTypeValue != 0) {
			news.setNewsType(NewsType.getItem(newsTypeValue));
		}
		if (isImageNewsValue != null && isImageNewsValue != YesNoStatus.ALL.getValue()) {
			news.setIsImageNews(YesNoStatus.getItem(isImageNewsValue));
		}
		news.setIsApply(YesNoStatus.NO);
		if(news.getId() == null || news.getId() == 0) {
			news.setUsername(username);
			news.setCreateTime(new Date());
			news.setUpdateUsername(username);
			news.setUpdateTime(new Date());
			newsService.save(news);
		} else{
			news.setUpdateUsername(username);
			news.setUpdateTime(new Date());
			newsService.merge(news);
		}
		logger.info("结束修改新闻信息");
		return "success";
	}
	public String input(){
		logger.info("进入输入新闻信息");
		if (news != null && news.getId() != null) {
			news = newsService.get(news.getId());
			isApplyValue = news.getIsApply().getValue();
			newsTypeValue = news.getNewsType().getValue();
		}
		logger.info("进入输入新闻信息");
		return "inputForm";
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

	public List<ArchivesType> getArchivesTypes() {
		return ArchivesType.getItems();
	}
	
	public List<ArchivesType> getArchivesTypesQuery() {
		return ArchivesType.getItemsForQuery();
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

	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public List<News> getNewses() {
		return newses;
	}

	public void setNewses(List<News> newses) {
		this.newses = newses;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getNewsTypeValue() {
		return newsTypeValue;
	}

	public void setNewsTypeValue(Integer newsTypeValue) {
		this.newsTypeValue = newsTypeValue;
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

	public List<NewsType> getNewsTypes() {
		return NewsType.getItems();
	}
	
	public List<YesNoStatus> getYesNoStatus() {
		return YesNoStatus.getItems();
	}
	
}