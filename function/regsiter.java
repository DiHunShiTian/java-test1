package function;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

class regsiter extends JDialog implements ActionListener
{

    JLabel name=new JLabel("账号：");
    JLabel mima=new JLabel("密码：");
    JTextField namenm=new JTextField(20);  //用于输入账号
    JTextField mimanm=new JTextField(20);  //用于输入密码

    JButton regsiter1=new JButton("注册");
    JButton back =new JButton("返回");

    Font f=new Font("微软雅黑",Font.BOLD,18);//设置字体格式

    JLabel titleLabel = new JLabel();
    ImageIcon icon = new ImageIcon("src/resources/背景图.png");//创建背景

    Toolkit kit=Toolkit.getDefaultToolkit();
    Dimension sc=kit.getScreenSize();//程序居中显示

    JLabel info=new JLabel();//提示信息

    public regsiter(JFrame parent,String name)
    {
        super(parent,name,true);

        setup();

    }

    public void setup()
    {

        JPanel a=new JPanel();
        a.setLayout(null);

        back.setFont(f);
        regsiter1.setFont(f);
        name.setFont(f);
        mima.setFont(f);        
        namenm.setFont(f);
        mimanm.setFont(f);
        info.setFont(new Font("微软雅黑",Font.BOLD,20));
        info.setForeground(Color.red);
        regsiter1.setBorderPainted(false);
        back.setBorderPainted(false);
        name.setForeground(Color.white);
        mima.setForeground(Color.white);
        //统一字体格式

        a.add(name);
        a.add(mima);
        a.add(back);
        a.add(regsiter1);
        a.add(namenm);
        a.add(mimanm);
        a.add(info);
        //添加组件

        titleLabel.setBounds(0,0,600,700);
        titleLabel.setIcon(icon);
        a.add(titleLabel);
        //设置背景图片

        add(a);//将JFrame添加进入JDialog

        name.setBounds(new Rectangle(130, 130, 80, 45));
        namenm.setBounds(new Rectangle(210, 130, 200, 45));
        mima.setBounds(new Rectangle(130, 230, 80, 45));
        mimanm.setBounds(new Rectangle(210, 230, 200, 45));
        back.setBounds(new Rectangle(330, 340, 130, 50));
        regsiter1.setBounds(new Rectangle(140, 340, 130, 50));
        info.setBounds(new Rectangle(220, 50, 300, 100));
        //设置组件位置
        
        regsiter1.addActionListener(this);
        back.addActionListener(this);
        //按钮监听器
        
        setSize(570,550);
        setResizable(false);//窗口大小固定
        setLocation((sc.width-this.getWidth())/2,(sc.height-this.getHeight())/2);
        setVisible(true);
    }
    

    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource()==back)
        {
            dispose();

        }

        if(e.getSource()==regsiter1)
        {
            try
            {

                BufferedReader in=new BufferedReader(new FileReader("src/resources/user.txt"));
                boolean have=true;
                String line;
                if((namenm.getText()).equals("")||(mimanm.getText()).equals(""))
                {
                    info.setText("用户名或密码未输入!!!");
                }
                else
                {
                    while((line=in.readLine())!=null)//文件中一行一行读取
                    {
                        String s[]=line.split("\t");
                        if(s[0].equals(namenm.getText())==true)
                        {
                            have=false;
                            {
                                info.setText("用户名已存在!!!");
                                namenm.setText(null);
                                mimanm.setText(null);
                            }
                            break;
                        }

                    }
                    in.close();
                    if(have)
                    {
                        BufferedWriter out=new BufferedWriter(new FileWriter("src/resources/user.txt",true));
                        out.write(namenm.getText());
                        out.write("\t");
                        out.write(mimanm.getText()+"\n");//将用户名和密码写入文件
                        out.close();
                        {
                            info.setText("用户创建成功!!!");
                            namenm.setText(null);
                            mimanm.setText(null);
                        }
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
    }
}
