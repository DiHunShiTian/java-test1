package function;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class Editor extends JFrame implements ActionListener{
	
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
	
	JButton jbSure = new JButton("确认");
	JButton jbCancel = new JButton("取消");

	JLabel titleLabel = new JLabel();
    ImageIcon icon = new ImageIcon("src/resources/背景图.jpg");
    
    JPanel a = new JPanel();
    
    Font fontMenu = new Font("黑体",Font.PLAIN,25);
    
    Inquiry jframe;
    Object[][] list;
    int[] selectRows;

public Editor(Inquiry jframe,Object[][] list,int[] selectRows) {
	
	super("编辑课程功能");
	this.selectRows = selectRows;
	this.list = list;
	this.jframe = jframe;
	
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
	jbSure.setFont(fontMenu);
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
	a.add(jbSure);
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
	jbSure.setBounds(350,560,100,50);
	jbCancel.setBounds(550,560,100,50);
	
	jtfCouresId.setText("");
	
	titleLabel.setBounds(-100,-100,this.getWidth()+100,this.getHeight()+100);
    titleLabel.setIcon(icon);
    a.add(titleLabel);
    
    set();
	
    add(a);
    
    jbSure.addActionListener(this);
    jbCancel.addActionListener(this);
    
}
@Override
public void actionPerformed(ActionEvent e) {
	System.out.println("12"+list[selectRows[0]][0]+"21");
	if(e.getSource()==jbSure&&!jtfCouresId.getText().equals("")
			&&!jtfCouresName.getText().equals("")&&!jtfCouresXingzhi.getText().equals("")
			&&!jtfCouresTime.getText().equals("")&&!jtfCouresGrade.getText().equals("")
			&&!jtfCouresTerm.getText().equals("")&&!jtfCouresMan.getText().equals("")
			) {
		list[selectRows[0]][0]=jtfCouresId.getText();
		list[selectRows[0]][1]=jtfCouresName.getText();
		list[selectRows[0]][2]=jtfCouresXingzhi.getText();
		list[selectRows[0]][3]=jtfCouresTime.getText();
		list[selectRows[0]][4]=jtfCouresGrade.getText();
		list[selectRows[0]][5]=jtfCouresTerm.getText();
		list[selectRows[0]][6]=jtfCouresMan.getText();
		if(hasKey(jtfCouresId.getText())) {
			new Info(this,"此课程编号已存在");
		}
		else {
			this.jframe.edit(list);
			new Info(this, "编辑成功");
			
			dispose();
			new Inquiry();
		}
	}
	if(e.getSource()==jbCancel) {
		new Inquiry();
		dispose();
	}
}
public void set() {
	jtfCouresId.setText(String.valueOf(list[selectRows[0]][0]));
	jtfCouresName.setText(String.valueOf(list[selectRows[0]][1]));
	jtfCouresXingzhi.setText(String.valueOf(list[selectRows[0]][2]));
	jtfCouresTime.setText(String.valueOf(list[selectRows[0]][3]));
	jtfCouresGrade.setText(String.valueOf(list[selectRows[0]][4]));
	jtfCouresTerm.setText(String.valueOf(list[selectRows[0]][5]));
	jtfCouresMan.setText(String.valueOf(list[selectRows[0]][6]));
	
}
public boolean hasKey(String no){
	int count = 0;
	for(int i =0;i<list.length;i++) {
		if(list[i][0].toString().equals(no)) {
			count++;
		}
	}
	return count > 1;
}
}
