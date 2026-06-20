package demo;
import java.awt.GridLayout; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JPanel; 
import javax.swing.JTextField;
public class Demo 
{
	public static void main(String[] args) 
	{ 
		JFrame jf = new JFrame();//构架frame框架
		JPanel inputpanel = new JPanel();//构造panel框架 
		 //Frame是框架窗体，有边框的，Panel是面板，无边框，一般把几个Panel加到一个Frame上
		//Sun公司的定义:Frame是带标题和边框的最顶层窗体;
		//Panel是个最简单的容器类，它提供空间让程序放其它组件，包括其它Panel。
	
		/** 
		*设计框架 
		*/ 
		JLabel jL1 = new JLabel("Source"); 
		JLabel jL2 = new JLabel("Target"); 
		final JTextField jtf1 = new JTextField(); 
		jtf1.setSize(10,10); //一个是横坐标，一个是纵坐标。或者说，一个是长度，一个是高度。在可视化控件中 
		final JTextField jtf2 = new JTextField(); 
		jtf2.setSize(10,10); 
	
		inputpanel.setLayout(new GridLayout(2,2)); //整体意思是将窗口布局设置为网格式布局，网格的行数和列数分别是2和2. 
		inputpanel.add(jL1); 
		inputpanel.add(jtf1); 
		inputpanel.add(jL2); 
		inputpanel.add(jtf2); 
	
		/** 
		*设计按钮 
		*/ 
		JPanel btnpanel = new JPanel(); 
		JButton jb1 = new JButton("Clear"); 
		jb1.addActionListener(new ActionListener() 
		{ 
		public void actionPerformed(ActionEvent e) 
		{ 
		jtf1.setText(""); 
		jtf2.setText(""); 
		} 
		}); 
		JButton jb2 = new JButton("Copy"); 
		jb2.addActionListener(new ActionListener() 
		{ 
		public void actionPerformed(ActionEvent e) 
		{ 
		jtf2.setText(jtf1.getText()); 
		} 
		}); 
		JButton jb3 = new JButton("Close"); 
		jb3.addActionListener(new ActionListener() 
		{ 
		public void actionPerformed(ActionEvent e) 
		{ 
		System.exit(0); 
		} 
		}); 
		/** 
		*调用显示布局 
		*/ 
		btnpanel.add(jb1); 
		btnpanel.add(jb2); 
		btnpanel.add(jb3); 
	
		jf.setLayout(new GridLayout(3,3)); 
		jf.add(inputpanel); 
		jf.add(btnpanel); 
		jf.setVisible(true); 
		jf.setSize(300,150); 
	}
}
