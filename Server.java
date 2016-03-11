/**
* Server side of the Share Market 
* 
* @author  Yoan Ribeiro
*/

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class Server
{
    public static void main ( String[] args )
    {
        if (System.getSecurityManager() == null) 
            System.setSecurityManager( new RMISecurityManager()  );
        try
        {
            //Registry registry = LocateRegistry.getRegistry()
            StockFactoryManager manager = (StockFactoryManager)Naming.lookup("rmi://localhost/manager");
            Broker broker = new BrokerImpl( manager );
            //registry.bind("broker",broker);
            Naming.rebind("//localhost/broker", broker);
            System.out.println("Broker added to Naming Server...");
        }
        catch(Exception e){ e.printStackTrace(); }
    }
}
