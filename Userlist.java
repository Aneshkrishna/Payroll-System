import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import java.sql.*;
public class Userlist extends JFrame {
    DefaultTableModel model = new DefaultTableModel();
    Container cnt = this.getContentPane();
    JTable jtbl = new JTable(model);
    JButton update=new JButton("Update");
    JButton delete=new JButton("delete");
    
//    JTextField  id= new JTextField();
//    JTextField  usertype= new JTextField();
//    JTextField  empname= new JTextField();
//    JTextField  address= new JTextField();
//    JTextField  rnumber= new JTextField();
//    JTextField  dob= new JTextField();
//    JTextField  Gender= new JTextField();
//    JTextField doj= new JTextField();
//    JTextField department= new JTextField();
    
    String usertype1;
    String empname1;
    String address1;
    String rnumber1;
    String dob1;
    String Gender1;
    String doj1;
    String department1;
    int id1;
    Object[] row = new Object[9];
    public Userlist() {
    	DB db =new DB();
        cnt.setLayout(new FlowLayout(FlowLayout.LEFT));
        model.addColumn("Id");
        model.addColumn("usertype");
        model.addColumn("empname");
        model.addColumn("address");
        model.addColumn("rnumber");
        model.addColumn("dob");
        model.addColumn("Gender");
        model.addColumn("doj");
        model.addColumn("department");
        try {
/*            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_db", "root", "");*/
            PreparedStatement pstm = db.con.prepareStatement("SELECT * FROM registrationform WHERE status='A' ");
            ResultSet Rs = pstm.executeQuery();
            while(Rs.next()){
                model.addRow(new Object[]{Rs.getInt(1), Rs.getString(2),Rs.getString(3),Rs.getString(4),Rs.getString(5),Rs.getString(6),Rs.getString(7),Rs.getString(8),Rs.getString(9)});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        jtbl.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jtbl.setRowHeight(30);
               
        update.setBounds(800, 900, 100, 100);
        delete.setBounds(800, 1000, 100, 100);
                
        cnt.add(update);
        cnt.add(delete);
        JScrollPane pg = new JScrollPane(jtbl);
        cnt.add(pg);
       // cnt.setLayout(null);
        
        pg.setPreferredSize(new Dimension(1100,400));
               
        this.pack();
                       
        delete.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
	        // i = the index of the selected row
            int i = jtbl.getSelectedRow();
            int c=jtbl.getColumnCount();
            id1= (int) jtbl.getValueAt(i, 0);
            if(i >= 0){
                // remove a row from jtable
                model.removeRow(i);
                try{
                	
       				String sql = "UPDATE registrationform SET status='C' where eid="+id1;
       				System.out.println(id1);
       				db.stmt.execute(sql);
       				System.out.println("deleted");
       			} catch (SQLException e1) {
       				e1.printStackTrace();
       			}
            }
            else{
                System.out.println("Delete Error");
            }
		}
    });
        
        update.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
             
                // i = the index of the selected row
                int i = jtbl.getSelectedRow();
                int c=jtbl.getColumnCount();
                   id1= (int) jtbl.getValueAt(i, 0);
                   usertype1=(String) jtbl.getValueAt(i, 1);
                   empname1=(String) jtbl.getValueAt(i, 2);
                   address1=(String) jtbl.getValueAt(i, 3);
                   rnumber1=(String) jtbl.getValueAt(i, 4);
                   dob1=(String) jtbl.getValueAt(i, 5);
                   Gender1=(String) jtbl.getValueAt(i, 6);
                   doj1=(String) jtbl.getValueAt(i, 7);
                   department1=(String) jtbl.getValueAt(i, 8);
                   
                   try{
       				String sql = "UPDATE registrationform SET usertype='"+usertype1+"',empname='"+empname1+"',address='"+address1+"',rnumber='"+rnumber1+"',dob='"+dob1+"',Gender='"+Gender1+"',doj='"+doj1+"',department='"+department1+"' WHERE eid ="+id1;
       				//System.out.println(sql);
       				db.stmt.execute(sql);
       				System.out.println("updated");
       			} catch (SQLException e1) {
       				e1.printStackTrace();
       			}
            }
        });
}
}