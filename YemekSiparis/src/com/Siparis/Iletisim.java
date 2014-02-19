package com.Siparis;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Iletisim")
public class Iletisim extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Iletisim() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		out.println("<html>"+
					"<body>"+
				"<form>"+
		"<table width='500' border='0' bgcolor='#e2e2e2' font face='Thoma' size='2' align='center' cellpadding='20' cellspacing='20'>"+
		"<tr>"+
		"<th colspan='2'  scope='col'> Site Ýletiþim Formu </th>"+
		"</tr>"+
		"<tr>"+
		"<td>"+
		"<pre>"+
		"ADINIZ    : <input type='text' name='isim'><br>"+
		"SOYADINIZ : <input type='text' name='soyad'><br>"+
		"E-MAÝL    : <input type='text' name='mail'><br>"+
		"YORUM     : <textarea  cols='30' rows='10' name='yorum'>Bu alana yorum yapabilirsiniz</textarea><br>"+
		  
		"<input type='submit' value='GÖNDER'>    <input type='reset' value='TEMÝZLE'>"+
		"</pre>"+
		"</td>"+
		"</tr>"+
		"</table>"+
		"</form>"+
		"</body>"+
		"</html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
