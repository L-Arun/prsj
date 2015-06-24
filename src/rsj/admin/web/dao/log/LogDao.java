package rsj.admin.web.dao.log;

import java.util.Date;
import java.util.List;

import rsj.admin.web.bean.PageBean;
import rsj.admin.web.domain.log.Log;

public interface LogDao {
	List<Log> list(String userName,String name,Date beginDate,Date endDate,Long logType, String url, String actionName, String params, String ip, PageBean pageBean);
	PageBean getPageBean(String userName,String name,Date beginDate,Date endDate,Long logType, String url, String actionName, String params, String ip, PageBean pageBean);
	Log get(Long ID);
	void save(Log log);
}
