import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
public class Menuemp1 extends JFrame{
	JMenu m1,m2,m3;
	//JFrame f;
	JMenuBar mb;
	JMenuItem i1,i2,i3;
	public Menuemp1(){
		//f=new JFrame();
		mb=new JMenuBar();
		m1=new JMenu("Employee Attendance");
		m2=new JMenu("Employee leave Request");
		m3=new JMenu("Employee Report");

		mb.add(m1);mb.add(m2);mb.add(m3);
		setJMenuBar(mb);
		 
        i1=new JMenuItem("Employee Attendance");  
        i2=new JMenuItem("Employee Leave Request");  
        i3=new JMenuItem("Employee Report");  
        
        m1.add(i1);m2.add(i2);m3.add(i3);
        
        i1.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
            	Employee_attendence e1=new Employee_attendence();
            }  
            });
        i2.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
           	 Employee_leavetype e3=new Employee_leavetype();
            }  
            });
        i3.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
            	EmployeeFinalReport e1=new EmployeeFinalReport();
            	e1.main(null);
            }  
            });
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0,0,screenSize.width, screenSize.height);
		setLayout(null);
		setVisible(true);
	}
//	public static void main(String[] args) {
//		new Menuemp1();		
//	}
}