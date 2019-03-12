import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import com.sun.glass.events.KeyEvent;

//import org.jdatepicker.impl.JDatePanelImpl;
//import org.jdatepicker.impl.JDatePickerImpl;
//import org.jdatepicker.impl.UtilDateModel;
public class RegisterForm implements ActionListener {
	int eid;
	String empiddb;
	JPanel panel;
	JLabel usertype;
	JLabel EmpId;
	JLabel Empname;
	JLabel address;
	JLabel R_password;
	JLabel Dob;
	JLabel gender;
	JLabel Doj;
	JLabel department;
	JRadioButton r1;
	JRadioButton r2;
	ButtonGroup bg;
	JTextField EID;
	JTextField 	Ename;
	JTextArea Addres;
	JTextField RP;
	JTextField DOB;
	JRadioButton r3;
	JRadioButton r4;
	ButtonGroup bg1;
	JTextField datejoin;
	JComboBox Dept;
    DateFormat df;
    Date d1,d2;
    
	public RegisterForm(){
		df = new SimpleDateFormat("yyyy-MM-dd");
		
		panel=new JPanel();
		usertype=new JLabel("usertype:");
		EmpId=new JLabel("Employee_ID:");
		Empname=new JLabel("Employee_Name:");
		address=new JLabel("Address:");
		R_password=new JLabel("Random_Password:");
		Dob=new JLabel("DOB");
		gender=new JLabel("Gender");
		Doj=new JLabel("Date_of_join");
		department=new JLabel("Department");
		//JDatePanelImpl datePanel = new JDatePanelImpl(model, null);
		//JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, null);
		
		r1=new JRadioButton("Employee");
		r2=new JRadioButton("Admin");
		bg=new ButtonGroup();    
		bg.add(r1);bg.add(r2);
		
		EID=new JTextField();
		EID.setEditable(false);
		try{
			PreparedStatement pstm = db.con.prepareStatement("select eid from registrationform where eid=(select max(eid) from registrationform)");
			ResultSet Rs = pstm.executeQuery();
			if(Rs.next())
		    {
				empiddb=Rs.getString("eid");
		    }
			if(empiddb == null){
				eid=1;
			}else{
			int e=Integer.parseInt(empiddb);
			eid=e+1;
			}
			String seteid=Integer.toString(eid);
			EID.setText(seteid);
			
		}catch(SQLException e1){
			e1.printStackTrace();
		}
		Ename=new JTextField("ABC");
		//AutoClearOnFocusManager.install(Ename);
				
		Addres=new JTextArea();
		RP=new JTextField();
		RP.setEditable(false);
		DOB=new JTextField("yyyy-MM-dd");
		
		r3=new JRadioButton("M");
		r4=new JRadioButton("F");
		bg1=new ButtonGroup();    
		bg1.add(r3);bg1.add(r4);
		
		datejoin=new JTextField("yyyy-MM-dd");
		
		String Depart[]={"SELECT DEPARTMENT","A","B","C","D"};
		Dept=new JComboBox(Depart);
		JButton b1=new JButton("Click");
		
		Random rand=new Random();
		int n=rand.nextInt(9999)+99;
		String randnum=Integer.toString(n);
		RP.setText(randnum);
		
		usertype.setBounds(100, 50, 100, 50);
		EmpId.setBounds(100, 100, 100, 50);
		Empname.setBounds(100, 170, 100, 50);
		address.setBounds(100, 270, 100, 50);
		R_password.setBounds(90, 470, 200, 50);
		Dob.setBounds(100, 570, 100, 50);
		gender.setBounds(100, 650, 100, 50);
		Doj.setBounds(100, 750, 100, 50);
		department.setBounds(100, 850, 100, 50);
		r1.setBounds(300, 50, 110, 30);
		r2.setBounds(450, 50, 110, 30);
		EID.setBounds(300, 110, 300, 30);
		Ename.setBounds(300, 190, 300, 30);
		Addres.setBounds(300, 290, 300, 100);
		RP.setBounds(300, 480, 280, 30);
		DOB.setBounds(300, 580, 280, 30);
	    r3.setBounds(300, 650, 60, 30);
	    r4.setBounds(450, 650, 60, 30);
	    datejoin.setBounds(300,750,310,30);
	    Dept.setBounds(300, 850, 310, 30);
	    b1.setBounds(800, 500, 100, 50);
	      
	    panel.add(usertype);
	    panel.add(EmpId);
	    panel.add(Empname);
	    panel.add(address);
	    panel.add(R_password);
	    panel.add(Dob);
	    panel.add(gender);
	    panel.add(Doj);
	    panel.add(department);
	    panel.add(r1);
	    panel.add(r2);
	    panel.add(EID);
	    panel.add(Ename);
	    panel.add(Addres);
	    panel.add(RP);
	    panel.add(RP);
	    panel.add(DOB);
	    panel.add(r3);
	    panel.add(r4);
	    panel.add(datejoin);
	    panel.add(Dept);
	    panel.add(b1);
	    
	    Ename.addMouseListener(new MouseAdapter() {
	    	public void mousePressed(MouseEvent e){
				Ename.setText("");
			}
		});
	    DOB.addMouseListener(new MouseAdapter() {
	    	public void mousePressed(MouseEvent e){
				DOB.setText("");
			}
		});
	    datejoin.addMouseListener(new MouseAdapter() {
	    	public void mousePressed(MouseEvent e){
				datejoin.setText("");
			}
		});
	    
		JScrollPane scrollBar=new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		JFrame f=new JFrame("REGISTER FORM");
		f.add(scrollBar);
	    
	    //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    panel.setLayout(null);
	    panel.setSize(1600, 900);
	 	f.setVisible(true);
	 	f.setBounds(350, 20,1200, 1100);
		b1.addActionListener(this);
		}
	// static UtilDateModel model = new UtilDateModel();
	//frame.add(datePicker);
	static DB db =new DB();

//	public static void main(String[] args) {
//		new RegisterForm();
//	
//	} 
	
	public void actionPerformed(ActionEvent e){
		String usertype = null;		
		String empname=Ename.getText().toString();
		String address = Addres.getText().toString();
		String Rpassword =RP.getText().toString();
		String dob = DOB.getText().toString();
		String gender = null;
		String doj =datejoin.getText().toString();
		String combovalue =Dept.getSelectedItem().toString();
		if((r1.isSelected()==false)&&(r2.isSelected()==false)){
				JOptionPane.showMessageDialog(null,"Please select Either Empp or admin");
				return;
				}
				else if(EID.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"Please Enter ID");
					return;
				}
				else if(Ename.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"Please Enter name");
					return;
				}
				else if(Addres.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"Please Enter Address");
					return;
				}
				
				try {
					d1 = df.parse(dob);
				} catch (ParseException e2) {
					JOptionPane.showMessageDialog(null,"Enter date of birth in yyyy-MM-dd");
					e2.printStackTrace();
					return;
				}
				if((r3.isSelected()==false)&&(r4.isSelected()==false)){
					JOptionPane.showMessageDialog(null,"Please select either M or F");
					return;
				}
				try {
					d2 = df.parse(doj);
				} catch (ParseException e2) {
						JOptionPane.showMessageDialog(null,"Enter date of join in yyyy-MM-dd");
						e2.printStackTrace();
						return;
					}
				if(combovalue=="SELECT DEPARTMENT"){
					JOptionPane.showMessageDialog(null,"Please select the department");
					return;
				}
		if(r1.isSelected()){usertype=r1.getText().toString();}
		if(r2.isSelected()){usertype=r2.getText().toString();}
		if(r3.isSelected()){gender=r3.getText().toString();}
		if(r4.isSelected()){gender=r4.getText().toString();}
				
				try{
				String sql = "INSERT INTO registrationform (usertype,empname,address,rnumber,dob,Gender,doj,department,status) " + 
		                   "VALUES ('"+usertype+"', '"+empname+"','"+address+"',"+Rpassword+",'"+df.format(d1)+"','"+gender+"','"+df.format(d2)+"','"+combovalue+"','A')";
				db.stmt.executeUpdate(sql);
				//JOptionPane.showMessageDialog(null,"Saved in DB");
				new Userlogin();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}}
}