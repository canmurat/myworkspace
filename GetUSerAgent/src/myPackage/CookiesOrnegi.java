package myPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CookiesOrnegi")
public class CookiesOrnegi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CookiesOrnegi() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Cookie userCookie = new Cookie("user", "uid1234");
		userCookie.setMaxAge(60 * 60 * 24 * 365); // Store cookie for 1 year
		response.addCookie(userCookie);

		String cookieName = "user";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookieName.equals(cookie.getName())) {
					out.println(cookie.getDomain());
				}
			}
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
