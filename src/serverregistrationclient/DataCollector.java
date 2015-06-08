/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverregistrationclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 *
 * @author Ivan
 */
public class DataCollector {
    
    public static Data CollectData(){
        Data data = new Data();
        
        try {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                            whatismyip.openStream()));

            String ip = in.readLine(); //you get the IP as a String
            data.IP = ip;
            System.out.println(ip);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return data;
    }
    
}
