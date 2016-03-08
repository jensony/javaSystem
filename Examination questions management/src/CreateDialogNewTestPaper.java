import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretListener;

public class CreateDialogNewTestPaper extends JFrame{
	private JDialog dialog_new;
	private JButton button_new;
	private JTextField text_dif1,text_dif2,text_Pno;
	private JTextField[] text_quantity,text_score;
	private JPanel[] panel;
	private JLabel label_total;
	private JLabel []label_score;
    public  Object[] createDialogNewTestPaper(JFrame jFrame,Object[] obj)
    {
    	Object[] newPager = new Object[10];
    	
    	int i,n=obj.length;
    	dialog_new=new JDialog(jFrame,"新建试卷",true);
    	dialog_new.setSize(280,(n+5)*30+45);
    	dialog_new.setDefaultCloseOperation(HIDE_ON_CLOSE);
    	dialog_new.setResizable(false);
    	JPanel panel_main=new JPanel(new GridLayout(n+5,1));
    	JPanel panel_south=new JPanel(new FlowLayout(FlowLayout.CENTER));
    	panel=new JPanel[n+5];
    	
    	text_dif1=new JTextField("1",3);
    	text_dif1.setHorizontalAlignment(JTextField.CENTER);
    	text_dif2=new JTextField("5",3);
    	text_dif2.setHorizontalAlignment(JTextField.CENTER);
    	for(i=0; i<n+5; i++)
    	{
    		panel[i]=new JPanel(new FlowLayout(FlowLayout.LEFT));
    		panel_main.add(panel[i]);
    	}
    	panel[0].add(new JLabel(" 试卷号: "));
    	text_Pno=new JTextField(10);
    	text_quantity=new JTextField[n];
    	text_score=new JTextField[n];
    	label_score=new JLabel[n];
    	panel[0].add(text_Pno);
    	panel[0].add(new JLabel("　总计"));
    	label_total=new JLabel("0");
    	panel[0].add(label_total);
    	panel[0].add(new JLabel("分"));
    	panel[1].add(new JLabel("----------------------------------------------------------------------"));
    	panel[2].add(new JLabel(" 题型   每题分数      题目数    该类型题总分"));
    	for(i=0; i<n; i++)
    	{
    		panel[i+3].add(new JLabel(obj[i].toString()+" "));
    		text_score[i]=new JTextField("0",3);
    		text_score[i].setHorizontalAlignment(JTextField.CENTER);
    		text_score[i].addCaretListener((CaretListener) jFrame);
    		text_quantity[i]=new JTextField("0",3);
    		text_quantity[i].setHorizontalAlignment(JTextField.CENTER);
    		text_quantity[i].addCaretListener((CaretListener) jFrame);
    		label_score[i]=new JLabel("0",JLabel.CENTER);
    		panel[i+3].add(text_score[i]);
    		panel[i+3].add(new JLabel("   "));
			panel[i+3].add(text_quantity[i]);
    		panel[i+3].add(new JLabel("             "));
    		panel[i+3].add(label_score[i]);
    		panel[i+3].add(new JLabel("分"));
    	}
    	panel[i+3].add(new JLabel("----------------------------------------------------------------------"));
    	panel[n+4].add(new JLabel(" 难度范围: "));
    	panel[n+4].add(text_dif1);
    	panel[n+4].add(new JLabel(" ～  "));
    	panel[n+4].add(text_dif2);
    	button_new=new JButton("生成试卷");
    	button_new.addActionListener((ActionListener) jFrame);
    	panel_south.add(button_new);
     	dialog_new.getContentPane().add(panel_main);
    	dialog_new.getContentPane().add(panel_south,"South");
    	
    	newPager[0]=dialog_new;
    	newPager[1]=panel;
    	newPager[2]=text_dif1;
    	newPager[3]=text_dif2;
    	newPager[4]=text_Pno;
    	newPager[5]=text_quantity;
    	newPager[6]=text_score;
    	newPager[7]=label_total;
    	newPager[8]=label_score;
    	newPager[9]=button_new;
    	
    	return newPager;
    	
    	
    }
}
