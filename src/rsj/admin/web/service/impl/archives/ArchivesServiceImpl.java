package rsj.admin.web.service.impl.archives;

import java.util.Date;
import java.util.List;

import rsj.admin.web.bean.PageBean;
import rsj.admin.web.dao.archives.ArchivesDao;
import rsj.admin.web.domain.archives.Archives;
import rsj.admin.web.enums.ArchivesType;
import rsj.admin.web.service.archives.ArchivesService;

import com.lehecai.core.YesNoStatus;

public class ArchivesServiceImpl implements ArchivesService {

	private ArchivesDao archivesDao;
	
	@Override
	public List<Archives> list(String number, String title, String department,
			Date beginCreateTime, Date endCreateTime, Date beginUpdateTime,
			Date endUpdateTime, ArchivesType archivesType, String path,
			YesNoStatus isDigital, String memo, PageBean pageBean) {
		// TODO Auto-generated method stub
		return archivesDao.list(number, title, department, beginCreateTime, endCreateTime, beginUpdateTime, endUpdateTime, archivesType, path, isDigital, memo, pageBean);
	}

	@Override
	public PageBean getPageBean(String number, String title, String department,
			Date beginCreateTime, Date endCreateTime, Date beginUpdateTime,
			Date endUpdateTime, ArchivesType archivesType, String path,
			YesNoStatus isDigital, String memo, PageBean pageBean) {
		// TODO Auto-generated method stub
		return archivesDao.getPageBean(number, title, department, beginCreateTime, endCreateTime, beginUpdateTime, endUpdateTime, archivesType, path, isDigital, memo, pageBean);
	}

	@Override
	public Archives get(Long id) {
		// TODO Auto-generated method stub
		return archivesDao.get(id);
	}

	@Override
	public void save(Archives archives) {
		// TODO Auto-generated method stub
		archives.setCreateTime(new Date());
		archives.setUpdateTime(new Date());
		archivesDao.save(archives);
	}


	@Override
	public void merge(Archives archives) {
		// TODO Auto-generated method stub
		archives.setUpdateTime(new Date());
		archivesDao.merge(archives);
	}

	public ArchivesDao getArchivesDao() {
		return archivesDao;
	}

	public void setArchivesDao(ArchivesDao archivesDao) {
		this.archivesDao = archivesDao;
	}
}
