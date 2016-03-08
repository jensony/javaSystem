import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.awt.image.ImageProducer;

import javax.swing.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.table.*;

public class CreateDialogAddUser extends JFrame{
	private JDialog dialog_addUer;
	private JButton button_addUser;
	private JTextField text_userID,text_userName,text_pwd;
	public Object[] createDialogAddUser(JFrame jFrame)
	    {
		    Object[] obj = new Object[5];
	    	dialog_addUer=new JDialog(jFrame,"添加用户",true);
	    	dialog_addUer.setSize(400,260);
	    	dialog_addUer.setDefaultCloseOperation(HIDE_ON_CLOSE);
	    	dialog_addUer.setResizable(false);
	    	dialog_addUer.getContentPane().setLayout(new FlowLayout());
	    	
	    	dialog_addUer.getContentPane().add(new JLabel("用户代码: "));
	    	text_userID=new JTextField(30);
	    	dialog_addUer.getContentPane().add(text_userID);
	    	
	    	dialog_addUer.getContentPane().add(new JLabel("用户名称: "));
	    	text_userName=new JTextField(30);
	    	dialog_addUer.getContentPane().add(text_userName);
	    	
	    	dialog_addUer.getContentPane().add(new JLabel("登录密码: "));
	    	text_pwd=new JTextField(30);
	    	dialog_addUer.getContentPane().add(text_pwd);
	    	
	    	button_addUser=new JButton("添 加");
	    	button_addUser.addActionListener((ActionListener) jFrame);
	    	dialog_addUer.getContentPane().add(button_addUser);
	    	obj[0]=dialog_addUer;
	    	obj[1]=text_userID;
	    	obj[2]=text_userName;
	    	obj[3]=text_pwd;
	    	obj[4]=button_addUser;
	    	
	    	return obj;
	    }
}
