package serverregistrationclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author Ivan Ivanov
 */
public class DataCollector {
    /**
     * Collects the needed data from the computer.
     * @return Data object with the computers data.
     */
    public static Data CollectData(){
        Data data = new Data();
        Scanner scan = new Scanner(System.in);
        String password = null;
        
        while (password == null || password == "") {
            System.out.println("Please enter password: ");
            password = scan.nextLine();
        }
        scan.close(); 
        data.password = password;

        String s;
        Process p;
        try {
            
            //GET USERNAME
            p = Runtime.getRuntime().exec(new String[] { 
                "bash", "-c", "echo $USER"
            });
            BufferedReader br_username = new BufferedReader(
                new InputStreamReader(p.getInputStream()));
            while ((s = br_username.readLine()) != null) {
                data.username = s;
            }
            
            //GET IP ADDRESS
            p = Runtime.getRuntime().exec(new String[] { 
                "bash", "-c", "ifconfig -a  | grep inet | sed 's/inet addr://g' | sed 's/inet6 addr: //g'  | grep -v 'Host' | awk '{print $1}' | grep -v 127.0.0.1"
            });
            BufferedReader br_ip = new BufferedReader(
                new InputStreamReader(p.getInputStream()));
            String result = "";
            while ((s = br_ip.readLine()) != null) {
                result += s + ", ";
                data.server_ip = result;
            }
            
            //GET HOST NAME
            p = Runtime.getRuntime().exec("hostname");
            BufferedReader br_hostname = new BufferedReader(
                new InputStreamReader(p.getInputStream()));
            while ((s = br_hostname.readLine()) != null) {
                data.server_name = s;
            }
            
            //GET IF DOCKER IS INSTALLED
            try {
                p = Runtime.getRuntime().exec("docker");
                BufferedReader br_docker = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
                while ((s = br_docker.readLine()) != null) {
                    data.docker_status = "Docker_installed";
                }
            } catch (Exception e) {
                data.docker_status = "No_docker_installed";
            }
            
            
                p.waitFor();
            System.out.println ("exit: " + p.exitValue());
            p.destroy();
        } catch (Exception e) {
            System.out.println("Exeption message: ");
            System.out.println(e.getMessage());
        }
        
        
        return data;
    }
    
}