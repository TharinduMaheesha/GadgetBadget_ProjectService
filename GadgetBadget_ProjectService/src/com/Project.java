package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Project {
	
	private Connection connect()
	 {
		Connection con = null;
		
		try
		{
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/gadget_badget", "root", "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return con;
		
	 }
//========================================================================================================
	
	public String insertFinishedProject(String reasearcherID, String topic, String status, String price)
	{
		String output = "";
		
		try
		{
				Connection con = connect();
				
				if (con == null)
				{
					return "Error while connecting to the database for inserting.";
				}
				
			// create a prepared statements
			String query = " insert into project values (NULL,?,?,?,NULL)";
			String query2 = " insert into finished values (LAST_INSERT_ID(),?,NULL,?)";

			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 PreparedStatement preparedStmt2 = con.prepareStatement(query2);

			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(reasearcherID));
			 preparedStmt.setString(2, topic);
			 preparedStmt.setString(3, status);
			 
			 preparedStmt2.setInt(1, Integer.parseInt(reasearcherID));
			 preparedStmt2.setFloat(2, Float.parseFloat(price));

			// execute the statements
			 preparedStmt.execute();
			 preparedStmt2.execute();

			 con.close();
			 
			 String newItems = readFinishedProjects("1002"); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newItems + "\"}";			 }
			 catch (Exception e)
			 {
				 output = "Error while inserting the Project.";
				 System.err.println(e.getMessage());
			 }
			 return output;
	 }
//=============================================================================================================
	
	public String insertUnfinishedProject(String researcherID, String topic, String status, String amount)
	{
		String output = "";
		
		try
		{
				Connection con = connect();
				
				if (con == null)
				{
					return "Error while connecting to the database for inserting.";
				}
				
			// create a prepared statements
			String query = " insert into project values (NULL,?,?,?,NULL)";
			String query2 = " insert into unfinished values (LAST_INSERT_ID(),?,?)";

			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 PreparedStatement preparedStmt2 = con.prepareStatement(query2);

			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(researcherID));
			 preparedStmt.setString(2, topic);
			 preparedStmt.setString(3, status);
			 
			 preparedStmt2.setInt(1, Integer.parseInt(researcherID));
			 preparedStmt2.setFloat(2, Float.parseFloat(amount));
			 
			// execute the statements
			 preparedStmt.execute();
			 preparedStmt2.execute();

			 con.close();
			 
			 String newItems = readUnfinishedProjects("1002"); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newItems + "\"}";				 }
			 catch (Exception e)
			 {
				 output = "Error while inserting the Project.";
				 System.err.println(e.getMessage());
			 }
			 return output;
	 }
//===================================================================================================	
	
	public String deleteProject(String projectID)
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
			String query = "delete from project where pid = ?";

			 PreparedStatement preparedStmt = con.prepareStatement(query);

			 // binding value
			 preparedStmt.setInt(1, Integer.parseInt(projectID));
			
			// execute the statement
			 preparedStmt.execute();

			 con.close();
			 
			 String newItems = readFinishedProjects("1002"); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newItems + "\"}"; 			 }
			 catch (Exception e)
			 {
				 output = "Error while deleting the Project.";
				 System.err.println(e.getMessage());
			 }
			 return output;
	 }
//================================================================================================================================
	
	public String updateFinishedProject(String projectID , String topic, String amount , String researcherID)
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
			String query = "update project p,finished f set p.topic = ? ,f.price=? "
					+ "where  p.pid = ? and p.researcherID = ? and  f.pid = ? and f.researcherID = ?";

			 PreparedStatement preparedStmt = con.prepareStatement(query);

			 // binding values
			 preparedStmt.setString(1, topic);
			 preparedStmt.setInt(3, Integer.parseInt(projectID));
			 preparedStmt.setInt(4, Integer.parseInt(researcherID));
			 preparedStmt.setString(2, amount);

			 preparedStmt.setInt(5, Integer.parseInt(projectID));
			 preparedStmt.setInt(6, Integer.parseInt(researcherID));

			// execute the statement
			 preparedStmt.execute();

			 con.close();
			 
			 String newItems = readFinishedProjects("1002"); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newItems + "\"}"; 			 }
			 catch (Exception e)
			 {
				 output = "Error while updating the Project.";
				 System.err.println(e.getMessage());
			 }
			 return output;
	 }
//=======================================================================================================================================
	
	public String updateUnfinishedProject(String projectID , String topic, String amount , String researcherID)
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
			String query = "update project p,unfinished f set p.topic = ? ,f.requiredAmount=? "
					+ "where  p.pid = ? and p.researcherID = ? and  f.pid = ? and f.researcherID = ?";

			 PreparedStatement preparedStmt = con.prepareStatement(query);

			 // binding values
			 preparedStmt.setString(1, topic);
			 preparedStmt.setInt(3, Integer.parseInt(projectID));
			 preparedStmt.setInt(4, Integer.parseInt(researcherID));
			 preparedStmt.setString(2, amount);

			 preparedStmt.setInt(5, Integer.parseInt(projectID));
			 preparedStmt.setInt(6, Integer.parseInt(researcherID));

			// execute the statement
			 preparedStmt.execute();

			 con.close();
			 
			 output = "Project updated successfully";
			 }
			 catch (Exception e)
			 {
				 output = "Error while updating the Project.";
				 System.err.println(e.getMessage());
			 }
			 return output;
	 }
//============================================================================================================================================
	
	public String readFinishedProjects(String researcherID)
	{
		String  output = "<table border='1'><tr><th>ProjectID</th><th>Project Name</th>" +
				 "<th>Project Price</th>" +
				 "<th> Status</th>" +
				 "<th> Client</th>" +
				 "<th> Payment Status</th>" +
				 "<th>Update</th><th>Remove</th></tr>";
		
		try
		{
				Connection con = connect();
				
				if (con == null)
				{
					return "Error while connecting to the database for reading.";
				}
				
			// create a prepared statement
				String query = "Select * from finished f , project p  where p.pid = f.pid  and f.researcherid =?";
				String query2 = "Select * from user  where userid=?";

		 
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 PreparedStatement preparedStmt2;
				 preparedStmt.setString(1, researcherID);

			 //Creating resultset to read values from the database
			 ResultSet rs = preparedStmt.executeQuery();
			 ResultSet rs2 ;
			 while(rs.next()) {
				 String projectid = rs.getString("pid");
				 String name = rs.getString("topic");
				 String clientID = rs.getString("clientid");

				 String status = rs.getString("status");
				 String price = rs.getString("price");
				 String client = "";

				 output += "<tr><td>" + projectid + "</td>";
				 output += "<td>" + name + "</td>";
				 output += "<td>" + price + "</td>";
				 
				 preparedStmt2 = con.prepareStatement(query2);
				 preparedStmt2.setString(1, clientID);
				 rs2 = preparedStmt2.executeQuery();
				 while(rs2.next()) {
					  client = rs2.getString("firstName")+" " + rs2.getString("lastName");

					 
				 }
				 
				 if(client == "") {
					 output += "<td> Not Sold</td>";
				 	 output += "<td> None </td>";
				 }

				 else {
					 output += "<td> Sold </td>";
				 	 output += "<td>"+client+"</td>";
				 }
				 
				 output += "<td>" + status + "</td>";
				 
				 output += "<td><input name='btnUpdate' type='button' value='Update' "
							+ "class='btnUpdate btn btn-secondary' data-projectid='" + projectid + "'></td>"
							+ "<td><input name='btnRemove' type='button' value='Remove' "
							+ "class='btnRemove btn btn-danger' data-projectid='" + projectid + "'></td></tr>"; 
			 
			 }
			 con.close();
			 output += "</table>";

			 			 }
			 catch (Exception e)
			 {
				 output = "Error while reading the Project.";
				 System.err.println(e.getMessage());
			 }
			 return output;
	 }
	
	public String readUnfinishedProjects(String researcherID)
	{
		String  output = "<table border='1'><tr><th>ProjectID</th><th>Project Name</th>" +
				 "<th>Funds Required</th>" +
				 "<th> Status</th>" +
				 "<th> Client</th>" +
				 "<th> Fund Status</th>" +
				 "<th>Update</th><th>Remove</th></tr>";
		
		try
		{
				Connection con = connect();
				
				if (con == null)
				{
					return "Error while connecting to the database for reading.";
				}
				
			// create a prepared statement
			String query = "Select * from unfinished f , project p  where p.pid = f.pid and f.researcherid =?";
	 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 preparedStmt.setString(1, researcherID);
			 
			 //Creating resultset to read values from the database
			 ResultSet rs = preparedStmt.executeQuery();
			 ResultSet rs2;

			 String query2 = "Select * from user  where userid=?";

			 PreparedStatement preparedStmt2;
			 preparedStmt.setString(1, researcherID);
			 
			 while(rs.next()) {
				 String projectid = rs.getString("pid");
				 String name = rs.getString("topic");
				 String clientID = rs.getString("clientID");
				 String status = rs.getString("status");
				 String price = rs.getString("requiredAmount");
				 String client = "";
				 output += "<tr><td>" + projectid + "</td>";
				 output += "<td>" + name + "</td>";
				 output += "<td>" + price + "</td>";
				 
				 preparedStmt2 = con.prepareStatement(query2);
				 preparedStmt2.setString(1, clientID);
				 rs2 = preparedStmt2.executeQuery();
				 while(rs2.next()) {
					  client = rs2.getString("firstName")+" " + rs2.getString("lastName");

					 
				 }
				 
				 if(client == "") {
					 output += "<td> Not Funded</td>";
				 	 output += "<td> None </td>";
				 }

				 else {
					 output += "<td> Funded </td>";
				 	 output += "<td>"+client+"</td>";
				 }
				 
				 if(status.equalsIgnoreCase("Paid"))
				 	output += "<td>Received</td>";
				 else 
					output += "<td>Not Received</td>";

				 
				 output += "<td><input name='btnUpdate' type='button' value='Update' "
							+ "class='btnUpdate btn btn-secondary' data-itemid='" + projectid + "'></td>"
							+ "<td><input name='btnRemove' type='button' value='Remove' "
							+ "class='btnRemove btn btn-danger' data-projectid='" + projectid + "'></td></tr>"; 
			 
			 
			 }
			 con.close();
			 output += "</table>";

			 			 }
			 catch (Exception e)
			 {
				 output = "Error while reading the Project.";
				 System.err.println(e.getMessage());
			 }
			 return output;
	 }
//===============================================================================================================================================
	
	public String readUnfinishedProjects()
	{
		String  output = "<table border='1'><tr><th>ProjectID</th>"
				+ "<th>Project Name</th>" +
				 "<th>Funds Required</th>" +
				 "<th>Researcher</th>" ;
		
		try
		{
				Connection con = connect();
				
				if (con == null)
				{
					return "Error while connecting to the database for reading.";
				}
				
			// create a prepared statement
			String query = "Select * from unfinished f , project p  where p.pid = f.pid and p.clientid is null";
			String query2 = "select * from user where userid = ?";
	 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 //Creating resultset to read values from the database
			 ResultSet rs = preparedStmt.executeQuery();
			 ResultSet rs2 ;
			 
			 while(rs.next()) {
				 String projectid = rs.getString("pid");
				 String name = rs.getString("topic");
				 String id = rs.getString("researcherid");
				 String researcher = "";
				 String status = rs.getString("status");
				 String price = rs.getString("requiredAmount");
				 
				 PreparedStatement preparedStmt2 = con.prepareStatement(query2);
				 preparedStmt2.setString(1, id);
				 rs2 = preparedStmt2.executeQuery();
				 
				 while(rs2.next()) {
					  researcher = rs2.getString("firstName") + " " + rs2.getString("lastName");

					 
				 }

				 
				 output += "<tr><td>" + projectid + "</td>";
				 output += "<td>" + name + "</td>";
				 output += "<td>" + price + "</td>";
				 output += "<td>" + researcher + "</td>";
				 output += "<td><input name='btnCart' type='button' value='View Details' class='btn btn-secondary'></td>"
						 + "<td><form method='post' action='Research_service.java'>"
						 + "<input name='btnBuy' type='button' value='Fund now' class='btn btn-danger'>"
						 + "<input name='itemID' type='hidden' value='" + projectid
						 + "'>" + "</form></td></tr>";
			 
			 }
			 con.close();
			 output += "</table>";

			 			 }
			 catch (Exception e)
			 {
				 output = "Error while reading the Project.";
				 System.err.println(e.getMessage());
			 }
			 return output;
	 }
//====================================================================================================================================================
	
	public String readfinishedProjects()
	{
		String  output = "<table border='1'><tr><th>ProjectID</th>"
				+ "<th>Project Name</th>" +
				 "<th>Price</th>" +
				 "<th>Researcher</th>" ;
		
		try
		{
				Connection con = connect();
				
				if (con == null)
				{
					return "Error while connecting to the database for reading.";
				}
				
			// create a prepared statement
				String query = "Select * from finished f , project p  where p.pid = f.pid and p.clientid is null";
				String query2 = "select * from user where userid = ?";
		 
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 
				 //Creating resultset to read values from the database
				 ResultSet rs = preparedStmt.executeQuery();
				 ResultSet rs2 ;
			 while(rs.next()) {
				 String projectid = rs.getString("pid");
				 String name = rs.getString("topic");
				 String id = rs.getString("researcherid");
				 String researcher = "";				 String status = rs.getString("status");
				 String price = rs.getString("price");
				 
				 PreparedStatement preparedStmt2 = con.prepareStatement(query2);
				 preparedStmt2.setString(1, id);
				 rs2 = preparedStmt2.executeQuery();
				 
				 while(rs2.next()) {
					  researcher = rs2.getString("firstName") + " " + rs2.getString("lastName");

					 
				 }
				 
				 output += "<tr><td>" + projectid + "</td>";
				 output += "<td>" + name + "</td>";
				 output += "<td>" + price + "</td>";
				 output += "<td>" + researcher + "</td>";

				 
				

				 
				 output += "<td><input name='btnCart' type='button' value='View Details' class='btn btn-secondary'></td>"
						 + "<td><form method='post' action='Research_service.java'>"
						 + "<input name='btnBuy' type='button' value='Buy now' class='btn btn-danger'>"
						 + "<input name='btnBuy' type='button' value='Add to cart ' class='btn btn-danger'>"

						 + "<input name='itemID' type='hidden' value='" + projectid
						 + "'>" + "</form></td></tr>";
			 
			 }
			 con.close();
			 output += "</table>";

			 			 }
			 catch (Exception e)
			 {
				 output = "Error while reading the Project.";
				 System.err.println(e.getMessage());
			 }
			 return output;
	 }
}
