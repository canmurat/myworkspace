package myJavaApps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


class Asallik extends Thread {
	long baslangic, bitis;
	String thread;
	PrintWriter out;

	public Asallik(long baslangic, long bitis) {
		this.baslangic = baslangic;
		this.bitis = bitis;
	}
	
	public void run()
	{
					
		if(250000< baslangic && 500000 >= bitis)
			thread = "2. thread, " + "baslangic = " + baslangic + ",bitis = " + bitis;
		else if(500000< baslangic && 750000 >= bitis)
			thread = "3. thread," + "baslangic = " + baslangic + ",bitis = " + bitis;
		else if(750000< baslangic && 1000000>= bitis)
			thread = "4. thread, " + "baslangic = " + baslangic + ",bitis = " + bitis;
		else
		{
			thread = "1. thread," + "baslangic = " + baslangic + ",bitis = " + bitis;
		}
		for (long i = baslangic ; i <= bitis ; i++)
		{
			
			if(Asalmi(i))
			{
				try {
				    out = new PrintWriter(new BufferedWriter(new FileWriter("Sonucum.txt", true)));
				    out.println(thread + "--> " + i + " sayisi asaldir." );
				    out.close();
				} catch (IOException e) {
				    //oh noes!
				}
							
			}
		}	
	}
	
	
	
	
	
	static boolean Asalmi(long n) {
		if (n <= 1)
			return false;
		double limit = Math.sqrt(n);
		for (long i = 2; i <= limit; i++) 
		{
			if (n % i == 0)
				return false;
		}
		return true;
	}
}

public class AsalSayýlar {

	public static void main(String[] args) {
		
		Thread th1 = new Asallik(1,250000);
		Thread th2 = new Asallik(250001,500000);
		Thread th3 = new Asallik(500001,750000);
		Thread th4 = new Asallik(750001, 1000000);
		th1.start();
		th2.start();
		th3.start();
		th4.start();
		
		try {
			th1.join();
		}

		catch (InterruptedException ie) {
			System.out.println("Hata1");
		}

		try {
			th2.join();
		}

		catch (InterruptedException ie) {
			System.out.println("Hata2");
		}
		try {
			th3.join();
		}

		catch (InterruptedException ie) {
			System.out.println("Hata3");
		}
		try {
			th4.join();
		}

		catch (InterruptedException ie) {
			System.out.println("Hata4");
		}

	}
}
