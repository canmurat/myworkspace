/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.50
 * Generated at: 2014-01-31 12:22:00 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.sql.*;;

public final class UyeSorgula_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");

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

    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
