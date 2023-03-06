package function;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class start extends JFrame implements ActionListener
{

    JLabel un=new JLabel("账号:");//用户名标签
    JTextField username=new JTextField(20);//用户名文本框

    JLabel pw=new JLabel("密码: ");//密码标签
    JPasswordField password=new JPasswordField(20);//密码文本框
    Font a=new Font("微软雅黑",Font.BOLD,20);
    //统一字体格式

    JButton signin=new JButton("登录");//登录按钮
    JButton register=new JButton("注册");//注册按钮

    Toolkit kit=Toolkit.getDefaultToolkit();
    Dimension sc=kit.getScreenSize();//使应用在屏幕正中心

    JLabel info=new JLabel();//提示信息

    public start()
    {
        super("学生管理系统");
        setBackground(new Color(253,253,253));//设置背景颜色

        ImageIcon img = new ImageIcon("src/resources/start.png");
        JLabel jl_bg = new JLabel(img);    //背景
        jl_bg.setBounds(30, 30, 1050, 140); //设置位置和大小
        this.getLayeredPane().add(jl_bg, new Integer(Integer.MIN_VALUE));
        ((JPanel)this.getContentPane()).setOpaque(false); //设置透明
        //插入图片

        setLayout(null);//无固定布局方式

        un.setFont(a);
        pw.setFont(a);
        signin.setFont(a);
        signin.setBackground(new Color(253,253,253));
        signin.setBorderPainted(false);
        register.setFont(a);
        register.setBackground(new Color(253,253,253));
        register.setBorderPainted(false);
        info.setFont(new Font("微软雅黑",Font.BOLD,25));
        info.setForeground(Color.red);
        username.setFont(a);
        password.setFont(a);
        //设置文字、按钮颜色

        add(un);
        add(pw);
        add(signin);
        add(register);
        add(username);
        add(password);
        add(info);
        //添加组件

        un.setBounds(new Rectangle(190, 150, 113, 45));
        username.setBounds(new Rectangle(250, 155, 200, 40));
        pw.setBounds(new Rectangle(190, 250, 113, 45));
        password.setBounds(new Rectangle(250, 255, 200, 40));
        signin.setBounds(150, 380, 120, 45);
        register.setBounds(400, 380, 120, 45);
        info.setBounds(new Rectangle(220, 50, 300, 100));
        //设置组件位置


        setVisible(true);
        setSize(700, 600);
        this.setResizable(false);
        setLocation((sc.width-this.getWidth())/2,(sc.height-this.getHeight())/2);
        //设计窗口属性

        addWindowListener(new WindosClose());
        signin.addActionListener(this);
        register.addActionListener(this);
        //添加动作监听器

    }
    public static void main(String args[])
    {
        new start();
    }


    public void actionPerformed(ActionEvent e)
    {

        String s=new String(password.getPassword());
        if(e.getSource()==signin)
        {
            if((username.getText()).equals("")||(s.equals("")))
            {
                info.setText("用户名或密码未输入!!!");
            }
            else
            {
                try
                {
                    BufferedReader in=new BufferedReader(new FileReader("src/resources/user.txt"));
                    String line;
                    boolean yes=false;
                    while((line=in.readLine())!=null)
                    {

                        String s1[]=line.split("\t");//文件中一行一行读取
                        if(s1[0].equals(username.getText())&&s1[1].equals(s))//进行用户名与密码配对
                        {
                            info.setText("登陆成功");
                            yes=true;
                            this.dispose();
                            new MainShow();//进入主界面
                        }

                    }
                    if(!yes)info.setText("账号或密码错误!!!");
                    {
                        username.setText(null);
                        password.setText(null);
                    }
                    in.close();
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

        if(e.getSource()==register)
        {
            new regsiter(this,"账户注册");
        }

    }

}
class WindosClose extends WindowAdapter
{
    public void main(WindowEvent we)
    {
        System.exit(0);
    }
}
