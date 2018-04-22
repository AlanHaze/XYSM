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

//�γ̱����㷨
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

	// ��ʼ������飺�趨����ͨ�õ�Ԫ���ʽ
	{
		style = workbook.createCellStyle();
		style.setBorderTop(HSSFBorderFormatting.BORDER_THIN);
		style.setBorderBottom(HSSFBorderFormatting.BORDER_THIN);
		style.setBorderLeft(HSSFBorderFormatting.BORDER_THIN);
		style.setBorderRight(HSSFBorderFormatting.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	}

	// ������
	public calculation() {
		// ��ȡϵͳʱ�䲢�Դ�Ϊ�ļ�����D�̸�Ŀ¼�´���.xls������
		sheet = workbook.createSheet("���ۿα�");
		int current_year = Calendar.getInstance().get(Calendar.YEAR);
		int current_mon = Calendar.getInstance().get(Calendar.MONTH + 1);
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
		String datetime = tempDate.format(new Date());
		excelPath = "d:/" + datetime + ".xls";
		// ������һ��
		Row row = sheet.createRow(0);
		// ������һ�еĸ�����Ԫ��int��Ϊ������Ԫ������
		Cell cell_1 = row.createCell(0);
		// �����ϲ���Ԫ����󣨿�ʼ�У������У���ʼ�У������У�
		CellRangeAddress cra = new CellRangeAddress(0, 2, 0, 16);
		// sheet.addMergedRegion(cra);
		if (current_mon > 7) {
			cell_1.setCellValue(current_year + "~" + (current_year + 1) + "ѧ���һѧ��" + Class + "���ۿα�");
		} else {
			cell_1.setCellValue((current_year - 1) + "~" + current_year +"ѧ��ڶ�ѧ��" + Class + "���ۿα�");
		}
		// ���ñ����ʽ
		CellStyle style_head = workbook.createCellStyle();
		cra_style_generic(cra);
		style_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style_head.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		Font font_head = workbook.createFont();
		font_head.setFontHeightInPoints((short) 18);
		style_head.setFont(font_head);
		cell_1.setCellStyle(style_head);
		// �����ڶ��С�����������ͷ�������ø�ʽ�븳ֵ
		Row row_0 = sheet.createRow(3);
		String[] head_0 = new String[] { "�ܴ�", "����", "����", "�ڴ�", "ѧʱ" };
		for (int i = 0; i < 17; i++) {
			Cell cell = row_0.createCell(i);
			if (i < 5) {
				cell.setCellValue(head_0[i]);
			}
			if (i == 5) {
				cell.setCellValue("�ڿ�����");
			} else if (i == 13) {
				cell.setCellValue("�ڿ���ʦ");
			} else if (i == 15) {
				cell.setCellValue("������ʦ");
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
			System.out.println("д��Excelʧ��: ");
			e.printStackTrace();
		}
	}

	// ����dateGUI���ݹ����ĸ�������
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

	// �ϲ���Ԫ��ͨ�ø�ʽ
	private void cra_style_generic(CellRangeAddress cra) {
		RegionUtil.setBorderBottom(1, cra, sheet, workbook);
		RegionUtil.setBorderLeft(1, cra, sheet, workbook);
		RegionUtil.setBorderRight(1, cra, sheet, workbook);
		RegionUtil.setBorderTop(1, cra, sheet, workbook);
		sheet.addMergedRegion(cra);
	}

	// �ж��Ƿ�Ϊͬһ��
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	private boolean isSameWeek(String year, String mon, String day) {
		if (today_LT == null) {
			return true;
		}
		// ����ǰ���ڸ�ֵ��today����
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
		cal1.setFirstDayOfWeek(Calendar.MONDAY);// ����һ��Ϊһ�ܵ�һ��
		cal2.setFirstDayOfWeek(Calendar.MONDAY);
		cal1.setTime(d1);
		cal2.setTime(d2);
		dayOfWeek = cal1.get(Calendar.DAY_OF_WEEK) - 1;
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		today_LT = year + "-" + mon + "-" + day;
		dayOfYear = cal1.get(Calendar.DAY_OF_YEAR);
		if (subYear == 0)// subYear==0,˵����ͬһ��
		{
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (subYear == 1 && cal2.get(Calendar.MONTH) == 11) // subYear==1,˵��cal��cal2��һ��;java��һ����"0"��ʶ����ô12����"11"
		{
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (subYear == -1 && cal1.get(Calendar.MONTH) == 11)// subYear==-1,˵��cal��cal2Сһ��
		{
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		return false;
	}

	// �趨��ѧ�ڿ�ʼ������
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

	// isMorning����
	int weekCounter = 1;
	int count = 1;
	String year_LT = null;
	String mon_LT = null;
	String day_LT = null;
	String today_LT = null;
	public void isMorning() {
		// ������һ�ε��������
		System.out.println(Class + Course);
		if (count == 1) {
			today_LT = year + "-" + mon + "-" + day;
			day_beginOfSemester();
		}
		count++;
		// ���ڵ�������ɵ�Ԫ��
		for (int i = 0; i < 4; i++) {
			++current_row;
			Row row_morning = sheet.createRow((short) current_row);
			for (int j = 0; j < 5; j++) {
				Cell cell = row_morning.createCell((short) j);
				cell.setCellStyle(style);
				// �ܴεĸ�ֵ
				if (j == 0) {
					// �жϣ��Ƿ�Ϊͬһ�ܣ�true��
					if (isSameWeek(year, mon, day)) {
						cell.setCellValue(String.valueOf(weekCounter));
						if (cell.getStringCellValue()
								.equals(sheet.getRow(current_row - 1).getCell(0).getStringCellValue())) {
							CellRangeAddress cra_date = new CellRangeAddress(current_row - 1, current_row, 0, 0);
							cra_style_generic(cra_date);
						}
						// �жϣ��Ƿ�Ϊͬһ�ܣ�false��
					} else {
						int ret = dayOfYear - day_beginOfSemester;
						if (ret < 0) {
							if (Integer.parseInt(year) % 4 == 0 && Integer.parseInt(year) % 100 != 0
									|| Integer.parseInt(year) % 400 == 0) {// ������жϹ���
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
					// ���ڵĸ�ֵ
				} else if (j == 1) {
					cell.setCellValue(mon + "-" + day);
					if (cell.getStringCellValue()
							.equals(sheet.getRow(current_row - 1).getCell(1).getStringCellValue())) {
						CellRangeAddress cra_date = new CellRangeAddress(current_row - 1, current_row, 1, 1);
						cra_style_generic(cra_date);
					}
					// ����Ϊ�ܼ��ĸ�ֵ
				} else if (j == 2) {
					cell.setCellValue(String.valueOf(dayOfWeek));
					if (cell.getStringCellValue()
							.equals(sheet.getRow(current_row - 1).getCell(2).getStringCellValue())) {
						CellRangeAddress cra_dayOfWeek = new CellRangeAddress(current_row - 1, current_row, 2, 2);
						cra_style_generic(cra_dayOfWeek);
					}
				}
			}
			// Ԥ���γ����ݡ��γ���ʦ�������ʦ�ĺϲ���Ԫ��
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
			System.out.println("д��Excelʧ��: ");
			e.printStackTrace();
		}
	}

	// isAfternoon����
	public void isAfternoon() {
		isMorning();
	}
}
