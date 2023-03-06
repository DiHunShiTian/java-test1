package function;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MainShow extends JFrame implements ActionListener
{

    JLabel t1=new JLabel("欢迎进入");
    JLabel t2=new JLabel("学生选修课管理系统");//欢迎文字

    JPanel a=new JPanel();//菜单栏面板
    JPanel b=new JPanel();//功能显示面板

    JMenuBar jMenuBar = new JMenuBar();//菜单条
    JMenu jmClass = new JMenu("课程管理");
    JMenu jmStudent = new JMenu("学生管理");
    JMenu jmChoise = new JMenu("选修管理");
    JMenu jmCount = new JMenu("统计报表");
    JMenu jmExit = new JMenu("退出系统");

    JMenuItem jmiAddClass = new JMenuItem("新增课程");
    JMenuItem jmiInquiryClass = new JMenuItem("查询课程");
    JMenuItem jmiAddStudent = new JMenuItem("新增学生");
    JMenuItem jmiInquiryStudent = new JMenuItem("查询学生");
    JMenuItem jmiAddClassToStudent = new JMenuItem("添加学生所选修课程");
    JMenuItem jmiDeleteClassToStudent = new JMenuItem("删除学生所选修课程");
    JMenuItem jmiInquiryAllStudent = new JMenuItem("查询课程所有学生");
    JMenuItem jmiInquiryAllClass = new JMenuItem("查询学生所有课程");
    JMenuItem jmiCount = new JMenuItem("选修统计");

    Font fontMenu = new Font("黑体",Font.PLAIN,20);
    Font fontMenuItem = new Font("黑体",Font.PLAIN,18);


    JLabel titleLabel = new JLabel();
    ImageIcon icon = new ImageIcon("src/resources/背景图.png");//创建背景

    public MainShow()
    {
        super("学生选修课程系统");

        a.setLayout(null);
        a.setVisible(true);
        b.setLayout(null);
        b.setVisible(true);

        jMenuBar.add(jmClass);
        jMenuBar.add(jmStudent);
        jMenuBar.add(jmChoise);
        jMenuBar.add(jmCount);
        jMenuBar.add(jmExit);

        jmClass.add(jmiAddClass);
        jmClass.addSeparator();
        jmClass.add(jmiInquiryClass);
        jmStudent.add(jmiAddStudent);
        jmStudent.addSeparator();
        jmStudent.add(jmiInquiryStudent);
        jmChoise.add(jmiAddClassToStudent);
        jmChoise.addSeparator();
        jmChoise.add(jmiDeleteClassToStudent);
        jmChoise.addSeparator();
        jmChoise.add(jmiInquiryAllStudent);
        jmChoise.addSeparator();
        jmChoise.add(jmiInquiryAllClass);
        jmCount.add(jmiCount);

        jmClass.setFont(fontMenu);
        jmStudent.setFont(fontMenu);
        jmChoise.setFont(fontMenu);
        jmCount.setFont(fontMenu);
        jmExit.setFont(fontMenu);
        jmiAddClass.setFont(fontMenuItem);
        jmiInquiryClass.setFont(fontMenuItem);
        jmiAddStudent.setFont(fontMenuItem);
        jmiInquiryStudent.setFont(fontMenuItem);
        jmiAddClassToStudent.setFont(fontMenuItem);
        jmiDeleteClassToStudent.setFont(fontMenuItem);
        jmiInquiryAllStudent.setFont(fontMenuItem);
        jmiInquiryAllClass.setFont(fontMenuItem);
        jmiCount.setFont(fontMenuItem);

        a.add(jMenuBar);
        b.add(t1);
        b.add(t2);
        add(a);
        add(b);

        jMenuBar.setBounds(0, 0, 1400, 32);
        a.setBounds(0, 0, 1400, 32);
        b.setBounds(0, 0, 1400, 800);
        t1.setBounds(550, 200, 300, 100);
        t2.setBounds(450, 350, 700, 100);
        t1.setFont(new Font("楷体",Font.BOLD,60));
        t2.setFont(new Font("楷体",Font.BOLD,60));
        t1.setForeground(Color.cyan);
        t2.setForeground(Color.cyan);


        titleLabel.setBounds(0,0,1600,1000);
        titleLabel.setIcon(icon);
        b.add(titleLabel);

        jmiAddClass.addActionListener(this);
        jmiInquiryClass.addActionListener(this);
        jmiAddStudent.addActionListener(this);
        jmiInquiryStudent.addActionListener(this);
        jmiAddClassToStudent.addActionListener(this);
        jmiDeleteClassToStudent.addActionListener(this);
        jmiInquiryAllStudent.addActionListener(this);
        jmiInquiryAllClass.addActionListener(this);
        jmiCount.addActionListener(this);
        jmExit.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                System.exit(0);
            }
        });

        setSize(1400,800);//设置窗口大小
        setLocationRelativeTo(null);//设置窗体相对于另一组间的居中位置，参数null表示窗体相对于屏幕的中央位置
        setResizable(false);//禁止调整窗口大小
        setDefaultCloseOperation(3);//设置窗体关闭操作，3表示关闭窗体退出程序
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
    	if(e.getSource()==jmiInquiryClass) {
    		new Inquiry();
    	}
    	if(e.getSource()==jmiAddClass) {
    		new AddCouresTest();
    	}
    	if(e.getSource()==jmiCount) {
    		new CountAll();
    	}
        if(e.getSource()==jmiAddStudent)
        {
            new student();
        }
        if(e.getSource()==jmiInquiryStudent)
        {
            new findstudent();
        }
        if(e.getSource()==jmiAddClassToStudent)
        {
            new addElectiveCourse();//添加

        }
        if(e.getSource()==jmiDeleteClassToStudent)
        {
            new delete();//删除
        }
        if(e.getSource()==jmiInquiryAllStudent)
        {
            new searchclassstudents();//查询课程的所有学生
         
        }
        if(e.getSource()==jmiInquiryAllClass)
        {
            new searchstudentsclass();//查询学生的所有选课
        }
    }

    public static void main(String[] args)
    {
        new MainShow();
    }

}