import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CreateDialogDelUser extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JDialog dialog_delUer;
	private JButton button_delUser;
	private JTextField text_delUserID;
	public Object[] CreateDialogDelUser(JFrame jFrame)
	    {
		    Object[] obj = new Object[5];
		    dialog_delUer=new JDialog(jFrame,"删除用户",true);
	    	dialog_delUer.setSize(400,260);
	    	dialog_delUer.setDefaultCloseOperation(HIDE_ON_CLOSE);
	    	dialog_delUer.setResizable(false);
	    	dialog_delUer.getContentPane().setLayout(new FlowLayout());
	    	
	    	dialog_delUer.getContentPane().add(new JLabel("用户代码: "));
	    	text_delUserID=new JTextField(30);
	    	dialog_delUer.getContentPane().add(text_delUserID);
	    	
	    	
	    	button_delUser=new JButton("删除用户");
	    	button_delUser.addActionListener((ActionListener) jFrame);
	    	dialog_delUer.getContentPane().add(button_delUser);
	    	
	    	obj[0]=dialog_delUer;
	    	obj[1]=text_delUserID;
	    	obj[2]=button_delUser;
	    	
	    	return obj;
	    }
}
