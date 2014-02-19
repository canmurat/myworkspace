<%@ page import="java.util.*" %>
<%@ page import="java.sql.*;"%>
<%
	
	String url = "jdbc:sqlserver://localhost:1433;databaseName=GameOfThrones;user=mehmet;password=1234567";
	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	Connection con = DriverManager.getConnection(url);

	Statement stmt = con.createStatement();
	Statement stmt1 = con.createStatement();
	Statement stmt2 = con.createStatement();
	Statement stmt3 = con.createStatement();
	Statement stmt4 = con.createStatement();
	Statement stmt5 = con.createStatement();

	ResultSet rs, rs1, rs2, rs3, rs4, rs5;

	PreparedStatement pstmt, pstmt1, pstmt2, pstmt3, pstmt4, pstmt5;
%>