package rsj.admin.web.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelUtil {
	protected final static Logger logger = LoggerFactory.getLogger(ExcelUtil.class.getName());
	
	protected static InputStream excelInput;
	protected static Workbook rwb;

	public static Workbook createWorkbook(File excelFile) {
		try {
			excelInput = new FileInputStream(excelFile);
			rwb = new HSSFWorkbook(excelInput);
		 } catch (Exception e) {
			try {
				excelInput = new FileInputStream(excelFile);
				rwb = new XSSFWorkbook(excelInput);
			} catch (Exception e1){
				logger.error("上传的excel文件格式不对，{}", e);
				return null;
			}
		}
		 return rwb;
	}
	
	public static String getCellValue(Cell cell) {
		String cellValue = "";
		if (cell == null) {
			return cellValue;
		} else {
			switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					NumberFormat formater = NumberFormat.getInstance();
					formater.setGroupingUsed(false);
					cellValue = formater.format(cell.getNumericCellValue());
				break;
				case Cell.CELL_TYPE_STRING:
					cellValue = cell.getStringCellValue();
				break;
				case Cell.CELL_TYPE_FORMULA:
					cellValue = cell.getStringCellValue();
				break;
				case Cell.CELL_TYPE_BLANK:
					cellValue = cell.getStringCellValue();
				break;
				case Cell.CELL_TYPE_BOOLEAN:
					cellValue = Boolean.valueOf(cell.getBooleanCellValue()).toString();
				break;
				case Cell.CELL_TYPE_ERROR:
					cellValue = String.valueOf(cell.getErrorCellValue());
				break;
				default:
					cellValue = "";
			}
		}
		return cellValue.trim();

	}
	
	/**
	 * 关闭Excel文件
	 */
	public static void closeExcel() {
		try {
			if (excelInput != null) {
				excelInput.close();
			}
		} catch (IOException e) {
			logger.error("流文件关闭异常 ，{}", e);
		}
	}
}
