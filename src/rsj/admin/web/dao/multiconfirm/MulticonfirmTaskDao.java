package rsj.admin.web.dao.multiconfirm;

import java.util.Date;
import java.util.List;

import rsj.admin.web.bean.PageBean;
import rsj.admin.web.multiconfirm.MulticonfirmTask;
import rsj.admin.web.multiconfirm.MulticonfirmTaskStatus;

public interface MulticonfirmTaskDao {

	MulticonfirmTask getTask(Long id);

	List<MulticonfirmTask> getTaskList(Long id, Long configId, String taskKey,
			MulticonfirmTaskStatus taskStatus, Date createTimeFrom, Date createTimeTo, Date timtoutTimeFrom, Date timeoutTimeTo, PageBean pageBean);

	PageBean getTaskPageBean(Long id, Long configId, String taskKey,
			MulticonfirmTaskStatus taskStatus, Date createTimeFrom, Date createTimeTo, Date timtoutTimeFrom, Date timeoutTimeTo, PageBean pageBean);

	MulticonfirmTask manage(MulticonfirmTask task);

	MulticonfirmTask getTask(String taskKey, MulticonfirmTaskStatus taskStatus);

}