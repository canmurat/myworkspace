package com.Siparis;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SiparisleriAl")
public class SiparisleriAl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String alinanyemekler ="";
	public String alinanicecekler = "";
	public String alinantatlilar ="";
	public String yemekleradet ="";
	public String icecekleradet;
	public String tatlilaradet;
	public String SiparisleriAl = "SiparisleriAl";
	public String toplamtutar;
	public String kullanici;
	
	SiparisleriYazdir sp = new SiparisleriYazdir();

    public SiparisleriAl() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		alinanyemekler = request.getParameter("secilenyemekler");
		alinanicecekler = request.getParameter("secilenicecekler");
		alinantatlilar = request.getParameter("secilentatlilar");
		toplamtutar = request.getParameter("geneltoplam");
		
	/*Siparisler , form'dan alýnan bilgilere gore SiparisleriYazdir sýnýfýnýn SiparisleriYaz metodunda gerekli yazdirma islemleri yapýlýyor.
	 * Siparis Id degeri txt dosyasýndan okunup +1 fazlasý yazýlýyor.*/	
		
		sp.SiparisleriYaz("##-Siparis_Id:", String.valueOf(sp.SiparisId()+1));
		sp.SiparisleriYaz("Alinan Yemekler :",alinanyemekler);
		sp.SiparisleriYaz("Alinan icecekler :",alinanicecekler);
		sp.SiparisleriYaz("Alinan tatlilar :",alinantatlilar);
		sp.SiparisleriYaz("Toplam Tutar :",toplamtutar);
		sp.SiparisleriYaz("-----------------------------","");
		
		
		/*Siparis Servlet'e geri donuluyor.durum niteliginin konulmasýnýn sebebi; hangi serlet'ten gelindigini anlamak ve ona gore davranmak*/
		
		RequestDispatcher rs = getServletContext().getRequestDispatcher("/SiparisServlet");
		request.setAttribute("durum", SiparisleriAl);
		rs.forward(request,response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
