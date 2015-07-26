package rsj.admin.web.enums;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lehecai.core.IntegerBeanLabelItem;
import com.lehecai.core.YesNoStatus;

public class HitechNewsType extends IntegerBeanLabelItem{
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(YesNoStatus.class.getName());
	
	private static List<HitechNewsType> items = new ArrayList<HitechNewsType>();
	private static List<HitechNewsType> queryItems = new ArrayList<HitechNewsType>();
	
	protected HitechNewsType(String name, int value, boolean queryOnly) {
		super(HitechNewsType.class.getName(), name, value);
		
		queryItems.add(this);
		if (!queryOnly) {
			items.add(this);
		}
	}
	
	protected HitechNewsType(String name, int value) {
		this(name, value, false);
	}
	
	public static HitechNewsType getItem(int value){
		try {
			return (HitechNewsType)HitechNewsType.getResult(HitechNewsType.class.getName(), value);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	public static List<HitechNewsType> getItems() {
		return items;
	}
	
	public static List<HitechNewsType> getItemsForQuery() {
		return queryItems;
	}
	
	
	public final static HitechNewsType ALL = new HitechNewsType("全部", -1,true);
	
//	public final static HitechNewsType ZHENG_CE = new HitechNewsType("政策法规", 1);
//	public final static HitechNewsType ZHENG_WU = new HitechNewsType("政务信息", 2);
//	public final static HitechNewsType JIU_YE = new HitechNewsType("就业创业", 3);
//	public final static HitechNewsType SHE_BAO = new HitechNewsType("社会保障", 4);
//	public final static HitechNewsType REN_SHI = new HitechNewsType("人事人才", 5);
//	public final static HitechNewsType ZHONG_CAI = new HitechNewsType("仲裁监察", 6);
//	public final static HitechNewsType TONG_ZHI = new HitechNewsType("通知公告", 7);
//	public final static HitechNewsType JI_GOU = new HitechNewsType("机构设置", 8);
//	public final static HitechNewsType ZHAO_PIN = new HitechNewsType("招聘信息", 9);
//	public final static HitechNewsType WEN_DA = new HitechNewsType("政策问答", 10);
//	public final static HitechNewsType XIA_ZAI = new HitechNewsType("资料下载", 11);
	public final static HitechNewsType QI_TA = new HitechNewsType("其他", 99);
	
	
}
