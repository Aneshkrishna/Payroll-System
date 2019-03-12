import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
public class EmpExpDetail implements ActionListener, ItemListener {
	JFrame f;
	JLabel head;
	JLabel employeeid;
	JLabel employeename;
	JLabel orgname;
	JLabel from;
	JLabel duration;
	JLabel department;
	JLabel to;
	JTextField employeenametxt;
	JTextField orgnametxt;
	JTextField fromtxt;
	JTextField durationtxt;
	JTextField departmenttxt;
	JTextField totxt;
	JButton b;
	JComboBox cb;
	int eid;
	DateFormat df;
	Date d1,d2;
	String empname = null,dept = null,dojdb=null;
	static DB db =new DB();
	public EmpExpDetail(){
		f=new JFrame();
		df=new SimpleDateFormat("yyyy-MM-dd");
		head=new JLabel("EMPLOYEE EXPERIANCE DETAILS");
		employeeid=new JLabel("Employee_CODE:");
		employeename=new JLabel("Employee_Name:");
		orgname=new JLabel("Organization_name:");
		from=new JLabel("From:");
		duration=new JLabel("Duration:");
		department=new JLabel("Department:");
		to=new JLabel("To:");
		employeenametxt=new JTextField();
		orgnametxt=new JTextField();
		fromtxt=new JTextField();
		durationtxt=new JTextField();
		departmenttxt=new JTextField();
		totxt=new JTextField();
		b=new JButton("Click");
		cb=new JComboBox();
		cb.addItem("SELECT EMPLOYEE CODE");
		try{
			PreparedStatement pstm = db.con.prepareStatement("SELECT eid FROM registrationform WHERE usertype='Employee' and status='A'");
            ResultSet Rs = pstm.executeQuery();
            while(Rs.next()) {
                String pat = Rs.getString("eid");
                cb.addItem(pat);
                cb.addItemListener(this);{
                	
                }
            }			
		}catch(SQLException e){
			e.printStackTrace();
		}
				
		employeeid.setBounds(100, 200, 100, 60);
		employeename.setBounds(100, 280, 100, 60);
		orgname.setBounds(100, 360, 120, 60);
		from.setBounds(100, 440, 100, 60);
		duration.setBounds(100, 520, 100, 60);
		department.setBounds(100, 600, 100, 60);
		to.setBounds(100, 680, 100, 60);
		
		cb.setBounds(300, 200, 290, 30);
		employeenametxt.setBounds(300, 290, 280, 40);
		orgnametxt.setBounds(300, 370, 280, 40);
		fromtxt.setBounds(300, 450, 280, 40);
		durationtxt.setBounds(300, 530, 280, 40);
		durationtxt.setEditable(false);
		departmenttxt.setBounds(300, 610, 280, 40);
		totxt.setBounds(300, 690, 280, 40);
		b.setBounds(350, 800, 100, 70);
		head.setBounds(280, 90, 400, 90);
		
		employeenametxt.setEditable(false);
		departmenttxt.setEditable(false);
		
		f.add(head);
		f.add(employeeid);
		f.add(cb);
		f.add(employeename);
		f.add(employeenametxt);
		f.add(orgname);
		f.add(orgnametxt);
		f.add(from);
		f.add(fromtxt);
		f.add(to);
		f.add(totxt);
		f.add(department);
		f.add(departmenttxt);
		f.add(duration);
		f.add(durationtxt);
		f.add(b);
		f.setLayout(null);
		f.setBounds(400, 20,1000, 1000);
		f.setVisible(true);
		b.addActionListener(this);
	}
//	public static void main(String[] args) {
//		new EmpExpDetail();
//	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String oname=orgnametxt.getText().toString();
		String fromS=fromtxt.getText().toString();
		String toS=totxt.getText().toString();
		if(cb.getSelectedIndex()==0){
			JOptionPane.showMessageDialog(null, "Please select");
			return;
		}else if(orgnametxt.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Please enter organization name");
			return;
		}
		try {
			if(fromS.isEmpty()){
				JOptionPane.showMessageDialog(null, "Enter From date");
				return;
			}else{
			d1 = df.parse(fromS);
			}
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(null,"Enter date in yyyy-MM-dd");
			e1.printStackTrace();
			return;
		}
		try {
			if(toS.isEmpty()){
				JOptionPane.showMessageDialog(null, "Enter To date");
				return;
			}else{
			d2 = df.parse(toS);
			}
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(null,"Enter date in yyyy-MM-dd");
			e1.printStackTrace();
			return;
		}
		long diffin=d2.getTime()-d1.getTime();
		long diff=TimeUnit.DAYS.convert(diffin, TimeUnit.MILLISECONDS);
		//String setdayreq=
		durationtxt.setText(diff+"");
		String duration=durationtxt.getText().toString();
		try{
			int empid=Integer.parseInt((String) cb.getSelectedItem());
			String sql = "INSERT INTO employee_exp_detail (eid,ename,orgname,fromdate,duration,department,todate) " + 
		                  "VALUES ('"+empid+"','"+empname+"','"+oname+"','"+fromS+"','"+duration+"','"+dept+"','"+toS+"')";
			System.out.println(sql);
			db.stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "save in DB");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	@Override
	public void itemStateChanged(ItemEvent i) {
		if (i.getStateChange() == ItemEvent.SELECTED) {
	          Object item = i.getItem();
	  		try{
				if(cb.getSelectedIndex()==0){
					employeenametxt.setText("");
					departmenttxt.setText("");
				}else{
					eid=Integer.parseInt((String) cb.getSelectedItem());
			PreparedStatement pstm = db.con.prepareStatement("SELECT empname,department,doj FROM registrationform WHERE eid="+eid);
			ResultSet Rs = pstm.executeQuery();
			
			if(Rs.next())
		    {
				empname=Rs.getString("empname");
				dept=Rs.getString("department");
				dojdb=Rs.getString("doj");
		    }
			employeenametxt.setText(empname);
			departmenttxt.setText(dept);
			fromtxt.setText(dojdb);
			}
			}catch(SQLException e1){
				e1.printStackTrace();
			}
		}
	}
}