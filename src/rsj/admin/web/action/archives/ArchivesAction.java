package rsj.admin.web.action.archives;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rsj.admin.web.action.BaseAction;
import rsj.admin.web.bean.PageBean;
import rsj.admin.web.domain.archives.Archives;
import rsj.admin.web.enums.ArchivesType;
import rsj.admin.web.service.archives.ArchivesService;
import rsj.admin.web.utils.PageUtil;

import com.lehecai.core.YesNoStatus;

public class ArchivesAction extends BaseAction {
	private static final long serialVersionUID = 2436161530465382824L;
	private Logger logger = LoggerFactory.getLogger(ArchivesAction.class);
	
	private ArchivesService archivesService;
	
	private Archives archives;
	
	private List<Archives> archiveses;
	
	private String number;
	private String title;
	private String department;
	private Date beginCreateTime;
	private Date endCreateTime;
	private Date beginUpdateTime;
	private Date endUpdateTime;
	private Integer archivesTypeValue;
	private String path;
	private Integer isDigitalValue;
	private String memo;
	
	public String handle() {
		logger.info("进入查询档案信息列表");
		
		if (beginCreateTime == null) {
			beginCreateTime = getDefaultQueryBeginDate();
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		
		YesNoStatus isDigital = null;
		ArchivesType archivesType = null;
		if(isDigitalValue != null && isDigitalValue != YesNoStatus.ALL.getValue()){
			isDigital = YesNoStatus.getItem(isDigitalValue);
		}
		if(archivesTypeValue != null && archivesTypeValue != ArchivesType.ALL.getValue()){
			archivesType = ArchivesType.getItem(archivesTypeValue);
		}
		archiveses = archivesService.list(number, title, department, beginCreateTime, endCreateTime, beginUpdateTime, endUpdateTime, archivesType, path, isDigital,
				memo, super.getPageBean());
		PageBean pageBean = archivesService.getPageBean(number, title, department, beginCreateTime, endCreateTime, beginUpdateTime, endUpdateTime, archivesType,
				path, isDigital, memo, super.getPageBean());
		super.setPageString(PageUtil.getPageString(request, pageBean));
		logger.info("结束查询档案信息列表");
		
		return "list";
	}
	
	public String query() {
		logger.info("进入查询档案信息列表");
		HttpServletRequest request = ServletActionContext.getRequest();
		
		YesNoStatus isDigital = null;
		ArchivesType archivesType = null;
		if(isDigitalValue != null && isDigitalValue != YesNoStatus.ALL.getValue()){
			isDigital = YesNoStatus.getItem(isDigitalValue);
		}
		if(archivesTypeValue != null && archivesTypeValue != ArchivesType.ALL.getValue()){
			archivesType = ArchivesType.getItem(archivesTypeValue);
		}
		archiveses = archivesService.list(number, title, department, beginCreateTime, endCreateTime, beginUpdateTime, endUpdateTime, archivesType, path, isDigital,
				memo, super.getPageBean());
		PageBean pageBean = archivesService.getPageBean(number, title, department, beginCreateTime, endCreateTime, beginUpdateTime, endUpdateTime, archivesType,
				path, isDigital, memo, super.getPageBean());
		super.setPageString(PageUtil.getPageString(request, pageBean));
		logger.info("结束查询日志信息列表");
		return "list";
	}
/**	
	@SuppressWarnings("deprecation")
	public String exportExcel() {
		logger.info("进入导出档案信息列表");
		
		
		YesNoStatus isDigital = null;
		ArchivesType archivesType = null;
		if(isDigitalValue != null && isDigitalValue != YesNoStatus.ALL.getValue()){
			isDigital = YesNoStatus.getItem(isDigitalValue);
		}
		if(archivesTypeValue != null && archivesTypeValue != ArchivesType.ALL.getValue()){
			archivesType = ArchivesType.getItem(archivesTypeValue);
		}
		archiveses = archivesService.list(number, title, department, beginCreateTime, endCreateTime, beginUpdateTime, endUpdateTime, archivesType, path, isDigital,
				memo, null);
		
		HSSFWorkbook wb = new HSSFWorkbook();  
        HSSFSheet sheet = wb.createSheet("sheet1");  
        for (int i = 0; i < archiveses.size(); i++) {  
        	HSSFRow row = sheet.createRow((int) i + 1);  
            Archives a = archiveses.get(i);  
            // 第四步，创建单元格，并设置值  
            row.createCell((short) 0).setCellValue(a.getNumber());  
            row.createCell((short) 1).setCellValue(a.getTitle());  
            row.createCell((short) 2).setCellValue(a.getDepartment());  
            row.createCell((short) 3).setCellValue(new SimpleDateFormat("yyyy-mm-dd").format(a.getCreateTime()));  
            row.createCell((short) 4).setCellValue(a.getArchivesType().getName());  
            row.createCell((short) 5).setCellValue(a.getIsDigital().getName());  
            row.createCell((short) 6).setCellValue(a.getPath());  
            row.createCell((short) 7).setCellValue(a.getTitle());  
        }  
        // 第六步，将文件存到指定位置  
        try  
        {  
            FileOutputStream fout = new FileOutputStream("E:/archives.xls");  
            wb.write(fout);  
            fout.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
		
		logger.info("结束导出日志信息列表");
		
		return "list";
	}
	*/
	public String view(){
		logger.info("进入查询文件信息");
		if(archives != null && archives.getId() != null){
			archives = archivesService.get(archives.getId());
		}else{
			return "failure";
		}
		logger.info("结束查询文件信息");
		return "view";
	}
	public String manage(){
		logger.info("进入修改文件信息");		
		if (isDigitalValue != null && isDigitalValue != YesNoStatus.ALL.getValue()) {
			archives.setIsDigital(YesNoStatus.getItem(isDigitalValue));
		}
		if (archivesTypeValue != null && archivesTypeValue != 0) {
			archives.setArchivesType(ArchivesType.getItem(archivesTypeValue));
		}
		if (archives != null) {
			if(archives.getId() == null || archives.getId() == 0) {
				archivesService.save(archives);
			} else{
				archivesService.merge(archives);
			}
		}else{
			return "failure";
		}
		logger.info("结束修改文件信息");
		return "success";
	}
	public String input(){
		logger.info("进入输入文件信息");
		if (archives != null && archives.getId() != null) {
			archives = archivesService.get(archives.getId());
			isDigitalValue = archives.getIsDigital().getValue();
			archivesTypeValue = archives.getArchivesType().getValue();
		}
		logger.info("进入输入文件信息");
		return "inputForm";
	}

	public ArchivesService getArchivesService() {
		return archivesService;
	}

	public void setArchivesService(ArchivesService archivesService) {
		this.archivesService = archivesService;
	}

	public Archives getArchives() {
		return archives;
	}

	public void setArchives(Archives archives) {
		this.archives = archives;
	}

	public List<Archives> getArchiveses() {
		return archiveses;
	}

	public void setArchiveses(List<Archives> archiveses) {
		this.archiveses = archiveses;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Date getBeginCreateTime() {
		return beginCreateTime;
	}

	public void setBeginCreateTime(Date beginCreateTime) {
		this.beginCreateTime = beginCreateTime;
	}

	public Date getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(Date endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	public Date getBeginUpdateTime() {
		return beginUpdateTime;
	}

	public void setBeginUpdateTime(Date beginUpdateTime) {
		this.beginUpdateTime = beginUpdateTime;
	}

	public Date getEndUpdateTime() {
		return endUpdateTime;
	}

	public void setEndUpdateTime(Date endUpdateTime) {
		this.endUpdateTime = endUpdateTime;
	}

	public Integer getArchivesTypeValue() {
		return archivesTypeValue;
	}

	public void setArchivesTypeValue(Integer archivesTypeValue) {
		this.archivesTypeValue = archivesTypeValue;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getIsDigitalValue() {
		return isDigitalValue;
	}

	public void setIsDigitalValue(Integer isDigitalValue) {
		this.isDigitalValue = isDigitalValue;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<ArchivesType> getArchivesTypes() {
		return ArchivesType.getItems();
	}
	
	public List<ArchivesType> getArchivesTypesQuery() {
		return ArchivesType.getItemsForQuery();
	}
	
	public List<YesNoStatus> getYesNoStatuses(){
		return YesNoStatus.getItems();
	}

}
