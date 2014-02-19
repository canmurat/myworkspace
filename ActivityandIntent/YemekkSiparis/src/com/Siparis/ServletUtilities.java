package com.Siparis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServletUtilities {
	
	String secilenyemek,secilentatli,secilenicecek ="";
	ArrayList<String> secilenYiyeceklerListesi = new ArrayList<String>();
	ArrayList<String> secilenIceceklerListesi = new ArrayList<String>();
	ArrayList<String> secilenTatlilarListesi = new ArrayList<String>();
	static List<String> yemekListesi = new ArrayList<String>();
	static List<Integer> yemekFiyatListesi = new ArrayList<Integer>();
	static HashMap<String, Integer> hm = new HashMap<String, Integer>();
	 
	
	public static String yiyecekSec()
	{
		hm.put("pilav",5);
		hm.put("makarna",6);
		hm.put("kofte",10);
		hm.put("patates",8);
		hm.put("kebap",20);
		

		return("<select id =\"yiyeceklerkutusu\"> " +
				"<option value=\"pilav"+hm.get("pilav")+"\"" + " >Pilav " + hm.get("pilav") +" TL </option>" +
				"<option value=\"makarna"+hm.get("makarna")+"\"" + " >Makarna "+ hm.get("makarna") +" TL </option>" +
				"<option value=\"kofte"+hm.get("kofte")+"\"" +">Kofte "+ hm.get("kofte") +" TL </option>" +
				"<option value=\"patates"+hm.get("patates")+"\"" +">Patetes  "+ hm.get("patates") +" TL </option>" +
				"<option value=\"kebap"+hm.get("kebap")+"\"" +">Kebap  "+ hm.get("kebap") +" TL </option>" +
				"</select>");
	}
	public static String icecekSec()
	{
		hm.put("kutu kola",2);
		hm.put("ayran",1);
		hm.put("cay",1);
		hm.put("limonata",3);
		hm.put("kahve",3);
		
		return("<select id =\"iceceklerkutusu\"> " +
				"<option value=\"Kutu Kola"+hm.get("kutu kola")+"\"" + ">Kutu Kola " + hm.get("kutu kola") +" TL </option>" +
				"<option value=\"ayran"+hm.get("ayran")+"\"" +">Makarna "+ hm.get("makarna") +" TL </option>" +
				"<option value=\"kofte"+hm.get("kofte")+"\"" +">Kofte "+ hm.get("kofte") +" TL </option>" +
				"<option value=\"patates"+hm.get("patates")+"\"" +">Patetes  "+ hm.get("patates") +" TL </option>" +
				"<option value=\"kebap"+hm.get("kebap")+"\"" +">Kebap  "+ hm.get("kebap") +" TL </option>" +
				"</select>");
	}
	public static String tatliSec()
	{
		hm.put("kazandibi",5);
		hm.put("puding",3);
		hm.put("kunefe",5);
		hm.put("revani",5);
		hm.put("sütlac",5);
		
		return("<select id =\"tatlilarkutusu\"> " +
				"<option value=\"kazandibi"+hm.get("kazandibi")+"\"" + " >Kazandibi " + hm.get("kazandibi") +" TL </option>" +
				"<option value=\"puding"+hm.get("puding")+"\"" + " >Puding "+ hm.get("puding") +" TL </option>" +
				"<option value=\"kunefe"+hm.get("kunefe")+"\"" +">Kunefe "+ hm.get("kunefe") +" TL </option>" +
				"<option value=\"revani"+hm.get("revani")+"\"" +">Revani  "+ hm.get("revani") +" TL </option>" +
				"<option value=\"sütlac"+hm.get("sütlac")+"\"" +">Sütlac"+ hm.get("sütlac") +" TL </option>" +
				"</select>");
	}

	
	
}
