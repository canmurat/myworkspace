package com.search.package1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MySearch
 */
@WebServlet("/MySearch")
public class MySearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MySearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String aranacak_kelime = request.getParameter("AranacakKelime");
		String arama_motoru = request.getParameter("AramaMotoru");
		
		if(aranacak_kelime == null)
		{
			response.sendError(HttpServletResponse.SC_NOT_FOUND, 
                    "Aranacak Kelimeyi Girmediniz .");
		}
		if(arama_motoru == null)
		{
			response.sendError(HttpServletResponse.SC_NOT_FOUND, 
                    "Arama Motoru Turu Belirlenmedi");
		}
		else 
		{
			if(arama_motoru.equals("Google"))
				response.sendRedirect("https://www.google.com.tr/?gws_rd=cr&ei=LjqFUteiIciK4ASQ-oDoCA#q="+aranacak_kelime);
			else if(arama_motoru.equals("Yahoo"))
				response.sendRedirect("http://tr.search.yahoo.com/search;_ylt=AoRa0Wkt1t7uow7EOR8HYAUhl7x_;_ylc=X1MDMjE0MzA2NTg4OQRfcgMyBGZyA3lmcC10LTcyMwRuX2dwcwMxMARvcmlnaW4DdHIueWFob28uY29tBHF1ZXJ5A2ZhY2Vib29rBHNhbwMxBHRlc3QDNzIzBHZlcnNpb24DbGVnbw--?p="+aranacak_kelime+"&toggle=1&cop=mss&ei=UTF-8&fr=yfp-t-723");
			else if(arama_motoru.equals("Ask"))
				response.sendRedirect("http://www.ask.com/web?qsrc=1&o=0&l=dir&q="+aranacak_kelime);
			else if(arama_motoru.equals("Bing"))
				response.sendRedirect("http://www.bing.com/search?q="+aranacak_kelime+"&qs=n&form=QBLH&filt=all&pq=dj&sc=8-2&sp=-1&sk=");
			else if(arama_motoru.equals("Yandex"))
				response.sendRedirect("http://www.yandex.com.tr/yandsearch?msid=22861.4372.1384463416.26448&text="+aranacak_kelime);
			
		}
		
	}

}
