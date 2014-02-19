package com.Siparis;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SiparisServlet")
public class SiparisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<String> secilenYiyeceklerListesi = new ArrayList<String>();
	ArrayList<String> secilenIceceklerListesi = new ArrayList<String>();
	ArrayList<String> secilenTatlilarListesi = new ArrayList<String>();
	int secilmisYemekMiktari;
	int secilmisIcecekMiktari;
	int secilmisTatliMiktari;
       
	ServletUtilities sv = new ServletUtilities();
   
    public SiparisServlet() {
        super();
     
    }
    
    public String secilenlereYazdir(String deger)
    {
    	return deger;
    }

	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		String secilenYemek = request.getParameter("yiyeceklerkutusu");
//		String secilenIcecek = request.getParameter("iceceklerkutusu");
//		String secilenTatli = request.getParameter("tatlilarkutusu");
//		secilenYiyeceklerListesi.add(secilenYemek);
//		secilenIceceklerListesi.add(secilenIcecek);
//		secilenTatlilarListesi.add(secilenTatli);
//		secilmisYemekMiktari += 1;
//		secilmisIcecekMiktari += 1;
//		secilmisTatliMiktari += 1;
//		
		String pathVar1 = getServletContext().getRealPath("/") + "WEB-INF\\css" ;
		pathVar1 = pathVar1 +"\\bootstrap.css";
		String pathVar2 = getServletContext().getRealPath("/") + "WEB-INF\\css" ;
		pathVar2 = pathVar2 +"\\bootstrap.min.css";
		String pathVar3 = getServletContext().getRealPath("/") + "WEB-INF\\css" ;
		pathVar3 = pathVar3 +"\\bootstrap-responsive.css";
		String pathVar4 = getServletContext().getRealPath("/") + "WEB-INF\\css" ;
		pathVar4 = pathVar4 +"\\bootstrap-responsive.min.css";
		
		
		PrintWriter out = response.getWriter();
		out.println(request.getContextPath());
		response.setContentType("text/html");
		out.println("<!DOCTYPE html>" + 
					"<head><title> Ana Sayfa  </title>" +
					"<link  href=\"bootstrap/css/bootstrap.css\" rel=\"stylesheet\" type=\"text/css\" />\n"+
					
					"</head>" +
					"<body >" +
					"<script type=\"text/javascript\" src=\"YemekSiparis/bootstrap.js,\"YemekSiparis/bootstrap.min.js>" +		
					
					"function yemekleriYazdirma() {"+
						"var e = document.getElementById(\"yiyeceklerkutusu\"); "+
						"secilenYemek = e.options[e.selectedIndex].value.replace(/[0-9]/g,'');"+
						"var secilenYemekFiyati = parseInt(e.options[e.selectedIndex].value.replace(/[^0-9]/g,''));"+
						"var adet = parseInt(document.getElementById(\"yemekadetkutusu\").value);"+
						"if(adet==\"\"||isNaN(adet)){"+
							"adet = 1; }"+
						"document.getElementById(\"secilenyemeklertexti\").value += secilenYemek+ '(' + adet + ' adet),';   " +
						
						"var deger = parseInt(document.getElementById(\"toplamyemeklertexti\").value);" +
						"document.getElementById(\"toplamyemeklertexti\").value = +document.getElementById(\"toplamyemeklertexti\").value + secilenYemekFiyati*adet; " +				
						"var genelToplam = parseInt(document.getElementById(\"geneltoplamtexti\").value);" +
						"document.getElementById(\"geneltoplamtexti\").value = secilenYemekFiyati + +document.getElementById(\"geneltoplamtexti\").value; }"+
					"function icecekleriYazdirma(){ "+
						"var e = document.getElementById(\"iceceklerkutusu\"); "+
						"secilenIcecek = e.options[e.selectedIndex].value.replace(/[0-9]/g,'');"+
						"var secilenIcecekFiyati = parseInt(e.options[e.selectedIndex].value.replace(/[^0-9]/g,''));"+
						"var adet = parseInt(document.getElementById(\"icecekadetkutusu\").value);"+
						"if(adet==\"\"||isNaN(adet)){"+
							"adet = 1; }"+
						"document.getElementById(\"secileniceceklertexti\").value += secilenIcecek + '(' + adet + ' adet),';   " +
						"var deger = parseInt(document.getElementById(\"toplamiceceklertexti\").value);" +
						"document.getElementById(\"toplamiceceklertexti\").value = +document.getElementById(\"toplamiceceklertexti\").value  + secilenIcecekFiyati*adet; " +				
						"var genelToplam = parseInt(document.getElementById(\"geneltoplamtexti\").value);" +
						"document.getElementById(\"geneltoplamtexti\").value = secilenIcecekFiyati+ +document.getElementById(\"geneltoplamtexti\").value; }"+
					"function tatlilariYazdirma(){ "+
						"var e = document.getElementById(\"tatlilarkutusu\"); "+
						"secilenTatli = e.options[e.selectedIndex].value.replace(/[0-9]/g,'');"+
						"var secilenTatliFiyati = parseInt(e.options[e.selectedIndex].value.replace(/[^0-9]/g,''));"+
						"var adet = parseInt(document.getElementById(\"tatliadetkutusu\").value);"+
						"if(adet==\"\"||isNaN(adet)){"+
							"adet = 1; }"+
						"document.getElementById(\"secilentatlilartexti\").value += secilenTatli + '(' + adet + ' adet),';   " +
						"var deger = parseInt(document.getElementById(\"toplamtatlilartexti\").value);" +
						"document.getElementById(\"toplamtatlilartexti\").value = +document.getElementById(\"toplamtatlilartexti\").value  + secilenTatliFiyati*adet; " +				
						"var genelToplam = parseInt(document.getElementById(\"geneltoplamtexti\").value);" +
						"document.getElementById(\"geneltoplamtexti\").value = secilenTatliFiyati + +document.getElementById(\"geneltoplamtexti\").value; }"+
					"</script>" +
					"<form action =\"SiparisServlet\"  method = \"GET\" >" +
					"<table border = 5px solid>" +
						"<tr>" +
							"<td> " +
								"Yiyecekler  :" + ServletUtilities.yiyecekSec() + 
								"<br> Adet : <input type='text' name='text1' id ='yemekadetkutusu'> " +
								"<button type='button' class=\"btn btn-success\" value='tamam' onclick=\"yemekleriYazdirma()\" >Tamam </button> " +
							"</td>" +
							"<td>" +
								"Secilen Yiyecekler : <input type='text' name='fname' id = 'secilenyemeklertexti' width = '200' >" +
							    "Toplam :  <input type='text' name='yemektexti' id = 'toplamyemeklertexti' >" +
						
							"</td>" +
						"</tr>" +
						"<tr>" +
							"<td>" +
								"Icecekler :" +  ServletUtilities.icecekSec() + 
								"<br> Adet : <input type='text' name='text2' id ='icecekadetkutusu' > " +
								"<button type='button' value='Tamam' onclick=\"icecekleriYazdirma()\" > Tamam </button>" +
							"</td>" +
							"<td>" +
								"Secilen Icecekler : <input type='text' name='fname' id = 'secileniceceklertexti'  >"  +
								"Toplam :  <input type='text' name='icecektexti' id = 'toplamiceceklertexti' >" +
							"</td>" +
						"</tr>" +
						"<tr>" +
							"<td>" +
								"Tatlilar :" + ServletUtilities.tatliSec() +
								"<br> Adet : <input type='text' name='text2' id ='tatliadetkutusu'> " +
								"<button type='button' value='Tamam' onclick =\"tatlilariYazdirma()\"> Tamam </button> " +
							"</td>"+
							"<td>" +
								"Secilen Tatlilar : <input type='text' name='text3' id = 'secilentatlilartexti' >"+
								"Toplam :  <input type='text' name='tatlitexti' id = 'toplamtatlilartexti' >" +
							"</td>"+ 
						"<tr>" +
							"<td>"+ 
								"Toplam Tutar :  <input type='text' name='geneltoplam' id = 'geneltoplamtexti' >" +
							"</td>"+
							"<td>"+ 
							
							"</td>"+
						"</tr>"	+
						"</tr>" +
					"</table>" +
					"</form>" +
						
					
					"</body>" + 
					"</html>");
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
