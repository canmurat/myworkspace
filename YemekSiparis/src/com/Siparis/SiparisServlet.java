package com.Siparis;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SiparisServlet")
public class SiparisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ArrayList<String> secilenYiyeceklerListesi = new ArrayList<String>();
	public ArrayList<String> secilenIceceklerListesi = new ArrayList<String>();
	public ArrayList<String> secilenTatlilarListesi = new ArrayList<String>();
	public int secilmisYemekMiktari;
	public int secilmisIcecekMiktari;
	public int secilmisTatliMiktari;
	
	public String yazilacak;
	public String durum;
	public String kullaniciadi;
	public String sifre;
	public static Boolean adminmi;
	
       
	ServletUtilities sv = new ServletUtilities();
   
    public SiparisServlet() {
        super();
     
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*Admin girisinden sonra normal kullanýcý girislerinde de 'admin-girisi' dememesi icin konuldu.*/
		adminmi = false;
		
		/*SiparisleriGoster ya da KullanicilariGoster servlet'lerinden geldigimizi anlamak icin bunu kullandým.
		 * Amac : bu servlet'lerden gelindigi taktirde hala admin olmayý saglamak..*/
		if(request.getParameter("stilladmin")!= null)
		{
			adminmi = true;
		}
		
		PrintWriter out = response.getWriter();
				
		/*Siparis Servletine ya UyeSorgulaServlet'ten gelinebilir,Ya da UyeOlServletten.
		 * ikisinde de 'durum' isminde attiribute konulmustur.*/
		if(request.getAttribute("durum") != null){	
			
			
			durum = (String) request.getAttribute("durum");
			
			
		/*Girilen durum attribute'si UyeSorgulaya ait ise kullanici ismini al.
		 */
			if(durum == "UyeSorgulaServlet"){
				if(request.getAttribute("user").equals(true))
				{
					adminmi = true;
					kullaniciadi = request.getAttribute("username")+ "-admin";
					
				}
				/*Admin ise Kulanicilari ve Siparisleri listeleyebilir.*/
				else
				{
					kullaniciadi = (String) request.getAttribute("user");
					
				}
			}
									
		}
		/*JavaScript kýsmýnda gerekli hesap islemleri yapýlmaktadýr.
		 * Yardýmcý sýnýf olarak ServletUtilities sýnýfý kullanýldý.(Amac tekrarlarý onlemek..)*/
		
		response.setContentType("text/html");
		out.println("<!DOCTYPE html>" + 	
					"<head><title> Ana Sayfa  </title>" +
					"<link  href=\"bootstrap/css/bootstrap.css\" rel=\"stylesheet\" type=\"text/css\" />\n"+
					"<link  href=\"bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\" />\n"+
					"<link  href=\"bootstrap/css/bootstrap-responsive.css\" rel=\"stylesheet\" type=\"text/css\" />\n"+
					"<link  href=\"bootstrap/css/bootstrap-responsive.min.css\" rel=\"stylesheet\" type=\"text/css\" />\n"+
					"<link  href=\"css/styles.css\" rel=\"stylesheet\" type=\"text/css\" />\n"+	
					"</head>" +
					"<body>" +
					"<script>" +		
					
					"function temizle() {" +
						"document.getElementById(\"secilenyemeklertexti\").value = ''; "+
						"document.getElementById(\"toplamyemeklertexti\").value = ''; "+
						"document.getElementById(\"secileniceceklertexti\").value = ''; "+
						"document.getElementById(\"toplamiceceklertexti\").value = ''; "+
						"document.getElementById(\"secilentatlilartexti\").value = ''; "+
						"document.getElementById(\"toplamtatlilartexti\").value = ''; "+
						"document.getElementById(\"geneltoplamtexti\").value = ''; "+
					"}" +
					"function yemekleriYazdirma() {"+
						"var e = document.getElementById(\"yiyeceklerkutusu\"); "+
						"secilenYemek = e.options[e.selectedIndex].value.replace(/[0-9]/g,'');"+
						"var secilenYemekFiyati = parseInt(e.options[e.selectedIndex].value.replace(/[^0-9]/g,''));"+
						"var adet = parseInt(document.getElementById(\"yemekadetkutusu\").value);"+
						"if(adet==\"\"||isNaN(adet)){"+
							"adet = 1; }"+
						"document.getElementById(\"secilenyemeklertexti\").value += secilenYemek+ '(' + adet + ' adet),';   " +
						
						"var deger = parseInt(document.getElementById(\"toplamyemeklertexti\").value);" +
						"document.getElementById(\"toplamyemeklertexti\").value = +document.getElementById(\"toplamyemeklertexti\").value + (secilenYemekFiyati * adet); " +
						"var genelToplam = parseInt(document.getElementById(\"geneltoplamtexti\").value);" +
						"if(!isNaN(genelToplam)){"+
						
						"document.getElementById(\"geneltoplamtexti\").value = genelToplam + secilenYemekFiyati*adet; }"+
						"else{"+
						"document.getElementById(\"geneltoplamtexti\").value = secilenYemekFiyati*adet;}"+
						
						"document.getElementById(\"yemekadetkutusu\").value = 1;"+
						"}"+

					"function icecekleriYazdirma(){ "+
						"var e = document.getElementById(\"iceceklerkutusu\"); "+
						"secilenIcecek = e.options[e.selectedIndex].value.replace(/[0-9]/g,'');"+
						"var secilenIcecekFiyati = parseInt(e.options[e.selectedIndex].value.replace(/[^0-9]/g,''));"+
						"var adet = parseInt(document.getElementById(\"icecekadetkutusu\").value);"+
						"if(adet==\"\"||isNaN(adet)){"+
							"adet = 1; }"+
						"document.getElementById(\"secileniceceklertexti\").value += secilenIcecek + '(' + adet + ' adet),';   " +
						"var deger = parseInt(document.getElementById(\"toplamiceceklertexti\").value);" +
						"document.getElementById" +
						"(\"toplamiceceklertexti\").value = +document.getElementById(\"toplamiceceklertexti\").value  + secilenIcecekFiyati*adet; " +				
						"var genelToplam = parseInt(document.getElementById(\"geneltoplamtexti\").value);" +
						"if(!isNaN(genelToplam)){"+
						
						"document.getElementById(\"geneltoplamtexti\").value = genelToplam + secilenIcecekFiyati*adet; }"+
						"else{"+
						"document.getElementById(\"geneltoplamtexti\").value = secilenIcecekFiyati*adet;}"+
						"document.getElementById(\"icecekadetkutusu\").value = 1;"+
						"}"+
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
						"if(!isNaN(genelToplam)){"+
						
						"document.getElementById(\"geneltoplamtexti\").value = genelToplam + secilenTatliFiyati*adet; }"+
						"else{"+
						"document.getElementById(\"geneltoplamtexti\").value = secilenTatliFiyati*adet;}"+
						"document.getElementById(\"tatliadetkutusu\").value = 1;"+
						"}"+
						
					"</script>" +
						
					
		           /*Ana div , Header div , sag div , sol div gibi divler olusturulup sayfa sekillendiriliyor.*/
					
					"<div id=\"anadiv\">"+
						"<div id=\"headerdiv\">"+
						"</div>"+
						"<div = id=\"ustdiv\">"+
		            	"<ul>"+
		                	"<li><a href=\"#\">Ana Sayfa</a></li>" +
		                	"<li><a href=\"http://localhost:8080/YemekSiparis/Hakkimizda\"> Hakkimizda </a></li>"+
		                	"<li><a href=\"http://localhost:8080/YemekSiparis/Iletisim\"> Iletisim </a></li>"+
		                	"<li><a href=\"http://localhost:8080/YemekSiparis/GirisServlet\"> Cikis Yap </a></li>"+
		                	
		                "</ul>"+
		                "</div>"+
		       
					
					"<div id=\"solparca\" >");
										
				/*ÝF ELSE bloklarýnda :
				 * -Kullanicinin admin olmasýna gore ,
				 * -SiparisServlet'e gelinen yere gore
				 *   Belirlemeler yapýlmakta ve Sayfanýn sol kýsmýn ona gore dizayn edilmektedir.*/
		
					if(durum == "UyeSorgulaServlet" && !adminmi){
						
						out.println("<h4> HOSGELDINIZ!! </h4>"+
								"<h4>"+kullaniciadi+"</h4>");
						out.println("<h4>SIPARISINIZI YAPINIZ </h4>");
					}
					else if(durum == "SiparisleriAl")
					{
						
						out.println("<h4> Secilen Yemekler Yazdirilmistir ! </h4>");
					}
					
					else if(adminmi)
					{
						out.println("<h5>"+ kullaniciadi +"</h5>"+
								"<h5> Admin Girisi </h5> " +
								"<form action='SiparisleriGoster' class='btn btn-warning' method='get'><br>"+
										"<input type='submit' value='Siparisleri Goster'>"+
									"</form>"+
									"<form action='KullanicilariGoster'class='btn btn-warning' method='get'><br>"+
										"<input type='submit' value='Kullanicilari Goster'>"+
									"</form>");
					}
					
			/*Yemeklerin siparis tablosu burda olusturuluyor. Gerekli hesaplamalar yukarda olusturulan javascript fonksiyonlari ile saglanýyor.*/
					out.println("</div>"+
		            "<div id=\"sagparca\">" +
					"<form action=\"SiparisleriAl\" method=\"get\">"+
					"<table class =\"table table-bordered\" >" +
						"<tr>" +
							"<td class=\"tr1td1\"> " +
								"Yiyecekler  :" + ServletUtilities.yiyecekSec() + 
								"<br> Adet :<input type=\"text\" name='yemekleradet' id ='yemekadetkutusu'> " +
								"<button type='button' class=\"btn btn-success\" value='tamam' onclick=\"yemekleriYazdirma()\" >Tamam </button> " +
							"</td>" +
							"<td class=\"tr1td2\"> " +
								"Secilen Yiyecekler : <textarea class=\"form-control\" rows=\"3\" name='secilenyemekler' id = 'secilenyemeklertexti' width = '200' ></textarea>" +
								"Toplam :  <input type='text' name='yemektexti' id = 'toplamyemeklertexti' >" +
								
						
							"</td>" +
						"</tr>" +
						"<tr>" +
							"<td class=\"tr2td1\">" +
								"Icecekler :" +  ServletUtilities.icecekSec() + 
								"<br> Adet : <input type='text' name='icecekleradet' id ='icecekadetkutusu' > " +
								"<button type='button' class=\"btn btn-success\" value='Tamam' onclick=\"icecekleriYazdirma()\" > Tamam </button>" +
							"</td>" +
							"<td class=\"tr2td2\">" +
								"Secilen Icecekler : <textarea class=\"form-control\" rows=\"3\"  name='secilenicecekler' id = 'secileniceceklertexti'  ></textarea>"  +
								"Toplam :  <input type='text' name='icecektexti' id = 'toplamiceceklertexti' >" +
							"</td>" +
						"</tr>" +
						"<tr>" +
							"<td class=\"tr3td1\">" +
								"Tatlilar :" + ServletUtilities.tatliSec() +
								"<br> Adet : <input type='text' name='tatlilaradet' id ='tatliadetkutusu'> " +
								"<button type='button' class=\"btn btn-success\" value='Tamam' onclick =\"tatlilariYazdirma()\"> Tamam </button> " +
							"</td>"+
							"<td class=\"tr3td2\">" +
								"Secilen Tatlilar : <textarea class=\"form-control\" name='secilentatlilar' id = 'secilentatlilartexti' ></textarea>"+
								"Toplam :  <input type='text' name='tatlitexti' id = 'toplamtatlilartexti' >" +
							"</td>"+ 
						"<tr>" +
							"<td>"+ 
								"Toplam Tutar :  <input type='text' name='geneltoplam' id = 'geneltoplamtexti' >" +
							"</td>"+
							"<td>"+ 
							"<button type='button' class=\"btn btn-success\" value='Temizle' onclick=\"temizle()\" > Temizle </button>" +
							"</td>"+
						"</tr>"	+
						"<tr>" +
							"<td colspan=2>"+
			/*Siparisi Onaylar ise Siparis'i yazdirmak icin SiparisleriAl servlet'ine gidiliyor.*/				
								"<input type='submit'  class=\"btn btn-success\" value='Siparisi Onayla'>  " +
								
							"</td>"+
						"</tr>"+
					"</table>" +
					"</form>" +
					
					"<div id=\"altparca\">"+
					"Copyright © McanMurat </div>"+
						
					"</div>" +
					
					"</body>" + 
					"</html>");
		
		Cookie c = new Cookie("repeatVisitor", "yes");
		c.setMaxAge(60*60*24*7);
		response.addCookie(c);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
