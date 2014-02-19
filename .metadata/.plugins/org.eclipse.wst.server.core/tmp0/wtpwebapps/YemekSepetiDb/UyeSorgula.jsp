<%@page import="java.util.*"%>
<%@page import="java.sql.*;"%>
<%
	String uyeBilgi = "";
	String username = request.getParameter("username");
	long password = Long.valueOf(request.getParameter("password"));
	String UyeSorgula = "UyeSorgula";
	boolean uyeVarmi = false;
	boolean adminMi = false;
	boolean admin = true;
	String uyeAd;
	int KullaniciId=0;
	long sifre;
	int adminBilgi;
	long KrediKartNo =0;
	String url = "jdbc:sqlserver://localhost:1433;databaseName=YemekSepeti;user=mehmet;password=1234567";
	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	Connection conn = DriverManager.getConnection(url);

	try {
		conn = DriverManager.getConnection(url);
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {

		PreparedStatement ps = conn
				.prepareStatement("select * from kullanicilar");

		ResultSet rs = ps.executeQuery();
		int i = 0;
		while (rs.next()) {

			KullaniciId = rs.getInt("Id");
			uyeAd = rs.getString("Ad");
			sifre = rs.getLong("Sifre");
			adminBilgi = rs.getInt("Admin");
			KrediKartNo = rs.getLong("KrediKartNo");
			System.out.println(adminBilgi + " adminBilgi");

			if (uyeAd.trim().equals(username.trim())
					&& password == sifre) {
				uyeVarmi = true;
				if(uyeVarmi)
				{
					if(adminBilgi == 1)
						adminMi = true;
					else
						break;
					
				}

			}
			
			i++;
			
		}
		if (!uyeVarmi) {

			uyeBilgi = "Kayit Bulunamadi";
			session.setAttribute("uyeBilgi", uyeBilgi);
			response.sendRedirect("GirisSayfa.jsp");

		} else if (uyeVarmi && !adminMi) {
			uyeBilgi = username;
			session.setAttribute("user", !admin);
			session.setAttribute("username", username);
			response.sendRedirect("SiparisSayfa.jsp");
		} else if (uyeVarmi && adminMi) {

			uyeBilgi = username;
			session.setAttribute("user", admin);
			session.setAttribute("username", username);
			response.sendRedirect("SiparisSayfa.jsp");
		}
		
		session.setAttribute("durum", UyeSorgula);
		session.setAttribute("KullaniciId", KullaniciId);
		session.setAttribute("KrediKartNo",KrediKartNo);
		rs.close();
		ps.close();

	} catch (Exception e) {
		e.printStackTrace();
	}
%>