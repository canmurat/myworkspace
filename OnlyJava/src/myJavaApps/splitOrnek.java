package myJavaApps;



public class splitOrnek {

	
	public static void main(String[] args) {
		
		String ifade= "Ahmet arabas�na atlay�p evin yolunu tuttu.";
		
		String dizi[] = ifade.split(" ");
		
		System.out.println(dizi.length + "Dizinin boyutu");
		System.out.println("Dizinin elemanlar�: ");
		
		for(String str : dizi){
			
			
			System.out.println(str);
			
		}
	}
}
