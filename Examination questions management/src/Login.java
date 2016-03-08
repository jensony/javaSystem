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
		JFrame frame = new JFrame("系统登录");
		private JTextField userID;
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost/paper?user=root&password=root";
		private JPasswordField passwordField;
		public  Login() {
			// 窗体大小
			frame.setSize(900,595);
			frame.setLocation(200,100);
			// 显示窗体
			frame.setVisible(true);
			
			userID = new JTextField();
			userID.setBounds(535, 221, 141, 28);
			frame.getContentPane().add(userID);
			userID.setColumns(10);
			
			passwordField = new JPasswordField();
			passwordField.setBounds(535, 256, 141, 28);
			frame.getContentPane().add(passwordField);
			
			ImageIcon img = new ImageIcon("./image/login1.png");// 这是背景图片J
			JLabel imgLabel = new JLabel(img);// 将背景图放在标签里。
			frame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));// 注意这里是关键，将背景标签添加到jfram的// LayeredPane面板里。
			imgLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());// 设置背景标签的位置// 
			imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());// 设置背景标签的位置
			Container cp = frame.getContentPane();
			cp.setLayout(null); 
			
			// 这里选择绝对布局管理器，对于边界布局管理器，放入控件后，无法显示背景图片；因为将整个面板都填充满了；
			((JPanel) cp).setOpaque(false); // 这样就能显示出背景图片出来了
			
			
			
			Button button = new Button("连接测试");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try
		    		{
		    			con=new ConnectDB(driver,url);
		    			JOptionPane.showMessageDialog(frame,"连接数据库成功！","连接",JOptionPane.INFORMATION_MESSAGE);
		    		}
		    		catch(Exception ex)
		    		{
		    			JOptionPane.showMessageDialog(frame,"连接数据库失败！","连接",JOptionPane.OK_OPTION);
		    		}
				}
			});
			button.setBounds(472, 319, 76, 23);
			frame.getContentPane().add(button);
			
			Button btnNewButton = new Button("登录");
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
						JOptionPane.showMessageDialog(frame,"用户名或密码不正确，请联系管理员！","系统提示",JOptionPane.OK_OPTION);
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
