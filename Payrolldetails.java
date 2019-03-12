import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
public class Payrolldetails implements ItemListener, ActionListener {
	static DB db =new DB();
	JFrame f;
	String empnamedb=null;
	int eid=0,basicpaydb=0,hradb=0,otherdb=0,pfdb=0,total1db=0;
	JLabel employeecode,employeename,basicpay,hra,totsalary,lop,pf,head;
	JTextField employeenametxt,basicpaytxt,hratxt,totsalarytxt,othertxt,pftxt;
	JComboBox cb;
	JButton b1;
	public Payrolldetails(){
		f=new JFrame();
		head=new JLabel("PAY ROLL CALCULATION");
		employeecode=new JLabel("Employee Code");
		employeename=new JLabel("Employee Name");
		basicpay=new JLabel("Basic Pay");
		hra=new JLabel("HRA");
		lop=new JLabel("other pays");
		pf=new JLabel("PF");
		totsalary=new JLabel("Total_salary");
		
		employeenametxt=new JTextField();
		basicpaytxt=new JTextField();
		hratxt=new JTextField();
		othertxt=new JTextField();
		pftxt=new JTextField();
		totsalarytxt=new JTextField();
		b1=new JButton("click");
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
		
		head.setBounds(450, 20, 160, 30);
		employeecode.setBounds(50,130,110,30);
		cb.setBounds(160, 130, 200, 30);
		employeename.setBounds(410, 130, 110, 30);
		employeenametxt.setBounds(540, 130, 200, 30);
		employeenametxt.setEditable(false);
		basicpay.setBounds(820, 130,110,30);
		basicpaytxt.setBounds(930, 130, 200, 30);
		hra.setBounds(160, 250, 60, 30);
		hratxt.setBounds(260, 250, 90, 30);
		lop.setBounds(460, 250, 60, 30);
		othertxt.setBounds(560, 250, 90, 30);
		pf.setBounds(160, 350, 60, 30);
		pftxt.setBounds(260, 350, 90, 30);
		totsalary.setBounds(460, 350, 90, 30);
		totsalarytxt.setBounds(570, 350, 90, 30);
		totsalarytxt.setEditable(false);
		
		b1.setBounds(370, 450, 90, 30);
		f.add(lop);
		f.add(othertxt);
		f.add(basicpay);
		f.add(basicpaytxt);
		f.add(cb);
		f.add(employeename);
		f.add(employeecode);
		f.add(employeenametxt);
		f.add(head);
		f.add(hra);
		f.add(hratxt);
		f.add(pf);
		f.add(pftxt);
		f.add(totsalary);
		f.add(totsalarytxt);
		f.add(b1);
		b1.addActionListener(this);
		f.setLayout(null);
		f.setBounds(300, 150,1200,700);
		f.setVisible(true);
	}
//	public static void main(String[] args) {
//		new Payrolldetails();
//	}
	@Override
	public void itemStateChanged(ItemEvent i) {
		if (i.getStateChange() == ItemEvent.SELECTED) {
	          Object item = i.getItem();
	  		if(cb.getSelectedIndex()==0){
	  			employeenametxt.setText("");
				JOptionPane.showMessageDialog(null, "Select employee code");
				return;
			}
			try{
				eid=Integer.parseInt((String)cb.getSelectedItem());
				PreparedStatement pstm = db.con.prepareStatement("SELECT eid,empname FROM registrationform WHERE eid="+eid);
				ResultSet Rs = pstm.executeQuery();
				if(Rs.next())
			    {
					empnamedb=Rs.getString("empname");
			    }
				employeenametxt.setText(empnamedb);
			}catch(SQLException e1){
				e1.printStackTrace();
			}
		}		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(basicpaytxt.getText().isEmpty() || hratxt.getText().isEmpty() || othertxt.getText().isEmpty() || pftxt.getText().isEmpty() ){
			JOptionPane.showMessageDialog(null, "Fill all the datails");
			return;
		}
		basicpaydb=Integer.parseInt((String)basicpaytxt.getText());
		hradb=Integer.parseInt((String)hratxt.getText());
		otherdb=Integer.parseInt((String)othertxt.getText());
		pfdb=Integer.parseInt((String)pftxt.getText());
		int sum=0;
		sum=basicpaydb+hradb+otherdb-pfdb;
		String sumstring=Integer.toString(sum);
		totsalarytxt.setText(sumstring);
		total1db=Integer.parseInt((String)totsalarytxt.getText());
		
		try{
		String sql = "INSERT INTO employeepaydetails (ename,basicpay,hra,pf,otherpay,totalsalary,eid) " + 
                   "VALUES ('"+empnamedb+"','"+basicpaydb+"',"+hradb+",'"+pfdb+"','"+otherdb+"','"+total1db+"','"+eid+"')";
		System.out.println(sql);
		db.stmt.executeUpdate(sql);
		JOptionPane.showMessageDialog(null,"Saved in DB");
	}catch (SQLException e1) {
		e1.printStackTrace();
	}
	}
}
