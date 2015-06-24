package rsj.admin.web.dao.multiconfirm;

import java.util.List;

import rsj.admin.web.bean.PageBean;
import rsj.admin.web.multiconfirm.MulticonfirmRecord;
import rsj.admin.web.multiconfirm.MulticonfirmTask;

public interface MulticonfirmRecordDao {

	MulticonfirmRecord get(MulticonfirmTask task, Long userId);

	MulticonfirmRecord manage(MulticonfirmRecord record);

	List<MulticonfirmRecord> getRecordList(MulticonfirmTask task, PageBean pageBean);

	PageBean getRecordPageBean(MulticonfirmTask task, PageBean pageBean);

}