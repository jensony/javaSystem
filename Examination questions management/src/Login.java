	import java.awt.Button;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

	public class Login {
		private ConnectDB con;
		JFrame frame = new JFrame("ϵͳ��¼");
		private JTextField userID;
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost/paper?user=root&password=root";
		private JPasswordField passwordField;
		public  Login() {
			// �����С
			frame.setSize(900,595);
			frame.setLocation(200,100);
			// ��ʾ����
			frame.setVisible(true);
			
			userID = new JTextField();
			userID.setBounds(535, 221, 141, 28);
			frame.getContentPane().add(userID);
			userID.setColumns(10);
			
			passwordField = new JPasswordField();
			passwordField.setBounds(535, 256, 141, 28);
			frame.getContentPane().add(passwordField);
			
			ImageIcon img = new ImageIcon("./image/login1.png");// ���Ǳ���ͼƬJ
			JLabel imgLabel = new JLabel(img);// ������ͼ���ڱ�ǩ�
			frame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));// ע�������ǹؼ�����������ǩ��ӵ�jfram��// LayeredPane����
			imgLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());// ���ñ�����ǩ��λ��// 
			imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());// ���ñ�����ǩ��λ��
			Container cp = frame.getContentPane();
			cp.setLayout(null); 
			
			// ����ѡ����Բ��ֹ����������ڱ߽粼�ֹ�����������ؼ����޷���ʾ����ͼƬ����Ϊ��������嶼������ˣ�
			((JPanel) cp).setOpaque(false); // ����������ʾ������ͼƬ������
			
			
			
			Button button = new Button("���Ӳ���");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try
		    		{
		    			con=new ConnectDB(driver,url);
		    			JOptionPane.showMessageDialog(frame,"�������ݿ�ɹ���","����",JOptionPane.INFORMATION_MESSAGE);
		    		}
		    		catch(Exception ex)
		    		{
		    			JOptionPane.showMessageDialog(frame,"�������ݿ�ʧ�ܣ�","����",JOptionPane.OK_OPTION);
		    		}
				}
			});
			button.setBounds(472, 319, 76, 23);
			frame.getContentPane().add(button);
			
			Button btnNewButton = new Button("��¼");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DefaultTableModel dtm = new DefaultTableModel();
					dtm.setRowCount(0);
					dtm.setColumnCount(8);
					String sql = "SELECT * FROM user where userid='"+userID.getText()+"'"+" and pwd='"+passwordField.getText()+"'";
					int n = 0;
	    			try {
		    			con=new ConnectDB(driver,url);
	    				n=con.select(sql, dtm);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(n>0){
						new PaperSystem(userID.getText());
						frame.setVisible(false);
					}else{
						JOptionPane.showMessageDialog(frame,"�û��������벻��ȷ������ϵ����Ա��","ϵͳ��ʾ",JOptionPane.OK_OPTION);
					}
					
				}
			});
			btnNewButton.setBounds(560, 319, 93, 23);
			frame.getContentPane().add(btnNewButton);
			
			}
		 public static void main(String[] args) 
		    {
		    	new Login();
		    }
		}
