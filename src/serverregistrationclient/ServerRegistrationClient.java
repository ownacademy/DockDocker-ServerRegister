/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverregistrationclient;


/**
 *
 * @author Ivan
 */
public class ServerRegistrationClient {

    public static void main(String[] args) {
        
        Data data = DataCollector.CollectData();

        boolean isSended = DataSender.SendToServer(data);
        
        if(isSended == true){
            System.out.println("Your request has been sended to Docker server register application");
        } else {
            System.out.println("Somethings goes wrong");
        }
    }
    
}
