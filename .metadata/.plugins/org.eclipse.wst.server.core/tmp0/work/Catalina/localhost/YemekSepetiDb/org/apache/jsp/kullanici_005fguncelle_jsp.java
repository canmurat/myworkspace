/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.50
 * Generated at: 2014-01-31 13:05:48 UTC
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

public final class kullanici_005fguncelle_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/include/common.jsp", Long.valueOf(1391171880204L));
    _jspx_dependants.put("/include/./database.jsp", Long.valueOf(1390919848595L));
  }

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

	
	String url = "jdbc:sqlserver://localhost:1433;databaseName=YemekSepeti;user=mehmet;password=1234567";
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

      out.write("\r\n");
      out.write("\r\n");

	String yemekAd = "", yemekFiyat1 = "", urun1 = "", yemekId1 = "", guncel_yemek = "";
	String icecekAd = "", icecekFiyat1 = "", urun2 = "", icecekId1 = "", guncel_icecek = "";
	String salataAd = "", salataFiyat1 = "", urun3 = "", salataId1 = "", guncel_salata = "";
	String tatliAd = "", tatliFiyat1 = "", urun4 = "", tatliId1 = "", guncel_tatli = "";
	String yemegi_sil = "", icecegi_sil = "", salatayi_sil = "", tatliyi_sil = "", kullanici_sil = "", urunu_sil = "";
	String Ad = "", Soyad = "", DTarih = "", Adres = "", EPosta = "", guncel_kullanici = "";
	String KullaniciId1 = "", Tel1 = "", Sifre1 = "", Para1 = "", KrediKartNo1 = "", Admin1 = "", kullanici = "";

	String sepet_yemek_sil ="", sepet_icecek_sil="", sepet_salata_sil="", sepet_tatli_sil=""; 
	String timeStamp = "";
	int yemekId = 0, yemekFiyat = 0, icecekId = 0, icecekFiyat = 0, salataId = 0, salataFiyat = 0, tatliId = 0, tatliFiyat = 0,geneltoplam=0;
	int KullaniciId = 0, Para = 0, Admin = 0, SiparisId1 = 0;
	long Tel = 0, KrediKartNo = 0, Sifre = 0;

	String alinanyemekler = "", alinanicecekler = "", alinansalatalar = "", alinantatlilar = "";
	String yemekleradet = "", icecekleradet = "",salatalaradet="", tatlilaradet = "";
	int yemeklertoplam = 0, iceceklertoplam = 0, salatalartoplam = 0, tatlilartoplam = 0, toplamtutar = 0;
	
	String yemeklertoplam1="",iceceklertoplam1="",salatalartoplam1="",tatlilartoplam1="";
	
	String SiparisleriAl = "SiparisleriAl";

	String SiparisId = "", Yemekler = "", Icecekler = "", Salatalar = "", Tatlilar = "";

	int x=0;

	

      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");

	KullaniciId1 = request.getParameter("KullaniciId");
	if (KullaniciId1 != null)
		KullaniciId = Integer.parseInt(KullaniciId1);

	Ad = request.getParameter("Ad");
	Soyad =request.getParameter("Soyad");
	DTarih =request.getParameter("DTarih");
	Adres =request.getParameter("Adres");
	EPosta =request.getParameter("EPosta");
	
	Tel1 = request.getParameter("Tel");
	if (Tel1 != null || !"".equals(Tel1))
		Tel = Long.parseLong(Tel1);
	
	Sifre1 = request.getParameter("Sifre");
	if (Sifre1 != null || !"".equals(Sifre1))
		Sifre = Long.parseLong(Sifre1);
	
	Admin1 = request.getParameter("Admin");
	if (Admin1 != null || !"".equals(Admin1))
		Admin = Integer.parseInt(Admin1);
	
	KrediKartNo1 = request.getParameter("KrediKartNo");
	if (KrediKartNo1 != null || !"".equals(KrediKartNo1))
		KrediKartNo = Long.parseLong(KrediKartNo1);
	


	pstmt = con
			.prepareStatement("Update kullanicilar set Ad=?,Soyad=?,DTarih=?,Adres=?,EPosta=?,Tel=?,Sifre=?,Admin=?,KrediKartNo=? where Id="
					+ KullaniciId);

	pstmt.setString(1, Ad);
	pstmt.setString(2, Soyad);
	pstmt.setString(3, DTarih);
	pstmt.setString(4, Adres);
	pstmt.setString(5, EPosta);
	pstmt.setLong(6, Tel);
	pstmt.setLong(7, Sifre);
	pstmt.setInt(8, Admin);
	pstmt.setLong(9, KrediKartNo);

	
	
	pstmt.executeUpdate();

	con.close();
	session.setAttribute("guncel_kullanici", " ("
			+ Ad + ") kullanici basari ile guncellendi !");

      out.write("\r\n");
      out.write("<!-- sayfanÄ±n Ã§Ä±ktÄ± deÄerinin deÄil, iÃ§eriÄinin yorumlanmadan \r\n");
      out.write("jsp ye direkt dahil edilmesi iÃ§in:\r\n");
      out.write(" -->\r\n");
      if (true) {
        _jspx_page_context.forward("kullanici_duzenle.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("KullaniciId", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(KullaniciId), request.getCharacterEncoding()));
        return;
      }
      out.write('\r');
      out.write('\n');
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
