import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class Userlogin implements ActionListener {
	JFrame f;
	JLabel employeename;
	JLabel password;
	JLabel usertype,head;
	JTextField employeetxt;
	JPasswordField passwordtxt;
	JRadioButton r1;
	JRadioButton r2;
	JButton b1,b2;
	public static int eid;
	static DB db =new DB();
	public Userlogin(){
		f=new JFrame();
		head=new JLabel("LOGIN");
		employeename=new JLabel("EMPLOYEE_NAME:");
		password=new JLabel("PASSWORD:");
		usertype=new JLabel("USERTYPE");
		employeetxt=new JTextField();
		passwordtxt=new JPasswordField();
		r1=new JRadioButton("Employee");
		r2=new JRadioButton("Admin");
		ButtonGroup bg=new ButtonGroup();
		bg.add(r1);
		bg.add(r2);
		b1=new JButton("<= Login =>");
		b2=new JButton("New User");
		
		head.setBounds(400, 40, 100, 30);
		usertype.setBounds(200,300,160,50);
		employeename.setBounds(200, 100, 160, 50);
		password.setBounds(200, 200, 160, 50);
		employeetxt.setBounds(360,100,280,40);
		passwordtxt.setBounds(360, 200, 280, 40);
		r1.setBounds(330, 305, 150, 40);
		r2.setBounds(500, 305, 150, 40);
		
		b1.setBounds(480,430,100,60);
		b2.setBounds(280, 430, 100, 60);
		f.add(employeename);
		f.add(employeetxt);
		f.add(password);
		f.add(passwordtxt);
		f.add(r1);
		f.add(r2);
		f.add(b1);
		f.add(b2);
		f.add(usertype);
		f.add(head);
		f.setLayout(null);
		f.setSize(1000, 700);
		f.setVisible(true);
		f.setBounds(400, 170, 1000, 650);
		b1.addActionListener(this);
		b2.addActionListener(this);
	}
	public static void main(String[] args) {
		new Userlogin();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(b2)){
			new RegisterForm();
		}
		if(e.getSource().equals(b1)){
		String empname,ename = null;
		int pass;
		int pass1 = 0,id = 0;
		String usertyperadio = null,userchk = null;
		if(r1.isSelected()){usertyperadio=r1.getText().toString();}
		if(r2.isSelected()){usertyperadio=r2.getText().toString();}
		try {
			if(employeetxt.getText().isEmpty() || passwordtxt.getText().isEmpty() || usertyperadio==null){
				 JOptionPane.showMessageDialog(null, "Fill all Fields");
				 return;
			}
			empname=employeetxt.getText().toString();
			pass=Integer.parseInt(passwordtxt.getText());
			PreparedStatement pstm = db.con.prepareStatement("SELECT eid,empname,rnumber,usertype FROM registrationform WHERE empname='"+empname+"'");
			ResultSet Rs = pstm.executeQuery();
			
			if(Rs.next())
		    {
				 eid=Rs.getInt("eid");
		    	 ename = Rs.getString("empname");
		    	 pass1=Rs.getInt("rnumber");
		    	 userchk=Rs.getString("usertype");
		     }
		     if(empname.equals(ename) && pass==pass1){
		    	if(usertyperadio.equals(userchk) && usertyperadio.equals("Admin")){
		    	 Menuemp me=new Menuemp();
		    	 }
		    	else if(usertyperadio.equals(userchk) && usertyperadio.equals("Employee")){
//		    		JOptionPane.showMessageDialog(null, "Working");
		    		System.out.println(eid);
		    		Menuemp1 efr=new Menuemp1();
		    		//efr.main(null);
		    	}
		    	else{
			    	 JOptionPane.showMessageDialog(null, "Please enter the correct fields");
		    	}
		    	}
		     else{
		    	 JOptionPane.showMessageDialog(null, "Please enter the correct fields");
		     }
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	}
}