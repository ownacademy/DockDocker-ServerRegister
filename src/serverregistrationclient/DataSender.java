package serverregistrationclient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author Ivan Ivanov
 */
public class DataSender {
    
    private static String USER_AGENT = "Mozilla/5.0";
    
    /**
     * Sends Data object to the server to be written into the database.
     * @param data Data object to be sent.
     * @return true if the operation is successful.
     */
    public static boolean SendToServer(Data data){
        HttpURLConnection con = null;
        try {
            String username = replaceStringToASCII(data.username);
            String password = replaceStringToASCII(data.password);
            String server_name = replaceStringToASCII(data.server_name);
            String server_ip = replaceStringToASCII(data.server_ip);
            String docker_status = replaceStringToASCII(data.docker_status);
            
            String url = "http://localhost:5100/fromRegApp/"+
                    username+"/"+
                    password+"/"+
                    server_name+"/"+
                    server_ip+"/"+
                    docker_status;

		URL obj = new URL(url);
		con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
 
                if(responseCode == 200) {
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer responseReader = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                            responseReader.append(inputLine);
                    }
                    in.close();

                    //print result
                    System.out.println(responseReader.toString());              
                } else if(responseCode == 404) {
                    System.out.println("Error 404: DockDocker Server Managment not found");
                } else {
                    System.out.println("Unable to send request to DockDocker Server Managment");
                } 

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            con.disconnect();
        }

    }
    
    /**
     * Replace string characters so does not break the GET request.
     * @param value String to be converted.
     * @return The converted string.
     */
    private static String replaceStringToASCII(String value){
        return value.
                replace(", ", "%7Bcomma%7D").
                replace("/", "%7Bslash%7D").
                replace(", ", "{comma}").
                replace("/", "{slash}").
                replace(" ", "%20");
    }
}