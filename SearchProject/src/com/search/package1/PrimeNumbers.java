package com.search.package1;

import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/PrimeNumbers")
public class PrimeNumbers extends HttpServlet {
	ArrayList<String> supplierNames1 = new ArrayList<String>();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrimeNumbers() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Thread th1 = new Asallik2(1,250000);
		Thread th2 = new Asallik2(250001,500000);
		Thread th3 = new Asallik2(500001,750000);
		Thread th4 = new Asallik2(750001, 1000000);
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
		PrintWriter out = response.getWriter();
		out.println(supplierNames1);
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	

	public class Asallik2 extends Thread
	{
		
		long baslangic,bitis;
		String thread;
		
		public Asallik2(long baslangic , long bitis)
		{
			this.baslangic = baslangic;
			this.bitis = bitis;
			
			
		if(250000< baslangic && 500000 >= bitis)
			thread = "2. thread, ";
		else if(500000< baslangic && 750000 >= bitis)
			thread = "3. thread" ;
		else if(750000< baslangic && 1000000>= bitis)
			thread = "4. thread, ";
		else
		{
			thread = "1. thread," ;
		}
		for (long i = baslangic ; i <= bitis ; i++)
		{
			if(Asalmi(i))
			{
				supplierNames1.add(thread + "--> " + i + " sayisi asaldir.\n");
			}			
			
		}
		
		
		}
		public boolean Asalmi(long n) {
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
}
