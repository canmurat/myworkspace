
<%
	java.util.GregorianCalendar calendar = new java.util.GregorianCalendar();

	// Calendar'dan gun,ay ve saat degerleri alınıp degiskene atıldı. 
	String current_time = "" + calendar.get(java.util.Calendar.HOUR_OF_DAY); 
	current_time = current_time + ":" + calendar.get(java.util.Calendar.MINUTE);
	current_time = current_time + ":" + calendar.get(java.util.Calendar.SECOND);

	int hr = calendar.get(java.util.Calendar.HOUR);
	if (hr == 0)
		hr = 12;
	String mytime = "" + hr;

	int min = calendar.get(java.util.Calendar.MINUTE);
	int sec = calendar.get(java.util.Calendar.SECOND);
	String min1 = "", sec1 = "";
	if (min < 10)
		min1 = "0";
	if (sec < 10)
		sec1 = "0";
	mytime = mytime + ":" + min1 + min + ":" + sec1 + sec;

	int ampm = calendar.get(java.util.Calendar.AM_PM);
	String ap = "AM";
	if (ampm == 1)
		ap = "PM";
	mytime = mytime + ap;
	//System.out.println("mytime="+mytime+""+ap);

	String current_date = "" + calendar.get(java.util.Calendar.YEAR);
	current_date = current_date + "-"
			+ calendar.get(java.util.Calendar.MONTH);
	current_date = current_date + "-"
			+ calendar.get(java.util.Calendar.DATE);
	String[] ar = current_date.split("-");
	int current_month = calendar.get(java.util.Calendar.MONTH);
		
	String dd = ar[2];
	String mm = ar[1];
	String yy = ar[0];

	int m = Integer.parseInt(mm);
	int m1 = m + 1;

	String system_date = yy + "-" + m1 + "-" + dd;

	String a[] = { "Jan", "Feb", "Mar", "Apr", "May", "June", "July",
			"Aug", "Sept", "Oct", "Nov", "Dec" };

	//String day[]={"1st","2nd","3rd","4th","5th","6th","7th","8th","9th","0th"}; 

	String mydate = dd + " " + a[m1] + " " + yy;
	current_month = current_month + 1;

	//------------------------------- 
	//String myDate="1 Jan 2008";
	//String myString = java.text.DateFormat.getDateInstance().format(myDate);
	//java.text.DateFormat df = java.text.DateFormat.getDateInstance(DateFormat.LONG, Locale.FRANCE);
	//System.out.println("df="+df);

	//------------------------------------

	//out.println("current_time="+current_time);
	//out.println("system_date="+system_date);
%>