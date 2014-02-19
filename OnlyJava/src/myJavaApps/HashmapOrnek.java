package myJavaApps;

import java.util.HashMap;

public class HashmapOrnek {

	static HashMap<Integer, Integer> ornek = new HashMap<Integer, Integer>();

	public static void main(String[] args) {

		int sayac=0;
		
		
		ornek.put(0, 0);
		ornek.put(1, 1);
		ornek.put(2, 0);
		ornek.put(3, 1);
		ornek.put(4, 1);

		for (int i = 0; i < ornek.size(); i++) {

			if (ornek.get(i) > 0) {
				
				sayac++;

			}

		}
		System.out.println("sayac= "+sayac);
	}
}
