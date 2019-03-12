import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
public class Employee_leavetype implements ItemListener, ActionListener {
	Userlogin u;
	int id=u.eid;
	
	int eid=0,dayreq=0;
	String empnamedb=null,f_date,t_date,leavety,monthdb=null;
	JLabel empcode,empname,leavetype,fromdate,todate,daysreq,head,month;
	JButton b1;
	JFrame f;
	JTextField empnametxt,fromdatetxt,todatetxt,daysreqtxt;
	JComboBox cb1,cb2,cb3;
	DateFormat df;
	Date d1,d2;
	
	static DB db =new DB();
	public Employee_leavetype(){
		df = new SimpleDateFormat("yyyy-MM-dd");
		f=new JFrame();
		head=new JLabel("EMPLOYEE  LEAVE TYPE");
		empcode=new JLabel("Employee_Code");
		empname=new JLabel("Employee_Name");
		leavetype=new JLabel("LeaveType");
		fromdate=new JLabel("Form_Date");
		todate=new JLabel("To_Date");
		daysreq=new JLabel("Days_Required");
		month=new JLabel("Month");
		b1=new JButton("Click");
		empnametxt=new JTextField();
		fromdatetxt=new JTextField();
		todatetxt=new JTextField();
		daysreqtxt=new JTextField();
		
		cb1=new JComboBox();
		cb1.addItem("SELECT EMPLOYEE CODE");
		try{
			PreparedStatement pstm = db.con.prepareStatement("SELECT eid FROM registrationform WHERE eid="+id);
            ResultSet Rs = pstm.executeQuery();
            while(Rs.next()) {
                String pat = Rs.getString("eid");
                cb1.addItem(pat);
                cb1.addItemListener(this);
            }			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		String s_type[]={"choose leave type","Personal leave","Medical leave"};
		cb2=new JComboBox(s_type);
		
		String mon[]={"Select Month","January","February","March","April","May","June","July","August","Sep","Oct","Nov","dec"};
		cb3=new JComboBox(mon);
		
		head.setBounds(400, 20, 160, 30);
		empcode.setBounds(100,100,160,30);
		empname.setBounds(100, 200, 160, 30);
		leavetype.setBounds(100, 300, 160, 30);
		month.setBounds(400,300,160,30);
		fromdate.setBounds(100, 400, 160, 30);		
		todate.setBounds(100, 500, 160, 30);	
		daysreq.setBounds(100, 600, 160, 30);
		empnametxt.setBounds(300, 200, 260, 30);
		empnametxt.setEditable(false);
		fromdatetxt.setBounds(300, 400, 260, 30);
		todatetxt.setBounds(300, 500, 260, 30);
		daysreqtxt.setBounds(300, 600, 260, 30);
		daysreqtxt.setEditable(false);
		b1.setBounds(250, 700, 160, 30);
		cb1.setBounds(300, 100, 260, 30);
		cb2.setBounds(200, 300, 160, 30);
		cb3.setBounds(500, 300, 160, 30);
		f.add(todate);
		f.add(todatetxt);
		f.add(leavetype);
		f.add(head);
		f.add(fromdatetxt);
		f.add(fromdate);
		f.add(empnametxt);
		f.add(empname);
		f.add(empcode);
		f.add(daysreqtxt);
		f.add(daysreq);
		//f.add(cb3);
		f.add(cb2);
		f.add(cb1);
		f.add(b1);
		f.add(cb3);
		f.add(month);
		
		b1.addActionListener(this);
		f.setLayout(null);
		f.setBounds(500, 100,800, 800);
		f.setVisible(true);
}
	public static void main(String[] args) {
			new Employee_leavetype();
	}
	@Override
	public void itemStateChanged(ItemEvent i) {
		if (i.getStateChange() == ItemEvent.SELECTED) {
	          Object item = i.getItem();
	  		if(cb1.getSelectedIndex()==0){
				JOptionPane.showMessageDialog(null, "Select employee code");
				empnametxt.setText("");
				return;
			}
			try{
				eid=Integer.parseInt((String)cb1.getSelectedItem());
				PreparedStatement pstm = db.con.prepareStatement("SELECT eid,empname FROM registrationform WHERE eid="+eid);
				ResultSet Rs = pstm.executeQuery();
				if(Rs.next())
			    {
					empnamedb=Rs.getString("empname");
			    }
				empnametxt.setText(empnamedb);
			}catch(SQLException e1){
				e1.printStackTrace();
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent i) {
		leavety=cb2.getSelectedItem().toString();	
		monthdb=cb3.getSelectedItem().toString();
		System.out.println(monthdb);
		f_date=fromdatetxt.getText().toString();
		t_date=todatetxt.getText().toString();
		if(cb1.getSelectedIndex()==0 || cb2.getSelectedIndex()==0 || cb3.getSelectedIndex()==0){
			JOptionPane.showMessageDialog(null, "Please select the field");
			return;
		}
		try {
			if(f_date.isEmpty()){
				JOptionPane.showMessageDialog(null,"Enter From date");
				return;
			}else{
			d1 = df.parse(f_date);
			}
		} catch (ParseException e2) {
			JOptionPane.showMessageDialog(null,"Enter date in yyyy-MM-dd");
			e2.printStackTrace();
			return;
		}
		
		try {
			if(t_date.isEmpty()){
				JOptionPane.showMessageDialog(null,"Enter TO date");
				return;
			}else{
			d2 = df.parse(t_date);
			}
		} catch (ParseException e2) {
			JOptionPane.showMessageDialog(null,"Enter date in yyyy-MM-dd");
			e2.printStackTrace();
			return;
		}
		long diffin=d2.getTime()-d1.getTime();
		long diff=TimeUnit.DAYS.convert(diffin, TimeUnit.MILLISECONDS);
		//String setdayreq=
		daysreqtxt.setText(diff+"");
		if(daysreqtxt.getText().isEmpty()){
			JOptionPane.showMessageDialog(null,"Enter the Daysrequired");
			return;
		}else{
			try{
			dayreq=Integer.parseInt(daysreqtxt.getText());
			String sql = "INSERT INTO leavetype (empname,L_type,f_date,t_date,daysreq,eid,month) " + 
	                   "VALUES ('"+empnamedb+"','"+leavety+"','"+df.format(d1)+"','"+df.format(d2)+"','"+dayreq+"','"+eid+"','"+monthdb+"')";
			System.out.println(sql);
			db.stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null,"Saved in DB");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		}
	}
}
