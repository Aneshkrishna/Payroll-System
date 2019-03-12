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

import javax.swing.*;
public class Shiftdetail implements ActionListener, ItemListener {
	JFrame f;
	JLabel employeeid;
	JLabel employeename;
	JLabel shifttype;
	JLabel shiftdate;
	JLabel extensiontime;
	//JTextField employeeidtxt;
	JTextField employeenametxt;
	JTextField shiftdatetxt;
	JTextField extensiontimetxt;
	JRadioButton shifttypeselect1;
	JRadioButton shifttypeselect2;
	ButtonGroup bg;
	JButton b1;
	DateFormat df;
	Date d1,d2;
	JComboBox cb;
	
	int eid = 0;
	String empname=null,eiddb=null;
	String shifttypesel = null;
	
	static DB db =new DB();
	public Shiftdetail(){
		df = new SimpleDateFormat("yyyy-MM-dd");
		f=new JFrame();
		
		cb=new JComboBox();
		cb.addItem("SELECT EMPLOYEE CODE");
		try{
			PreparedStatement pstm = db.con.prepareStatement("SELECT eid FROM registrationform WHERE usertype='Employee' and status='A'");
            ResultSet Rs = pstm.executeQuery();
            while(Rs.next()) {
                String pat = Rs.getString("eid");
                cb.addItem(pat);
                cb.addItemListener(this);
            }			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
		employeeid=new JLabel("EMPLOYYEE_CODE:");
		employeename=new JLabel("EMPLOYEE_NAME");
		shifttype=new JLabel("SHIFT_TYPE");
		shiftdate=new JLabel("SHIFT_DATE");
		extensiontime=new JLabel("EXTENSION_TIME");
		//employeeidtxt=new JTextField();
		employeenametxt=new JTextField();
		extensiontimetxt=new JTextField();
		shiftdatetxt=new JTextField();
		shifttypeselect1 =new JRadioButton("DAY");
		shifttypeselect2 =new JRadioButton("NIGHT");
		bg=new ButtonGroup();
		bg.add(shifttypeselect1); bg.add(shifttypeselect2);
		
		b1=new JButton("click");
		employeeid.setBounds(200, 100, 160, 30);
		employeename.setBounds(200, 200, 160, 30);
		shiftdate.setBounds(200, 300, 100, 30);
		shifttype.setBounds(200, 400, 160, 30);
		extensiontime.setBounds(200, 500, 100, 30);
		
		cb.setBounds(400, 100, 260, 30);
		employeenametxt.setBounds(400, 200, 260, 30);
		employeenametxt.setEditable(false);
		shiftdatetxt.setBounds(400, 300, 260, 30);
		shifttypeselect1.setBounds(400, 400, 60, 30);
		shifttypeselect2.setBounds(500, 400, 60, 30);
		extensiontimetxt.setBounds(400, 500, 260, 30);
		b1.setBounds(400, 600, 100,70);
		
		f.add(employeeid);
		f.add(cb);
		f.add(employeename);
		f.add(employeenametxt);
		f.add(extensiontime);
		f.add(extensiontimetxt);
		f.add(shiftdate);
		f.add(shiftdatetxt);
		f.add(shifttype);
		f.add(shifttypeselect1);
		f.add(shifttypeselect2);
		f.add(b1);
		
		b1.addActionListener(this);
		f.setLayout(null);
		f.setVisible(true);
		f.setSize(800, 800);
		f.setBounds(400, 150, 1000, 750);
	}
//	public static void main(String[] args) {
//		new Shiftdetail();
//	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if(shifttypeselect1.isSelected()){shifttypesel=shifttypeselect1.getText();}
		if(shifttypeselect2.isSelected()){shifttypesel=shifttypeselect2.getText();}
		String extensiontime=extensiontimetxt.getText();
		String shiftdatevalid=shiftdatetxt.getText();
		
			employeenametxt.setText(empname);
			
			try {
				if(shiftdatevalid.isEmpty()){
					JOptionPane.showMessageDialog(null, "Enter To date");
					return;
				}else{
				d1 = df.parse(shiftdatevalid);
				}
			} catch (ParseException e2) {
				JOptionPane.showMessageDialog(null,"Enter date of birth in yyyy-MM-dd");
				e2.printStackTrace();
				return;
			}
			String ename=employeenametxt.getText();
			try{
				String sql = "INSERT INTO shiftdetails (ename,shiftdate,shifttype,extensiontime,eid) " + 
		                  "VALUES ('"+ename+"','"+shiftdatevalid+"','"+shifttypesel+"','"+extensiontime+"','"+eid+"')";
			System.out.println(sql);
			db.stmt.executeUpdate(sql);
			f.setEnabled(false);
			JOptionPane.showMessageDialog(null, "save in DB");
			}catch(SQLException e1){
				e1.printStackTrace();
			}
	}
	public void itemStateChanged(ItemEvent i) {
		if (i.getStateChange() == ItemEvent.SELECTED) {
	          Object item = i.getItem();
	  		if(cb.getSelectedIndex()==0){
				JOptionPane.showMessageDialog(null, "Select employee code");
				return;
			}
			try{
				eid=Integer.parseInt((String)cb.getSelectedItem());
				PreparedStatement pstm = db.con.prepareStatement("SELECT eid,empname FROM registrationform WHERE eid="+eid);
				ResultSet Rs = pstm.executeQuery();
				if(Rs.next())
			    {
					eiddb=Rs.getString("eid");
					empname=Rs.getString("empname");
			    }
				employeenametxt.setText(empname);
			}catch(SQLException e1){
				e1.printStackTrace();
			}
		}
	}
}
