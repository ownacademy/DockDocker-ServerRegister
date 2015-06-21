package serverregistrationclient;



/**
 *
 * @author Ivan
 */
public class ServerRegistrationClient {
    
    public static void main(String[] args) {

        Data data = DataCollector.CollectData();

        System.out.println("----------- The data I collected is: -----------");
        System.out.println("Username: " + data.username);
        System.out.println("Password: " + data.password);
        System.out.println("Server name: " + data.server_name);
        System.out.println("Server ip: " + data.server_ip);
        System.out.println("Docker status: " + data.docker_status);
        System.out.println("----------- Making request... ----------");

        
        boolean isSended = DataSender.SendToServer(data);

        System.out.println("---------------------------------");
        
        if(isSended == true){
            System.out.println("Your request has been sended to DockDocker server managment");
        } else {
            System.out.println("Cannot connect DockDocker Server Managment");
        }

        System.out.println("---------------------------------");

    }
    
}