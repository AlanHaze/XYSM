package classSchedule;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.SelectableChannel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.Test;

/**
 * 思路： 1：设计第一层GUI选择临床or麻醉口腔or八年制；
 *       2: 设计第二层GUI选择理论课程/实习课程；
 *       3：设计第三层GUI选择课程具体日期；
 *       4：设计第四层GUI选择上午/下午，并调用calculation类进行算法计算并操作GUI和IO流进行本地文件的创建与读写；
 */
public class chooseGUI extends JFrame implements ActionListener {
	// 声明课程选择与班级选择按钮
	private JButton button_clinicalClass = new JButton("临床");
	private JButton button_anaesthesiologyStomatologyClass = new JButton("麻醉&口腔");
	private JButton button_eightyearsClass = new JButton("八年制");
	private JButton button_theoreticalCourse = new JButton("理论课程");
	private JButton button_practicalCourse = new JButton("实践课程");
	public static StringBuilder className;

	// 构造器
	public chooseGUI() {
		super();
		this.selectClass();
		this.setResizable(false);
	}

	// 选择班级窗口
	private void selectClass() {
		button_clinicalClass.addActionListener(this);
		button_anaesthesiologyStomatologyClass.addActionListener(this);
		button_eightyearsClass.addActionListener(this);
		button_theoreticalCourse.addActionListener(this);
		button_practicalCourse.addActionListener(this);
		button_clinicalClass.setPreferredSize(new Dimension(120, 40));
		button_anaesthesiologyStomatologyClass.setPreferredSize(new Dimension(120, 40));
		button_eightyearsClass.setPreferredSize(new Dimension(120, 40));
		JFrame jf = new JFrame();
		jf.setTitle("选择班级");
		// 预留用户输入框
		jf.setLocationRelativeTo(null);
		jf.setSize(550, 100);
		JPanel p1 = new JPanel();
		p1.add(button_clinicalClass, BorderLayout.CENTER);
		p1.add(button_anaesthesiologyStomatologyClass, BorderLayout.CENTER);
		p1.add(button_eightyearsClass, BorderLayout.CENTER);
		jf.add(p1);
		jf.setVisible(true);
	}

	// 选择课程窗口
	private void selectCourse() {
		button_theoreticalCourse.setPreferredSize(new Dimension(120, 40));
		button_practicalCourse.setPreferredSize(new Dimension(120, 40));
		JFrame jf = new JFrame();
		jf.setTitle("选择课程");
		jf.setLocationRelativeTo(null);
		jf.setSize(550, 100);
		JPanel p1 = new JPanel();
		p1.add(button_theoreticalCourse, BorderLayout.CENTER);
		p1.add(button_practicalCourse, BorderLayout.CENTER);
		jf.add(p1);
		jf.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button_clinicalClass) {
			selectCourse();
			String data = button_clinicalClass.getText();
			calculation.getClss(data);
			
		} else if (e.getSource() == button_eightyearsClass) {
			selectCourse();
			String data = button_eightyearsClass.getText();
			calculation.getClss(data);
		} else if (e.getSource() == button_anaesthesiologyStomatologyClass) {
			selectCourse();
			String data = button_anaesthesiologyStomatologyClass.getText();
			calculation.getClss(data);
		} else if (e.getSource() == button_theoreticalCourse) {
			new dateGUI();
			String data = button_theoreticalCourse.getText();
			calculation.getCourse(data);
		} else if (e.getSource() == button_practicalCourse) {
			new dateGUI();
			String data = button_theoreticalCourse.getText();
			calculation.getCourse(data);
		}
	}

	public static void main(String[] args) {
		chooseGUI cg = new chooseGUI();
		cg.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
}
