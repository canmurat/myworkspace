package com.Siparis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UyeClass {

	FileWriter fileWriter = null;
	FileReader fileReader = null;

	/*Yeni Uye yazmak icin gerekli yordam*/
	public void UyeYaz(String user, String pass) throws IOException {

		FileWriter fwriter = new FileWriter("kullanicilar.txt", true);
		try {
			BufferedWriter bw = new BufferedWriter(fwriter);
			bw.write(user);
			bw.write(" " + pass);
			bw.newLine();
			bw.close();
		} catch (IOException ex) {
			System.out.println("Yazarken oluþan hata = " + ex.toString());
		}

	}
	/*Girilen uye bilgilerinde uye olup olmamasýný anlamak icin kullanýlan yordam*/
	public boolean UyeAra(String user, String pass) throws IOException {
		String username;
		String password;

		try (BufferedReader br = new BufferedReader(new FileReader(
				"kullanicilar.txt"))) {
			for (String line; (line = br.readLine()) != null;) {
				String[] keywords = line.split(" ");
				username = keywords[0];
				password = keywords[1];

				if (username.equals(user.trim())
						&& password.equals(pass.trim())) {
					return true;
				}
			}

		}
		return false;
	}
	/*Kullanilan bir isim girilmemesi icin yordam*/
	public boolean IsýmBul(String user) throws IOException {

		try (BufferedReader br = new BufferedReader(new FileReader(
				"kullanicilar.txt"))) {
			for (String line; (line = br.readLine()) != null;) {
				if (line.trim().contains(user.trim())) {
					return true;
				}
			}

		}
		return false;
	}
	/*Girilen kullanici isim ve sifresi , bir admine karsýlýk geliyor ise true donecek yordam
	 * Txt dosyasýnýn icerisinde admin olanlarin Kullanici ismi ve sifresinden sonra ek olarak admin yazýyor.
	 * Satýr, ona gore split edilip gerekli kontroller saglanýyor.*/
	public boolean adminMi(String user, String pass) throws IOException {
		String username;
		String password;

		try (BufferedReader br = new BufferedReader(new FileReader(
				"kullanicilar.txt"))) {
			for (String line; (line = br.readLine()) != null;) {
				
				String[] keywords = line.split(" ");
				username = keywords[0];
				password = keywords[1];

				
				if (username.equals(user.trim())
						&& password.equals(pass.trim())) {
					
					if (keywords.length == 3 && keywords[2].equals("admin"))
					{
						return true;
					}
						

				}

			}

		}
		return false;
	}
}