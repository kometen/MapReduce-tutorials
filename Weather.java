package no.gnome.mapreduce1;

class Weather {
	protected String year  = "";	// 4 digits, ie. 1967
	protected int month = 0;		// 2 digits, ie. 10
	protected int day = 0;			// 2 digits, ie. 28
	protected int temperature = 0;
	protected int quality = 0;
	
	public Weather() {
		year = "";
		month = 0;
		day = 0;
		temperature = 0;
		quality = 0;
	}

	public Weather(String a, int b, int c, int d, int e) {
		year = a;
		month = b;
		day = c;
		temperature = c;
		quality = d;
	}
	
	void setYear(String a) {
		year = a;
	}
	
	void setMonth(int a) {
		month = a;
	}
	
	void setDay(int a) {
		day = a;
	}
	
	void setTemperature(int a) {
		temperature = a;
	}

	void setQuality(int a) {
		quality = a;
	}

	String getYear() {
		return year;
	}

	int getMonth() {
		return month;
	}

	int getDay() {
		return day;
	}

	int getTemperature() {
		return temperature;
	}

	int getQuality() {
		return quality;
	}
}
