package ServletPackage;

public class SifreDogrula {
	
	static String format = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
	
	public static boolean sifreFormatiDogrumu(String string)
	{
		if(string.matches(format))
		{
			return true; 
		}
		
		return false;
	}
	public static boolean sifrelerEslesiyormu(String sifre1,String sifre2)
	{
		
		if(sifre1.equals(sifre2))
		{
			return true; 
		}
		else{
			return false;
		}
	}

}
