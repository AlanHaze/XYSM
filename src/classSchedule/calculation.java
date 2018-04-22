package classSchedule;

import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFBorderFormatting;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.junit.experimental.theories.Theories;

//课程编排算法
public class calculation {
	private String year;
	private String mon;
	private String day;
	private Workbook workbook = new HSSFWorkbook();
	private Sheet sheet;
	private int dayOfWeek;
	private int dayOfYear;
	private String excelPath;
	private int current_row = 3;
	private CellStyle style;
	private int day_beginOfSemester;
	private static String Class;
	private static String Course;

	// 初始化代码块：设定正文通用单元格格式
	{
		style = workbook.createCellStyle();
		style.setBorderTop(HSSFBorderFormatting.BORDER_THIN);
		style.setBorderBottom(HSSFBorderFormatting.BORDER_THIN);
		style.setBorderLeft(HSSFBorderFormatting.BORDER_THIN);
		style.setBorderRight(HSSFBorderFormatting.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	}

	// 构造器
	public calculation() {
		// 获取系统时间并以次为文件名在D盘根目录下创建.xls工作表
		sheet = workbook.createSheet("理论课表");
		int current_year = Calendar.getInstance().get(Calendar.YEAR);
		int current_mon = Calendar.getInstance().get(Calendar.MONTH + 1);
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
		String datetime = tempDate.format(new Date());
		excelPath = "d:/" + datetime + ".xls";
		// 创建第一行
		Row row = sheet.createRow(0);
		// 创建第一行的各个单元格（int）为创建单元格的序号
		Cell cell_1 = row.createCell(0);
		// 创建合并单元格对象（开始行，结束行，开始列，结束列）
		CellRangeAddress cra = new CellRangeAddress(0, 2, 0, 16);
		// sheet.addMergedRegion(cra);
		if (current_mon > 7) {
			cell_1.setCellValue(current_year + "~" + (current_year + 1) + "学年第一学期" + Class + "理论课表");
		} else {
			cell_1.setCellValue((current_year - 1) + "~" + current_year +"学年第二学期" + Class + "理论课表");
		}
		// 设置标题格式
		CellStyle style_head = workbook.createCellStyle();
		cra_style_generic(cra);
		style_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style_head.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		Font font_head = workbook.createFont();
		font_head.setFontHeightInPoints((short) 18);
		style_head.setFont(font_head);
		cell_1.setCellStyle(style_head);
		// 创建第二行————表格表头，并设置格式与赋值
		Row row_0 = sheet.createRow(3);
		String[] head_0 = new String[] { "周次", "日期", "星期", "节次", "学时" };
		for (int i = 0; i < 17; i++) {
			Cell cell = row_0.createCell(i);
			if (i < 5) {
				cell.setCellValue(head_0[i]);
			}
			if (i == 5) {
				cell.setCellValue("授课内容");
			} else if (i == 13) {
				cell.setCellValue("授课老师");
			} else if (i == 15) {
				cell.setCellValue("跟课老师");
			}
			cell.setCellStyle(style);
		}
		CellRangeAddress cra_0 = new CellRangeAddress(3, 3, 5, 12);
		sheet.addMergedRegion(cra_0);
		CellRangeAddress cra_1 = new CellRangeAddress(3, 3, 13, 14);
		sheet.addMergedRegion(cra_1);
		CellRangeAddress cra_2 = new CellRangeAddress(3, 3, 15, 16);
		sheet.addMergedRegion(cra_2);
		try {
			FileOutputStream outputStream = new FileOutputStream(excelPath);
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			System.out.println("写入Excel失败: ");
			e.printStackTrace();
		}
	}

	// 接收dateGUI传递过来的各个变量
	public void getData(String year_1, int mon_1, String day_1) {
		year = year_1;
		mon = String.valueOf(mon_1);
		day = day_1;
	}
	public static void getClss(String Class_1) {
		Class = Class_1;
	}
	public static void getCourse(String Course_1) {
		Course = Course_1;
	}

	// 合并单元格通用格式
	private void cra_style_generic(CellRangeAddress cra) {
		RegionUtil.setBorderBottom(1, cra, sheet, workbook);
		RegionUtil.setBorderLeft(1, cra, sheet, workbook);
		RegionUtil.setBorderRight(1, cra, sheet, workbook);
		RegionUtil.setBorderTop(1, cra, sheet, workbook);
		sheet.addMergedRegion(cra);
	}

	// 判断是否为同一周
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	private boolean isSameWeek(String year, String mon, String day) {
		if (today_LT == null) {
			return true;
		}
		// 将当前日期赋值给today变量
		String today = year + "-" + mon + "-" + day;
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = format.parse(today);
			d2 = format.parse(today_LT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setFirstDayOfWeek(Calendar.MONDAY);// 将周一设为一周第一天
		cal2.setFirstDayOfWeek(Calendar.MONDAY);
		cal1.setTime(d1);
		cal2.setTime(d2);
		dayOfWeek = cal1.get(Calendar.DAY_OF_WEEK) - 1;
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		today_LT = year + "-" + mon + "-" + day;
		dayOfYear = cal1.get(Calendar.DAY_OF_YEAR);
		if (subYear == 0)// subYear==0,说明是同一年
		{
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (subYear == 1 && cal2.get(Calendar.MONTH) == 11) // subYear==1,说明cal比cal2大一年;java的一月用"0"标识，那么12月用"11"
		{
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (subYear == -1 && cal1.get(Calendar.MONTH) == 11)// subYear==-1,说明cal比cal2小一年
		{
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		return false;
	}

	// 设定本学期开始的日期
	private void day_beginOfSemester() {
		String begin = year + "-" + mon + "-" + day;
		Calendar c1 = Calendar.getInstance();
		Date d1 = null;
		try {
			d1 = format.parse(begin);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c1.setTime(d1);
		day_beginOfSemester = c1.get(Calendar.DAY_OF_YEAR);
	}

	// isMorning方法
	int weekCounter = 1;
	int count = 1;
	String year_LT = null;
	String mon_LT = null;
	String day_LT = null;
	String today_LT = null;
	public void isMorning() {
		// 保存上一次点击的日期
		System.out.println(Class + Course);
		if (count == 1) {
			today_LT = year + "-" + mon + "-" + day;
			day_beginOfSemester();
		}
		count++;
		// 用于点击后生成单元格
		for (int i = 0; i < 4; i++) {
			++current_row;
			Row row_morning = sheet.createRow((short) current_row);
			for (int j = 0; j < 5; j++) {
				Cell cell = row_morning.createCell((short) j);
				cell.setCellStyle(style);
				// 周次的赋值
				if (j == 0) {
					// 判断：是否为同一周（true）
					if (isSameWeek(year, mon, day)) {
						cell.setCellValue(String.valueOf(weekCounter));
						if (cell.getStringCellValue()
								.equals(sheet.getRow(current_row - 1).getCell(0).getStringCellValue())) {
							CellRangeAddress cra_date = new CellRangeAddress(current_row - 1, current_row, 0, 0);
							cra_style_generic(cra_date);
						}
						// 判断：是否为同一周（false）
					} else {
						int ret = dayOfYear - day_beginOfSemester;
						if (ret < 0) {
							if (Integer.parseInt(year) % 4 == 0 && Integer.parseInt(year) % 100 != 0
									|| Integer.parseInt(year) % 400 == 0) {// 闰年的判断规则
								ret += 366 - day_beginOfSemester;
								weekCounter += (ret / 7);
							} else {
								ret += 365 - day_beginOfSemester;
								weekCounter += (ret / 7);
							}
						} else {
							weekCounter = 1 + (ret / 7);
						}
						cell.setCellValue(String.valueOf(weekCounter));
						if (cell.getStringCellValue()
								.equals(sheet.getRow(current_row - 1).getCell(0).getStringCellValue())) {
							CellRangeAddress cra_date = new CellRangeAddress(current_row - 1, current_row, 0, 0);
							cra_style_generic(cra_date);
						}
					}
					// 日期的赋值
				} else if (j == 1) {
					cell.setCellValue(mon + "-" + day);
					if (cell.getStringCellValue()
							.equals(sheet.getRow(current_row - 1).getCell(1).getStringCellValue())) {
						CellRangeAddress cra_date = new CellRangeAddress(current_row - 1, current_row, 1, 1);
						cra_style_generic(cra_date);
					}
					// 当天为周几的赋值
				} else if (j == 2) {
					cell.setCellValue(String.valueOf(dayOfWeek));
					if (cell.getStringCellValue()
							.equals(sheet.getRow(current_row - 1).getCell(2).getStringCellValue())) {
						CellRangeAddress cra_dayOfWeek = new CellRangeAddress(current_row - 1, current_row, 2, 2);
						cra_style_generic(cra_dayOfWeek);
					}
				}
			}
			// 预留课程内容、课程老师与跟课老师的合并单元格
			CellRangeAddress cra_content = new CellRangeAddress(current_row, current_row, 5, 12);
			cra_style_generic(cra_content);
			CellRangeAddress cra_techer = new CellRangeAddress(current_row, current_row, 13, 14);
			cra_style_generic(cra_techer);
			CellRangeAddress cra_techer2 = new CellRangeAddress(current_row, current_row, 15, 16);
			cra_style_generic(cra_techer2);
		}
		try {
			FileOutputStream outputStream = new FileOutputStream(excelPath);
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			System.out.println("写入Excel失败: ");
			e.printStackTrace();
		}
	}

	// isAfternoon方法
	public void isAfternoon() {
		isMorning();
	}
}
