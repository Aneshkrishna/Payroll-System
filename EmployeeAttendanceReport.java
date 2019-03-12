import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import java.sql.*;
public class EmployeeAttendanceReport extends JFrame {
    DefaultTableModel model = new DefaultTableModel();
    Container cnt = this.getContentPane();
    JTable jtbl = new JTable(model);
    Object[] row = new Object[7];
    public EmployeeAttendanceReport() {
    	DB db =new DB();
        cnt.setLayout(new FlowLayout(FlowLayout.LEFT));
        model.addColumn("Employee id");
        model.addColumn("Employee Name");
        model.addColumn("Date");
        model.addColumn("In Time");
        model.addColumn("Out Time");
        model.addColumn("Department");
        try {
/*            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_db", "root", "");*/
            PreparedStatement pstm = db.con.prepareStatement("SELECT * FROM empatds ");
            ResultSet Rs = pstm.executeQuery();
            while(Rs.next()){
                model.addRow(new Object[]{Rs.getInt(1), Rs.getString(2),Rs.getString(3),Rs.getString(4),Rs.getString(5),Rs.getString(6),Rs.getString(7)});
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
        JFrame frame = new EmployeeAttendanceReport();
        frame.setTitle("Swing Example");
        frame.setLayout(null);
        frame.setSize(1900, 1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}