

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Servlet1() {
        super();
        // TODO Au to-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String muz = "Haghjkafag hga kah kah kahhh.";
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<!DOCTYPE html>" +
					"<html>"+
					"<head><title> Ana Sayfa  </title>" +
					"<script type=\"text/javascript\">" +
					"function yemekleriYazdirma() {"+
					"var e = document.getElementById(\"yiyeceklerkutusu\"); "+
					"var secilenYemek= e.options[e.selectedIndex].value;"+
					"document.getElementById(\"secilenyemeklertexti\").value += secilenYemek; "+
					"</script>" +
					"</head>" +
					"<body bgcolor = lightblue >" +
					"<form action =\"DenemeTahtasi\" name=\"formum\" method = \"GET\" >" +
					"<button type=\"button\" onclick=\"yemekleriYazdirma()\"> Ol Ulen </button></br> " +
					"Ben 1.textim : <input type='text' name='fname1' id = \"secilenyemeklertexti\" > </br>" +
					"Ben 2.textim : <input type='text' name='fname2' id = \"secilenyemeklertexti2\" >"+
					"<p> my Pc is here ! </p>" +
					"</form>" +
					"</body>" +
					"</html>" );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
