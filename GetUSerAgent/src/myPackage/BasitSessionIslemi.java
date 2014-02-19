package myPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/BasitSessionIslemi")
public class BasitSessionIslemi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BasitSessionIslemi() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String ornek1 = "Gattamigala ola cekceketerek";
		HttpSession session = request.getSession();
		session.setAttribute("ornek1",ornek1);
		String yenideger = (String) session.getAttribute("ornek1");
		
		
		out.println("yenideger = "+yenideger);
		
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
