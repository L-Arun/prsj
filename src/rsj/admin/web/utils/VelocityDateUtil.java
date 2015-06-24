/**
 * 
 */
package rsj.admin.web.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lehecai.core.util.CoreDateUtils;

/**
 * @author Sunshow
 *
 */
public class VelocityDateUtil {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	public static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE = "yyyy-MM-dd";
	
	public String format(String formatPattern, String datestr, String parsePattern) {
		try {
			SimpleDateFormat parseSdf = new SimpleDateFormat(parsePattern, Locale.CHINA);
			Date date = parseSdf.parse(datestr);
			
			SimpleDateFormat sdf = new SimpleDateFormat(formatPattern, Locale.CHINA);
			return sdf.format(date);
		} catch (Exception e) {
			logger.error("日期转换错误", e);
			return "";
		}
	}

	public String formatDateTime(String pattern, String datestr) {
		return format(pattern, datestr, DATETIME);
	}
	
	public String formatDate(String pattern, String datestr) {
		return format(pattern, datestr, DATE);
	}
	
	public int getDayOfWeek() {
		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
	}
	
	public String now() {
		return CoreDateUtils.formatDateTime(new Date());
	}
	
	public static void main(String[] args) {
		VelocityDateUtil util = new VelocityDateUtil();
		System.out.println(util.formatDateTime("yyyy-MM-dd", "2011-01-16 22:30:00 "));
	}
}
