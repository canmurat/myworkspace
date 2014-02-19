package ServletPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletProjeClass2
 */
@WebServlet("/ServletProjeClass2")
public class ServletProjeClass2 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    Boolean sifrelerEslesiyormu = false;  
	
    public ServletProjeClass2() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println(
				"<HTML>\n" +
				"<HEAD><TITLE>"+"UYELIK FORMU" + "</TITLE></HEAD>\n" +
				"<BODY BGCOLOR=\"#FDF5E6\">\n" +
				"<H1 ALIGN=\"CENTER\">" + "UYELIK FORMU" + "</H1>\n" +
				"<UL>\n" +
				" <LI><B>Kullanici Adi : </B>: "
				+ request.getParameter("kullaniciadi") + "\n" +
				" <LI><B>Sifre :</B>: "
				+ request.getParameter("sifre") + "\n" +
				" <LI><B>Sifre Tekrar :</B>: "
				+ request.getParameter("sifretekrar") + "\n" +
				" <LI><B>E-Posta : </B>: "
				+ request.getParameter("eposta") + "\n" +
				" <LI><B>Telefon No :</B>: "
				+ request.getParameter("telnumber") + "\n" +
				"</UL>\n" +
				"</BODY></HTML>");
		
		
		String birincisifre = (String)request.getParameter("sifre");
		String ikincisifre = (String)request.getParameter("sifretekrar");
		ArrayList<String> Hatalar = new ArrayList<String>();
		Boolean bool = true;
		
		if(!EmailDogrula.emailMi(request.getParameter("eposta")))
		{
			 Hatalar.add("E-mail ifadesi yanlis !\n");
			 bool = false;
			 
			  
		}
		
		if (!SifreDogrula.sifrelerEslesiyormu(birincisifre,ikincisifre))
		{
			 Hatalar.add("Sifreler Eslesmiyor !");
			bool = false;
		}
		else if (SifreDogrula.sifrelerEslesiyormu(birincisifre,ikincisifre))
		{
			sifrelerEslesiyormu = true;

		}
		if(sifrelerEslesiyormu)
		{
			
			if(!SifreDogrula.sifreFormatiDogrumu(request.getParameter("sifre")))
			{
				Hatalar.add("Yazdiginiiz sifreler eslesiyor fakat Dogru formatta degil"+ "\n");
				Hatalar.add("-   Ifade de 0-9 arasi bir sayi gerekmektedir."+ "\n");
				Hatalar.add("-   Ifade kucuk harfli karakter icermelidir." +"\n");
				Hatalar.add("-   Ifade buyuk harfli karakter icermelidir" +"\n");
				Hatalar.add("-   Ifade gosterilenlerin icinden bir sembol icermelidir \"@#$%\""+"\n");
				Hatalar.add("-   Ifade 6 ile 20 karakter arasinda olmalidir.\n");
				
			}
			
		}
		if( bool == false)
		{
			for (int i = 0;i < Hatalar.size(); i++) {
				
				out.println(Hatalar.get(i));
				
			}
			Hatalar.clear();
			response.setHeader("Refresh", "5; URL=http://localhost:8080/ServletProject2/test1.html");
		
			
		}
			
			
	}
	
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
