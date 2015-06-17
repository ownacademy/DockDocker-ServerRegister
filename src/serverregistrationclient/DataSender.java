package serverregistrationclient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author Ivan
 */
public class DataSender {
    
    private static String USER_AGENT = "Mozilla/5.0";
    
    public static boolean SendToServer(Data data){
        
        try {
            String new_serve_ip = data.server_ip.replace("/", "{slash}");
            String url = "http://localhost:4567/addServer/"+data.server_name+"/"+new_serve_ip+"/"+data.docker_status+"";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//print result
		System.out.println(response.toString());
            
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }
}