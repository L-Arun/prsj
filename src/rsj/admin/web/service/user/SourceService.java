package rsj.admin.web.service.user;

import java.util.Map;

import rsj.admin.web.bean.PageBean;

import com.lehecai.core.exception.ApiRemoteCallFailedException;

public interface SourceService {

	public Map<String, Object> getResult(Integer id, Integer partnerId, String name, Integer status, PageBean pageBean) throws ApiRemoteCallFailedException;
	
	public boolean update(Integer id, Integer partnerId, String name, Integer status) throws ApiRemoteCallFailedException;

	public boolean create(Integer id, Integer partnerId, String name, Integer status) throws ApiRemoteCallFailedException;
	
	public boolean refreshSource(Integer partnerId);
}
