package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


import java.sql.*;

public class Units {

	//A common method to connect to the DB
		private Connection connect()
		 {
			Connection con = null;
		 try
		 {
			 Class.forName("com.mysql.jdbc.Driver");

			 //Provide the correct details: DBServer/DBName, username, password
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid-db", "root", "");
		 }
		 catch (Exception e)
		 {
			 e.printStackTrace();
		 }
		 return con;
		 } 
		
		
		

		public String insertUnits(String acc, String name, String address, String district, String units, String year, String month)
		 {
			String output = "";
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {
				 return "Error while connecting to the database for inserting !"; 
			 }
			 
			 	// create a prepared statement
			 	String query = " insert into unitrecords(`recordID`,`accountNo`,`consumerName`,`address`,`district`,`consumedUnits`, `year`, `month`)"
			 	+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
			 	
			 	PreparedStatement preparedStmt = con.prepareStatement(query);
			 	// binding values
			 	preparedStmt.setInt(1, 0);
			 	preparedStmt.setString(2, acc);
		 		preparedStmt.setString(3, name);
		 		preparedStmt.setString(4, address);
		 		preparedStmt.setString(5, district);
		 		preparedStmt.setString(6, units);
		 		preparedStmt.setString(7, year);
		 		preparedStmt.setString(8, month);
		 		
		 		// execute the statement
		 
		 		preparedStmt.execute();
		 		con.close();
//		 		output = "Unit Record Inserted successfully!!!";
		 		
		 		String newItems = readUnits();
		 		 output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		 }
		 catch (Exception e)
		 {
//			 	output = "Error while inserting the record !";
			 	output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
			 	System.err.println(e.getMessage());
		 }
		 
		 return output;
		 
		 }
		
		

		public String readUnits()
		 {
			String output = "";
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {
				 return "Error while connecting to the database for reading."; 
			 }
			 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>AccountNO</th><th>Consumer Name</th>" +"<th>Address</th>" +
		 "<th>District</th>" + "<th>Consumed Units</th>" +"<th>Year</th>" + "<th>Month</th>"+"<th>Update</th>"+"<th>Delete</th></tr>";

		 String query = "select * from unitrecords";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
			 String recordID = Integer.toString(rs.getInt("recordID"));
			 String accountNo = rs.getString("accountNo");
			 String consumerName = rs.getString("consumerName");
			 String address = rs.getString("address");
			 String district = rs.getString("district");
			 String consumedUnits =Integer.toString(rs.getInt("consumedUnits"));
			 String year = rs.getString("year");
			 String month = rs.getString("month");
		 
			 // Add into the html table
			 
			 output += "<tr><td><input id='hidRecIDUpdate' name='hidRecIDUpdate' type='hidden' value='" + recordID + "'>";
			 output += "<tr><td>" + accountNo + "</td>";
			 output += "<td>" + consumerName + "</td>";
			 output += "<td>" + address + "</td>";
			 output += "<td>" + district + "</td>";
			 output += "<td>" + consumedUnits + "</td>";
			 output += "<td>" + year + "</td>";
			 output += "<td>" + month + "</td>";
			 // buttons
			 
			 output += "<td><input name='btnUpdate' type='button' value='Update' class=' btnUpdate btn btn-secondary'></td> "
			 		+ "<td><form method='post' action='units.jsp'> <input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'> <input name='hidRecIDDelete' type='hidden'value='" + recordID + "'>" + "</form></td></tr>";
			 
		 }
		 	con.close();
		 	// Complete the html table
		 	output += "</table>";
		 }
		 		catch (Exception e)
		 {
		 		output = "Error while reading the records.";
		 		System.err.println(e.getMessage());
		 }
		 		return output;
		 }
		
		
		
		
		public String updateUnit(String ID, String acc, String name, String address, String district, String units, String year, String month)
		
		{
			 String output = "";
			 try
			 {
				 Connection con = connect();
				 if (con == null)
				 {
					 return "Error while connecting to the database for updating."; 
				 }
				 
			 // create a prepared statement
			 String query = "UPDATE unitrecords SET accountNo=?,consumerName=?,address=?,district=?,consumedUnits=?,year=?,month=? WHERE recordID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 	preparedStmt.setString(1, acc);
		 		preparedStmt.setString(2, name);
		 		preparedStmt.setString(3, address);
		 		preparedStmt.setString(4, district);
		 		preparedStmt.setString(5, units);
		 		preparedStmt.setString(6, year);
		 		preparedStmt.setString(7, month);
		 		preparedStmt.setInt(8, Integer.parseInt(ID));
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 String newItems = readUnits();
			 output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}"; 

			 
//			 output = "The Record Updated Successfully";
			 }
			 		catch (Exception e)
			 {
//			 		output = "Error while updating the Record !!!.";
			 		output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}"; 

			 		System.err.println(e.getMessage());
			 }
			 
			 return output;
		}
		
		
		
		
		public String deleteUnit(String recordID)
		 {
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for deleting."; 
				}
					// create a prepared statement
					String query = "delete from unitrecords where recordID=?";
					
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(recordID));
					
					// execute the statement
					preparedStmt.execute();
					con.close();
					
					String newItems = readUnits();
					 output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}"; 

					
//					output = "The Record Deleted Successfully";
				}
				catch (Exception e)
				{
//					output = "Error while deleting the record !";
					
					output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
					
					System.err.println(e.getMessage());
				}
			
		 return output;
		 
		 }
		


	public String searchConnection(String accountNo) {
		
		String output="";
		try{ 
			Connection con = connect(); 
			if (con == null)  {
				return "Error while connecting to the database";
				} 
				
				output = "<html>"+"<table border='1'><tr>"
						+ "<th>accountNo</th>"+
						"<th>consumerName</th>"+
						"<th>address</th>" +
						"<th>district</th>" +
						"<th>consumedUnits</th>" +
						"<th>year</th>" +
						"<th>month</th></tr>"; 

			// create a prepared statement
			String query = "select * from unitrecords where accountNo='"+accountNo+"'"; 
			Statement stmt = con.createStatement(); 
		 	ResultSet rs = stmt.executeQuery(query);
		 	while(rs.next()) {
		 		String consumerName = rs.getString("consumerName"); 
				String address = rs.getString("address");
				String district= rs.getString("district"); 
				String consumedUnits = rs.getString("consumedUnits");
				String year = rs.getString("year");
				String month = rs.getString("month");
				
				output += "<td>" + accountNo + "</td>"; 
					output += "<td>" + consumerName + "</td>"; 
					output += "<td>" + address + "</td>"; 
					output += "<td>" + district + "</td>"; 
					output += "<td>" + consumedUnits + "</td>"; 
					output += "<td>" + year + "</td>"; 
					output += "<td>" + month + "</td></tr>"; 
		 	}
			con.close(); 
	 
				output += "</table></html>"; 
		} 
		catch (Exception e) { 
			output = "Error while searching"; 
			System.err.println(e.getMessage()); 
		} 
		return output;
	}

	
}
