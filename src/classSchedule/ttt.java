package classSchedule;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ttt {
	public static void main(String[] args) {
		boolean b = isSameWeek("2018", "4", "17");
		System.out.println(b);
		boolean c = isSameWeek("2018", "4", "28");
		System.out.println(c);
	}
	public static boolean isSameWeek(String year, String mon, String day) {
		int count = 1;
		String year_LT = null;
		String mon_LT= null;
		String day_LT= null;
		if(count % 2 == 0){
			year_LT = year;
			mon_LT = mon;
			day_LT = day;
			count ++;
		}
		String today =year + "-" + mon + "-" + day;
		String today_LT = year_LT + "-" + mon_LT  + "-" + day_LT;
		System.out.println(today);
		System.out.println(today_LT);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		Date d1 = null;
		Date d2 = null;
		  try 
		  {
		   d1 = format.parse(today);
		   d2 = format.parse(today_LT);
		  }
		  catch (Exception e) 
		  {
		   e.printStackTrace();
		  }
		  Calendar cal1 = Calendar.getInstance();
		  Calendar cal2 = Calendar.getInstance();
		  cal1.setFirstDayOfWeek(Calendar.MONDAY);//��������Ϊһ�ܵĵ�һ�죬�۵ý���һ��Ϊһ�ܵ�һ��
		  cal2.setFirstDayOfWeek(Calendar.MONDAY);
		  cal1.setTime(d1);
		  cal2.setTime(d2);
		  int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		  if (subYear == 0)// subYear==0,˵����ͬһ��
		  {
		   if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
		    return true;
		   System.out.println("true");
		  }
		  else if (subYear == 1 && cal2.get(Calendar.MONTH) == 11) //subYear==1,˵��cal��cal2��һ��;java��һ����"0"��ʶ����ô12����"11"
		  {
		   if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
		    return true;
		   System.out.println("true");
		  }
		  else if (subYear == -1 && cal1.get(Calendar.MONTH) == 11)//subYear==-1,˵��cal��cal2Сһ��
		  {
		   if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
		    return true;
		   System.out.println("true");
		  }
		  System.out.println("false");
		  return false;
	}
}
