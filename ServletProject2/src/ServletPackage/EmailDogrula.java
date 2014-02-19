package ServletPackage;

public class EmailDogrula {
	
	static String emailPattern="^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})";
	
	public static boolean emailMi(String email)
	 {
	  if(email==null)
	  {
	   return false;
	  }
	  //Assigning the email format regular expression
	  
	  return email.matches(emailPattern); 
	 }

}
