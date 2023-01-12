import java.io.*;
import java.net.*;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class WeatherApp {

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter the location: ");
		String location = scan.nextLine();
		String url = "http://api.openweathermap.org/data/2.5/weather?q="+location+"&appid=YOUR_API_KEY";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(response.toString());
		
		JSONObject main = (JSONObject) json.get("main");
		
		System.out.println("Temperature: "+ main.get("temp"));
		System.out.println("Pressure: "+ main.get("pressure"));
		System.out.println("Humidity: "+ main.get("humidity"));
		
		JSONObject wind = (JSONObject) json.get("wind");
		System.out.println("Wind Speed: "+ wind.get("speed"));
		
		JSONObject sys = (JSONObject) json.get("sys");
		System.out.println("Country: "+ sys.get("country"));
	}

}
