package function;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import java.io.*;

class sdinfo //学生信息类
{
    String id;
    String name;
    String faculty;
    String banji;
    String tel;
}

public class student extends JFrame implements ActionListener//添加学生
{

    String no=new String();
    String name=new String();
    String faculty=new String();
    String Class=new String();
    String tel=new String();

    JLabel xh=new JLabel("学号：");
    JLabel xm=new JLabel("姓名：");
    JLabel yx=new JLabel("院系：");
    JLabel bj=new JLabel("班级：");
    JLabel dh=new JLabel("联系方式：");
    JLabel ts=new JLabel("添加学生信息");
    JButton qr=new JButton("确认");
    JButton qx=new JButton("取消");

    JTextField xuehao = new JTextField();
    JTextField xingming = new JTextField();
    JTextField yuanxi = new JTextField();
    JTextField banji = new JTextField();
    JTextField dianhua = new JTextField();
    Toolkit kit=Toolkit.getDefaultToolkit();
    Dimension sc=kit.getScreenSize();//使应用在屏幕正中心

    static Font a=new Font("微软雅黑",Font.CENTER_BASELINE,30);


    public student()
    {
        setLayout(null);
        setVisible(true);

        this.setTitle("添加学生信息");

        add(qr);
        add(qx);
        add(xh);
        add(xm);
        add(yx);
        add(bj);
        add(dh);
        add(ts);
        add(xuehao);
        add(xingming);
        add(yuanxi);
        add(banji);
        add(dianhua);
        qr.addActionListener(this);
        qx.addActionListener(this);

        this.setVisible(true);
        this.background();

        xh.setBounds(400, 120, 300, 50);
        xuehao.setBounds(600, 120, 400, 50);
        xm.setBounds(400, 200, 300, 50);
        xingming.setBounds(600, 200, 400, 50);
        yx.setBounds(400, 280, 300, 50);
        yuanxi.setBounds(600, 280, 400, 50);
        bj.setBounds(400, 360, 300, 50);
        banji.setBounds(600, 360, 400, 50);
        dh.setBounds(342, 440, 300, 50);
        dianhua.setBounds(600, 440, 400, 50);
        qr.setBounds(550, 600, 150, 70);
        qx.setBounds(900, 600, 150, 70);
        ts.setBounds(600, 30, 400, 50);

        xh.setFont(a);
        xm.setFont(a);
        yx.setFont(a);
        bj.setFont(a);
        dh.setFont(a);
        qr.setFont(a);
        qx.setFont(a);
        xuehao.setFont(a);
        xingming.setFont(a);
        yuanxi.setFont(a);
        banji.setFont(a);
        dianhua.setFont(a);
        ts.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,50));
        ts.setForeground(Color.red);

        setSize(1400,800);
        setLocation((sc.width-this.getWidth())/2+50,(sc.height-this.getHeight())/2-20);


    }

    public void background()//创建背景
    {
        JLabel titleLabel = new JLabel();
        ImageIcon icon = new ImageIcon("src/resources/背景图.png");
        titleLabel.setBounds(0,0,1600,1000);
        titleLabel.setIcon(icon);
        this.add(titleLabel);
    }

    public static void main(String args[])
    {
        new student();

    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==qr)//单击确认添加后
        {
            if(xuehao.getText().equals("")||xingming.getText().equals("")||yuanxi.getText().equals("")||banji.getText().equals("")||dianhua.getText().equals(""))
            {
                new info1(this,"输入信息不完整!");
            }
            else
            {
                boolean yes=true;
                try
                {
                    String line;
                    File file = new File("src/resources/studentinfo.txt");
                    BufferedReader in = new BufferedReader(new FileReader(file));
                    while((line = in.readLine())!=null)
                    {
                        String s[] = line.split("\t");
                        if(s[0].equals(xuehao.getText())==true)
                        {
                            yes=false;
                            new info1(this,"输入的学号相同！");
                            this.reset();
                            break;
                        }
                    }
                    in.close();
                    if(yes)
                    {
                        BufferedWriter out = new BufferedWriter(new FileWriter(file,true));

                        out.write(xuehao.getText());
                        out.write("\t");
                        out.write(xingming.getText());
                        out.write("\t");
                        out.write(yuanxi.getText());
                        out.write("\t");
                        out.write(banji.getText());
                        out.write("\t");
                        out.write(dianhua.getText());
                        out.write("\n");

                        new info1(this,"输入成功");
                        this.reset();
                        out.close();
                    }

                }
                catch(FileNotFoundException fe)
                {
                    System.err.println(fe);
                }
                catch(IOException ioe)
                {
                    System.out.println(ioe);
                }



            }

        }
        if(e.getSource()==qx)//单击取消后
        {
            this.dispose();
        }
    }
    public void reset()//输入后清空文本框
    {
        xuehao.setText(null);
        xingming.setText(null);
        yuanxi.setText(null);
        banji.setText(null);
        dianhua.setText(null);
    }

}

class findstudent extends JFrame implements ActionListener//查询界面
{
    JLabel a=new JLabel("请输入所要查询的学生学号：");
    JTextField in=new JTextField();
    JButton find=new JButton("查询");
    JButton edit=new JButton("编辑");
    JButton del=new JButton("删除");
    JButton qx=new JButton("取消");
    Container contentPane=this.getContentPane();
    String[] name= {"学号：","姓名：","系别：","班级：","联系方式："};
    Object[][] tableDate=new Object[100][5];
    JTable table=new JTable(tableDate,name);
    JScrollPane scrollpane=new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    JTableHeader tab_header = this.table.getTableHeader();					//获取表头
    DefaultTableModel model=new DefaultTableModel (tableDate,name);
    static boolean yes;
    static boolean dan;
    static boolean wu;




    public findstudent()
    {
        tab_header.setFont(new Font("宋体",Font.CENTER_BASELINE,25));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));
        table.setRowHeight(40);
        table.setFont(new Font("宋体",Font.CENTER_BASELINE,25));
        DefaultTableCellRenderer r=new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,r);

        this.setVisible(true);
        this.setLayout(null);
        this.add(a);
        this.add(in);
        this.add(find);
        this.add(edit);
        this.add(del);
        this.add(qx);
        this.add(scrollpane);
        this.background();

        this.setTitle("查找学生信息");

        this.duiqi();

        edit.addActionListener(this);
        find.addActionListener(this);
        del.addActionListener(this);
        qx.addActionListener(this);

        a.setFont(student.a);
        a.setBounds(250, 80, 400, 50);
        in.setFont(student.a);
        in.setBounds(650, 80, 600, 50);
        find.setFont(student.a);
        find.setBounds(500, 160, 150, 55);
        edit.setFont(student.a);
        edit.setBounds(700, 160, 150, 55);
        del.setFont(student.a);
        del.setBounds(900, 160, 150, 55);
        qx.setFont(student.a);
        qx.setBounds(1100, 160, 150, 55);

        scrollpane.setBounds(100, 220,this.getWidth() -200, 400);



    }
    public void background()//创建背景
    {
        JLabel titleLabel = new JLabel();
        ImageIcon icon = new ImageIcon("src/resources/背景图.png");
        titleLabel.setBounds(0,0,1600,1000);
        titleLabel.setIcon(icon);
        this.add(titleLabel);
    }
    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource()==find)//点击查询按钮
        {
            if(in.getText().equals("")==true)//如果文本框没有输入内容
            {
                try
                {
                    String line;
                    File file = new File("src/resources/studentinfo.txt");
                    BufferedReader in = new BufferedReader(new FileReader(file));
                    int i=0;
                    while((line = in.readLine())!=null)
                    {

                        String s[] = line.split("\t");
                        for(int j=0; j<5; j++)
                        {
                            tableDate[i][j]=s[j];
                        }
                        i++;

                    }
                    in.close();

                }
                catch(FileNotFoundException fe)
                {
                    System.err.println(fe);
                }
                catch(IOException ioe)
                {
                    System.out.println(ioe);
                }
                this.repaint();
                dan=false;
                wu=false;
            }

            else//如果文本框输入内容
            {
                boolean yes=true;
                int i;
                try
                {
                    String line;
                    File file = new File("src/resources/studentinfo.txt");
                    BufferedReader in1 = new BufferedReader(new FileReader(file));
                    while((line = in1.readLine())!=null)
                    {
                        String s[] = line.split("\t");
                        if(s[0].equals(in.getText()))//输入内容有效
                        {
                            for(int j=0; j<5; j++)
                            {
                                tableDate[0][j]=s[j];
                            }
                            dan=true;
                            yes=false;
                            break;
                        }
                    }
                    in1.close();


                }
                catch(FileNotFoundException fe)
                {
                    System.err.println(fe);
                }
                catch(IOException ioe)
                {
                    System.out.println(ioe);
                }
                if(dan)//输入内容有效
                {
                	wu=false;
                }
                else if(wu)
                {
                	dan=false;
                }

                if(yes)//输入内容无效
                {
                    i=0;
                    wu=true;
                }
                else
                {
                    i=1;
                    dan=true;
                }
                for(; i<100; i++)
                {
                    for(int j=0; j<5; j++)
                        tableDate[i][j]="";
                }
            }
            this.repaint();
        }


        if(e.getSource()==edit)//单击编辑按钮
        {
            int[] a=table.getSelectedRows();
            if(wu==true)//查询后没有结果
            {
                new info1(this,"选择有误！");
            }
            else if(a.length!=1)//选择多行
            {
                new info1(this,"选择有误！");
            }
            else if(table.getValueAt(a[0],0)==null)//选择的内容为空
            {
                new info1(this,"选择有误！");
            }
            else
            {
                this.dispose();
                int i=1;
                if(!dan)
                {
                    for(i=0;; i++)
                    {
                        if(table.getValueAt(i,0)==null)
                            break;
                    }
                }

                new editstudent (tableDate,a,i-1);

            }
        }
        if(e.getSource()==del)//单击删除按钮
        {
            int i=0;
            this.yes=true;
            if(!dan&&!wu)//输入框为空后单击删除按钮
                for( i=0;; i++)
                {
                    if(table.getValueAt(i,0)==null)
                        break;
                }
            int[] a=table.getSelectedRows();//获得选择的行数
            if(wu)
            {
                new info1(this,"选择有误！");
                yes=false;
            }
            else if(dan)
            {
            	yes=true;
            }
            else
            {
                for(int w=0; w<a.length; w++)
                    if(a[w]>=i)
                    {
                        new info1(this,"选择有误！");
                        yes=false;
                        break;
                    }
            }

            if(yes)
                new info2(this,"你确认删除吗？");
            if(yes&&!dan)
            {
                shanchu(a);
                new info1(this,"     删除成功！");
                new info1(this,"   点击查询刷新");
            }
            else if(yes&&dan)
            {
                shanchu(String.valueOf(tableDate[0][0]));
                new info1(this,"     删除成功！");
                new info1(this,"   点击查询刷新");
            }

        }
        if(e.getSource()==qx)
        {
            this.dispose();
        }
    }
    public void shanchu(int selectedRows[])
    {
        int line;
        for( line=0;; line++)
        {
            if(table.getValueAt(line,0)==null)
                break;
        }
        try
        {

            File file = new File("src/resources/studentinfo.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            for(int j1=0; j1<line; j1++)
            {
                boolean yes=true;
                for(int j2=0; j2<selectedRows.length; j2++)
                    if(j1==selectedRows[j2])
                    {
                        yes=false;
                        break;
                    }
                if(yes)
                {
                    out.write(table.getValueAt(j1,0).toString());
                    out.write("\t");
                    out.write(table.getValueAt(j1,1).toString());
                    out.write("\t");
                    out.write(table.getValueAt(j1,2).toString());
                    out.write("\t");
                    out.write(table.getValueAt(j1,3).toString());
                    out.write("\t");
                    out.write(table.getValueAt(j1,4).toString());
                    out.write("\n");
                }
            }
            out.close();

        }
        catch(FileNotFoundException fe)
        {
            System.err.println(fe);
        }
        catch(IOException ioe)
        {
            System.out.println(ioe);
        }

        int i=0;
        for(; i<100; i++)
        {
            for(int j=0; j<5; j++)
                tableDate[i][j]=null;
        }


    }
    public void shanchu(String ss)
    {
        try
        {
            String line;
            int i=0;
            File file = new File("src/resources/studentinfo.txt");
            BufferedReader in = new BufferedReader(new FileReader(file));
            while((line=in.readLine())!=null)
            {
                String s[]=line.split("\t");
                if(s[0].equals(ss)!=true)
                {
                    tableDate[i][0]=s[0];
                    tableDate[i][1]=s[1];
                    tableDate[i][2]=s[2];
                    tableDate[i][3]=s[3];
                    tableDate[i][4]=s[4];
                    i++;  
                }
                else
                	continue;
               
            }
            in.close();
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            for(int j=0; j<i; j++)
            {
                out.write(tableDate[j][0].toString());
                out.write("\t");
                out.write(tableDate[j][1].toString());
                out.write("\t");
                out.write(tableDate[j][2].toString());
                out.write("\t");
                out.write(tableDate[j][3].toString());
                out.write("\t");
                out.write(tableDate[j][4].toString());
                out.write("\n");
            }
            out.close();
        }
        catch(FileNotFoundException fe)
        {
            System.err.println(fe);
        }
        catch(IOException ioe)
        {
            System.out.println(ioe);
        }
        in.setText(null);

    }
    public void duiqi()
    {
        Toolkit kit=Toolkit.getDefaultToolkit();
        Dimension sc=kit.getScreenSize();//使应用在屏幕正中心
        this.setSize(1400,800);
        this.setLocation((sc.width-this.getWidth())/2+50,(sc.height-this.getHeight())/2-20);
    }



}

class editstudent extends JFrame implements ActionListener
{
    static boolean yes=false;
    String no=new String();
    String name=new String();
    String faculty=new String();
    String Class=new String();
    String tel=new String();

    JLabel xh=new JLabel("学号：");
    JLabel xm=new JLabel("姓名：");
    JLabel yx=new JLabel("院系：");
    JLabel bj=new JLabel("班级：");
    JLabel dh=new JLabel("联系方式：");
    JButton qr=new JButton("确认");
    JButton qx=new JButton("取消");
    JLabel ts=new JLabel("编辑学生信息");

    JTextField xuehao = new JTextField();
    JTextField xingming = new JTextField();
    JTextField yuanxi = new JTextField();
    JTextField banji = new JTextField();
    JTextField dianhua = new JTextField();
    Object[][] yuanshi;
    int xb[];
    int i;



    static Font a=new Font("微软雅黑",Font.CENTER_BASELINE,30);
    public editstudent(Object[][] yuanshi,int xb[],int i)
    {

        setLayout(null);
        setVisible(true);

        this.setTitle("编辑学生信息");
        this.yuanshi=yuanshi;
        this.xb=xb;
        this.i=i;

        add(qr);
        add(qx);
        add(xh);
        add(xm);
        add(yx);
        add(bj);
        add(dh);
        add(xuehao);
        add(xingming);
        add(yuanxi);
        add(banji);
        add(dianhua);
        add(ts);
        qr.addActionListener(this);
        qx.addActionListener(this);

        this.duiqi();
        this.run();

        this.setVisible(true);
        this.background();

        xh.setBounds(400, 120, 300, 50);
        xuehao.setBounds(600, 120, 400, 50);
        xm.setBounds(400, 200, 300, 50);
        xingming.setBounds(600, 200, 400, 50);
        yx.setBounds(400, 280, 300, 50);
        yuanxi.setBounds(600, 280, 400, 50);
        bj.setBounds(400, 360, 300, 50);
        banji.setBounds(600, 360, 400, 50);
        dh.setBounds(342, 440, 300, 50);
        dianhua.setBounds(600, 440, 400, 50);
        qr.setBounds(550, 600, 150, 70);
        qx.setBounds(900, 600, 150, 70);
        ts.setBounds(600, 30, 400, 50);

        xh.setFont(a);
        xm.setFont(a);
        yx.setFont(a);
        bj.setFont(a);
        dh.setFont(a);
        xuehao.setFont(a);
        xingming.setFont(a);
        yuanxi.setFont(a);
        banji.setFont(a);
        dianhua.setFont(a);
        qr.setFont(a);
        qx.setFont(a);
        ts.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,50));
        ts.setForeground(Color.red);

    }



    public void background()
    {
        JLabel titleLabel = new JLabel();
        ImageIcon icon = new ImageIcon("src/resources/背景图.png");//创建背景
        titleLabel.setBounds(0,0,1600,1000);
        titleLabel.setIcon(icon);
        this.add(titleLabel);
    }

    public void run()
    {
        xuehao.setText(String.valueOf(yuanshi[xb[0]][0]));
        xingming.setText(String.valueOf(yuanshi[xb[0]][1]));
        yuanxi.setText(String.valueOf(yuanshi[xb[0]][2]));
        banji.setText(String.valueOf(yuanshi[xb[0]][3]));
        dianhua.setText(String.valueOf(yuanshi[xb[0]][4]));
    }


    public void actionPerformed(ActionEvent e)
    {


        if(e.getSource()==qr)
        {
            if(xuehao.getText().equals("")||xingming.getText().equals("")||yuanxi.getText().equals("")||banji.getText().equals("")||dianhua.getText().equals(""))
            {
                new info1(this,"输入信息不完整!");
            }
            else
            {
                new info2(this,"你确定要修改数据吗?");
            }

            if(yes&&!findstudent.dan)
            {
                try
                {
                    yuanshi[xb[0]][0]=xuehao.getText();
                    yuanshi[xb[0]][1]=xingming.getText();
                    yuanshi[xb[0]][2]=yuanxi.getText();
                    yuanshi[xb[0]][3]=banji.getText();
                    yuanshi[xb[0]][4]=dianhua.getText();

                    File file = new File("src/resources/studentinfo.txt");
                    BufferedWriter out = new BufferedWriter(new FileWriter(file));

                    for(int j=0; j<=i; j++)
                    {
                        out.write(yuanshi[j][0].toString());
                        out.write("\t");
                        out.write(yuanshi[j][1].toString());
                        out.write("\t");
                        out.write(yuanshi[j][2].toString());
                        out.write("\t");
                        out.write(yuanshi[j][3].toString());
                        out.write("\t");
                        out.write(yuanshi[j][4].toString());
                        out.write("\n");

                    }

                    out.close();

                }
                catch(FileNotFoundException fe)
                {
                    System.err.println(fe);
                }
                catch(IOException ioe)
                {
                    System.out.println(ioe);
                }
            }
            else if(yes&&findstudent.dan)
            {
                int qq=-1;
                try
                {
                    String l=String.valueOf(yuanshi[xb[0]][0]);
                    String line;
                    int i=0;
                    File file = new File("src/resources/studentinfo.txt");
                    BufferedReader in = new BufferedReader(new FileReader(file));
                    while((line=in.readLine())!=null)
                    {
                        String s[]=line.split("\t");
                        if(s[0].equals(l)!=true)
                        {
                            yuanshi[i][0]=s[0];
                            yuanshi[i][1]=s[1];
                            yuanshi[i][2]=s[2];
                            yuanshi[i][3]=s[3];
                            yuanshi[i][4]=s[4];
                        }
                        else
                        {
                            yuanshi[i][0]=xuehao.getText();
                            yuanshi[i][1]=xingming.getText();
                            yuanshi[i][2]=yuanxi.getText();
                            yuanshi[i][3]=banji.getText();
                            yuanshi[i][4]=dianhua.getText();

                        }
                        i++;
                        qq++;
                    }
                    in.close();

                    BufferedWriter out = new BufferedWriter(new FileWriter(file));
                    for(int j=0; j<=qq; j++)
                    {
                        out.write(yuanshi[j][0].toString());
                        out.write("\t");
                        out.write(yuanshi[j][1].toString());
                        out.write("\t");
                        out.write(yuanshi[j][2].toString());
                        out.write("\t");
                        out.write(yuanshi[j][3].toString());
                        out.write("\t");
                        out.write(yuanshi[j][4].toString());
                        out.write("\n");
                    }

                    out.close();

                }
                catch(FileNotFoundException fe)
                {
                    System.err.println(fe);
                }
                catch(IOException ioe)
                {
                    System.out.println(ioe);
                }
            }

            this.dispose();
            new findstudent();


        }

        if(e.getSource()==qx)
        {
            this.dispose();
            new findstudent();
        }
    }
    public void duiqi()
    {
        Toolkit kit=Toolkit.getDefaultToolkit();
        Dimension sc=kit.getScreenSize();//使应用在屏幕正中心
        this.setSize(1400,800);
        this.setLocation((sc.width-this.getWidth())/2+50,(sc.height-this.getHeight())/2-20);
    }

}

class info1 extends JDialog implements ActionListener
{
    Toolkit kit=Toolkit.getDefaultToolkit();
    Dimension sc=kit.getScreenSize();//使应用在屏幕正中心
    JPanel a=new JPanel();
    JPanel b=new JPanel();

    JButton qr=new JButton("确认");
    public info1(JFrame p,String ts)
    {
        super(p,"",true);
        JLabel tips=new JLabel("        "+ts);
        setLocation((sc.width-this.getWidth())/2-100,(sc.height-this.getHeight())/2-100);
        Font f1=new Font("微软雅黑",Font.BOLD,15);
        a.setLayout(new BorderLayout());
        b.setLayout(new FlowLayout());
        tips.setFont(f1);
        qr.setFont(f1);
        a.add(tips);
        b.add(qr);
        a.add("South",b);
        add(a);

        qr.addActionListener(this);

        this.setSize(200,150);
        this.setVisible(true);
        this.setResizable(false);

    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==qr)
        {
            dispose();
        }

    }
}

class info2 extends JDialog implements ActionListener
{
    Toolkit kit=Toolkit.getDefaultToolkit();
    Dimension sc=kit.getScreenSize();//使应用在屏幕正中心
    JPanel a=new JPanel();
    JPanel b=new JPanel();

    JButton qr=new JButton("确认");
    JButton qx=new JButton("取消");
    public info2(JFrame p,String ts)
    {
        super(p,"",true);
        JLabel tips=new JLabel("        "+ts);
        setLocation((sc.width-this.getWidth())/2-100,(sc.height-this.getHeight())/2-100);
        Font f1=new Font("微软雅黑",Font.BOLD,15);
        a.setLayout(new BorderLayout());
        b.setLayout(new FlowLayout());
        tips.setFont(f1);
        qr.setFont(f1);
        qx.setFont(f1);
        a.add(tips);
        b.add(qr);
        b.add(qx);
        a.add("South",b);
        add(a);

        qr.addActionListener(this);
        qx.addActionListener(this);

        this.setSize(200,150);
        this.setVisible(true);
        this.setResizable(false);

    }
    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource()==qr)
        {
            editstudent.yes=true;
            findstudent.yes=true;
            this.dispose();
        }
        else if(e.getSource()==qx)
        {
            editstudent.yes=false;
            findstudent.yes=false;
            this.dispose();
        }

    }
}
