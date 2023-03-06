package function;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class Info extends JDialog implements ActionListener
{
    Toolkit kit=Toolkit.getDefaultToolkit();
    Dimension sc=kit.getScreenSize();//使应用在屏幕正中心
    JPanel a=new JPanel();
    JPanel b=new JPanel();

    JButton qr=new JButton("确认");
    public Info(JFrame p,String ts)
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

        this.setSize(300,150);
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


