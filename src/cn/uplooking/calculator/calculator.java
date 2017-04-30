package cn.uplooking.calculator;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class calculator extends Frame {

	private String str1 = null;// ������������ǰ����(�����ֵ��ַ���)
	private String str2 = null;// �����������������(�����ֵ��ַ���)
	private boolean flag = false;// �ж��������ǰ��С����
	private boolean flag2 = false;// �ж���������С����
	private String sign = null;// ����λ
	private double value1 = 0;// ������������ǰ������ֵ(double�͵���ֵ)
	private double value2 = 0;// �����������ǰ������ֵ(double�͵���ֵ)
	private double result = 0;// ������
	private boolean f = false;// ���Ⱥ�(����Ⱥ�ʱ����־),����ʱ��ʱһ�����ֱ�ӡ�ֱ����ֱ�ˡ�ֱ���������Ⱥŵ����

	public calculator() {
		this.setSize(240, 240);
		this.setTitle("������");
		Dimension dim = getToolkit().getScreenSize();
		double height = dim.getHeight();
		double width = dim.getWidth();
		int x = (int) (width - 240) / 2;
		int y = (int) (height - 180) / 2;
		this.setLocation(x, y);
		Panel();
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
		this.addWindowListener(new myAdapter());
	}

	public void Panel() {
		Panel p = new Panel();
		p.setBackground(Color.BLUE);
		Panel p2 = new Panel();
		p2.setBackground(Color.green);
		String[] str = { "", "Backspace", "CE", "C", "7", "8", "9", "/", "6",
				"5", "4", "*", "3", "2", "1", "-", "0", ".", "=", "+", };
		p2.setLayout(new GridLayout(5, 4, 3, 3));
		final TextField tf = new TextField(40);
		tf.setEditable(false);// �ı��򲻿ɱ༭
		p.add(tf);
		this.add(p, BorderLayout.NORTH);
		for (int i = 0; i < str.length; i++) {
			final Button b = new Button(str[i]);
			b.setBackground(Color.cyan);
			p2.add(b);
			this.add(p2, BorderLayout.SOUTH);
			if (i > 3 && i < 18 && i != 3 && i != 7 && i != 11 && i != 15) {
				 b.setBackground(Color.PINK);
				// ------------������ť�����¼�---------------------------
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (str1 == null && sign == null) {// �ж��Ƿ��ǵ�һ������
							if (!"0".equals(b.getLabel())) {// ���벻Ϊ��
								if (".".equals(b.getLabel())) {// ����ΪС����
									flag = true;
									str1 = "0.";
									value1 = Double.valueOf(str1);
									tf.setText(str1);
								} else {// ����Ĳ���С����
									str1 = b.getLabel();
									value1 = Double.valueOf(str1);
									tf.setText(str1);
								}
							} else {
							}// ��һ������0�����κβ���
						} else if (str1 != null && sign == null) {// �ǵ�һ������,�����������������֮ǰ
							if (flag) {// �ж�ǰ��������Ƿ���С����
								flag = true;
								if (".".equals(b.getLabel())) {// ǰ���Ѿ�����С����������������С���㲻���κβ���

								} else {
									str1 += b.getLabel();
									value1 = Double.valueOf(str1);
									tf.setText(str1);
								}
							} else if (".".equals(b.getLabel())) {// ǰ��û�������С����
								flag = true;// ����С����󣬱�ʶ�ı�
								str1 += b.getLabel();
								value1 = Double.valueOf(str1);
								tf.setText(str1);
							} else {
								str1 += b.getLabel();
								value1 = Double.valueOf(str1);
								tf.setText(str1);
							}
						} else if (str1 != null && sign != null) {// �����������
							if (str2 == null) {// �ж�ǰ��������Ƿ���С����
								// flag = true;
								if (".".equals(b.getLabel())) {// ǰ���Ѿ�����С����������������С���㲻���κβ���
									flag2 = true;
									str2 = "0.";
									tf.setText(str2);
								} else {
									str2 = b.getLabel();
									value2 = Double.valueOf(str2);
									tf.setText(str2);
								}
							} else if (flag2) {
								flag2 = true;
								if (".".equals(b.getLabel())) {
								} else {
									str2 += b.getLabel();
									value2 = Double.valueOf(str2);
									tf.setText(str2);
								}
							} else if(".".equals(b.getLabel())){
								flag2 = true;
								str2 += b.getLabel();
								value2 = Double.valueOf(str2);
								tf.setText(str2);
							}else{
								str2 += b.getLabel();
								value2 = Double.valueOf(str2);
								tf.setText(str2);
							}
						}
					}
				});
			}
			if (i == 0) {
				// b.setVisible(false);
			}// ����ո����κβ���
			if (i == 1) {// ����BackSpace��ť
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (sign == null && str1 != null) {// �ж�ǰ���Ƿ����������������������֪���ǳ��������ǰ�����ݻ��Ǻ��
							int len = str1.length();// ��str1Ϊ�գ��������ᱨ��ָ�룬������ǰ���ж���
							if (len == 0) {// �ж��Ƿ�����������
							} else if (len > 1) {
								str1 = str1.substring(0, len - 1);
								value1 = Double.valueOf(str1);
								tf.setText(str1);
							} else if (len == 1) {
								str1 = "";
								// value1 = Double.valueOf(str1);
								tf.setText(str1);
							}
						} else if (sign != null) {
							int len = str2.length();
							if (len == 0) {

							} else {
								str2 = str2.substring(0, len - 1);
								value2 = Double.valueOf(str2);
								tf.setText(str2);
							}
						}
					}
				});
			}
			if (i == 2) {// ���볷����ť
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (str1 != null && str2 == null && sign == null) {
							str1 = null;
							tf.setText("");
						} else if (str1 != null && sign != null && str2 == null) {
							sign = null;
							tf.setText(str1);
						} else if (str1 != null && sign != null && str2 != null) {
							str2 = null;
							tf.setText(str2);
						}

					}
				});

			}
			if (i == 3) {// ������հ�ť
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						str1 = null;
						str2 = null;
						sign = null;
						flag = false;
						flag2 = false;
						f = false;
						tf.setText("0");
						result = 0;
					}
				});
			}
			if (i == 7) {// ���롰/����
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (str1 == null) {// ��һ�����롰/��û���κβ���

						} else {
							if (sign == null && f == false) {// �ж�֮ǰû������������������Ⱥ�
								tf.setText(str1);// ��һ�����������ʱ��ʾ����
							} else if (result == 0) {
								result = value1 / value2;
								tf.setText(String.valueOf(result));
							} else if (sign != null && f == false) {
								result /= value2;
								tf.setText(String.valueOf(result));
							} else {
								tf.setText(String.valueOf(result));
								f = false;
							}
							sign = "/";
							str2 = null;
						}
					}
				});
			}
			if (i == 11) {// ���롰*����
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (str1 == null) {

						} else {
							if (sign == null && f == false) {// �ж�֮ǰû������������������Ⱥ�
								tf.setText(str1);// ��һ�����������ʱ��ʾ����
							} else if (result == 0) {
								result = value1 * value2;
								tf.setText(String.valueOf(result));
							} else if (sign != null && f == false) {
								result *= value2;
								tf.setText(String.valueOf(result));
							} else {
								tf.setText(String.valueOf(result));
								f = false;
							}
							sign = "*";
							str2 = null;
						}
					}
				});
			}
			if (i == 15) {// ���롰-����
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (str1 == null) {

						} else {
							if (sign == null && f == false) {// �ж�֮ǰû������������������Ⱥ�
								tf.setText(str1);// ��һ�����������ʱ��ʾ����
							} else if (result == 0) {
								result = value1 - value2;
								tf.setText(String.valueOf(result));
							} else if (sign != null && f == false) {
								result -= value2;
								tf.setText(String.valueOf(result));
							} else {
								tf.setText(String.valueOf(result));
								f = false;
							}
							sign = "-";
							str2 = null;
						}
					}
				});
			}
			if (i == 19) {// ���롰+����
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (str1 == null) {

						} else {
							if (sign == null && f == false) {// �ж�֮ǰû������������������Ⱥ�
								tf.setText(str1);// ��һ�����������ʱ��ʾ����
							} else if (result == 0) {
								result = value1 + value2;
								tf.setText(String.valueOf(result));
							} else if (sign != null && f == false) {
								result += value2;
								tf.setText(String.valueOf(result));
							} else {
								tf.setText(String.valueOf(result));
								f = false;
								// resultl();
							}
							sign = "+";
							str2 = null;
						}
					}
				});
			}
			if (i == 18) {// ���롰=����
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						f = true;
						if (value1 != 0 && value2 != 0) {
							if ("/".equals(sign)) {// ����
								if (result == 0) {// �ж��Ƿ��ǵ�һ�����
									result = value1 / value2;
									str2 = null;// ���������������������
									tf.setText(String.valueOf(result));
								} else {// �ǵ�һ������
									result = result / value2;
									str2 = null;// ���������������������
									tf.setText(String.valueOf(result));
								}
							} else if ("*".equals(sign)) {// �˷�
								if (result == 0) {// �ж��Ƿ��ǵ�һ�����
									result = value1 * value2;
									str2 = null;// ���������������������
									tf.setText(String.valueOf(result));
								} else {// �ǵ�һ������
									result = result * value2;
									str2 = null;// ���������������������
									tf.setText(String.valueOf(result));
								}
							} else if ("-".equals(sign)) {// ����
								if (result == 0) {// �ж��Ƿ��ǵ�һ�����
									result = value1 - value2;
									str2 = null;// ���������������������
									tf.setText(String.valueOf(result));
								} else {// �ǵ�һ������
									result = result - value2;
									str2 = null;// ���������������������
									tf.setText(String.valueOf(result));
								}
							} else if ("+".equals(sign)) {// �ӷ�
								// f=false;
								if (result == 0) {// �ж��Ƿ��ǵ�һ�����
									result = value1 + value2;
									str2 = null;// ���������������������
									tf.setText(String.valueOf(result));
								} else {// �ǵ�һ������
									result = result + value2;
									str2 = null;// ���������������������
									tf.setText(String.valueOf(result));
								}
							}
						}
					}
				});
			}

		}

	}

	public static void main(String[] args) {
		new calculator();
	}

}

// ------------��������ʵ�ֹرմ��巽��--------------
class myAdapter extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}
