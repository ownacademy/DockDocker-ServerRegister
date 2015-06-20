package serverregistrationclient;



/**
 *
 * @author Ivan
 */
public class ServerRegistrationClient {
    
    public static void main(String[] args) {

        Data data = DataCollector.CollectData();

        System.out.println(data.username);
        System.out.println(data.password);
        System.out.println(data.server_name);
        System.out.println(data.server_ip);
        System.out.println(data.docker_status);
        
        boolean isSended = DataSender.SendToServer(data);

        if(isSended == true){
            System.out.println("Your request has been sended to DockDocker server managment");
        } else {
            System.out.println("Somethings goes wrong");
        }


    }
    
}