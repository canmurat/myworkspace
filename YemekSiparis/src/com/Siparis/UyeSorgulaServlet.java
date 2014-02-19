package com.Siparis;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UyeSorgulaServlet")
public class UyeSorgulaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UyeClass uye = new UyeClass();

	String user;
	String pass;
	String textarea;
	String UyeSorgulaServlet = "UyeSorgulaServlet";
	boolean admin = true;

	public UyeSorgulaServlet() {
		super();

	}
	/*GÝris servlet'te yasadisi karakterler girilmememesi icin yordam*/

	public boolean yasadisiKaraktervarmi(String toExamine) {
		String[] arr = toExamine.split("[~#@*+%{}<>\\[\\]|\"\\_^]", 2);
		return arr.length > 1;
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		user = request.getParameter("username");
		pass = request.getParameter("password");

		/*Girilen uye isim ve sifresi , kullanicilar txt dosyasýnda aranýr.
		 * Boyle bir uye var ise , Siparis Servlet'ine iletim saglanýr.*/
		
		if (uye.UyeAra(user, pass)) {
			RequestDispatcher r = getServletContext().getRequestDispatcher(
					"/SiparisServlet");
			request.setAttribute("durum", UyeSorgulaServlet);
			if (uye.adminMi(user, pass)) {
				request.setAttribute("user", admin);
				request.setAttribute("username", user);
			} else {
				request.setAttribute("user", user);
			}

			r.forward(request, response);
		} 
		/*Girilen kullanici ismi , sifre bilgilerinde kayitli bir uyenin bulunmamasý durumnda Giris Servlet'e (Hatalar ile birlikte)
		 * Geri Donulur..*/
		else {

			RequestDispatcher r = getServletContext().getRequestDispatcher(
					"/GirisServlet");
			if (yasadisiKaraktervarmi(user)) {
				out.println("<h3> Kullanici ismi icin '~#@*+%{}<>\\[\\]|\"\\_^' yasadisi karakterlerinden birini girdin ! \n "
						+ "hatta araniyor olabilirsin !</h3>");
			} else {
				out.println("<h3> Boyle bir kullanici bulunmamaktadir. </h3>"
						+ "<h3> Kullanici Adi ya da Sifrenizi dogru yazdiginizdan emin olun ! ");

			}
			r.include(request, response);

		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}
}
