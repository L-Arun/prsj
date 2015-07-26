package rsj.admin.web.domain.hitechNews;

import java.io.Serializable;
import java.util.Date;

import rsj.admin.web.enums.HitechNewsType;
import com.lehecai.core.YesNoStatus;

public class HitechNews implements Serializable {

	/**
	 *高创园新闻
	 */
	private static final long serialVersionUID = -6100412628585480536L;
	
	public static final String ORDER_VIEW_TIMES = "viewTimes";
	
	private Long newsId;
	private String title;
	private HitechNewsType hitechNewsType;
	private Long viewTimes;
	private String content;
	private String username;
	private String updateUsername;
	private Date createTime;
	private Date updateTime;
	private String imagePath;
	private YesNoStatus isApply;
	private YesNoStatus isImageNews;
	private String memo;
	
	public Long getNewsId() {
		return newsId;
	}
	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUpdateUsername() {
		return updateUsername;
	}
	public void setUpdateUsername(String updateUsername) {
		this.updateUsername = updateUsername;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Long getViewTimes() {
		return viewTimes;
	}
	public void setViewTimes(Long viewTimes) {
		this.viewTimes = viewTimes;
	}
	public YesNoStatus getIsApply() {
		return isApply;
	}
	public void setIsApply(YesNoStatus isApply) {
		this.isApply = isApply;
	}
	public YesNoStatus getIsImageNews() {
		return isImageNews;
	}
	public void setIsImageNews(YesNoStatus isImageNews) {
		this.isImageNews = isImageNews;
	}
	public HitechNewsType getHitechNewsType() {
		return hitechNewsType;
	}
	public void setHitechNewsType(HitechNewsType hitechNewsType) {
		this.hitechNewsType = hitechNewsType;
	}

}
