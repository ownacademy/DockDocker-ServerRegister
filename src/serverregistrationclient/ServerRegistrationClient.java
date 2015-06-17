package serverregistrationclient;


/**
 *
 * @author Ivan
 */
public class ServerRegistrationClient {

    public static void main(String[] args) {
        
        //TO DO: check eventureel voor java version
        Data data = DataCollector.CollectData();
        
        boolean isSended = DataSender.SendToServer(data);
        
        if(isSended == true){
            System.out.println("Your request has been sended to DockDocker server managment");
        } else {
            System.out.println("Somethings goes wrong");
        }
    }
    
}