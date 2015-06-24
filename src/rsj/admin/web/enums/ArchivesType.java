package rsj.admin.web.enums;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lehecai.core.IntegerBeanLabelItem;
import com.lehecai.core.YesNoStatus;

public class ArchivesType extends IntegerBeanLabelItem{
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(YesNoStatus.class.getName());
	
	private static List<ArchivesType> items = new ArrayList<ArchivesType>();
	private static List<ArchivesType> queryItems = new ArrayList<ArchivesType>();
	
	protected ArchivesType(String name, int value, boolean queryOnly) {
		super(ArchivesType.class.getName(), name, value);
		
		queryItems.add(this);
		if (!queryOnly) {
			items.add(this);
		}
	}
	
	protected ArchivesType(String name, int value) {
		this(name, value, false);
	}
	
	public static ArchivesType getItem(int value){
		try {
			return (ArchivesType)ArchivesType.getResult(ArchivesType.class.getName(), value);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	public static List<ArchivesType> getItems() {
		return items;
	}
	
	public static List<ArchivesType> getItemsForQuery() {
		return queryItems;
	}
	
	
	public final static ArchivesType ALL = new ArchivesType("全部",-1,true);
	
	public final static ArchivesType XIANWEI = new ArchivesType("县委文件",1);
	public final static ArchivesType XIANZHENGFU = new ArchivesType("县政府文件",2);
	public final static ArchivesType XIANNEIBUMEN = new ArchivesType("县内各部门文件",3);
	public final static ArchivesType SHIJI = new ArchivesType("市级文件",4);
	public final static ArchivesType SHENGJI = new ArchivesType("省级文件",5);
	
}
