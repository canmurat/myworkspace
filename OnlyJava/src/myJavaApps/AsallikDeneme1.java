package myJavaApps;

public class AsallikDeneme1 {
	
	
	public void Asalmi(long sayi) {

		int sayac = 0;

		if (sayi == 2)  System.out.println("Asal");
			
		else {
			for (int i = 1; i < sayi; i++) {
				if (sayi % i == 0) {

					sayac++;
				}
			}
			
			if (sayac == 2 )
			{
				System.out.println("Asal");
			}
		}
		System.out.println(sayac);
	}
	
	public static void main(String[] args) {
		
		System.out.println("Mustafa");
		AsallikDeneme1 as = new AsallikDeneme1();
		as.Asalmi(100);
		
	}

}
