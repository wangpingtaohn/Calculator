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

	private String str1 = null;// 存放输入运算符前的数(带数字的字符串)
	private String str2 = null;// 存放输入运算符后的数(带数字的字符串)
	private boolean flag = false;// 判断运算符号前的小数点
	private boolean flag2 = false;// 判断运算符后的小数点
	private String sign = null;// 符号位
	private double value1 = 0;// 存放输入运算符前的数的值(double型的数值)
	private double value2 = 0;// 存放输后运算符前的数的值(double型的数值)
	private double result = 0;// 运算结果
	private boolean f = false;// 给等号(输入等号时做标志),计算时有时一连贯的直加、直减、直乘、直除而不按等号的情况

	public calculator() {
		this.setSize(240, 240);
		this.setTitle("计算器");
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
		tf.setEditable(false);// 文本框不可编辑
		p.add(tf);
		this.add(p, BorderLayout.NORTH);
		for (int i = 0; i < str.length; i++) {
			final Button b = new Button(str[i]);
			b.setBackground(Color.cyan);
			p2.add(b);
			this.add(p2, BorderLayout.SOUTH);
			if (i > 3 && i < 18 && i != 3 && i != 7 && i != 11 && i != 15) {
				 b.setBackground(Color.PINK);
				// ------------单击按钮触发事件---------------------------
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (str1 == null && sign == null) {// 判断是否是第一次输入
							if (!"0".equals(b.getLabel())) {// 输入不为空
								if (".".equals(b.getLabel())) {// 输入为小数点
									flag = true;
									str1 = "0.";
									value1 = Double.valueOf(str1);
									tf.setText(str1);
								} else {// 输入的不是小数点
									str1 = b.getLabel();
									value1 = Double.valueOf(str1);
									tf.setText(str1);
								}
							} else {
							}// 第一次输入0不做任何操作
						} else if (str1 != null && sign == null) {// 非第一次输入,而且是在输入运算符之前
							if (flag) {// 判断前面输入的是否有小数点
								flag = true;
								if (".".equals(b.getLabel())) {// 前面已经输入小数点的情况下再输入小数点不做任何操作

								} else {
									str1 += b.getLabel();
									value1 = Double.valueOf(str1);
									tf.setText(str1);
								}
							} else if (".".equals(b.getLabel())) {// 前面没有输入过小数点
								flag = true;// 输入小数点后，标识改变
								str1 += b.getLabel();
								value1 = Double.valueOf(str1);
								tf.setText(str1);
							} else {
								str1 += b.getLabel();
								value1 = Double.valueOf(str1);
								tf.setText(str1);
							}
						} else if (str1 != null && sign != null) {// 运算符后输入
							if (str2 == null) {// 判断前面输入的是否有小数点
								// flag = true;
								if (".".equals(b.getLabel())) {// 前面已经输入小数点的情况下再输入小数点不做任何操作
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
			}// 输入空格不做任何操作
			if (i == 1) {// 键入BackSpace按钮
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (sign == null && str1 != null) {// 判断前面是否有输入运算符和数，方便知道是撤销运算符前的数据还是后的
							int len = str1.length();// 若str1为空，此条语句会报空指针，所有在前面判断了
							if (len == 0) {// 判断是否输入了数据
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
			if (i == 2) {// 键入撤销按钮
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
			if (i == 3) {// 键入清空按钮
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
			if (i == 7) {// 键入“/”号
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (str1 == null) {// 第一次输入“/”没有任何操作

						} else {
							if (sign == null && f == false) {// 判断之前没有输入任务运算符及等号
								tf.setText(str1);// 第一次输入运算符时显示不变
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
			if (i == 11) {// 键入“*”号
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (str1 == null) {

						} else {
							if (sign == null && f == false) {// 判断之前没有输入任务运算符及等号
								tf.setText(str1);// 第一次输入运算符时显示不变
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
			if (i == 15) {// 键入“-”号
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (str1 == null) {

						} else {
							if (sign == null && f == false) {// 判断之前没有输入任务运算符及等号
								tf.setText(str1);// 第一次输入运算符时显示不变
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
			if (i == 19) {// 键入“+”号
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (str1 == null) {

						} else {
							if (sign == null && f == false) {// 判断之前没有输入任务运算符及等号
								tf.setText(str1);// 第一次输入运算符时显示不变
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
			if (i == 18) {// 键入“=”号
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						f = true;
						if (value1 != 0 && value2 != 0) {
							if ("/".equals(sign)) {// 除法
								if (result == 0) {// 判断是否是第一次相除
									result = value1 / value2;
									str2 = null;// 运算完后，运算符后的数重置
									tf.setText(String.valueOf(result));
								} else {// 非第一次运算
									result = result / value2;
									str2 = null;// 运算完后，运算符后的数重置
									tf.setText(String.valueOf(result));
								}
							} else if ("*".equals(sign)) {// 乘法
								if (result == 0) {// 判断是否是第一次相乘
									result = value1 * value2;
									str2 = null;// 运算完后，运算符后的数重置
									tf.setText(String.valueOf(result));
								} else {// 非第一次运算
									result = result * value2;
									str2 = null;// 运算完后，运算符后的数重置
									tf.setText(String.valueOf(result));
								}
							} else if ("-".equals(sign)) {// 减法
								if (result == 0) {// 判断是否是第一次相减
									result = value1 - value2;
									str2 = null;// 运算完后，运算符后的数重置
									tf.setText(String.valueOf(result));
								} else {// 非第一次运算
									result = result - value2;
									str2 = null;// 运算完后，运算符后的数重置
									tf.setText(String.valueOf(result));
								}
							} else if ("+".equals(sign)) {// 加法
								// f=false;
								if (result == 0) {// 判断是否是第一次相加
									result = value1 + value2;
									str2 = null;// 运算完后，运算符后的数重置
									tf.setText(String.valueOf(result));
								} else {// 非第一次运算
									result = result + value2;
									str2 = null;// 运算完后，运算符后的数重置
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

// ------------适配器：实现关闭窗体方法--------------
class myAdapter extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}
