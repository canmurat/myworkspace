package com.Siparis;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Uyeler")
public class Uyeler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String newuser;
	String newusertekrar;
	String passwd;
	String passwdtekrar;
	static String username = "";
	String textarea;
	String err = "alert(\"haydar\")";
	Boolean uyeOlabilir = true;
	String[] illegalKarakter;

	UyeClass uye = new UyeClass();

	public Uyeler() {
		super();

	}
	/*Yasadisi karakter'leri engellemek icin yordam*/
	public boolean yasadisiKaraktervarmi(String toExamine) {
		String[] arr = toExamine.split("[~#@*+%{}<>\\[\\]|\"\\_^]", 2);
		return arr.length > 1;
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		newusertekrar = request.getParameter("usernametekrar");
		newuser = request.getParameter("username");
		passwd = request.getParameter("password");
		passwdtekrar = request.getParameter("passwordtekrar");

		RequestDispatcher r = getServletContext().getRequestDispatcher(
				"/GirisServlet");

		/*
		 * Uye Ol servlet'inden alýnan bilgiler'in uyumlulugu ve dogruluklarý
		 * test edilir.
		 */

		if (newusertekrar == null || newuser == null || passwd == null
				|| passwdtekrar == null) {
			out.println("Hic bir alan bos býrakýlmamalýdýr !");
			uyeOlabilir = false;
		}
		/*girilen kullanici isimlerinin eslesip eslesmeme sorgusu*/
		if (!newusertekrar.trim().equals(newuser.trim())) {

			out.println("<h3 id='h3'>Kullanici isimleri eslesmiyor.<h3>");
			uyeOlabilir = false;
		}
		/* Girilen kullanici isminde bir kullanici mevcut ise uyarýlýr. */
		if (uye.IsýmBul(newuser)) {

			out.println("<h3>Girilen Kullanici adi kullanilmaktadir. !</h3>");
			uyeOlabilir = false;
		}
		/* Karakter sorgulamasý */
		if (yasadisiKaraktervarmi(username)) {
			out.println("Kullanici ismi icin yanlis karakterler girdiniz ! \n"
					+ "[~#@*+%{}<>\\[\\]|\"\\_^] karakterlerinden herhangi birisi kullanýlamaz !");
			uyeOlabilir = false;
		}
		/* Sifrelerin eslesip eslememe sorgusu */
		if (!passwdtekrar.equals(passwd)) {

			out.println("<h3 id='h3'>Sifreler eslesmiyor !</h3>");
			uyeOlabilir = false;

		}

		/* Sorun yok ise , girilen isimler kullanicilar.txt'e yazilir. */
		if (uyeOlabilir == true) {
			uye.UyeYaz(newuser, passwd);
			out.println("<h3 id='h3'>Uye oldun hadi hayirli olsun !</h3>");

		}

		r.include(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
