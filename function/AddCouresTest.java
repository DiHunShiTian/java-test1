package function;

import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.swing.*;



public class AddCouresTest extends JFrame implements ActionListener{
	
		JLabel CouresId = new JLabel("课程编号：",JLabel.RIGHT);
		JLabel CouresName = new JLabel("课程名称：",JLabel.RIGHT);
		JLabel CouresXingzhi = new JLabel("课程性质：",JLabel.RIGHT);
		JLabel CouresTime = new JLabel("总学时：",JLabel.RIGHT);
		JLabel CouresGrade = new JLabel("学分：",JLabel.RIGHT);
		JLabel CouresTerm = new JLabel("开课学期：",JLabel.RIGHT);
		JLabel CouresMan = new JLabel("允许的选修人数：",JLabel.RIGHT);
	
		JTextField jtfCouresId = new JTextField();
		JTextField jtfCouresName = new JTextField();
		JTextField jtfCouresXingzhi = new JTextField();
		JTextField jtfCouresTime = new JTextField();
		JTextField jtfCouresGrade = new JTextField();
		JTextField jtfCouresTerm = new JTextField();
		JTextField jtfCouresMan = new JTextField();
		
		JButton jbAdd = new JButton("添加");
		JButton jbCancel = new JButton("取消");
	
		JLabel titleLabel = new JLabel();
	    ImageIcon icon = new ImageIcon("src/resources/背景图.jpg");
	    
	    JPanel a = new JPanel();
	    
	    Font fontMenu = new Font("黑体",Font.PLAIN,25);
	    
	    
	public AddCouresTest() {
		
		super("添加课程功能");
		
		setSize(900,700);//设置窗口大小
		setLocationRelativeTo(null);//设置窗体相对于另一组间的居中位置，参数null表示窗体相对于屏幕的中央位置
		setResizable(false);//禁止调整窗口大小
		setVisible(true);
		
		a.setLayout(null);//取消布局设置
		
		CouresId.setFont(fontMenu);
		CouresName.setFont(fontMenu);
		CouresXingzhi.setFont(fontMenu);
		CouresTime.setFont(fontMenu);
		CouresGrade.setFont(fontMenu);
		CouresTerm.setFont(fontMenu);
		CouresMan.setFont(fontMenu);
		jtfCouresId.setFont(fontMenu);
		jtfCouresName.setFont(fontMenu);
		jtfCouresXingzhi.setFont(fontMenu);
		jtfCouresTime.setFont(fontMenu);
		jtfCouresGrade.setFont(fontMenu);
		jtfCouresTerm.setFont(fontMenu);
		jtfCouresMan.setFont(fontMenu);
		jbAdd.setFont(fontMenu);
		jbCancel.setFont(fontMenu);
		
		a.add(CouresId);
		a.add(CouresName);
		a.add(CouresXingzhi);
		a.add(CouresTime);
		a.add(CouresGrade);
		a.add(CouresTerm);
		a.add(CouresMan);
		a.add(jtfCouresId);
		a.add(jtfCouresName);
		a.add(jtfCouresXingzhi);
		a.add(jtfCouresTime);
		a.add(jtfCouresGrade);
		a.add(jtfCouresTerm);
		a.add(jtfCouresMan);
		a.add(jbAdd);
		a.add(jbCancel);
		
		CouresId.setBounds(100,20,200,40);
		CouresName.setBounds(100,100,200,40);
		CouresXingzhi.setBounds(100,180,200,40);
		CouresTime.setBounds(100,260,200,40);
		CouresGrade.setBounds(100,340,200,40);
		CouresTerm.setBounds(100,420,200,40);
		CouresMan.setBounds(100,500,200,40);
		jtfCouresId.setBounds(300,20,400,40);
		jtfCouresName.setBounds(300,100,400,40);
		jtfCouresXingzhi.setBounds(300,180,400,40);
		jtfCouresTime.setBounds(300,260,400,40);
		jtfCouresGrade.setBounds(300,340,400,40);
		jtfCouresTerm.setBounds(300,420,400,40);
		jtfCouresMan.setBounds(300,500,400,40);
		jbAdd.setBounds(350,560,100,50);
		jbCancel.setBounds(550,560,100,50);
		
		titleLabel.setBounds(-100,-100,this.getWidth()+100,this.getHeight()+100);
        titleLabel.setIcon(icon);
        a.add(titleLabel);
		
        add(a);
        
        jbAdd.addActionListener(this);
        jbCancel.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==jbAdd) {//点击添加按钮
			try {
				
				File file = new File("src/resources/coures.txt");
				
				BufferedReader in = new BufferedReader(new FileReader(file));
				boolean have = true;
				String line;
				if(jtfCouresId.getText().equals("")||jtfCouresName.getText().equals("")||
						jtfCouresXingzhi.getText().equals("")||jtfCouresTime.getText().equals("")||
						jtfCouresGrade.getText().equals("")||jtfCouresTerm.getText().equals("")||
						jtfCouresMan.getText().equals("")) {
					new Info(this, "存在信息尚未填写！！");
				}else {
				while((line = in.readLine())!=null) {
						String s[] = line.split("\t");
						if(s[0].equals(jtfCouresId.getText())==true) {
							have=false;
						{
						new Info(this, "该课程编号已存在！！\\n请重新输入");
						}
						break;
						}				
				}
				in.close();
				
				if(have) {
				
				BufferedWriter out = new BufferedWriter(new FileWriter(file,true));
				
				out.write(jtfCouresId.getText());
				out.write("\t");
				out.write(jtfCouresName.getText());
				out.write("\t");
				out.write(jtfCouresXingzhi.getText());
				out.write("\t");
				out.write(jtfCouresTime.getText());
				out.write("\t");
				out.write(jtfCouresGrade.getText());
				out.write("\t");
				out.write(jtfCouresTerm.getText());
				out.write("\t");
				out.write(jtfCouresMan.getText());
				out.write("\t");
				out.write("0");
				out.write("\n");
				
				out.close();
				new Info(this, "添加成功");
				jtfCouresId.setText("");//清空
				jtfCouresName.setText("");
				jtfCouresXingzhi.setText("");
				jtfCouresTime.setText("");
				jtfCouresGrade.setText("");
				jtfCouresTerm.setText("");
				jtfCouresMan.setText("");
				}
			}
		}
				
				catch (FileNotFoundException fe)
	            {
	                System.err.println(fe);
	            }
	            catch (IOException ioe)
	            {
	                System.err.println(ioe);
	            }
		}
		
		if(e.getSource()==jbCancel) {//点击取消按钮
			dispose();
		}
	}
}
