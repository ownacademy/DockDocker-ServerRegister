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
        
        // /sbin/ifconfig eth0 | grep 'inet addr:' | cut -d: -f2 | awk '{ print $1}'         145.24.222.146
        // "bash", "-c", "/sbin/ifconfig | awk 'NR==2{print$2}' | sed 's/addr://g'"             172.17.42.1

        String s;
        Process p;
        try {
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
