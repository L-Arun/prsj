package rsj.admin.web.enums;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lehecai.core.IntegerBeanLabelItem;
import com.lehecai.core.YesNoStatus;

public class NewsType extends IntegerBeanLabelItem{
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(YesNoStatus.class.getName());
	
	private static List<NewsType> items = new ArrayList<NewsType>();
	private static List<NewsType> queryItems = new ArrayList<NewsType>();
	
	protected NewsType(String name, int value, boolean queryOnly) {
		super(NewsType.class.getName(), name, value);
		
		queryItems.add(this);
		if (!queryOnly) {
			items.add(this);
		}
	}
	
	protected NewsType(String name, int value) {
		this(name, value, false);
	}
	
	public static NewsType getItem(int value){
		try {
			return (NewsType)NewsType.getResult(NewsType.class.getName(), value);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	public static List<NewsType> getItems() {
		return items;
	}
	
	public static List<NewsType> getItemsForQuery() {
		return queryItems;
	}
	
	
	public final static NewsType ALL = new NewsType("全部", -1,true);
	
	public final static NewsType ZHENG_CE = new NewsType("政策法规", 1);
	public final static NewsType ZHENG_WU = new NewsType("政务公开", 2);
	public final static NewsType JIU_YE = new NewsType("就业创业", 3);
	public final static NewsType SHE_BAO = new NewsType("社会保障", 4);
	public final static NewsType REN_SHI = new NewsType("人事人才", 5);
	public final static NewsType ZHONG_CAI = new NewsType("仲裁监察", 6);
	public final static NewsType TONG_ZHI = new NewsType("通知公告", 7);
	public final static NewsType JI_GOU = new NewsType("机构设置", 8);
	public final static NewsType QI_TA = new NewsType("其他", 9);
	
	
}
