package com.sepetim;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SiparisleriYazdir {

	FileWriter fileWriter = null;
	FileReader fileReader = null;

	ArrayList<String> sonsiparis;
	public final static ArrayList<String> siparislertxt = new ArrayList<String>();

	/*Girilen siparisler yazdiriliyor.*/

	public void SiparisleriYaz(String tanim, String alinan) throws IOException {

		FileWriter fwriter = new FileWriter("siparisler.txt", true);
		try {
			BufferedWriter bw = new BufferedWriter(fwriter);
			bw.write(tanim + " " + alinan);
			siparislertxt.add((tanim + " " + alinan));
			bw.newLine();
			bw.close();
		} catch (IOException ex) {
			System.out.println("Yazarken oluþan hata = " + ex.toString());
		}

	}

	
	public void oncekileriTemizle(int index, ArrayList<String> liste) {

		for (int i = index - 1; i >= 0; i--) {

			liste.remove(i);
		}
	}

	
	/*Siparis Id degerinin belirlenmesi icin siparisler txt'ini okunuyor ve son 8 satir'i Arraylist'e yazdiriliyor.
	 * Gerekli arama sonucunda id bilgisi bulunup alýnýyor ve donduruluyor*/
	
	public int SiparisId() throws IOException {

		int id = 0;
		String aradigimsatir;
		sonsiparis = new ArrayList<String>();

		try (BufferedReader br = new BufferedReader(new FileReader(
				"siparisler.txt"))) {

			for (String line; (line = br.readLine()) != null;) {

				sonsiparis.add(line);
				
				if (sonsiparis.size() > 8) {
					sonsiparis.remove(0);
				}

			}

		}

		for (int i = sonsiparis.size() -1 ; i >= 0; i--) {

			if (sonsiparis.get(i).contains("##-Siparis_Id:")) {
				aradigimsatir = sonsiparis.get(i);
				String[] satirinParcalari = aradigimsatir.split(" ");
				id = Integer.valueOf(satirinParcalari[1]);

				return id;
			}
		}
		return id;

	}
	/*ArrayList'te araligiSil tarzinda bir method olmadýgý icin kendim bir ArrayList'ten extends edilmis bir sýnýf olusturmayý denedim , 
	 * ancak calýsmadý:) Bu isi normal arrayList ile yapmak zorunda kaldým , ancak 'araliksil' ozelligini kullanamadýgým icin bu array list'in
	 * boyutunu 8 tuttum ve dosyanýn son 8 satýrýný okudum. Burada son Id degeri mevcuttur.*/
	public int abc() throws FileNotFoundException, IOException {

		class ArrayListem extends ArrayList<String> {

			private static final long serialVersionUID = 1L;

			ArrayListem listem;

			public ArrayListem() {
				listem = new ArrayListem();

			}

			public void araligiSil(int baslangic, int bitis) {
				listem.removeRange(baslangic, bitis);
			}
		}
		return 0;

	}
}
