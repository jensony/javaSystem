package com.test;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class JavaTest extends JFrame
{
  /**
	 * 
	 */
	private static final long serialVersionUID = -6894138835777956957L;

TestArea testPanel=null;
  Container con=null;
  public JavaTest()
  {
    super("javaÄ£Äâ¿¼ÊÔ");
    testPanel=new TestArea();
    con=getContentPane();
    con.add(testPanel,BorderLayout.CENTER); 
  
   addWindowListener(new WindowAdapter()
                 { public void windowClosing(WindowEvent e)
                     { System.exit(0);
      	             }
                 });
    setVisible(true);
    setBounds(60,40,660,460);
    con.validate();
    validate();
  }
 public static void main(String args[])
   {
      new JavaTest();
   }
}
