import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
public class Menuemp extends JFrame{
	JMenu m1,m2,m3,m4,m5,m6,m7,m8,m9;
	//JFrame f;
	JMenuBar mb;
	JMenuItem i1,i2,i3,i4,i5,i6,i7,i8,r1,r2,r3,r4,r5;
	public Menuemp(){
		//f=new JFrame();
		mb=new JMenuBar();
		m1=new JMenu("Employee exp detail");
//		m2=new JMenu("Employee attendence");
//		m3=new JMenu("Employee leavetype");
		m4=new JMenu("Payroll details");
		m5=new JMenu("Payroll calculation");
		m6=new JMenu("Shift Details");
		m7=new JMenu("View");
		m8=new JMenu("Register Form");
		m9=new JMenu("Report");
		
		mb.add(m8);mb.add(m1);/*mb.add(m2);mb.add(m3)*/;mb.add(m4);mb.add(m5);mb.add(m6);mb.add(m9);mb.add(m7);
		setJMenuBar(mb);
		 
        i1=new JMenuItem("Employee exp detail");  
        i2=new JMenuItem("Employee attendance");  
        i3=new JMenuItem("Employee leavetype");  
        i4=new JMenuItem("Payroll details");  
        i5=new JMenuItem("Payroll calculation");
        i6=new JMenuItem("Shift Details");
        i7=new JMenuItem("View");
        i8=new JMenuItem("Register Form");
        
        r1=new JMenuItem("Employee Experiance Report");
        r2=new JMenuItem("Employee Attendence Report");
        r3=new JMenuItem("Employee LeaveType Report");
        r4=new JMenuItem("Payroll Calculation Report");
        r5=new JMenuItem("Shift Details Report");
        
        m8.add(i8);m1.add(i1);/*m2.add(i2);m3.add(i3)*/;m4.add(i4);m5.add(i5);m6.add(i6);m7.add(i7);
        m9.add(r1);m9.add(r2);m9.add(r3);m9.add(r4);
        i1.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
            	EmpExpDetail e1=new EmpExpDetail();
            }  
            });
            
 /*       i2.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                	Employee_attendence e2=new Employee_attendence();
                }  
             });
                
        i3.addActionListener(new ActionListener(){  
                 public void actionPerformed(ActionEvent e){  
                	 Employee_leavetype e3=new Employee_leavetype();
                 }  
                 });*/
        i4.addActionListener(new ActionListener(){  
                 public void actionPerformed(ActionEvent e){  
                	 Payrolldetails e4=new Payrolldetails();
                 }  
                 });
        i5.addActionListener(new ActionListener(){  
                     public void actionPerformed(ActionEvent e){  
                    	 Payrollcalc e5=new Payrollcalc();
                     }  
                });
        i6.addActionListener(new ActionListener(){  
                     public void actionPerformed(ActionEvent e){  
                    	 Shiftdetail e6=new Shiftdetail();
                    	 
                     }  
                });
        i7.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
           	 Main e7=new Main();
           	 e7.main(null);
            }  
       });
        i8.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
            	RegisterForm e8=new RegisterForm();
            }  
       });
        
        
        r1.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
            	EmployeeExpreport r11=new EmployeeExpreport();
            	r11.main(null);
            }  
       });
        
        r2.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
            	EmployeeAttendanceReport r22=new EmployeeAttendanceReport();
            	r22.main(null);
            }  
       });
        
        r3.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
            	EmployeeLeavetypeReport r33=new EmployeeLeavetypeReport();
            	r33.main(null);
            }  
       });
        
        r4.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
            	PayrollcalcReport r44=new PayrollcalcReport();
            	r44.main(null);
            }  
       });
        
        r5.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
            	ShiftdetailsReport r55=new ShiftdetailsReport();
            	r55.main(null);
            }  
       });
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0,0,screenSize.width, screenSize.height);;
		setLayout(null);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Menuemp();		
	}
}