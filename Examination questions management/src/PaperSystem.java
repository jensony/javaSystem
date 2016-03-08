import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.awt.image.ImageProducer;

import javax.swing.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.table.*;
public class PaperSystem extends JFrame implements ActionListener,ItemListener,TableModelListener,ListSelectionListener,CaretListener
{
	private JDialog dialog_insert,dialog_select,dialog_new,dialog_addUer,dialog_delUer;
	private JButton button_insert,button_select,button_new,button_addUser,button_delUser;
	private JTextField text_QTno1,text_Qno1,text_QTno2,text_Qno2,text_question,text_difficulty,text_answer,
			text_itemA,text_itemB,text_itemC,text_itemD,text_Qinclude,text_Darrange1,text_Darrange2,text_Pno,
			text_dif1,text_dif2,text_userID,text_userName,text_pwd,text_delUserID;
	private JComboBox combo_Qtype1,combo_Qtype2,combo_operator1,combo_logic,combo_operator2;
	private JCheckBox check_question,check_difficulty;
	private JRadioButton radio_all,radio_Qno,radio_require;
	private JMenuItem menuitem_connect,menuitem_close,menuitem_exit,menuitem_insert,menuitem_select,menuitem_refresh,menuitem_delete,
			menuitem_new,menuitem_open,menuitem_remove,menuitem_save,menuitem_theme,menuitem_about,menuitem_addUser,menuitem_delUser;
	private JTable table_show;
	private JPanel[] panel;
	private JTextField []text_quantity,text_score;
	private JLabel []label_score;
	private JLabel label_total;
	private JTextArea text_paper;
	private JScrollPane scrollpane_table,scrollpane_text;
	private DefaultTableModel dtm;
	private int currentTable;
	private Object []obj_editRow;
	private Object []obj={"ѡ����","�����","�ж���","�����","Ӧ����"};
	private String []table={"Selection","Blank","Judge","Brief","Application"};
	private String []chineseNum={"һ","��","��","��","��"};
	private String lastSelectSQL,paperNo;
	private ConnectDB con;
	
	/* ==================================�޸�����===================================   */
	private ImageIcon background;
	private JPanel imagePanel;
	/* =====================================================================   */
    public PaperSystem(String userID)
    {
    	super("��������ϵͳ                                         "+"��ǰ��¼�û���"+userID);
    	this.setSize(900,595);
    	this.setLocation(200,100);
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	/* ==================================�޸�����===================================   */
        
        background = new ImageIcon("image\\loginmain.png");// ����ͼƬ
        JLabel label = new JLabel(background);// �ѱ���ͼƬ��ʾ��һ����ǩ����
        // �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������
        label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());
        // �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
        imagePanel = (JPanel) this.getContentPane();
        imagePanel.setOpaque(false);
        // ���ݴ���Ĭ�ϵĲ��ֹ�����ΪBorderLayout
//        imagePanel.setLayout(new FlowLayout());

        this.getLayeredPane().setLayout(null);
        // �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
         /* =====================================================================   */

       
    	dtm=new DefaultTableModel();
    	table_show=new JTable();
    	scrollpane_table=new JScrollPane(table_show);
    	table_show.getModel().addTableModelListener(this);
    	table_show.getSelectionModel().addListSelectionListener(this);
    	table_show.getTableHeader().setReorderingAllowed(false);
    	table_show.getTableHeader().setResizingAllowed(false);
    	text_paper=new JTextArea();
    	text_paper.setFont(new Font("����",Font.PLAIN,16));
    	text_paper.setLineWrap(true);
    	scrollpane_text=new JScrollPane(text_paper);
    	this.createMenu(userID);
    	
    	//���������û�����
    	CreateDialogAddUser creatAddUser = new CreateDialogAddUser();
    	Object[] objAdd = creatAddUser.createDialogAddUser(this);
    	dialog_addUer=(JDialog) objAdd[0];
    	text_userID=(JTextField) objAdd[1];
    	text_userName=(JTextField) objAdd[2];
    	text_pwd=(JTextField) objAdd[3];
    	button_addUser=(JButton) objAdd[4];
    	
    	//���������û�����
    	CreateDialogDelUser creatDelUser = new CreateDialogDelUser();
    	Object[] objdel = creatDelUser.CreateDialogDelUser(this);
    	dialog_delUer=(JDialog) objdel[0];
    	text_delUserID=(JTextField) objdel[1];
    	button_delUser=(JButton) objdel[2];
    	
    	//�������⻭��
    	CreateDialogInsertQues insertQus = new CreateDialogInsertQues();
    	Object[] insertObj=insertQus.createDialogInsertQues(this,obj);
    	dialog_insert=(JDialog) insertObj[0];
    	combo_Qtype1=(JComboBox) insertObj[1];
    	text_QTno1=(JTextField) insertObj[2];
    	text_Qno1=(JTextField) insertObj[3];
    	text_difficulty=(JTextField) insertObj[4];
    	text_question=(JTextField) insertObj[5];
    	text_itemA=(JTextField)insertObj[6];
    	text_itemB=(JTextField)insertObj[7];
    	text_itemC=(JTextField)insertObj[8];
    	text_itemD=(JTextField)insertObj[9];
    	text_answer=(JTextField)insertObj[10];
    	button_insert=(JButton) insertObj[11];
    	
    	//��ѯ���⻭��
    	CreateDialogQueryQues querQus = new CreateDialogQueryQues();
    	Object[] queryObj=querQus.createDialogQueryQues(this, obj);
    	dialog_select=(JDialog) queryObj[0];
    	combo_Qtype2=(JComboBox) queryObj[1];
    	radio_all=(JRadioButton) queryObj[2];
    	radio_Qno=(JRadioButton) queryObj[3];
    	text_QTno2=(JTextField) queryObj[4];
    	text_Qno2=(JTextField) queryObj[5];
    	radio_require=(JRadioButton) queryObj[6];
    	check_question=(JCheckBox) queryObj[7];
    	text_Qinclude=(JTextField) queryObj[8];
    	check_difficulty=(JCheckBox) queryObj[9];
    	combo_operator1=(JComboBox) queryObj[10];
    	combo_operator2=(JComboBox) queryObj[11];
    	combo_logic=(JComboBox) queryObj[12];
    	text_Darrange1=(JTextField) queryObj[13];
    	text_Darrange2=(JTextField) queryObj[14];
    	button_select=(JButton) queryObj[15];
    	
    	//��������ҳ��
    	CreateDialogNewTestPaper newTestPaper = new CreateDialogNewTestPaper();
		Object[] newPager=newTestPaper.createDialogNewTestPaper(this, obj);
    	dialog_new=(JDialog) newPager[0];
    	panel=(JPanel[]) newPager[1];
    	text_dif1=(JTextField) newPager[2];
    	text_dif2=(JTextField) newPager[3];
    	text_Pno=(JTextField) newPager[4];
    	text_quantity=(JTextField[]) newPager[5];
    	text_score=(JTextField[]) newPager[6];
    	label_total=(JLabel) newPager[7];
    	label_score=(JLabel[]) newPager[8];
    	button_new=(JButton) newPager[9];
    	
    	this.setVisible(true);
    	
    }

    private void createMenu(String userID)
    {
    	JMenuBar menubar=new JMenuBar();
    	this.setJMenuBar(menubar);
    	JMenu menu_file=new JMenu("�ļ�(F)");
    	menu_file.setMnemonic('F');
    	menubar.add(menu_file);
//    	menuitem_connect=new JMenuItem("����(C)...");
//    	menuitem_connect.setMnemonic('C');
//    	menuitem_connect.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
//    	menuitem_connect.addActionListener(this);
//    	menu_file.add(menuitem_connect);
//    	menuitem_close=new JMenuItem("�Ͽ�(B)");
//    	menuitem_close.setMnemonic('B');
//    	menuitem_close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,InputEvent.CTRL_MASK));
//    	menuitem_close.addActionListener(this);
//    	menu_file.add(menuitem_close);
    	//�����û��˵�
    	if(userID != null &&"root".equals(userID)){
    		menuitem_addUser=new JMenuItem("�����û�(A)");
        	menuitem_addUser.setMnemonic('A');
        	menuitem_addUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_MASK));
        	menuitem_addUser.addActionListener(this);
        	menu_file.add(menuitem_addUser);
        	
        	menuitem_delUser=new JMenuItem("ɾ���û�(D)");
        	menuitem_delUser.setMnemonic('D');
        	menuitem_delUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.CTRL_MASK));
        	menuitem_delUser.addActionListener(this);
        	menu_file.add(menuitem_delUser);
        	
        	menu_file.addSeparator();
    	}
    	
    	menuitem_exit=new JMenuItem("�˳�(E)");
    	menuitem_exit.setMnemonic('E');
    	menuitem_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,InputEvent.CTRL_MASK));
    	menuitem_exit.addActionListener(this);
    	menu_file.add(menuitem_exit);
    	JMenu menu_question=new JMenu("����༭(E)");
    	menubar.add(menu_question);
    	menu_question.setMnemonic('E');
    	menuitem_insert=new JMenuItem("����(I)...");
    	menuitem_insert.setMnemonic('I');
    	menuitem_insert.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,InputEvent.CTRL_MASK));
    	menuitem_insert.addActionListener(this);
    	menu_question.add(menuitem_insert);
    	menuitem_select=new JMenuItem("��ѯ(Q)...");
    	menuitem_select.setMnemonic('Q');
    	menuitem_select.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,InputEvent.CTRL_MASK));
    	menuitem_select.addActionListener(this);
    	menu_question.add(menuitem_select);
    	menuitem_refresh=new JMenuItem("ˢ��(R)");
    	menuitem_refresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5,0));
    	menuitem_refresh.addActionListener(this);
    	menu_question.add(menuitem_refresh);
    	menuitem_delete=new JMenuItem("ɾ��(D)");
    	menuitem_delete.setMnemonic('D');
    	menuitem_delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.CTRL_MASK));
		menuitem_delete.addActionListener(this);
    	menu_question.add(menuitem_delete);
    	JMenu menu_paper=new JMenu("�Ծ����(P)");
    	menu_paper.setMnemonic('P');
    	menubar.add(menu_paper);
    	menuitem_new=new JMenuItem("�½�(N)...");
    	menuitem_new.setMnemonic('N');
    	menuitem_new.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK));
    	menuitem_new.addActionListener(this);
    	menu_paper.add(menuitem_new);
    	menuitem_open=new JMenuItem("��(O)...");
    	menuitem_open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK));
    	menuitem_open.setMnemonic('O');
    	menuitem_open.addActionListener(this);
    	menu_paper.add(menuitem_open);
    	menuitem_remove=new JMenuItem("ɾ��(R)...");
    	menuitem_remove.setMnemonic('R');
    	menuitem_remove.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,InputEvent.CTRL_MASK));
    	menuitem_remove.addActionListener(this);
    	menu_paper.add(menuitem_remove);
    	menuitem_save=new JMenuItem("����Ϊtxt(S)...");
    	menuitem_save.setMnemonic('S');
    	menuitem_save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
    	menuitem_save.addActionListener(this);
    	menu_paper.add(menuitem_save);
    	JMenu menu_help=new JMenu("����(H)");
    	menu_help.setMnemonic('H');
    	menubar.add(menu_help);
    	menuitem_theme=new JMenuItem("��������");
    	menuitem_theme.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,0));
    	menuitem_theme.addActionListener(this);
    	menu_help.add(menuitem_theme);
    	menu_help.addSeparator();
    	menuitem_about=new JMenuItem("����...");
    	menuitem_about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2,0));
    	menuitem_about.addActionListener(this);
    	menu_help.add(menuitem_about);
//    	this.unconnnectedMenu();
    	//��¼ҳ���Ѿ���֤���ݿ����ӣ��ڴ�ֱ���������ݿ⼴��
    	String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost/paper?user=root&password=root";
		try {
			con=new ConnectDB(driver,url);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
   
    
    public void actionPerformed(ActionEvent e)
    {
    	String sql;
    	if(e.getSource()==button_addUser)
    	{
    		try
    		{
    			sql="INSERT INTO user VALUES('";
    				if(text_userID.getText().trim().length()==0||text_userName.getText().trim().length()==0||text_pwd.getText().trim().length()==0){
    					JOptionPane.showMessageDialog(this,"�������ݲ�������","����",JOptionPane.OK_OPTION);
    				}
    				else 
    				{
    					sql+=text_userID.getText()+"','"+text_userName.getText()+"',"+text_pwd.getText()+");";
    	    			con.dataUpdate(sql);
    	    			text_userID.setText("");
    	    			text_userName.setText("");
    	    			text_pwd.setText("");
    	    			JOptionPane.showMessageDialog(this,"����ɹ���","����",JOptionPane.INFORMATION_MESSAGE);
    				}
    		}
    		catch(Exception ex)
    		{
    			 JOptionPane.showMessageDialog(this,"��������ʧ�ܣ��������ݣ�","����",JOptionPane.OK_OPTION);
    		}
    	}
    	else if(e.getSource()==button_delUser)
    	{
    		int r=JOptionPane.showConfirmDialog(this,"�������û�����ɾ�����Ƿ�ȷ����","ɾ��",JOptionPane.OK_CANCEL_OPTION);
    		if(r==JOptionPane.OK_OPTION)
    		{
    			dtm=(DefaultTableModel)table_show.getModel();
    			try
    			{
					sql="DELETE FROM user WHERE userid='"+text_delUserID.getText()+"';";
					con.dataUpdate(sql);
					text_delUserID.setText("");
    				JOptionPane.showMessageDialog(this,"�ѳɹ�ɾ���û���","ɾ��",JOptionPane.INFORMATION_MESSAGE);
    			}
    			catch(Exception ex){
    				JOptionPane.showMessageDialog(this,"ɾ������ʧ�ܣ��������ݣ�","ɾ��",JOptionPane.OK_OPTION);
    			}
    			
    		}
    	}
    	else if(e.getSource()==button_insert)
    	{
    		try
    		{
    			int i;
    			i=combo_Qtype1.getSelectedIndex();
    			sql="INSERT INTO "+table[i]+" VALUES('"+text_QTno1.getText()+text_Qno1.getText()+"','"+text_question.getText()+"','";
    			if(i==0)
    				if(text_itemA.getText().trim().length()==0||text_itemB.getText().trim().length()==0||text_itemC.getText().trim().length()==0||
    						text_itemD.getText().trim().length()==0||text_answer.getText().trim().length()==0||text_question.getText().trim().length()==0)
    					JOptionPane.showMessageDialog(this,"�������ݲ�������","����",JOptionPane.OK_OPTION);
    				else 
    				{
    					sql+=text_itemA.getText()+"','"+text_itemB.getText()+"','"+text_itemC.getText()+"','"+text_itemD.getText()+"','";
    					sql+=text_answer.getText()+"',"+text_difficulty.getText()+");";
    	    			i=con.dataUpdate(sql);
    	    			text_question.setText("");
    	    			text_itemA.setText("");
    	    			text_itemB.setText("");
    	    			text_itemC.setText("");
    	    			text_itemD.setText("");
    	    			text_answer.setText("");
    	    			JOptionPane.showMessageDialog(this,"����ɹ���","����",JOptionPane.INFORMATION_MESSAGE);
    				}
    			else
    				if(text_answer.getText().trim().length()==0||text_question.getText().trim().length()==0)
    					JOptionPane.showMessageDialog(this,"�������ݲ�������","����",JOptionPane.OK_OPTION);
    				else
    				{	
    					sql+=text_answer.getText()+"',"+text_difficulty.getText()+");";
    					i=con.dataUpdate(sql);
    					text_question.setText("");
    					text_itemA.setText("");
    					text_itemB.setText("");
    					text_itemC.setText("");
    					text_itemD.setText("");
    					text_answer.setText("");
    					JOptionPane.showMessageDialog(this,"����ɹ���","����",JOptionPane.INFORMATION_MESSAGE);
    				}
    		}
    		catch(Exception ex)
    		{
    			 JOptionPane.showMessageDialog(this,"��������ʧ�ܣ��������ݣ�","����",JOptionPane.OK_OPTION);
    		}
    	}
    	else if(e.getSource()==button_select)
    	{
    		int i;
    		i=combo_Qtype2.getSelectedIndex();
    		sql="SELECT * FROM "+table[i];
    		if(radio_all.isSelected())
    			sql+=";";
    		else if(radio_Qno.isSelected())
    			sql+=" WHERE Qno='"+text_QTno2.getText()+text_Qno2.getText()+"';";
    		else
    		{
    			String str_q,str_d;
    			str_q="Question LIKE '%"+text_Qinclude.getText()+"%'";
    			str_d="(Difficulty"+combo_operator1.getSelectedItem().toString()+text_Darrange1.getText()+" "+
    				combo_logic.getSelectedItem().toString()+" Difficulty"+combo_operator2.getSelectedItem().toString()
    				+text_Darrange2.getText()+")";
    			if(check_question.isSelected()&&check_difficulty.isSelected())
    				sql+=" WHERE "+str_q+" AND "+str_d+";";
    			else if(check_question.isSelected())
    				sql+=" WHERE "+str_q+";";
    			else if(check_difficulty.isSelected())
    				sql+=" WHERE "+str_d+";";
    			else
    				sql+=";";
    		}
    		this.selectSQL(sql,i);
    		this.remove(scrollpane_text);
    		getContentPane().add(scrollpane_table);
    		this.validate();
    		scrollpane_table.repaint();
    		menuitem_save.setEnabled(false);
    		menuitem_refresh.setEnabled(true);
    		currentTable=i;
    		lastSelectSQL=sql;
    	}
    	else if(e.getSource()==button_new)
    	{
    		try
    		{
    			int i,j,dif1,dif2,k,p,sum=0,n=obj.length;
    			DefaultTableModel tmp=new DefaultTableModel();
    			int []score=new int[n];
    			int []quantity=new int[n];
    			String [][]str_qno=new String[n][];
    			for(i=0; i<n; i++)
    			{
    				score[i]=Integer.parseInt(text_score[i].getText());
    				quantity[i]=Integer.parseInt(text_quantity[i].getText());
    				sum+=quantity[i];
    			}
    			dif1=Integer.parseInt(text_dif1.getText());
    			dif2=Integer.parseInt(text_dif2.getText());
    			if (sum==0)
    				throw new Exception("û����Ŀ");
    			if (dif1>dif2)
    				throw new Exception("�Ѷȴ���");
    			sql="SELECT * FROM PaperInfo WHERE Pno='"+text_Pno.getText()+"';";
        		tmp.setColumnCount(4);
        		tmp.setRowCount(0);
        		con.select(sql,tmp);
        		if(tmp.getRowCount()!=0)
        			throw new Exception("�Ծ��Ѵ���");
    			for(i=0; i<n; i++)
    			{
    				tmp.setRowCount(0);
    				tmp.setColumnCount(1);
    				sql="SELECT Qno FROM "+table[i]+" WHERE Difficulty>="+dif1+" AND Difficulty<="+dif2+";";
    				con.select(sql, tmp);
    				k=tmp.getRowCount();
    				if(k<quantity[i])
    					throw new Exception("��Ŀ����");
    				str_qno[i]=new String[quantity[i]];
    				for(j=0; j<quantity[i]; j++)
    				{
    					p=(int)(Math.random()*(k-j));
    					str_qno[i][j]=tmp.getValueAt(p,0).toString();
    					tmp.removeRow(p);
    				}
    				sql="INSERT INTO PaperInfo VALUES('"+text_Pno.getText()+"','"+Integer.toString(i+1)+"',"+score[i]+","+quantity[i]+");";
    				if(quantity[i]!=0)
    					con.dataUpdate(sql);
    				for(j=0; j<quantity[i]; j++)   			
    				{
    					sql="INSERT INTO Paper VALUES('"+text_Pno.getText()+"','"+str_qno[i][j]+"');";
    					con.dataUpdate(sql);
    				}
    			}
    			this.showPaper(text_Pno.getText());
    			dialog_new.setVisible(false);
    		}
    		catch(NumberFormatException nfe)
    		{
    			JOptionPane.showMessageDialog(this,"�������������޷������Ծ�","�����Ծ�",JOptionPane.OK_OPTION);
    		}
    		catch(Exception ex)
    		{
    			if(ex.getMessage().equals("��Ŀ����"))
    				JOptionPane.showMessageDialog(this,"��Ŀ�������㣬�޷������Ծ�","�����Ծ�",JOptionPane.OK_OPTION);
    			else if(ex.getMessage().equals("�Ѷȴ���"))
    					JOptionPane.showMessageDialog(this,"�Ѷ���������޷������Ծ�","�����Ծ�",JOptionPane.OK_OPTION);
    				 else if(ex.getMessage().equals("�Ծ��Ѵ���"))
    					  	 JOptionPane.showMessageDialog(this,"�Ծ��Ѵ��ڣ�","�����Ծ�",JOptionPane.OK_OPTION);
    				 	  else if(ex.getMessage().equals("û����Ŀ"))
    				 		  	  JOptionPane.showMessageDialog(this,"����Ҫ����Ŀ��","�����Ծ�",JOptionPane.OK_OPTION); 
    				 	  	   else
    				 		      JOptionPane.showMessageDialog(this,"�����Ծ�ʧ�ܣ�","�����Ծ�",JOptionPane.OK_OPTION);
    		}
    	}
    	else if(e.getSource()==menuitem_connect)
    	{
    		try
    		{
    			String driver="com.mysql.jdbc.Driver";
    			String url="jdbc:mysql://localhost/paper?user=root&password=root";
    			con=new ConnectDB(driver,url);
    			JOptionPane.showMessageDialog(this,"�������ݿ�ɹ���","����",JOptionPane.INFORMATION_MESSAGE);
    		}
    		catch(Exception ex)
    		{
    			JOptionPane.showMessageDialog(this,"�������ݿ�ʧ�ܣ�","����",JOptionPane.OK_OPTION);
    		}
    	}
    	else if(e.getSource()==menuitem_exit)
    	{
    		System.exit(0);
    	}
    	else if(e.getSource()==menuitem_addUser)
    	{
    		dialog_addUer.setLocation(this.getX()+this.getWidth()/2-200,this.getY()+this.getHeight()/2-130);
    		dialog_addUer.setVisible(true);
    	}
    	else if(e.getSource()==menuitem_delUser)
    	{
    		dialog_delUer.setLocation(this.getX()+this.getWidth()/2-200,this.getY()+this.getHeight()/2-130);
    		dialog_delUer.setVisible(true);
    	}
    	
    	else if(e.getSource()==menuitem_close)
    	{
    		try
    		{
//    			this.unconnnectedMenu();
    			this.remove(scrollpane_table);
    			this.remove(scrollpane_text);
    			this.repaint();
    			con.finalize();
    			JOptionPane.showMessageDialog(this,"�ѶϿ����ݿ����ӣ�","����",JOptionPane.INFORMATION_MESSAGE);
    		}
    		catch(Exception ex){}
    	}
    	else if(e.getSource()==menuitem_insert)
    	{
    		dialog_insert.setLocation(this.getX()+this.getWidth()/2-200,this.getY()+this.getHeight()/2-130);
        	dialog_insert.setVisible(true);
    	}
    	else if(e.getSource()==menuitem_select)
    	{
    		dialog_select.setLocation(this.getX()+this.getWidth()/2-140,this.getY()+this.getHeight()/2-130);
        	dialog_select.setVisible(true);
    	}
    	else if(e.getSource()==menuitem_refresh)
    	{
    		this.selectSQL(lastSelectSQL,currentTable);
    		menuitem_delete.setEnabled(false);
    	}
    	else if(e.getSource()==menuitem_delete)
    	{
    		int r=JOptionPane.showConfirmDialog(this,"�������������ɾ�����Ƿ�ȷ����","ɾ��",JOptionPane.OK_CANCEL_OPTION);
    		int []selectRows=table_show.getSelectedRows();
    		if(r==JOptionPane.OK_OPTION)
    		{
    			dtm=(DefaultTableModel)table_show.getModel();
    			try
    			{
    				for(int i=0; i<selectRows.length; i++)
    				{
    					sql="DELETE FROM "+table[currentTable]+" WHERE Qno='"+dtm.getValueAt(selectRows[i],0)+"';";
    					con.dataUpdate(sql);
    				}
    				table_show.getSelectionModel().clearSelection();
    				for(int i=0; i<selectRows.length; i++)
    					dtm.removeRow(selectRows[i]-i);
    				JOptionPane.showMessageDialog(this,"�ѳɹ�ɾ�����⣡","ɾ��",JOptionPane.INFORMATION_MESSAGE);
    			}
    			catch(Exception ex){}
    			
    		}
    	}
    	
    	else if(e.getSource()==menuitem_new)
    	{
    		dialog_new.setLocation(this.getX()+this.getWidth()/2-140,this.getY()+(this.getHeight()-dialog_new.getHeight())/2);
        	dialog_new.setVisible(true);
    	}
    	else if(e.getSource()==menuitem_open)
    	{
    		String pno=JOptionPane.showInputDialog(this,"������Ҫ�򿪵��Ծ��: ");
    		if(pno.trim().length()==0)
    			JOptionPane.showMessageDialog(this,"�Ծ�Ų���Ϊ�գ�","��",JOptionPane.INFORMATION_MESSAGE);
    		else showPaper(pno);
    	}
    	else if(e.getSource()==menuitem_remove)
    	{
    		String pno=JOptionPane.showInputDialog(this,"������Ҫɾ�����Ծ��: ");
    		if(pno.trim().length()==0)
    			JOptionPane.showMessageDialog(this,"�Ծ�Ų���Ϊ�գ�","ɾ��",JOptionPane.INFORMATION_MESSAGE);
    		else
    		{
    			try
    			{
    				sql="DELETE FROM PaperInfo WHERE Pno='"+pno+"';";
    				int i=con.dataUpdate(sql);
    				if(i==0)
    					throw new Exception();
    				sql="DELETE FROM Paper WHERE Pno='"+pno+"';";
    				con.dataUpdate(sql);
    				JOptionPane.showMessageDialog(this,"�ѳɹ�ɾ���Ծ�","ɾ��",JOptionPane.INFORMATION_MESSAGE);
    			}
    			catch(Exception ex)
    			{
    				JOptionPane.showMessageDialog(this,"�Ծ�"+pno+"�����ڣ��޷�ɾ����","����",JOptionPane.OK_OPTION);
    			}
    		}
    	}
    	else if(e.getSource()==menuitem_save)
    	{
    		FileDialog saveAs=new FileDialog(this,"����Ϊtxt",FileDialog.SAVE);
    		saveAs.setFile("�Ծ�"+paperNo+".txt");
    		saveAs.setVisible(true);
    		String fileName=saveAs.getDirectory()+saveAs.getFile();
    		try
    		{   
            	File file=new File(fileName);
            	FileWriter writeOut=new FileWriter(file);
            	writeOut.write(text_paper.getText());
            	writeOut.close();
          	}   
          	catch(IOException ioe)
          	{
            	JOptionPane.showMessageDialog(this,"����Ϊtxt�ļ�ʧ�ܣ�","����",JOptionPane.OK_OPTION);
          	}
    	}
    	else if(e.getSource()==menuitem_theme)
    	{
    		try
    		{
    			Runtime.getRuntime().exec("notepad.exe ����.txt");

    		}
    		catch(Exception ex)
    		{
    			JOptionPane.showMessageDialog(this,"�Ҳ��������ļ���","����",JOptionPane.OK_OPTION);
    		}
    	}
    	else if(e.getSource()==menuitem_about)
    	{
    		JOptionPane.showMessageDialog(this,"��������ϵͳ\n����:jensony","����...",1,null);
    	}
	}
	public void itemStateChanged(ItemEvent e)
	{
		if(e.getSource()==combo_Qtype1)
		{
			if(combo_Qtype1.getSelectedIndex()!=0)
			{
				int i=combo_Qtype1.getSelectedIndex()+1;
				text_QTno1.setText(Integer.toString(i));
				text_itemA.setEnabled(false);
				text_itemB.setEnabled(false);
				text_itemC.setEnabled(false);
				text_itemD.setEnabled(false);
			}
			else
			{
				text_QTno1.setText("1");
				text_itemA.setEnabled(true);
				text_itemB.setEnabled(true);
				text_itemC.setEnabled(true);
				text_itemD.setEnabled(true);
			}
		}
		else if(e.getSource()==combo_Qtype2)
		{
			text_QTno2.setText(Integer.toString(combo_Qtype2.getSelectedIndex()+1));		
		}
			
	}
	public void valueChanged(ListSelectionEvent e)
	{
		if(table_show.getSelectedRowCount()>0)
		{
			int row=table_show.getSelectedRow();
			int col=table_show.getColumnCount();
			obj_editRow=new Object[col];
			for(int i=0; i<col; i++)
				obj_editRow[i]=table_show.getValueAt(row,i);
			menuitem_delete.setEnabled(true);
		}
	}
	public void tableChanged(TableModelEvent e)
	{
		if(table_show.getSelectedRowCount()>0)
		{
			int row=table_show.getEditingRow(),col=table_show.getEditingColumn();
			String sql;
			String []str_selection={"Qno","Question","ItemA","ItemB","ItemC","ItemD","Answer","Difficulty"};
			String []str_other={"Qno","Question","Answer","Difficulty"};
			try
			{
				if(!table_show.getValueAt(row,col).equals(obj_editRow[col]))
				{
					sql="UPDATE "+table[currentTable]+" SET ";
					if(currentTable==0)
						sql+=str_selection[col];
					else
						sql+=str_other[col];
					sql+="='"+table_show.getValueAt(row,col)+"' WHERE Qno='"+obj_editRow[0]+"';";
					con.dataUpdate(sql);
					JOptionPane.showMessageDialog(this,"�޸ĳɹ���","�޸�",JOptionPane.INFORMATION_MESSAGE);
				}
			
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(this,"�޷�����޸Ĳ�����","�޸�",JOptionPane.OK_OPTION);
				table_show.setValueAt(obj_editRow[col],row,col);
			}
		}
	}
	public void caretUpdate(CaretEvent e)
	{
		int s,q,i=0,n=obj.length,total=0;
		try
		{
			for(i=0; i<n; i++)
				if(e.getSource()==text_quantity[i]||e.getSource()==text_score[i])
				{
					s=Integer.parseInt(text_score[i].getText());
					q=Integer.parseInt(text_quantity[i].getText());
					label_score[i].setText(Integer.toString(s*q));
				}
			total=0;
			for(i=0; i<n; i++)
				total+=Integer.parseInt(label_score[i].getText());
			label_total.setText(Integer.toString(total));
		}
		catch(Exception ex)
		{
			label_score[i].setText("---");
			label_total.setText("---");
		}
	}
	 public void selectSQL(String sql,int i)
	    {
	    	try
	    	{
	    		int n;
	    		Object[] obj_selection={"���","��Ŀ","Aѡ��","Bѡ��","Cѡ��","Dѡ��","��","�Ѷ�"};
				Object[] obj_other={"���","��Ŀ","��","�Ѷ�"};
				table_show.getSelectionModel().clearSelection();
			 	dtm=(DefaultTableModel)table_show.getModel();
	    		dtm.setRowCount(0);
	    		if(i==0)
	    		{
	    			dtm.setColumnCount(8);
	    			n=con.select(sql,dtm);
	    			dtm.setColumnIdentifiers(obj_selection);
	   			}
	    		else
	    		{
	    			dtm.setColumnCount(4);
	    			n=con.select(sql,dtm);
	    			dtm.setColumnIdentifiers(obj_other);
	    		}
	    		int wid=this.getWidth();
	     		TableColumn []column=new TableColumn[table_show.getColumnCount()];
	    		for(int k=0;k<table_show.getColumnCount();k++)
	    			column[k]=table_show.getColumnModel().getColumn(k);
	    		column[0].setPreferredWidth(50);
	    		if(i==0)
	    		{
	    			column[1].setPreferredWidth(Math.max(200,(wid-160)/3));
	    			for(int p=2; p<6; p++)
	    				column[p].setPreferredWidth(Math.max(100,(wid-170)/6));
	    			column[6].setPreferredWidth(50);
	    			column[7].setPreferredWidth(50);
	    		}
	    		else if(i==3)
	    		{
	    			column[1].setPreferredWidth(150);
	    			column[2].setPreferredWidth(Math.max(150,wid-270));
	    			column[3].setPreferredWidth(50);
	    		}
	    		else
	    		{
	    			column[1].setPreferredWidth(Math.max(100,wid-220));
	    			column[2].setPreferredWidth(100);
	    			column[3].setPreferredWidth(50);
	    		}
	    		table_show.invalidate();
	    		dialog_select.setVisible(false);
	    		if(n==0)
	    		{
	    			JOptionPane.showMessageDialog(this,"û���ҵ����������Ľ����","��ѯ",JOptionPane.OK_OPTION);
	    		}	
	    	}
	    	catch(Exception ex)
	    	{
	    		 JOptionPane.showMessageDialog(this,"�޷�ִ�в�ѯ���������ݣ�","��ѯ",JOptionPane.OK_OPTION);
	    	}
	    }
	 public void showPaper(String pno)
	    {
	    	String sql;
	    	DefaultTableModel tmp_pi,tmp_q;
	    	try
	    	{
	    		int i,j,s,q,qt;
	    		sql="SELECT * FROM PaperInfo WHERE Pno='"+pno+"';";
	    		tmp_pi=new DefaultTableModel();
	    		tmp_pi.setColumnCount(4);
	    		tmp_pi.setRowCount(0);
	    		con.select(sql,tmp_pi);
	    		if(tmp_pi.getRowCount()==0)
	    			throw new Exception("�Ծ�����");
	    		tmp_q=new DefaultTableModel();
	    		text_paper.setText("");
	    		paperNo=pno;
	    		for(i=0; i<tmp_pi.getRowCount(); i++)
	    		{
	    			qt=Integer.parseInt(tmp_pi.getValueAt(i,1).toString());
	    			s=Integer.parseInt(tmp_pi.getValueAt(i,2).toString());
	    			q=Integer.parseInt(tmp_pi.getValueAt(i,3).toString());
	    			if(qt==1)
	    			{
	    				tmp_q.setColumnCount(5);
	    				sql="SELECT Question,ItemA,ItemB,ItemC,ItemD FROM "+table[qt-1]+",Paper WHERE Pno='"+pno+"' AND "+table[qt-1]+".Qno = Paper.Qno;";
	    			}
	    			else
	    			{
	    				tmp_q.setColumnCount(1);
	    				sql="SELECT Question FROM "+table[qt-1]+",Paper WHERE Pno='"+pno+"' AND "+table[qt-1]+".Qno = Paper.Qno;";
	    			}
	    			tmp_q.setRowCount(0);
	    			con.select(sql,tmp_q);
	    			text_paper.append(chineseNum[i]+"��"+obj[qt-1].toString()+"��"+s+"'��"+q+"="+(s*q)+"'��\r\n");    		
	    			for(j=0; j<tmp_q.getRowCount(); j++)
	    				{
	    					text_paper.append((j+1)+". "+tmp_q.getValueAt(j,0).toString()+"\r\n");
	    					if(qt==1)
	    						text_paper.append("A. "+tmp_q.getValueAt(j,1)+"\r\nB. "+tmp_q.getValueAt(j,2)+"\r\nC. "+tmp_q.getValueAt(j,3)+"\r\nD. "+tmp_q.getValueAt(j,4)+"\r\n");
	    					text_paper.append("\r\n");
	    				}
	    			text_paper.append("\r\n");	
	    		}
	    		text_paper.setEditable(false);
	    		this.remove(scrollpane_table);
	    		getContentPane().add(scrollpane_text);
	    		text_paper.setCaretPosition(0);
	    		this.validate();
	    		scrollpane_text.repaint();
	    		menuitem_refresh.setEnabled(false);
	    		menuitem_delete.setEnabled(false);
	    		menuitem_save.setEnabled(true);
	    	}
	    	catch(Exception e)
	    	{
	    		if(e.getMessage().equals("�Ծ�����"))
	    		    JOptionPane.showMessageDialog(this,"û���ҵ��Ծ�"+pno+"��","���Ծ�",JOptionPane.OK_OPTION);
	    	}
	    
	    }
  
   
   
    public static void main(String[] args) 
    {
    	new PaperSystem("root");
    }
}
