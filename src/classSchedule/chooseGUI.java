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
 * ˼·�� 1����Ƶ�һ��GUIѡ���ٴ�or�����ǻor�����ƣ�
 *       2: ��Ƶڶ���GUIѡ�����ۿγ�/ʵϰ�γ̣�
 *       3����Ƶ�����GUIѡ��γ̾������ڣ�
 *       4����Ƶ��Ĳ�GUIѡ������/���磬������calculation������㷨���㲢����GUI��IO�����б����ļ��Ĵ������д��
 */
public class chooseGUI extends JFrame implements ActionListener {
	// �����γ�ѡ����༶ѡ��ť
	private JButton button_clinicalClass = new JButton("�ٴ�");
	private JButton button_anaesthesiologyStomatologyClass = new JButton("����&��ǻ");
	private JButton button_eightyearsClass = new JButton("������");
	private JButton button_theoreticalCourse = new JButton("���ۿγ�");
	private JButton button_practicalCourse = new JButton("ʵ���γ�");
	public static StringBuilder className;

	// ������
	public chooseGUI() {
		super();
		this.selectClass();
		this.setResizable(false);
	}

	// ѡ��༶����
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
		jf.setTitle("ѡ��༶");
		// Ԥ���û������
		jf.setLocationRelativeTo(null);
		jf.setSize(550, 100);
		JPanel p1 = new JPanel();
		p1.add(button_clinicalClass, BorderLayout.CENTER);
		p1.add(button_anaesthesiologyStomatologyClass, BorderLayout.CENTER);
		p1.add(button_eightyearsClass, BorderLayout.CENTER);
		jf.add(p1);
		jf.setVisible(true);
	}

	// ѡ��γ̴���
	private void selectCourse() {
		button_theoreticalCourse.setPreferredSize(new Dimension(120, 40));
		button_practicalCourse.setPreferredSize(new Dimension(120, 40));
		JFrame jf = new JFrame();
		jf.setTitle("ѡ��γ�");
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
