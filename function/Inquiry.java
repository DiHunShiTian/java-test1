package function;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import java.io.RandomAccessFile;
import java.lang.reflect.Member;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.ls.LSOutput;


public class Inquiry extends JFrame implements ActionListener{

	JLabel jlCouresName = new JLabel("请输入所要查询的课程编号：");
	
	JTextField jtfCouresNameIn = new JTextField();
	
	JButton jbInquiry = new JButton("查询");
	JButton jbEditor = new JButton("编辑");
	JButton jbDelete = new JButton("删除");
	JButton jbCancel = new JButton("取消");
	
	JLabel titleLabel = new JLabel();//背景
    ImageIcon icon = new ImageIcon("src/resources/背景图.jpg");
	
    JPanel a = new JPanel();
	
    Font fontMenu = new Font("黑体",Font.PLAIN,20);
    Font fontMenu1 = new Font("黑体",Font.PLAIN,15);
    
    Container contentPane = this.getContentPane();
    
    String[] title = {"课程编号","课程名称","课程性质","总学时","学分","开课学期","允许的选修人数","已选修人数"};//表头
    Object[][] list;//表数据
    DefaultTableModel model = new DefaultTableModel(list,title) {
    	public boolean isCellEditable(int row,int column) {
    		return false;
    	}
    };//设置表格不可更改
    JTable table = new JTable(model);
    
    JScrollPane jsp = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);//创建滑动条
    
    DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
    
    File file = new File("src/resources/coures.txt");
    File file2 = new File("src/resources/classdata.txt");
    
    int[] selectRows;
    Object[][] List;
    int selectRow;
    static int count;
    static boolean isInquiry;
    static boolean OnlyOne;
    static boolean NoThing;
    static int Columns;
    
	public Inquiry() {
		
		super("查询课程功能");
		setSize(1100,700);//设置窗口大小
		setLocationRelativeTo(null);//设置窗体相对于另一组间的居中位置，参数null表示窗体相对于屏幕的中央位置
		setResizable(false);//禁止调整窗口大小
		setVisible(true);
		
		way();//将文本导入表格
		List = new Object[table.getRowCount()][table.getColumnCount()];
		way3();
	
		
		jlCouresName.setFont(fontMenu);//设置字体
		jbInquiry.setFont(fontMenu);
		jbEditor.setFont(fontMenu);
		jbDelete.setFont(fontMenu);
		jbCancel.setFont(fontMenu);
		table.setFont(fontMenu1);
	
		//table板块
		table.setRowHeight(40);
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();//设置单元格
		r.setHorizontalAlignment(JLabel.CENTER);//设置表格数据居中
		table.setDefaultRenderer(Object.class, r);
		
		a.setLayout(null);
		
		a.add(jlCouresName);
		a.add(jtfCouresNameIn);
		a.add(jbInquiry);
		a.add(jbEditor);
		a.add(jbDelete);
		a.add(jbCancel);
		a.add(jsp);
		
		jlCouresName.setBounds(100,40,300,70);//设置位置大小
		jtfCouresNameIn.setBounds(360,55,300,40);
		jbInquiry.setBounds(700,55,120,40);
		jbEditor.setBounds(350,150,120,50);
		jbDelete.setBounds(500,150,120,50);
		jbCancel.setBounds(650,150,120,50);
		jsp.setBounds(10,230,this.getWidth()-30,this.getHeight()-280);
		titleLabel.setBounds(-100,-100,this.getWidth()+100,this.getHeight()+100);
	    titleLabel.setIcon(icon);
	    
	    a.add(titleLabel);
	    
		add(a);
		
		jbEditor.addActionListener(this);
		jbInquiry.addActionListener(this);
		jbDelete.addActionListener(this);
		jbCancel.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		list = new Object[table.getRowCount()][table.getColumnCount()];
		
		
		selectRows = table.getSelectedRows();
		selectRow = table.getSelectedRows().length;
		
		
		//-------------------查询板块-----------------
		if(e.getSource()==jbInquiry&&!jtfCouresNameIn.getText().equals("")) {//点击查询并且文本框不为空
			isInquiry=true;//表示查了且找到了
			OnlyOne=true;//表示只有一个
			try {
				BufferedReader in = new BufferedReader(new FileReader(file));
				String line;
				while((line = in.readLine())!=null) {
					String[] s = line.split("\t");
					for(int i = 0;i<s.length;i++) {
					if(s[0].equals(jtfCouresNameIn.getText())) {//将文本框内容与文本进行匹配
						jtfCouresNameIn.setText("");
						model.setRowCount(0);
						model.addRow(new Object[] {s[0],s[1],s[2],s[3],s[4],s[5],s[6],s[7]});
						
						return;//表示找到了
					}
				}
			}
				new Info(this,"查询课程不存在，请重新输入");
				jtfCouresNameIn.setText("");
				isInquiry=false;//表示查了但没找到
				OnlyOne =false;
				NoThing = true;
				in.close();
			} catch (FileNotFoundException fe)
		    {
		        System.err.println(fe);
		    }
		    catch (IOException ioe)
		    {
		        System.err.println(ioe);
		    }
				
		}
		
		else if (e.getSource()==jbInquiry&&jtfCouresNameIn.getText().equals("")) {//点击查询并且文本框内无内容，刷新表格
			isInquiry=false;//表示没查
			tableModel.setRowCount(0);
			way();
		}
		//-------------------编辑板块-----------------
		
		if(e.getSource()==jbEditor&&!isInquiry) {
			if(selectRow>1) {
				new Info(this, "请勿选择多项课程进行编辑");
			}
			else if(selectRow==0) {
				new Info(this, "请选择一项课程进行编辑");
			}
			else{
				
				Load();//为list赋值
				
				new Editor(this,list,selectRows);
				dispose();
			}
		}
		if(e.getSource()==jbEditor&&OnlyOne) {
			if(selectRow>1) {
				new Info(this, "请勿选择多项课程进行编辑");
			}
			else if(selectRow==0) {
				new Info(this, "请选择一项课程进行编辑");
			}
			else{
			reLoad();
			new Editor(this, List, selectRows);
			dispose();
			}
		}
		//-------------------删除板块-----------------
		if(e.getSource()==jbDelete&&(selectRows.length!=0)&&!isInquiry) {
			
			//删除表格内容
			int removeNum = 0;
			for(int i=0;i<selectRows.length;i++) {
				
				int selectRowindex = selectRows[i];
				model.removeRow(selectRowindex-removeNum);
				removeNum++;
			}
			new Info(this,"删除成功!");
			//删除文本内容-----先删除表格内的内容，然后再将表格内的内容读取出来再写入到文本中
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(file));//写入
				for(int i =0;i<table.getRowCount();i++) {
					for(int j =0;j<table.getColumnCount();j++) {
						out.write((String) tableModel.getValueAt(i, j));
						out.write("\t");
					}
					out.write("\n");
				}
				out.close();
			} catch (FileNotFoundException fe)
		    {
		        System.err.println(fe);
		    }
		    catch (IOException ioe)
		    {
		        System.err.println(ioe);
		    }
		}
		else if(e.getSource()==jbDelete&&(selectRows.length==0)){
			new Info(this,"请选择要删除的课程" );
		}
		else if (e.getSource()==jbDelete&&OnlyOne) {
			reDelete();
			model.setRowCount(0);
			new Info(this, "删除成功");
		}
		//-------------------取消板块-----------------
		if(e.getSource()==jbCancel) {
			dispose();
		}
		
	}
public void way() {//把文本输入到表格中
	try {
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line;
		while((line = in.readLine())!=null) {
			String[] s = line.split("\t");
			Columns = s.length;
			BufferedReader in2 = new BufferedReader(new FileReader(file2));
			String line2;
			while((line2 = in2.readLine())!=null) {
				String[] s2 = line2.split("\t");
				if(s2[1].equals(s[0])) {
					count++;
				}
		}
			model.addRow(new Object[] {s[0],s[1],s[2],s[3],s[4],s[5],s[6],count});
			count = 0;
			
			
			in2.close();
		}
		BufferedWriter out = new BufferedWriter(new FileWriter(file));//写入
		for(int i =0;i<table.getRowCount();i++) {
			for(int j =0;j<table.getColumnCount();j++) {
				out.write(String.valueOf(tableModel.getValueAt(i, j)) );
				out.write("\t");
			}
			out.write("\n");
		}
		out.close();
		in.close();
	} catch (FileNotFoundException fe)
    {
        System.err.println(fe);
    }
    catch (IOException ioe)
    {
        System.err.println(ioe);
    }
}
public void way2() {
	try {
		BufferedWriter out = new BufferedWriter(new FileWriter(file));//写入
		for(int i =0;i<table.getRowCount();i++) {
			for(int j =0;j<table.getColumnCount();j++) {
				
				out.write((String) tableModel.getValueAt(i, j));
				out.write("\t");
			}
			out.write("\n");
		}
		out.close();
	} catch (FileNotFoundException fe)
    {
        System.err.println(fe);
    }
    catch (IOException ioe)
    {
        System.err.println(ioe);
    }
}
public void way3() {
	try
    {
        String line;
        BufferedReader in = new BufferedReader(new FileReader(file));
        int i=0;
        while((line = in.readLine())!=null)
        {
        	
            String s[] = line.split("\t");
            	for(int j=0;j<table.getColumnCount();j++)
            	{
            		List[i][j]=s[j];//赋值一行
            	}
            	i++;//行数加一
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

}
public void edit(Object[][] result){
	try {
		model.setRowCount(0);
		
		for(int i =0;i<result.length;i++) {
			
			model.addRow(result[i]);
		}
		
		BufferedWriter out = new BufferedWriter(new FileWriter(file));//写入
		for(int i =0;i<table.getRowCount();i++) {
			for(int j =0;j<table.getColumnCount();j++) {
				out.write((String) tableModel.getValueAt(i, j));
				out.write("\t");
			}
			out.write("\n");
		}
		out.close();
	} catch (FileNotFoundException fe)
    {
        System.err.println(fe);
    }
    catch (IOException ioe)
    {
        System.err.println(ioe);
    }
	}
public void Load() {//为list赋值
	try
    {
		model.setRowCount(0);
        String line;
        BufferedReader in = new BufferedReader(new FileReader(file));
        int i=0;
        while((line = in.readLine())!=null)
        {
        	
            String s[] = line.split("\t");
            	for(int j=0;j<table.getColumnCount();j++)
            	{
            		list[i][j]=s[j];//赋值一行
            	}
            	i++;//行数加一
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
}
public void reLoad() {
	int a = 0;
		String cellValue = (String)tableModel.getValueAt(((int)table.getSelectedRow()), 0);//获取选中行数的第一列内容
		for(int i = 0; i<List.length;i++) {
            			if(cellValue.equals(List[i][0])) {//判断所要查询的课程原来为哪一行
            				selectRows[0]=a;
            			}
            			a++;
            }
        
}
public void reDelete() {
	String cellValue = (String)tableModel.getValueAt(((int)table.getSelectedRow()), 0);//获取选中行数的第一列内容

	try {
		String line;
        int i=0;
        BufferedReader in = new BufferedReader(new FileReader(file));
        while((line=in.readLine())!=null)
        {
            String s[]=line.split("\t");
            if(s[0].equals(cellValue)!=true)
            {
                List[i][0]=s[0];
                List[i][1]=s[1];
                List[i][2]=s[2];
                List[i][3]=s[3];
                List[i][4]=s[4];
                i++;  
            }
            else
            	continue;
           
        }
		
		BufferedWriter out = new BufferedWriter(new FileWriter(file));//写入
        for(int j=0; j<i; j++)
        {
            out.write(List[j][0].toString());
            out.write("\t");
            out.write(List[j][1].toString());
            out.write("\t");
            out.write(List[j][2].toString());
            out.write("\t");
            out.write(List[j][3].toString());
            out.write("\t");
            out.write(List[j][4].toString());
            out.write("\t");
            out.write(List[j][5].toString());
            out.write("\t");
            out.write(List[j][6].toString());
            out.write("\t");
            out.write(List[j][7].toString());
            out.write("\n");
        }
		in.close();
		out.close();
	} catch (FileNotFoundException fe)
    {
        System.err.println(fe);
    }
    catch (IOException ioe)
    {
        System.err.println(ioe);
    }
}
public static void main(String[] args) {
	new MainShow();
}
}