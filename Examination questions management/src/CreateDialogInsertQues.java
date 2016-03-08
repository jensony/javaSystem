import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.awt.image.ImageProducer;

import javax.swing.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.table.*;

public class CreateDialogInsertQues extends JFrame{
	private JDialog dialog_insert;
	private JButton button_insert;
	private JTextField text_QTno1,text_Qno1,text_question,text_difficulty,text_answer,
			text_itemA,text_itemB,text_itemC,text_itemD;
	private JComboBox combo_Qtype1;
    public  Object[] createDialogInsertQues(JFrame jFrame,Object[] quesTyoe )
    {
    	Object[] obj = new Object[12];
    	dialog_insert=new JDialog(this,"插入试题",true);
    	dialog_insert.setSize(400,260);
    	dialog_insert.setDefaultCloseOperation(HIDE_ON_CLOSE);
    	dialog_insert.setResizable(false);
    	dialog_insert.getContentPane().setLayout(new FlowLayout());
    	dialog_insert.getContentPane().add(new JLabel("题  型: "));
    	combo_Qtype1=new JComboBox(quesTyoe);
    	combo_Qtype1.addItemListener((ItemListener) jFrame);
   		dialog_insert.getContentPane().add(combo_Qtype1);
    	dialog_insert.getContentPane().add(new JLabel("　题  号: "));
    	text_QTno1=new JTextField("1");
    	text_QTno1.setEditable(false);
    	dialog_insert.getContentPane().add(text_QTno1);
    	text_Qno1=new JTextField(6);
    	dialog_insert.getContentPane().add(text_Qno1);
    	dialog_insert.getContentPane().add(new JLabel("　难  度: "));
    	text_difficulty=new JTextField(4);
    	dialog_insert.getContentPane().add(text_difficulty);
    	dialog_insert.getContentPane().add(new JLabel("题  目: "));
    	text_question=new JTextField(30);
    	dialog_insert.getContentPane().add(text_question);
    	dialog_insert.getContentPane().add(new JLabel("A选项: "));
    	text_itemA=new JTextField(12);
    	dialog_insert.getContentPane().add(text_itemA);
    	dialog_insert.getContentPane().add(new JLabel("B选项: "));
    	text_itemB=new JTextField(12);
    	dialog_insert.getContentPane().add(text_itemB);
    	dialog_insert.getContentPane().add(new JLabel("C选项: "));
    	text_itemC=new JTextField(12);
    	dialog_insert.getContentPane().add(text_itemC);
    	dialog_insert.getContentPane().add(new JLabel("D选项: "));
    	text_itemD=new JTextField(12);
    	dialog_insert.getContentPane().add(text_itemD);
    	dialog_insert.getContentPane().add(new JLabel("答  案: "));
    	text_answer=new JTextField(30);
    	dialog_insert.getContentPane().add(text_answer);
    	button_insert=new JButton("添 加");
    	button_insert.addActionListener((ActionListener) jFrame);
    	dialog_insert.getContentPane().add(button_insert);
    	dialog_insert.setBounds(100, 150,400,260);
    	
    	
    	obj[0]=dialog_insert;
    	obj[1]=combo_Qtype1;
    	obj[2]=text_QTno1;
    	obj[3]=text_Qno1;
    	obj[4]=text_difficulty;
    	obj[5]=text_question;
    	obj[6]=text_itemA;
    	obj[7]=text_itemB;
    	obj[8]=text_itemC;
    	obj[9]=text_itemD;
    	obj[10]=text_answer;
    	obj[11]=button_insert;
    	
    	return obj;
    	
    	
    }
}
