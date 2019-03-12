import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import java.sql.*;
public class EmployeeFinalReport extends JFrame {
	Userlogin u;
	int id=u.eid;
	
    DefaultTableModel model = new DefaultTableModel();
    Container cnt = this.getContentPane();
    JTable jtbl = new JTable(model);
    Object[] row = new Object[13];
    public EmployeeFinalReport() {
    	DB db =new DB();
        cnt.setLayout(new FlowLayout(FlowLayout.LEFT));
        model.addColumn("Employee id");
        model.addColumn("Employee Name");
        model.addColumn("Date Of Join");
        model.addColumn("Department");
        model.addColumn("Month");
        model.addColumn("Leave Days");
        model.addColumn("LOP Amount");
        model.addColumn("Basic Pay");
        model.addColumn("HRA");
        model.addColumn("PF");
        model.addColumn("Other Pay");
        model.addColumn("Current Salary");
        model.addColumn("Net Salary");
        
        try {
            PreparedStatement pstm = db.con.prepareStatement("SELECT a.eid,a.empname,a.doj,a.department,c.month,c.leavedays,c.lopamt,c.basicpay,c.hra,c.pf,c.otherpay,c.currentsal,c.netsal FROM registrationform a INNER JOIN employeepayrolldb c ON a.eid=c.eid WHERE a.eid="+id);
            ResultSet Rs = pstm.executeQuery();
            while(Rs.next()){
                model.addRow(new Object[]{Rs.getInt(1), Rs.getString(2),Rs.getString(3),Rs.getString(4),Rs.getString(5),Rs.getString(6),Rs.getString(7),Rs.getString(8),Rs.getString(9),Rs.getString(10),Rs.getString(11),Rs.getString(12),Rs.getString(13)});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        jtbl.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jtbl.setRowHeight(30);
        JScrollPane pg = new JScrollPane(jtbl);
        cnt.add(pg);
        pg.setPreferredSize(new Dimension(1100,400));
               
        this.pack();
}
    public static void main(String[] args) {
        JFrame frame = new EmployeeFinalReport();
        frame.setTitle("Swing Example");
        frame.setLayout(null);
        frame.setSize(1900, 1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}