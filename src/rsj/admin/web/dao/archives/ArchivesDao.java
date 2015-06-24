package rsj.admin.web.dao.archives;

import java.util.Date;
import java.util.List;

import com.lehecai.core.YesNoStatus;

import rsj.admin.web.bean.PageBean;
import rsj.admin.web.domain.archives.Archives;
import rsj.admin.web.enums.ArchivesType;

public interface ArchivesDao {
	
	/**
	 * 多条件分页查询档案
	 * @param number 文号
	 * @param title 标题
	 * @param department 部门
	 * @param beginCreateTime 开始创建时间
	 * @param endCreateTime 结束创建时间
	 * @param beginUpdateTime 开始更新时间
	 * @param endUpdateTime 结束更新时间
	 * @param archivesType	档案类型
	 * @param path 保存路径
	 * @param isDigital 是否有电子版
	 * @param memo 备注
	 * @param pageBean
	 * @return
	 */
	List<Archives> list(String number, String title, String department, Date beginCreateTime, Date endCreateTime, Date beginUpdateTime, Date endUpdateTime,
			ArchivesType archivesType, String path, YesNoStatus isDigital, String memo, PageBean pageBean);
	/**
	 * 多条件查询分页信息
	 * @param number 文号
	 * @param title 标题
	 * @param department 部门
	 * @param beginCreateTime 开始创建时间
	 * @param endCreateTime 结束创建时间
	 * @param beginUpdateTime 开始更新时间
	 * @param endUpdateTime 结束更新时间
	 * @param archivesType	档案类型
	 * @param path 保存路径
	 * @param isDigital 是否有电子版
	 * @param memo 备注
	 * @param pageBean
	 * @return
	 */
	PageBean getPageBean(String number, String title, String department, Date beginCreateTime, Date endCreateTime, Date beginUpdateTime, Date endUpdateTime,
			ArchivesType archivesType, String path, YesNoStatus isDigital, String memo, PageBean pageBean);
	/**
	 * 根据ID查询档案
	 * @param id 文号
	 * @return
	 */
	Archives get(Long id);
	/**
	 * 添加
	 * @param archives
	 * @return
	 */
	void save(Archives archives);
	/**
	 * 修改
	 * @param archives
	 * @return
	 */
	void merge(Archives archives);
}
