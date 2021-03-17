package application;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;

public class MainProgram {

	public static void main(String[] args) {
		
		//Initiating null variables with the JDBC Class-Types needed for selection
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
	
		try {
			
		con = DB.getConnection();
		
		//OBS1: No case sensitivity at the command string
		
		pst = con.prepareStatement("select * from department");
		pst.executeQuery();
		
		rs = pst.getResultSet();
		
		while(rs.next()) {
			
		System.out.print(rs.getInt(1) + ", ");
		System.out.println(rs.getString(2));
	
			
		}
		}
		catch (SQLException e) {
			e.getMessage();
			e.printStackTrace();
		}
		
		/*Since Connection, PreparedStatement and ResultSet are
		  external resources (not controlled by Java JVM), it is 
		  important to close manually each of them in a finally clause*/
		
		finally {
			
			//Calling the specific closing methods in order to avoid multiple try catches
			DB.closeResultSet(rs);
			
			//Upcast in closeStatement as PreparedStatement pst is passed as a Statement parameter
			DB.closeStatement(pst);
			
			DB.closeConnection();
		}
		
	}

}
