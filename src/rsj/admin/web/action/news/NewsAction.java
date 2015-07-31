package rsj.admin.web.action.news;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import rsj.admin.web.action.BaseAction;
import rsj.admin.web.bean.PageBean;
import rsj.admin.web.bean.UserSessionBean;
import rsj.admin.web.constant.Global;
import rsj.admin.web.domain.news.News;
import rsj.admin.web.domain.news.NewsMail;
import rsj.admin.web.enums.ArchivesType;
import rsj.admin.web.enums.NewsType;
import rsj.admin.web.service.news.NewsMailService;
import rsj.admin.web.service.news.NewsService;
import rsj.admin.web.utils.DateUtil;
import rsj.admin.web.utils.PageUtil;
import rsj.admin.web.utils.StringUtil;

import com.lehecai.core.YesNoStatus;
import com.opensymphony.xwork2.Action;

public class NewsAction extends BaseAction {
	private static final long serialVersionUID = 2436161530465382824L;
	private Logger logger = LoggerFactory.getLogger(NewsAction.class);
	
	private NewsService newsService;
	private NewsMailService newsMailService;
	
	private News news;
	
	private List<News> newses;
	
	private Long newsId;
	private String title;
	private String username;
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
	
	private JavaMailSender mailSender;
	private String mailToListStr; 
	
	public String handle() {
		logger.info("进入查询新闻列表");
		if (beginCreateTime == null) {
			beginCreateTime = getDefaultQueryBeginDate();
		}
		return query();
	}
	
	public String query() {
		logger.info("进入查询新闻列表");
		
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
		newses = newsService.list(newsId, title, newsType, content, username, null, beginCreateTime, endCreateTime, beginUpdateTime, endUpdateTime, null, isApply, isImageNews, null, super.getPageBean());
		PageBean pageBean = newsService.getPageBean(newsId, title, newsType, content, username, null, beginCreateTime, endCreateTime, beginUpdateTime, endUpdateTime, null, isApply, isImageNews, null, super.getPageBean());
		super.setPageString(PageUtil.getPageString(request, pageBean));
		logger.info("结束查询新闻列表");
		
		return "list";
	}
	
	public String view(){
		logger.info("进入查询新闻信息");
		if(news != null && news.getNewsId() != null){
			news = newsService.get(news.getNewsId());
		}else{
			return "failure";
		}
		logger.info("结束查询新闻信息");
		return "view";
	}
	public String jumpInit() {
		return "init";
	}
	public String init(){
		logger.info("进入初始化新闻信息");
		if (news == null) {
			return "failure";
		}
		UserSessionBean userSessionBean = (UserSessionBean) super.getSession().get(Global.USER_SESSION);
		String tmpUsername = userSessionBean.getUser().getName();
		
		if (newsTypeValue != null && newsTypeValue != 0) {
			news.setNewsType(NewsType.getItem(newsTypeValue));
		}
		if (isImageNewsValue != null && isImageNewsValue != YesNoStatus.ALL.getValue()) {
			news.setIsImageNews(YesNoStatus.getItem(isImageNewsValue));
			if (news.getIsImageNews().getValue() == YesNoStatus.YES.getValue()) {
				if(news.getImagePath() == null || "".equals(news.getImagePath())){
					news.setIsImageNews(YesNoStatus.NO);
				}
			}
		}
		news.setIsApply(YesNoStatus.YES);
		news.setUsername(tmpUsername);
		news.setUpdateUsername(tmpUsername);
		news.setCreateTime(DateUtil.parseDate(news.getMemo()));
		news.setUpdateTime(DateUtil.parseDate(news.getMemo()));
		newsService.save(news);
		logger.info("结束初始化新闻信息");
		return "success";
	}
	public String manage(){
		logger.info("进入修改新闻信息");		
		if (news == null) {
			return "failure";
		}
		UserSessionBean userSessionBean = (UserSessionBean) super.getSession().get(Global.USER_SESSION);
		String tmpUsername = userSessionBean.getUser().getName();
		
		if (newsTypeValue != null && newsTypeValue != 0) {
			news.setNewsType(NewsType.getItem(newsTypeValue));
		}
		if (isImageNewsValue != null && isImageNewsValue != YesNoStatus.ALL.getValue()) {
			news.setIsImageNews(YesNoStatus.getItem(isImageNewsValue));
			if (news.getIsImageNews().getValue() == YesNoStatus.YES.getValue()) {
				if(news.getImagePath() == null || "".equals(news.getImagePath())){
					news.setIsImageNews(YesNoStatus.NO);
				}
			}
		}
		news.setIsApply(YesNoStatus.NO);
		if(news.getNewsId() == null || news.getNewsId() == 0) {
			news.setUsername(tmpUsername);
			news.setCreateTime(new Date());
			news.setUpdateUsername(tmpUsername);
			news.setViewTimes(Long.valueOf(0));
			news.setUpdateTime(new Date());
			newsService.save(news);
		} else{
			
			news.setUpdateUsername(tmpUsername);
			news.setUpdateTime(new Date());
			newsService.merge(news);
		}
		logger.info("结束修改新闻信息");
		return "success";
	}
	public String input(){
		logger.info("进入输入新闻信息");
		if (news != null && news.getNewsId() != null) {
			news = newsService.get(news.getNewsId());
			isApplyValue = news.getIsApply().getValue();
			newsTypeValue = news.getNewsType().getValue();
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
		
		if (news != null && news.getNewsId() != null && news.getNewsId() != 0) {
			news = newsService.get(news.getNewsId());
			if (news != null) {
				news.setIsApply(YesNoStatus.YES);
				newsService.merge(news);
				rc = 0;
				msg = "审核通过";
				
				//自动发送邮件
				SimpleMailMessage mail = new SimpleMailMessage();    
				//使用辅助类MimeMessage设定参数  
				mail.setFrom("lpxrsjbgs@126.com");    
				mail.setSubject(news.getTitle());    
				mail.setText(news.getContent().replaceAll("</?[^>]+>", "").replaceAll("\\s*|\t|\r|\n", "").replaceAll("&nbsp;", "") + Global.MAIL_SUFFIX); 
				mail.setTo(StringUtil.split(mailToListStr, Global.MAIL_TO_SPILT_KEY));
				mailSender.send(mail);
				
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

	public List<NewsType> getNewsTypesQuery() {
		return NewsType.getItemsForQuery();
	}
	
	public List<YesNoStatus> getYesNoStatusQuery() {
		return YesNoStatus.getItemsForQuery();
	}
	
	public List<NewsType> getNewsTypes() {
		return NewsType.getItems();
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

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setMailToListStr(String mailToListStr) {
		this.mailToListStr = mailToListStr;
	}

	public NewsMailService getNewsMailService() {
		return newsMailService;
	}

	public void setNewsMailService(NewsMailService newsMailService) {
		this.newsMailService = newsMailService;
	}
	
	public List<NewsMail> getNewsMailList() {
		return newsMailService.list(null, null, null, null);
	}

}
