import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import javax.swing.*;
public class Employee_attendence implements ItemListener, ActionListener {
	Userlogin u;
	int id=u.eid;
	JFrame f;
	JLabel head;
	JLabel empcode;
	JLabel empname;
	JLabel date;
	JLabel intime;
	JLabel outtime;
	JLabel empdept;
	JButton b1,b2,b3,b4;
	JTextField empnametxt,datetxt,intimetxt,outtimetxt,empdepttxt;
	JComboBox cb;
	int eid=0;
	String empnamedb=null,dept=null,dateS,intimeS,outtimeS,deptS,absntS;
	static DB db =new DB();
	public Employee_attendence(){
		f=new JFrame();
		head=new JLabel("Employee attendance");
		empcode=new JLabel("Employee_Code");
		empname=new JLabel("Employee_Name");
		date=new JLabel("date");
		intime=new JLabel("IN_time");
		outtime=new JLabel("Out_time");
		empdept=new JLabel("Employee_department");
		b1=new JButton("Click");
		b2=new JButton("Click");
		b3=new JButton("Click");
		b4=new JButton("Save");
		empnametxt=new JTextField();
		empnametxt.setEditable(false);
		datetxt=new JTextField();
		intimetxt=new JTextField();
		outtimetxt=new JTextField();
		empdepttxt=new JTextField();
		empdepttxt.setEditable(false);
		cb=new JComboBox();
		cb.addItem("SELECT EMPLOYEE CODE");
		try{
			PreparedStatement pstm = db.con.prepareStatement("SELECT eid FROM registrationform WHERE eid="+id);
            ResultSet Rs = pstm.executeQuery();
            while(Rs.next()) {
                String pat = Rs.getString("eid");
                cb.addItem(pat);
                cb.addItemListener(this);
            }			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		head.setBounds(300, 0, 300,100);
		empcode.setBounds(100, 100, 160, 30);
		empname.setBounds(100, 200, 160, 30);
		date.setBounds(100,300,160,30);
		intime.setBounds(100, 500, 160, 30);
		outtime.setBounds(100, 600, 160, 30);
		empdept.setBounds(100, 700, 160, 30);
		empnametxt.setBounds(300, 200,260, 30);
		datetxt.setBounds(300, 300, 260, 30);
		b1.setBounds(600, 300, 90, 30);
		intimetxt.setBounds(300, 500, 260, 30);
		b2.setBounds(600, 500, 90, 30);
		outtimetxt.setBounds(300, 600, 260, 30);
		b3.setBounds(600, 600, 90, 30);
		empdepttxt.setBounds(300, 700, 260, 30);
		cb.setBounds(300, 100, 260, 30);
		b4.setBounds(400,800,90,30);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		f.add(head);
		f.add(empcode);
		f.add(empname);
		f.add(empnametxt);
		f.add(date);
		f.add(datetxt);
		f.add(empdept);
		f.add(empdepttxt);
		f.add(intime);
		f.add(intimetxt);
		f.add(outtime);
		f.add(outtimetxt);
		f.add(b1);
		f.add(b2);
		f.add(b3);
		f.add(b4);
		f.add(cb);
		f.setLayout(null);
		f.setBounds(400, 50, 1000, 950);
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Employee_attendence();			
	}	


public void itemStateChanged(ItemEvent i) {
	if (i.getStateChange() == ItemEvent.SELECTED) {
          Object item = i.getItem();
  		if(cb.getSelectedIndex()==0){
			JOptionPane.showMessageDialog(null, "Select employee code");
			empnametxt.setText("");
			empdepttxt.setText("");
			return;
		}
		try{
			eid=Integer.parseInt((String)cb.getSelectedItem());
			PreparedStatement pstm = db.con.prepareStatement("SELECT eid,empname,department FROM registrationform WHERE eid="+eid);
			ResultSet Rs = pstm.executeQuery();
			if(Rs.next())
		    {
				dept=Rs.getString("department");
				empnamedb=Rs.getString("empname");
		    }
			empnametxt.setText(empnamedb);
			empdepttxt.setText(dept);
		}catch(SQLException e1){
			e1.printStackTrace();
		}
	}
}

@Override
public void actionPerformed(ActionEvent e) {
		
	if(e.getSource().equals(b1)){
		String date = new SimpleDateFormat("YYYY-MM-dd", Locale. getDefault()). format(new Date());
		datetxt.setText(date);
		}
	if(e.getSource().equals(b2)){
		String time = new SimpleDateFormat("HH:mm:ss", Locale. getDefault()). format(new Date());
		intimetxt.setText(time);
	}
	if(e.getSource().equals(b3)){
		String time = new SimpleDateFormat("HH:mm:ss", Locale. getDefault()). format(new Date());
		outtimetxt.setText(time);
	}
	if(e.getSource().equals(b4)){
		//empnamedb=null,dept=null,dateS,intimeS,outtimeS,deptS;
		dateS=datetxt.getText().toString();
		intimeS=intimetxt.getText().toString();
		outtimeS=outtimetxt.getText().toString();
		if(dateS.isEmpty() || intimeS.isEmpty() || outtimeS.isEmpty()){
			JOptionPane.showMessageDialog(null, "Enter all fields");
			return;
		}else{
			try{
			String sql = "INSERT INTO empatds (ename,date,intime,outtime,dept,eid) " + 
	                   "VALUES ('"+empnamedb+"','"+dateS+"','"+intimeS+"','"+outtimeS+"','"+dept+"','"+eid+"')";
			System.out.println(sql);
			db.stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null,"Saved in DB");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		}
	}
}
}