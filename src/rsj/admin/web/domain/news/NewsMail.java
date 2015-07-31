package rsj.admin.web.domain.news;

import java.io.Serializable;

import com.lehecai.core.YesNoStatus;

public class NewsMail implements Serializable {

	/**
	 * 新闻报送邮箱
	 */
	private static final long serialVersionUID = -6100412628585480536L;
	
	private Long newsMailId;
	private String newsMailName;
	private String newsMailAddr;
	private YesNoStatus isDefaultSend;
	private String memo;
	
	
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
	public YesNoStatus getIsDefaultSend() {
		return isDefaultSend;
	}
	public void setIsDefaultSend(YesNoStatus isDefaultSend) {
		this.isDefaultSend = isDefaultSend;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
