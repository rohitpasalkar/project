import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.*;
class alldata extends JFrame implements ActionListener
{
	JButton btn1;
	JPanel jp1;
	Icon icon;
    Connection conn;
    int row,column;
    Object[] data = new Object[9];


    DefaultTableModel defaulttablemodel = new DefaultTableModel();
    JTable jtable = new JTable(defaulttablemodel);
    ResultSet rs;

    public alldata()
    {

    jp1 = new JPanel();


	icon = new ImageIcon("back.jpg");
    btn1 = new JButton("Back",icon);
    btn1.setBackground(Color.white);
    btn1.setForeground(Color.BLACK);
    btn1.addActionListener(this);
    jp1.add(btn1);
    add(jp1, BorderLayout.SOUTH);

    String[] darr1={"Name","Room_No","Room_Type","Address","Mobile No","Id No","Check In","Check Out","No of Peoples"};

        		for(int column = 0; column < 9; column++)
        		{
            		defaulttablemodel.addColumn(darr1[column]);
        		}

        	try

	        {

 	        String query;
 	        if(conn!=null)
 	        conn.close();
 	        {
			 try
			   {
				Class.forName("com.mysql.jdbc.Driver");
			   }
			 catch(ClassNotFoundException e1)
			    {
			 	System.out.println(" Kalatey na -->"+e1);
			    }
				conn =  DriverManager.getConnection("jdbc:mysql://localhost/rohitdb","root","rohit@rvp");

			 	    Statement stmt = conn.createStatement();
 			 	    rs = stmt.executeQuery("select * from hotel;");


			 	          	for(row = 0; rs.next(); row++)
          					{
         	 					for(column = 0; column < 9; column++)
         	 					{
            	    				{
            	    				   data[column] = rs.getString(column+1);
            	    				}

             					}
             					defaulttablemodel.addRow(data);
           					}
			 	          rs.close();
		 		}
	         }
	         catch(SQLException sqle)
	         {
			 	          	System.out.println("Error in query search query"+sqle);
	          }


        getContentPane().add(new JScrollPane(jtable));
        setSize(1100,800);
     	setVisible(true);

    }
    public void actionPerformed(ActionEvent obj)
    {
		if(obj.getSource()==btn1)
		{

			frame2 f = new frame2();
			dispose();
		}
	}
	public static void main(String args[])
    {
		alldata ad = new alldata();
	}
}
