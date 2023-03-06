package function;

import java.awt.Font;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CountAll extends JFrame implements ActionListener{
	
	JTextField jtfCoures = new JTextField(); 
	JTextField jtfStudent = new JTextField(); 
	JLabel jlCoures = new JLabel("已有课程门数",JLabel.CENTER);
	JLabel jlStudent = new JLabel("学生总人数",JLabel.CENTER);
	JButton jbBack = new JButton("返回");
	
	JLabel titleLabel = new JLabel();
    ImageIcon icon = new ImageIcon("src/resources/背景图.jpg");
	
    JPanel a = new JPanel();
    
    Font jtfFont = new Font("黑体",Font.PLAIN,50);
    Font jlFont = new Font("黑体",Font.PLAIN,25);
    
	public CountAll() {
		super("统计报表");
		setSize(800,600);//设置窗口大小
        setLocationRelativeTo(null);//设置窗体相对于另一组间的居中位置，参数null表示窗体相对于屏幕的中央位置
        setResizable(false);//禁止调整窗口大小
        setVisible(true);
        
        jtfCoures.setEditable(false);
	    jtfStudent.setEditable(false);
        
        jtfCoures.setHorizontalAlignment(JTextField.CENTER);
        jtfStudent.setHorizontalAlignment(JTextField.CENTER);
        
        jtfCoures.setFont(jtfFont);
        jtfStudent.setFont(jtfFont);
		jlCoures.setFont(jlFont);
		jlStudent.setFont(jlFont);
		jbBack.setFont(jlFont);
        
        
        a.setLayout(null);
        
        jtfCoures.setBounds(130,100,200,200);
        jtfStudent.setBounds(470,100,200,200);
		jlCoures.setBounds(130,350,200,50);
		jlStudent.setBounds(470,350,200,50);
		jbBack.setBounds(10,10,100,50);
        
        
        titleLabel.setBounds(-150,-150,this.getWidth()+150,this.getHeight()+150);
	    titleLabel.setIcon(icon);
	    
	    
	    a.add(jtfStudent);
	    a.add(jtfCoures);
	    a.add(jlCoures);
	    a.add(jlStudent);
	    a.add(jbBack);
	    a.add(titleLabel);
	    add(a);
	    setCoures();//读取课程文本获得课程人数
	    setStudent();//读取学生文本获得学生人数
	    
		
	    jbBack.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jbBack) {
			dispose();
		}
}
	
public static void main(String[] args) {
	new CountAll();
}
public void setCoures() {
	try
    {
        String line;
        File file = new File("src/resources/coures.txt");
        BufferedReader in = new BufferedReader(new FileReader(file));
        int i=0;
        while((line = in.readLine())!=null)
        {
            	i++;//行数加一
        }
        jtfCoures.setText(String.valueOf(i));
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
}
public void setStudent() {
	try
    {
        String line;
        File file = new File("src/resources/studentinfo.txt");
        BufferedReader in = new BufferedReader(new FileReader(file));
        int i=0;
        while((line = in.readLine())!=null)
        {
            	i++;//行数加一
        }
        jtfStudent.setText(String.valueOf(i));
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
}


}
