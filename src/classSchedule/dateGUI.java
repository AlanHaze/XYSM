package classSchedule;
import java.awt.BorderLayout;  
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;  
import java.awt.GridLayout;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.util.Date;  
import java.util.GregorianCalendar;  
import javax.swing.JButton;  
import javax.swing.JComboBox;  
import javax.swing.JFrame;  
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.poi.ss.formula.functions.Choose;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.CORBA.PUBLIC_MEMBER;  
  
 //CalendarTrain
public class dateGUI extends JFrame implements ActionListener{
	calculation cal = new calculation();
    //�·ݺ�������� �б���  
    private JComboBox MonthBox = new JComboBox();  
    private JComboBox YearBox = new JComboBox();  
      
    //����·ݱ�ǩ  
    private JLabel YearLabel = new JLabel("��ݣ�");  
    private JLabel MonthLabel = new JLabel("�·ݣ�");  
      
    //�鿴������Ԥ�������硢���簴ť  
    private JButton button_ok = new JButton("�鿴");  
    private JButton input_preview = new JButton("����Ԥ��"); 
    private JButton button_morning = new JButton("����");
    private JButton button_afternoon = new JButton("����");
      
      
    //��ȡ��������ڡ���ݺ��·�  
    private Date now_date = new Date();  
    private int now_year = now_date.getYear() + 1900;  
    private int now_month = now_date.getMonth();  
    private boolean todayFlag = false;  
      
    //��һ�鰴ť��ʾ���ڣ�һ��7��7�С���һ��������  
    private JButton[] button_day = new JButton[42];  
    private final String[] week = {"SUN","MON","TUE","WEN","THR","FRI","SAT"};  
    private JButton[] button_week = new JButton[7];  
    private String year_String;  
    private int month_int; 
    private String day_String;
    private String mroning;
    private String afternoon;
    /*���캯��*/  
    public dateGUI(){  
        this.setTitle("ѡ������");  
        this.init();  
        this.setLocation(500, 300);  
        this.setResizable(true);  
        pack();  
        this.setVisible(true);
    }  
      
    //��ʼ������  
    private void init() {  
        Font font = new Font("Dialog",Font.BOLD,16);  
        YearLabel.setFont(font);  
        MonthLabel.setFont(font);  
        button_ok.setFont(font);  
        input_preview.setFont(font);  
        //��ȥ20��--δ��20��  
        for(int i = now_year - 20;i <= now_year + 100;i++){  
            YearBox.addItem(i+"");  
        }  
        YearBox.setSelectedIndex(20);  
          
        for(int i = 1;i <= 13;i++){  
            MonthBox.addItem(i+"");  
        }  
        MonthBox.setSelectedIndex(now_month);  
          
        //���������б���Ϳ��ư�ť�����  
        JPanel panel_ym = new JPanel();  
        panel_ym.add(YearLabel);  
        panel_ym.add(YearBox);  
        panel_ym.add(MonthLabel);  
        panel_ym.add(MonthBox);  
        panel_ym.add(button_ok);  
        panel_ym.add(input_preview);
          
        //Ϊ��ť�����¼�������  
        button_ok.addActionListener(this);  
        input_preview.addActionListener(this);
        button_morning.addActionListener(this);
    	button_afternoon.addActionListener(this);
          
        JPanel panel_day = new JPanel();  
        //7*7  
        panel_day.setLayout(new GridLayout(7, 7, 3, 3));  
        for(int i = 0; i < 7; i++) {  
            button_week[i] = new JButton(" ");  
            button_week[i].setText(week[i]);  
            button_week[i].setForeground(Color.black);  
            panel_day.add(button_week[i]);  
        }  
        button_week[0].setForeground(Color.red);  
        button_week[6].setForeground(Color.red);  
          
        for(int i = 0; i < 42;i++){  
            button_day[i] = new JButton(" ");
            button_day[i].addActionListener(this);
            panel_day.add(button_day[i]);  
        }  
          
        this.paintDay();//��ʾ��ǰ����  
          
        JPanel panel_main = new JPanel();  
        panel_main.setLayout(new BorderLayout());  
        panel_main.add(panel_day,BorderLayout.SOUTH);  
        panel_main.add(panel_ym,BorderLayout.NORTH);  
        getContentPane().add(panel_main);  
              
    }  
  
    private void paintDay() {  
        if(todayFlag){  
            year_String = now_year +"";  
            month_int = now_month;  
        }else{  
            year_String = YearBox.getSelectedItem().toString();  
            month_int = MonthBox.getSelectedIndex();          
        }  
        int year_sel = Integer.parseInt(year_String) - 1900;  
        Date firstDay = new Date(year_sel, month_int, 1);  
        GregorianCalendar cal = new GregorianCalendar();  
        cal.setTime(firstDay);  
        int days = 0;  
        int day_week = 0;  
          
        if(month_int == 0||month_int == 2||month_int == 4||month_int == 6  
                ||month_int == 7||month_int == 9||month_int == 11){  
            days = 31;  
        }else if(month_int == 3||month_int == 5||month_int == 8||month_int == 10){  
            days = 30;  
        }else{  
            if(cal.isLeapYear(year_sel)){  
                days = 29;  
            }else{  
                days = 28;  
            }  
        }  
          
        day_week = firstDay.getDay();  
        int count = 1;  
        for(int i = day_week;i<day_week+days;count++,i++){ 
            if(i%7 == 0||(i+1)%7 == 0){  
                if((i == day_week+now_date.getDate()-1)&& month_int==now_month && (year_sel == now_year-1900)){  
                    button_day[i].setForeground(Color.BLUE);  
                    button_day[i].setText(count+"");  
                }else{  
                    button_day[i].setForeground(Color.RED);  
                    button_day[i].setText(count+"");  
                }  
            }else{  
                if((i == day_week+now_date.getDate()-1)&& month_int==now_month && (year_sel == now_year-1900)){  
                    button_day[i].setForeground(Color.BLUE);  
                    button_day[i].setText(count+"");  
                }else{  
                    button_day[i].setForeground(Color.BLACK);  
                    button_day[i].setText(count+"");  
                }  
            }  
              
        }
        
        if(day_week == 0){  
            for(int i = days;i<42;i++){  
                button_day[i].setText("");  
            }  
        }else{  
            for(int i = 0;i<day_week;i++){  
                button_day[i].setText("");  
            }  
            for(int i=day_week+days;i<42;i++){  
                button_day[i].setText("");  
            }  
        }  
          
          
    }
  //����ѡ�񵯴�
    private  void select(){
    	button_morning.setPreferredSize(new Dimension(120, 40));
    	button_afternoon.setPreferredSize(new Dimension(120, 40));
    	JFrame jf=new JFrame();
    	jf.setTitle(year_String + "��" + (month_int + 1) + "��" + day_String + "��");
    	jf.setLocationRelativeTo(null);
    	jf.setSize(350, 100);
    	JPanel p1 = new JPanel();
    	p1.add(button_morning,BorderLayout.CENTER);
    	p1.add(button_afternoon,BorderLayout.CENTER);
    	jf.add(p1);
    	jf.setVisible(true);
    }
    //Ԥ����
    private void preview() {
		
		
	}
    
    @Override  
    public void actionPerformed(ActionEvent e) {  
    	for (int i = 0; i < button_day.length; i++) {
            if (e.getSource().equals(button_day[i])) {
            	String button_dayText = button_day[i].getText();
            	if ( ! "".equals(button_dayText)) {
            		day_String = button_dayText;
            		select();
            		cal.getData(year_String, month_int + 1, day_String);
				}
            }
        }if(e.getSource()==button_ok){  
            todayFlag=false;  
            paintDay();  
        }else if(e.getSource()==input_preview){  
        	preview();
		}else if (e.getSource()==button_morning) {
			cal.isMorning();
		}else if (e.getSource()==button_afternoon) {
			cal.isAfternoon();
		} 
    }  

  
}  