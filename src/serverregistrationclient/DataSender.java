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
public class DataSender {
    public static boolean SendToServer(Data data){
        
        try {
            //send web request
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }
}
