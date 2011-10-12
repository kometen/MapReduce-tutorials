package no.gnome;

class WeatherInput {
	public static void main(String[] args) {
		System.out.println("Blåbærsyltetøj");
		Weather a = new Weather(1967, 111, 0);
		Weather b = new Weather(1968, 191, 0);
		Weather c = new Weather(1969, 11, 0);
		Weather d = new Weather(1970, 115, 0);
		Weather max = new Weather();
		if (max.getTemperature() < a.getTemperature()) { max = a; }
		if (max.getTemperature() < b.getTemperature()) { max = b; }
		if (max.getTemperature() < c.getTemperature()) { max = c; }
		if (max.getTemperature() < d.getTemperature()) { max = d; }
		System.out.println("max temperature: " + max.getTemperature());
		System.out.println("done!");
	}
}