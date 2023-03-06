package function;
/*添加学生所选修课程
 * class文件是存放选修课程
 * */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class addElectiveCourse extends JFrame implements ActionListener
{
    JLabel classname=new JLabel("请输入需要添加选课的学生学号：");
    JTextField nametext=new JTextField();
    JLabel classnumber=new JLabel("请输入需要添加的选修课程编号：");
    JTextField numbertext=new JTextField();
    JButton sure=new JButton("添加");
    JButton cancel=new JButton("取消");
    Font a=new Font("微软雅黑",Font.BOLD,30);//同一字体大小
    Color co=new Color(23);
    DefaultTableModel model=new DefaultTableModel(100,4);//设置大小
    JTable jt=new JTable();
    
    File fclass=new File("src/resources/coures.txt");//存放选课信息，那位同学选择了哪个课程
    File fclassdata=new File("src/resources/classdata.txt");//存放课程信息
    File fstudentinfo=new File("src/resources/studentinfo.txt");//存放学生信息
    File fsum=new File("src/resources/sum.txt");//存放选课是否已满

    public addElectiveCourse()
    {
        super("添加学生选修课");
        //---------该部分为表格

        locat();
        setfont();
        setLayout(null);

        add(classname);
        add(classnumber);
        add(numbertext);
        add(nametext);
        add(sure);
        add(cancel);
        int x =100;
        classname.setBounds(x,100,500,50);
        nametext.setBounds(x+450,100,400,50);

        classnumber.setBounds(x,170,500,50);
        numbertext.setBounds(x+450,170,400,50);
        sure.setBounds(1000,100,100,50);
        cancel.setBounds(1000,175,100,50);
        
        writeSum();

        setbg();
        sure.addActionListener(this);
        cancel.addActionListener(this);
        setResizable(false);//禁止调整窗口大小

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
    public void setfont()
    {
        sure.setFont(a);
        cancel.setFont(a);

        classname.setFont(a);
        classname.setForeground(co);
        classnumber.setFont(a);
        classnumber.setForeground(co);
        nametext.setFont(a);
        nametext.setForeground(co);
        numbertext.setFont(a);
        numbertext.setForeground(co);
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

                this.dispose();
            }

        }
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
    	
        if(e.getSource()==sure)
        {

            /*首先在studentinfo中匹配是否有该同学，没有则输出“未查找到该同学”
             * 如果有则继续在clas.txt中查找是否有该们课程，没有就输出”无该门课程“
             * 如果有则继续在classdata中查找该名同学是否已经选择该门课程，如果有则输出”以及选择该门课程，请勿重复选择”
             * 如果没有则，在class中查找该门课程选课人数是否达到上线，如果达到，则无法选课
             * 否则，添加选课
             * */
            boolean xuehao=false;
            boolean kecheng=false;
            boolean full=false;
            boolean ishave=false;
            
            if(!(nametext.getText()).equals("")&&!((numbertext.getText()).equals("")))
            {
                try
                {
                    //是否存在该名同学
                    BufferedReader br=new BufferedReader(new FileReader(fstudentinfo));
                    String line;

                    while((line=br.readLine())!=null)
                    {
                        String s[]=line.split("\t");
                        if(s[0].equals(nametext.getText()))
                        {
                        	System.out.println("sttrue");
                            xuehao=true;
                            break;
                        }
                        System.out.println("stfalse");
                    }
                    br.close();
                    //查询是否有该课程
                    String p="0";
                    BufferedReader br1=new BufferedReader(new FileReader(fclass));
                    while((line=br1.readLine())!=null)
                    {
                        String s1[]=line.split("\t");
                        if(s1[0].equals(numbertext.getText()))
                        {
                        	System.out.println("kctrue");
                            kecheng=true;//是否存在该课程
                            p=s1[6];
                            System.out.println(p);//40
                            break;
                        }
                        System.out.println("kcfalse");
                    }
                    //查看选课是否满了
                    String nu = null;//定义修改人数的位置
                    String[][] temp=new String[1000][2];
                    int i=0;
                    BufferedReader br3=new BufferedReader(new FileReader(fsum));
                    while((line=br3.readLine())!=null)
                    {
                        String[] s3=line.split("\t");
                        if(s3[0].equals(numbertext.getText()))
                        {
                        	System.out.println(s3[1]);
                            if(Integer.parseInt(s3[1])<Integer.parseInt(p))
                            {
                                full=true;
                                nu=s3[1];
                                System.out.println(nu);
                            }
                        }
                        String []temp1=line.split("\t");
                        temp[i][0]=s3[0];
                        temp[i][1]=s3[1];
                        i++;
                    }
                    br1.close();
                    br3.close();
                    //查找是否已经选课
                    BufferedReader br2=new BufferedReader(new FileReader(fclassdata));
                    while((line=br2.readLine())!=null)
                    {
                        String s2[]=line.split("\t");
                        if(s2[0].equals(nametext.getText())&&s2[1].equals(numbertext.getText()))
                        {
                            ishave=true;
                        }
                    }

                    if(xuehao==false)
                    {
                        new info1(this,"未找到该名同学");
                        nametext.setText(null);
                        numbertext.setText(null);
                    }
                    else if(kecheng==false)
                    {
                        new info1(this,"未找到该选课");
                        nametext.setText(null);
                        numbertext.setText(null);
                    }
                    else if(full==false)
                    {
                        new info1(this,"选课已满");
                        nametext.setText(null);
                        numbertext.setText(null);
                    }
                    else if(ishave==true)
                    {
                        new info1(this,"该同学已选该课程");
                        nametext.setText(null);
                        numbertext.setText(null);
                    }
                    else //写入功能
                        if((xuehao==true)&&(kecheng==true)&&kecheng==true&&ishave==false)
                        {
                            BufferedWriter bw=new BufferedWriter(new FileWriter(fclassdata,true));
                            bw.write(nametext.getText()+"\t"+numbertext.getText()+"\n");
                            new info1(this,"添加成功");
                            nametext.setText(null);
                            numbertext.setText(null);
                            bw.close();
                            for(int j=0; j<i; j++)
                            {
                                if(temp[j][0].equals(numbertext.getText()))
                                {
                                    temp[j][1]=String.valueOf(Integer.parseInt(nu)+1);
                                }
                            }

                            BufferedWriter bw1=new BufferedWriter(new FileWriter("src/resources/sum.txt"));
                            String ae="";
                            for(int j=0; j<i; j++)
                            {
                                ae+=temp[j][0]+"\t"+temp[j][1]+"\n";
                            }

                            bw1.write(ae);
                            bw1.close();
                        }
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }


            }
            else new info1(this,"输入不能为空");
        }
        if(e.getSource()==cancel)
        {
            dispose();
        }
    }

    public static void main(String []args)
    {
        new addElectiveCourse();
    }
    public void writeSum() {
    	try {
    		String line;
			BufferedWriter bw=new BufferedWriter(new FileWriter(fsum));
			BufferedReader br3=new BufferedReader(new FileReader(fclass));
			bw.write("");
             while((line=br3.readLine())!=null)
             {
                 String[] s3=line.split("\t");
                 bw.write(s3[0]);
                 bw.write("\t");
                 bw.write(s3[7]);
                 bw.write("\n");
             }
			bw.close();
			br3.close();
		}catch (FileNotFoundException fe)
	    {
	        System.err.println(fe);
	    }
	    catch (IOException ioe)
	    {
	        System.err.println(ioe);
	    }
    }
}
