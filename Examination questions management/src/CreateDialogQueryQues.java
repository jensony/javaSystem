import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.awt.image.ImageProducer;

import javax.swing.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.table.*;

public class CreateDialogQueryQues extends JFrame{
	public CreateDialogQueryQues() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
	}
	private JDialog dialog_select;
	private JButton button_select;
	private JTextField text_QTno2,text_Qno2,text_Qinclude,text_Darrange1,text_Darrange2;
	private JComboBox combo_Qtype2,combo_operator1,combo_operator2,combo_logic;
	private JRadioButton radio_all,radio_Qno,radio_require;
	private JCheckBox check_question,check_difficulty;
    public  Object[] createDialogQueryQues(JFrame jFrame,Object[] quesTyoe )
    {
    	Object[] obj = new Object[16];
    	
    	dialog_select=new JDialog(jFrame,"查询试题",true);
    	dialog_select.setSize(280,350);
    	dialog_select.setDefaultCloseOperation(HIDE_ON_CLOSE);
    	dialog_select.setResizable(false);
    	dialog_select.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
    	
    	//查询题的类型
    	dialog_select.getContentPane().add(new JLabel("请选择要查询的题型: "));
    	combo_Qtype2=new JComboBox(quesTyoe);
    	combo_Qtype2.addItemListener((ItemListener) jFrame);
    	dialog_select.getContentPane().add(combo_Qtype2);
    	dialog_select.getContentPane().add(new JLabel("---------------------------------------------------------------"));
    	//查询方式1：查询全部
    	radio_all=new JRadioButton("查询全部",true);
    	dialog_select.getContentPane().add(radio_all);
    	dialog_select.getContentPane().add(new JLabel("　　　　　　　　　　　"));
    	dialog_select.getContentPane().add(new JLabel("---------------------------------------------------------------"));
    	//查询方式2：按题号查询
    	radio_Qno=new JRadioButton("按题号");
    	text_QTno2=new JTextField("1");
    	text_QTno2.setEditable(false);
    	text_Qno2=new JTextField(3);
    	dialog_select.getContentPane().add(radio_Qno);
    	dialog_select.getContentPane().add(text_QTno2);
    	dialog_select.getContentPane().add(text_Qno2);
    	dialog_select.getContentPane().add(new JLabel("　　　　　　　"));
    	dialog_select.getContentPane().add(new JLabel("---------------------------------------------------------------"));
    	//查询方式3：按条件查询
    	radio_require=new JRadioButton("按条件查询");
    	dialog_select.getContentPane().add(radio_require);
    	dialog_select.getContentPane().add(new JLabel("　　　　　　　　　"));
    	ButtonGroup bg_select=new ButtonGroup();
    	bg_select.add(radio_all);
    	bg_select.add(radio_Qno);
    	bg_select.add(radio_require);
    	
    	check_question=new JCheckBox("题目包含",true);
    	dialog_select.getContentPane().add(check_question);
    	text_Qinclude=new JTextField(16);
    	dialog_select.getContentPane().add(text_Qinclude);
    	check_difficulty=new JCheckBox("难度",true);
    	dialog_select.getContentPane().add(check_difficulty);
    	Object []obj_operator={">=",">","=","<","<="};
    	Object []obj_logic={"AND","OR"};
    	combo_operator1=new JComboBox(obj_operator);
    	combo_operator2=new JComboBox(obj_operator);
    	combo_operator2.setSelectedIndex(4);
    	combo_logic=new JComboBox(obj_logic);
    	text_Darrange1=new JTextField(2);
    	text_Darrange2=new JTextField(2);
    	dialog_select.getContentPane().add(combo_operator1);
    	dialog_select.getContentPane().add(text_Darrange1);
    	dialog_select.getContentPane().add(combo_logic);
    	dialog_select.getContentPane().add(combo_operator2);
    	dialog_select.getContentPane().add(text_Darrange2);
    	dialog_select.getContentPane().add(new JLabel("---------------------------------------------------------------"));
    	
	   	button_select=new JButton("查 询");
    	button_select.addActionListener((ActionListener) jFrame);
    	dialog_select.getContentPane().add(new JLabel("　　　　　　　"));
    	dialog_select.getContentPane().add(button_select);
    	
    	obj[0]=dialog_select;
    	obj[1]=combo_Qtype2;
    	obj[2]=radio_all;
    	obj[3]=radio_Qno;
    	obj[4]=text_QTno2;
    	obj[5]=text_Qno2;
    	obj[6]=radio_require;
    	obj[7]=check_question;
    	obj[8]=text_Qinclude;
    	obj[9]=check_difficulty;
    	obj[10]=combo_operator1;
    	obj[11]=combo_operator2;
    	obj[12]=combo_logic;
    	obj[13]=text_Darrange1;
    	obj[14]=text_Darrange2;
    	obj[15]=button_select;
    	
    	return obj;
    	
    	
    }
}
