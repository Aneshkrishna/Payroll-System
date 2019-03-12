import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.swing.*;
public class Payrollcalc implements ItemListener, ActionListener {
	//int eid=0;
	int leavedaysgetdb,eidgetdb,eidsetdb;
	
	String empnamedb=null,basicpaystr=null,hrastr,pfstr,otherpaystr,totalsalarystr,daysreqdb=null,leavedayssrtdb=null,monthgetdb=null,monthsetdb=null;
	int eid=0,basicpaydb=0,hradb=0,lopdb=0,otherdb=0,pfdb=0,total1db=0,total2db=0,cscdb=0,nsdb=0;
	JFrame f;
	JLabel head,employeecode,employeename,basicpay,hra,lop,other,pf,month;
	JTextField employeenametxt,basicpaytxt,hratxt,loptxt,othertxt,pftxt,total1,total2,currentsalarytxt,netsalarytxt;
	JComboBox cb,cb1;
	JButton b1,b2,b3,b4;
	static DB db =new DB();
	public Payrollcalc(){
		f=new JFrame();
		head=new JLabel("PAY ROLL CALCULATION");
		employeecode=new JLabel("Employee Code");
		employeename=new JLabel("Employee Name");
		basicpay=new JLabel("Basic Pay");
		hra=new JLabel("HRA");
		lop=new JLabel("Leave days");;
		other=new JLabel("Other Pay");
		pf=new JLabel("PF");
		month=new JLabel("Month");
		
		employeenametxt=new JTextField();
		basicpaytxt=new JTextField();
		hratxt=new JTextField();
		loptxt=new JTextField();
		othertxt=new JTextField();
		pftxt=new JTextField();
		total1=new JTextField();
		total2=new JTextField();
		currentsalarytxt=new JTextField();
		netsalarytxt=new JTextField();
		b1=new JButton("Lop amount");
		b2=new JButton("Current salary");
		b3=new JButton("Net salary");
		b4=new JButton("Apply");
		cb=new JComboBox();
		cb.addItem("SELECT EMPLOYEE CODE");
		try{
			PreparedStatement pstm = db.con.prepareStatement("SELECT eid FROM registrationform WHERE usertype='Employee' and status='A' ");
            ResultSet Rs = pstm.executeQuery();
            while(Rs.next()) {
                String pat = Rs.getString("eid");
                cb.addItem(pat);
                cb.addItemListener(this);
            }			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		String s[]={"Select Month","January","February","March","April","May","June","July","August","Sep","Oct","Nov","dec"};
		cb1=new JComboBox(s);
		cb1.addItemListener(this);
		
		head.setBounds(400, 20, 160, 30);
		employeecode.setBounds(50,130,110,30);
		cb.setBounds(160, 130, 200, 30);
		employeename.setBounds(410, 130, 110, 30);
		employeenametxt.setBounds(540, 130, 200, 30);
		employeenametxt.setEditable(false);
		basicpay.setBounds(820, 130,110,30);		
		basicpaytxt.setBounds(930, 130, 200, 30);
		month.setBounds(820, 250, 110, 30);
		cb1.setBounds(930, 250, 200, 30);
		hra.setBounds(160, 250, 60, 30);
		lop.setBounds(130, 350, 100, 30);
		b1.setBounds(100, 480, 120, 30);
		hratxt.setBounds(260, 250, 90, 30);
		loptxt.setBounds(260, 350, 90, 30);
		total1.setBounds(260, 480, 90, 30);
		other.setBounds(460, 250, 60, 30);
		othertxt.setBounds(560, 250, 90, 30);
		pf.setBounds(460, 350, 60, 30);
		pftxt.setBounds(560, 350, 90, 30);
		//b1.setBounds(440,450,90,30);
		//total2.setBounds(560, 450, 90, 30);
		b2.setBounds(250, 600, 150, 30);
		currentsalarytxt.setBounds(450, 600, 190,30);
		b3.setBounds(250, 700, 150, 30);
		netsalarytxt.setBounds(450, 700, 190,30);
		b4.setBounds(800, 600, 100, 50);
		
		f.add(employeecode);
		f.add(head);
		f.add(cb);
		f.add(employeename);
		f.add(employeenametxt);
		f.add(basicpay);
		f.add(basicpaytxt);
		f.add(hra);
		f.add(hratxt);
		f.add(lop);
		f.add(loptxt);
		f.add(pf);
		f.add(pftxt);
		f.add(total1);
		f.add(b1);
		f.add(other);
		f.add(othertxt);
		f.add(total2);
		f.add(b2);
		f.add(b3);
		f.add(b4);
		f.add(currentsalarytxt);
		f.add(netsalarytxt);
		f.add(cb1);
		f.add(month);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
		f.setLayout(null);
		f.setBounds(300, 10,1300, 1000);
		f.setVisible(true);
		
	}
//	public static void main(String[] args) {
//		new Payrollcalc();
//
//	}
	@Override
	public void itemStateChanged(ItemEvent i) {
		eid=Integer.parseInt((String)cb.getSelectedItem());
		if (i.getStateChange() == ItemEvent.SELECTED) {
	          Object item = i.getItem();
	  		if(cb.getSelectedIndex()==0){
	  			employeenametxt.setText("");
	  			basicpaytxt.setText("");
	  			hratxt.setText("");
	  			pftxt.setText("");
	  			othertxt.setText("");
	  			netsalarytxt.setText("");
	  			//loptxt.setText("");
				JOptionPane.showMessageDialog(null, "Select employee code");
				return;
			}
	  		
	  		if(i.getSource().equals(cb1)){
	  			String month=cb1.getSelectedItem().toString(); 	  		
	  		try{
	  			PreparedStatement pstm=db.con.prepareStatement("SELECT sum(daysreq) FROM leavetype WHERE month='"+month+"' AND eid="+eid);
				ResultSet Rs = pstm.executeQuery();
				if(Rs.next())
			    {
					leavedaysgetdb=Rs.getInt(1);
					System.out.println(leavedaysgetdb);
			    }
	  		}catch(SQLException e1){
	  			e1.printStackTrace();
	  		}
	  		}
			try{
				PreparedStatement pstm = db.con.prepareStatement("SELECT a.eid,a.ename,a.basicpay,a.hra,a.pf,a.otherpay,a.totalsalary FROM employeepaydetails a WHERE a.eid="+eid);
				ResultSet Rs = pstm.executeQuery();
				if(Rs.next())
			    {
					empnamedb=Rs.getString("a.ename");
					basicpaystr=Rs.getString("a.basicpay");
					hrastr=Rs.getString("a.hra");
					pfstr=Rs.getString("a.pf");
					otherpaystr=Rs.getString("a.otherpay");
					totalsalarystr=Rs.getString("a.totalsalary");
//					daysreqdb=Rs.getString("b.daysreq");
			    }
				employeenametxt.setText(empnamedb);
				basicpaytxt.setText(basicpaystr);
				hratxt.setText(hrastr);
				pftxt.setText(pfstr);
				othertxt.setText(otherpaystr);
				leavedayssrtdb=Integer.toString(leavedaysgetdb);
				loptxt.setText(leavedayssrtdb);
			}catch(SQLException e1){
				e1.printStackTrace();
			}
		}		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(b1)){
			if(loptxt.getText().isEmpty() || cb.getSelectedIndex()==0){
				JOptionPane.showMessageDialog(null, "Fill employee code and number of leave days");
				return;
			}else{
				int totsal=Integer.parseInt(totalsalarystr);
				String s=loptxt.getText().toString();
				int lopday=Integer.parseInt(s);
				int check=totsal/25;
				int tot=lopday*check;
				String settotal=Integer.toString(tot);
				total1.setText(settotal);
			}
		}
		
		if(e.getSource().equals(b2)){
			if(total1.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Fill total days of leave and press lop amount");
			return;
			}
		else{
			String s=total1.getText().toString();
			int c=Integer.parseInt(totalsalarystr);
			int t=Integer.parseInt(s);
			int cursal=c-t;
			String setcursal=Integer.toString(cursal);
			currentsalarytxt.setText(setcursal);
		}}
		
		if(e.getSource().equals(b3)){
			if(cb.getSelectedIndex()==0){
			JOptionPane.showMessageDialog(null, "Select employee code");
			return;
			}
		else{
			netsalarytxt.setText(totalsalarystr);
		}}
		
		if(e.getSource().equals(b4)){
		if(basicpaytxt.getText().isEmpty() || hratxt.getText().isEmpty() || loptxt.getText().isEmpty() ||  othertxt.getText().isEmpty() || pftxt.getText().isEmpty() || total1.getText().isEmpty() || currentsalarytxt.getText().isEmpty() || netsalarytxt.getText().isEmpty() || cb1.getSelectedIndex()==0){
			JOptionPane.showMessageDialog(null, "Fill all the datails");
			return;
		}else{
		basicpaydb=Integer.parseInt(basicpaytxt.getText());
		hradb=Integer.parseInt(hratxt.getText());
		lopdb=Integer.parseInt(loptxt.getText());
		otherdb=Integer.parseInt(othertxt.getText());
		pfdb=Integer.parseInt(pftxt.getText());
		total1db=Integer.parseInt(total1.getText());
		cscdb=Integer.parseInt(currentsalarytxt.getText());
		nsdb=Integer.parseInt(netsalarytxt.getText());
		
		
  		try{
  			PreparedStatement pstm=db.con.prepareStatement("SELECT month,eid FROM employeepayrolldb where eid="+eid);
			ResultSet Rs = pstm.executeQuery();
			if(Rs.next())
		    {
				monthgetdb=Rs.getString("month");
				eidgetdb=Rs.getInt("eid");

		    }
  		}catch(SQLException e1){
  			e1.printStackTrace();
  		}

		monthsetdb=cb1.getSelectedItem().toString();
		System.out.println(monthgetdb + eidgetdb + monthsetdb  + eid);
		if(monthsetdb.equals(monthsetdb) && eidgetdb==eid){
			JOptionPane.showMessageDialog(null,"This month is already stored");
		}else{
		
		try{
		String sql = "INSERT INTO employeepayrolldb (empname,basicpay,hra,otherpay,leavedays,pf,netsal,currentsal,eid,lopamt,month) " + 
                   "VALUES ('"+empnamedb+"','"+basicpaydb+"',"+hradb+",'"+otherdb+"','"+lopdb+"','"+pfdb+"','"+nsdb+"','"+cscdb+"','"+eid+"','"+total1db+"','"+monthsetdb+"')";
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
}