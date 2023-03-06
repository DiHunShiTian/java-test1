package function;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class searchclassstudents extends JFrame implements ActionListener
{

    public JPanel Psearchclassstudents =new JPanel();
    JLabel number=new JLabel("请输入需要查看课程编号:");
    JTextField numbertext=new JTextField();
    JButton sure=new JButton("确定");
    JButton cancel=new JButton ("取消");
    Font a=new Font("微软雅黑",Font.BOLD,20);//同一字体大小
    Color co=new Color(23);
    String[] title=new String[] {"学号：","姓名：","系别：","班级：","联系方式："};
    DefaultTableModel model=new DefaultTableModel(0,5);//设置大小
    JTable jt=new JTable() //设置为不可编辑
    {
        public boolean isCellEditable(int row, int column)
        {
            return false;
        }
    };

    //设置表头

    public searchclassstudents()
    {
        super("查询课学生所有选课");
        //---------该部分为表格

        jt.setFont(a);
        jt.getModel();
        numbertext.setFont(a);
        model.setColumnIdentifiers(title);
        DefaultTableCellRenderer r=new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        jt.setDefaultRenderer(Object.class,r);
        jt.setModel(model);
        jt.setRowHeight(30);
        JScrollPane jsp=new JScrollPane();
        jsp.setViewportView(jt);
        //--------
        //way();
        Psearchclassstudents.setSize(1400, 800);
        Psearchclassstudents.setLocation(0,0);
        Psearchclassstudents.setLayout(null);
        locat();
        setfont();
        number.setBounds(500,70,500,100);
        sure.setBounds(450,240,100,50);
        numbertext.setBounds(450,170,450,50);
        jsp.setBounds(100, 300, 1200, 400);
        cancel.setBounds(800,240,100,50);
        Psearchclassstudents.add(numbertext);
        Psearchclassstudents.add(sure);
        Psearchclassstudents.add(number);
        Psearchclassstudents.add(jsp);
        Psearchclassstudents.add(cancel);
        Psearchclassstudents.setOpaque(false);
        add(Psearchclassstudents);
        sure.addActionListener(this);
        cancel.addActionListener(this);
        setbg();
        setResizable(false);//禁止调整窗口大小
    }
    public void setfont()
    {
        sure.setFont(a);
        sure.setForeground(co);
        cancel.setFont(a);
        cancel.setForeground(co);
        number.setFont(new Font("微软雅黑",Font.BOLD,30));
        number.setForeground(co);


    }
    public void setbg()
    {
        ImageIcon icon = new ImageIcon("src/resources/背景图.png");//创建背景
        JLabel titleLabel = new JLabel();
        titleLabel.setBounds(0,0,1400,800);
        titleLabel.setIcon(icon);
        getLayeredPane().add(titleLabel, Integer.valueOf(Integer.MIN_VALUE));
        titleLabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        add(titleLabel);
        //设置背景图片
    }
    public void locat()
    {
        //设置窗体大小和位置
        Toolkit kit=Toolkit.getDefaultToolkit();
        Dimension sc=kit.getScreenSize();//程序居中显示
        setVisible(true);
        setSize(1400,800);
        setLayout(null);
        setLocation((sc.width-this.getWidth())/2+50,(sc.height-this.getHeight())/2-20);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        // TODO 自动生成的方法存根
        if(e.getSource()==cancel)
        {
            dispose();
        }
        if(e.getSource()==sure)
        {
            way();
        }

    }
    public void way()
    {
        try
        {
        	boolean yes=false;
            File f=new File("src/resources/classdata.txt");
            File f2=new File("src/resources/studentinfo.txt");
            BufferedReader raf=new BufferedReader(new FileReader(f));
            String line;
            model.setRowCount(0);
            while((line=raf.readLine())!=null)
            {
                String split[]=line.split("\t");
                if(split[1].equals(numbertext.getText()))  //将有这个课程的同学信息再studnetinfo中匹配
                {
                    BufferedReader br1=new BufferedReader(new FileReader(f2));
                    String line1;
                    while((line1=br1.readLine()) != null)  //将匹配的人员添加到表格中
                    {
                        String []s2=line1.split("\t");
                        if((split[0]).equals(s2[0]))
                        {
                            model.addRow(new Object[] {s2[0],s2[1],s2[2],s2[3],s2[4]});
                            yes=true;

                        }
                        jt.invalidate();
                    }
                    br1.close();
                }
            }
            raf.close();
            if(yes)
            new info1(this,"找到学生");
            else
            new info1(this,"该课程不存在学生");	

        }
        catch (IOException e1)
        {
            e1.printStackTrace();
            System.out.print(e1);
        }
    }

    public static void main(String []arg)
    {
        new searchclassstudents();


    }
}
