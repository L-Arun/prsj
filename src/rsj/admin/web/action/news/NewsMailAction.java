package rsj.admin.web.action.news;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rsj.admin.web.action.BaseAction;
import rsj.admin.web.domain.news.NewsMail;
import rsj.admin.web.service.news.NewsMailService;

import com.lehecai.core.YesNoStatus;

public class NewsMailAction extends BaseAction {
	private static final long serialVersionUID = 2436161530465382824L;
	private Logger logger = LoggerFactory.getLogger(NewsMailAction.class);
	
	private NewsMailService newsMailService;
	
	private NewsMail newsMail;
	
	private List<NewsMail> newsMails;
	
	private Long newsMailId;
	private String newsMailName;
	private String newsMailAddr;
	private Integer isDefaultSendValue;
	private String memo;
	
	public String handle() {
		logger.info("进入查询新闻报送邮箱列表");
		YesNoStatus isDefaultSend = null;
		if(isDefaultSendValue != null && isDefaultSendValue != YesNoStatus.ALL.getValue()){
			isDefaultSend = YesNoStatus.getItem(isDefaultSendValue);
		}
		newsMails = newsMailService.list(newsMailId, newsMailName, newsMailAddr, isDefaultSend);
		logger.info("结束查询新闻报送邮箱列表");
		return "list";
	}
	public String manage(){
		logger.info("进入修改新闻信息");		
		if (newsMail == null) {
			return "failure";
		}
		
		YesNoStatus isDefaultSend = null;
		if(isDefaultSendValue != null && isDefaultSendValue != YesNoStatus.ALL.getValue()){
			isDefaultSend = YesNoStatus.getItem(isDefaultSendValue);
		}
		
		newsMail.setIsDefaultSend(isDefaultSend);
		newsMailService.merge(newsMail);
		
		logger.info("结束修改新闻信息");
		return "success";
	}
	public String input(){
		logger.info("进入输入新闻信息");
		if (newsMail != null && newsMail.getNewsMailId() != null) {
			newsMail = newsMailService.get(newsMail.getNewsMailId());
			isDefaultSendValue = newsMail.getIsDefaultSend().getValue();
		}
		logger.info("进入输入新闻信息");
		return "inputForm";
	}
	public String del() {
		if (newsMail != null && newsMail.getNewsMailId() != null) {
			newsMail = newsMailService.get(newsMail.getNewsMailId());
			newsMailService.del(newsMail);
		} else {
			return "error";
		}
		return  handle();
	}

	public NewsMailService getNewsMailService() {
		return newsMailService;
	}

	public void setNewsMailService(NewsMailService newsMailService) {
		this.newsMailService = newsMailService;
	}

	public NewsMail getNewsMail() {
		return newsMail;
	}

	public void setNewsMail(NewsMail newsMail) {
		this.newsMail = newsMail;
	}

	public List<NewsMail> getNewsMails() {
		return newsMails;
	}

	public void setNewsMails(List<NewsMail> newsMails) {
		this.newsMails = newsMails;
	}

	public Long getNewsMailId() {
		return newsMailId;
	}

	public void setNewsMailId(Long newsMailId) {
		this.newsMailId = newsMailId;
	}

	public String getNewsMailName() {
		return newsMailName;
	}

	public void setNewsMailName(String newsMailName) {
		this.newsMailName = newsMailName;
	}

	public String getNewsMailAddr() {
		return newsMailAddr;
	}

	public void setNewsMailAddr(String newsMailAddr) {
		this.newsMailAddr = newsMailAddr;
	}

	public Integer getIsDefaultSendValue() {
		return isDefaultSendValue;
	}

	public void setIsDefaultSendValue(Integer isDefaultSendValue) {
		this.isDefaultSendValue = isDefaultSendValue;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public List<YesNoStatus> getYesNoStatuses() {
		return YesNoStatus.getItems();
	}
	
}
