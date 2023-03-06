package function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class delete extends JFrame implements ActionListener
{
    JLabel classname=new JLabel("请输入需要删除选课的学生学号：");
    JTextField nametext=new JTextField();
    JLabel classnumber=new JLabel("请输入需要删除的选修课程编号：");
    JTextField numbertext=new JTextField();
    JButton sure=new JButton("确定");
    JButton cancel=new JButton("取消");
    Font a=new Font("微软雅黑",Font.BOLD,30);//同一字体大小
    Color co=new Color(23);
    public delete()
    {
        super("删除学生选修课");
        locat();
        setfont();
        setLayout(null);
        add(classname);
        add(classnumber);
        add(numbertext);
        add(nametext);
        add(sure);
        add(cancel);
        int x = 200;
        classname.setBounds(x,170,500,50);
        nametext.setBounds(x+450,170,400,50);

        classnumber.setBounds(x,270,500,50);
        numbertext.setBounds(x+450,270,400,50);

        sure.setBounds(700,400,100,50);
        cancel.setBounds(900,400,100,50);
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
        if(e.getSource()==sure)  //如果输入为空，则弹出提示
        {
            if((nametext.getText()).equals("")||((numbertext.getText()).equals("")))
            {
                new info1(this,"不能为空 ");


            }
            else  //如果学号，课程不为空，则将先将学号，课程匹配

            {
                try
                {
                    /*首先判断输入是否不为空，
                     * 然后是判断是否有该名同学		xuehao=true
                     * 再判断是否有该课程				kecheng=true
                     * 若果有该同学已选择该课程则 	ishave=true;
                     * */
                    String line="";
                    boolean xuehao=false;//是否存在该名同学
                    boolean kecheng=false;//是否存在该课程
                    boolean ishave=false;//该同学是否有该选课

                    File fclassdata=new File("src/resources/classdata.txt");//存放课程信息
                    File fsum=new File("src/resources/sum.txt");//存放选课是否已满
                    File fstudentinfo=new File("src/resources/studentinfo.txt");
                    File fclass=new File("src/resources/coures.txt");



                    //是否存在该名同学
                    BufferedReader br=new BufferedReader(new FileReader(fstudentinfo));
                    while((line=br.readLine())!=null)
                    {
                        String s[]=line.split("\t");
                        if(s[0].equals(nametext.getText()))
                        {
                            xuehao=true;
                            break;
                        }
                    }
                    br.close();



                    //是否有该课程
                    BufferedReader br1=new BufferedReader(new FileReader(fclass));
                    while((line=br1.readLine())!=null)
                    {
                        String s1[]=line.split("\t");
                        if(s1[0].equals(numbertext.getText()))
                        {
                            kecheng=true;//是否存在该课程

                            break;
                        }
                    }
                    br1.close();


                    String[][]temp=new String[1000][2];
                    int i=0;
                    //修改人数

                    //--//修改该同学的选课
                    i=0;
                    BufferedReader br4=new BufferedReader(new FileReader(fclassdata));
                    String[][]temp1=new String[1000][2];
                    if(kecheng==true&&xuehao==true)//如果学好课程匹配成功，则匹配该同学是否有该选课
                    {
                        while((line=br4.readLine())!=null)
                        {
                            String []s3=line.split("\t");
                            temp1[i][0]=s3[0];
                            temp1[i][1]=s3[1];
                            if(s3[0].equals(nametext.getText())&&s3[1].equals(numbertext.getText()))
                            {
                                //如果该同学有该选课的话，就标记
                                ishave=true;
                            }
                            i++;
                        }

                        br4.close();

                        String ae1="";
                        BufferedWriter bw1=new BufferedWriter(new FileWriter(fclassdata));
                        //该同学如果选择了该课程则进行修改

                        for(int j=0; j<i; j++)
                        {
                            if(temp1[j][1].equals(numbertext.getText())&&(temp1[j][0].equals(nametext.getText())))
                            {

                            }
                            else
                            {
                                ae1+=(temp1[j][0]+"\t"+temp1[j][1]+"\n");
                            }

                        }
                        bw1.write(ae1);

                        bw1.close();

                        i=0;
                        //------//修改总人数
                        BufferedReader br3=new BufferedReader(new FileReader(fsum));

                        while((line=br3.readLine())!=null)
                        {
                            String []s3=line.split("\t");
                            temp[i][0]=s3[0];
                            temp[i][1]=s3[1];
                            i++;
                        }


                        br3.close();
                        String ae="";

                        BufferedWriter bw=new BufferedWriter(new FileWriter(fsum));

                        for(int j=0; j<i; j++)
                        {
                            if((temp[j][0].equals(numbertext.getText()))&&(ishave==true))
                            {
                                temp[j][1]=String.valueOf(Integer.parseInt(temp[j][1])-1);//该课程的人数减一
                                ae+=(temp[j][0]+"\t"+temp[j][1]+"\n");
                            }
                            else
                            {
                                ae+=(temp[j][0]+"\t"+temp[j][1]+"\n");
                            }

                        }
                        bw.write(ae);
                        bw.close();

                    }
            
                    if(xuehao==false)
                    {
                        new info1(this,"未找到该同学");
                        nametext.setText(null);
                        numbertext.setText(null);

                    }
                    if(kecheng==false)
                    {
                        new info1(this,"未找到该选课");
                        nametext.setText(null);
                        numbertext.setText(null);

                    }
                    if(ishave==false&&xuehao==true&&kecheng==true)
                    {
                        new info1(this,"该同学没有选择该课");
                        nametext.setText(null);
                        numbertext.setText(null);
                    }
                    else if(ishave==true&&xuehao==true&&kecheng==true)
                    {
                        new info1(this,"选课删除成功");
                        nametext.setText(null);
                        numbertext.setText(null);
                        ishave=true;
                    }
                }
                catch (FileNotFoundException e1)
                {
                    System.err.println(e1);
                }
                catch (IOException ioe)
                {
                    System.err.println(ioe);
                }
            }


        }
        if(e.getSource()==cancel)
        {

            dispose();
        }


    }
    public static void main(String  []args)
    {
        new delete();
    }
}

